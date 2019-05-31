import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipelineSegment, defaultValue } from 'app/shared/model/pipeline-segment.model';

export const ACTION_TYPES = {
  FETCH_PIPELINESEGMENT_LIST: 'pipelineSegment/FETCH_PIPELINESEGMENT_LIST',
  FETCH_PIPELINESEGMENT: 'pipelineSegment/FETCH_PIPELINESEGMENT',
  CREATE_PIPELINESEGMENT: 'pipelineSegment/CREATE_PIPELINESEGMENT',
  UPDATE_PIPELINESEGMENT: 'pipelineSegment/UPDATE_PIPELINESEGMENT',
  DELETE_PIPELINESEGMENT: 'pipelineSegment/DELETE_PIPELINESEGMENT',
  RESET: 'pipelineSegment/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipelineSegment>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipelineSegmentState = Readonly<typeof initialState>;

// Reducer

export default (state: PipelineSegmentState = initialState, action): PipelineSegmentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPELINESEGMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPELINESEGMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPELINESEGMENT):
    case REQUEST(ACTION_TYPES.UPDATE_PIPELINESEGMENT):
    case REQUEST(ACTION_TYPES.DELETE_PIPELINESEGMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPELINESEGMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPELINESEGMENT):
    case FAILURE(ACTION_TYPES.CREATE_PIPELINESEGMENT):
    case FAILURE(ACTION_TYPES.UPDATE_PIPELINESEGMENT):
    case FAILURE(ACTION_TYPES.DELETE_PIPELINESEGMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINESEGMENT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINESEGMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPELINESEGMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPELINESEGMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPELINESEGMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/pipeline-segments';

// Actions

export const getEntities: ICrudGetAllAction<IPipelineSegment> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINESEGMENT_LIST,
    payload: axios.get<IPipelineSegment>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipelineSegment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINESEGMENT,
    payload: axios.get<IPipelineSegment>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipelineSegment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPELINESEGMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipelineSegment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPELINESEGMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipelineSegment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPELINESEGMENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
