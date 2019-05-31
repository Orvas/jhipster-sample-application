import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipelineHist, defaultValue } from 'app/shared/model/pipeline-hist.model';

export const ACTION_TYPES = {
  FETCH_PIPELINEHIST_LIST: 'pipelineHist/FETCH_PIPELINEHIST_LIST',
  FETCH_PIPELINEHIST: 'pipelineHist/FETCH_PIPELINEHIST',
  CREATE_PIPELINEHIST: 'pipelineHist/CREATE_PIPELINEHIST',
  UPDATE_PIPELINEHIST: 'pipelineHist/UPDATE_PIPELINEHIST',
  DELETE_PIPELINEHIST: 'pipelineHist/DELETE_PIPELINEHIST',
  RESET: 'pipelineHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipelineHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipelineHistState = Readonly<typeof initialState>;

// Reducer

export default (state: PipelineHistState = initialState, action): PipelineHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPELINEHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPELINEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPELINEHIST):
    case REQUEST(ACTION_TYPES.UPDATE_PIPELINEHIST):
    case REQUEST(ACTION_TYPES.DELETE_PIPELINEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPELINEHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPELINEHIST):
    case FAILURE(ACTION_TYPES.CREATE_PIPELINEHIST):
    case FAILURE(ACTION_TYPES.UPDATE_PIPELINEHIST):
    case FAILURE(ACTION_TYPES.DELETE_PIPELINEHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINEHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPELINEHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPELINEHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPELINEHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPELINEHIST):
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

const apiUrl = 'api/pipeline-hists';

// Actions

export const getEntities: ICrudGetAllAction<IPipelineHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINEHIST_LIST,
    payload: axios.get<IPipelineHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipelineHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPELINEHIST,
    payload: axios.get<IPipelineHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipelineHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPELINEHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipelineHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPELINEHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipelineHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPELINEHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
