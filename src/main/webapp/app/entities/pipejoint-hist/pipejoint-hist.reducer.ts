import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipejointHist, defaultValue } from 'app/shared/model/pipejoint-hist.model';

export const ACTION_TYPES = {
  FETCH_PIPEJOINTHIST_LIST: 'pipejointHist/FETCH_PIPEJOINTHIST_LIST',
  FETCH_PIPEJOINTHIST: 'pipejointHist/FETCH_PIPEJOINTHIST',
  CREATE_PIPEJOINTHIST: 'pipejointHist/CREATE_PIPEJOINTHIST',
  UPDATE_PIPEJOINTHIST: 'pipejointHist/UPDATE_PIPEJOINTHIST',
  DELETE_PIPEJOINTHIST: 'pipejointHist/DELETE_PIPEJOINTHIST',
  RESET: 'pipejointHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipejointHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipejointHistState = Readonly<typeof initialState>;

// Reducer

export default (state: PipejointHistState = initialState, action): PipejointHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPEJOINTHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPEJOINTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPEJOINTHIST):
    case REQUEST(ACTION_TYPES.UPDATE_PIPEJOINTHIST):
    case REQUEST(ACTION_TYPES.DELETE_PIPEJOINTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPEJOINTHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPEJOINTHIST):
    case FAILURE(ACTION_TYPES.CREATE_PIPEJOINTHIST):
    case FAILURE(ACTION_TYPES.UPDATE_PIPEJOINTHIST):
    case FAILURE(ACTION_TYPES.DELETE_PIPEJOINTHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEJOINTHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEJOINTHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPEJOINTHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPEJOINTHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPEJOINTHIST):
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

const apiUrl = 'api/pipejoint-hists';

// Actions

export const getEntities: ICrudGetAllAction<IPipejointHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEJOINTHIST_LIST,
    payload: axios.get<IPipejointHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipejointHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEJOINTHIST,
    payload: axios.get<IPipejointHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipejointHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPEJOINTHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipejointHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPEJOINTHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipejointHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPEJOINTHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
