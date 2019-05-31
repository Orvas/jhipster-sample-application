import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipelineSection, defaultValue } from 'app/shared/model/pipeline-section.model';

export const ACTION_TYPES = {
  FETCH_PIPELINESECTION_LIST: 'pipelineSection/FETCH_PIPELINESECTION_LIST',
  FETCH_PIPELINESECTION: 'pipelineSection/FETCH_PIPELINESECTION',
  CREATE_PIPELINESECTION: 'pipelineSection/CREATE_PIPELINESECTION',
  UPDATE_PIPELINESECTION: 'pipelineSection/UPDATE_PIPELINESECTION',
  DELETE_PIPELINESECTION: 'pipelineSection/DELETE_PIPELINESECTION',
  RESET: 'pipelineSection/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipelineSection>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipelineSectionState = Readonly<typeof initialState>;

// Reducer

export default (state: PipelineSectionState = initialState, action): PipelineSectionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPELINESECTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPELINESECTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPELINESECTION):
    case REQUEST(ACTION_TYPES.UPDATE_PIPELINESECTION):
    case REQUEST(ACTION_TYPES.DELETE_PIPELINESECTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPELINESECTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPELINESECTION):
    case FAILURE(ACTION_TYPES.CREATE_PIPELINESECTION):
    case FAILURE(ACTION_TYPES.UPDATE_PIPELINESECTION):
    case FAILURE(ACTION_TYPES.DELETE_PIPELINESECTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINESECTION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINESECTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPELINESECTION):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPELINESECTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPELINESECTION):
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

const apiUrl = 'api/pipeline-sections';

// Actions

export const getEntities: ICrudGetAllAction<IPipelineSection> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINESECTION_LIST,
    payload: axios.get<IPipelineSection>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipelineSection> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINESECTION,
    payload: axios.get<IPipelineSection>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipelineSection> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPELINESECTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipelineSection> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPELINESECTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipelineSection> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPELINESECTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
