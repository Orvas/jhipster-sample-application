import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipeline, defaultValue } from 'app/shared/model/pipeline.model';

export const ACTION_TYPES = {
  FETCH_PIPELINE_LIST: 'pipeline/FETCH_PIPELINE_LIST',
  FETCH_PIPELINE: 'pipeline/FETCH_PIPELINE',
  CREATE_PIPELINE: 'pipeline/CREATE_PIPELINE',
  UPDATE_PIPELINE: 'pipeline/UPDATE_PIPELINE',
  DELETE_PIPELINE: 'pipeline/DELETE_PIPELINE',
  RESET: 'pipeline/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipeline>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipelineState = Readonly<typeof initialState>;

// Reducer

export default (state: PipelineState = initialState, action): PipelineState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPELINE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPELINE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPELINE):
    case REQUEST(ACTION_TYPES.UPDATE_PIPELINE):
    case REQUEST(ACTION_TYPES.DELETE_PIPELINE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPELINE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPELINE):
    case FAILURE(ACTION_TYPES.CREATE_PIPELINE):
    case FAILURE(ACTION_TYPES.UPDATE_PIPELINE):
    case FAILURE(ACTION_TYPES.DELETE_PIPELINE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPELINE):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPELINE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPELINE):
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

const apiUrl = 'api/pipelines';

// Actions

export const getEntities: ICrudGetAllAction<IPipeline> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINE_LIST,
    payload: axios.get<IPipeline>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipeline> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINE,
    payload: axios.get<IPipeline>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipeline> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPELINE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipeline> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPELINE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipeline> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPELINE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
