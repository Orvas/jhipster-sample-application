import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICpsHist, defaultValue } from 'app/shared/model/cps-hist.model';

export const ACTION_TYPES = {
  FETCH_CPSHIST_LIST: 'cpsHist/FETCH_CPSHIST_LIST',
  FETCH_CPSHIST: 'cpsHist/FETCH_CPSHIST',
  CREATE_CPSHIST: 'cpsHist/CREATE_CPSHIST',
  UPDATE_CPSHIST: 'cpsHist/UPDATE_CPSHIST',
  DELETE_CPSHIST: 'cpsHist/DELETE_CPSHIST',
  RESET: 'cpsHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICpsHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CpsHistState = Readonly<typeof initialState>;

// Reducer

export default (state: CpsHistState = initialState, action): CpsHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CPSHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CPSHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CPSHIST):
    case REQUEST(ACTION_TYPES.UPDATE_CPSHIST):
    case REQUEST(ACTION_TYPES.DELETE_CPSHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CPSHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CPSHIST):
    case FAILURE(ACTION_TYPES.CREATE_CPSHIST):
    case FAILURE(ACTION_TYPES.UPDATE_CPSHIST):
    case FAILURE(ACTION_TYPES.DELETE_CPSHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPSHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPSHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CPSHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_CPSHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CPSHIST):
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

const apiUrl = 'api/cps-hists';

// Actions

export const getEntities: ICrudGetAllAction<ICpsHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CPSHIST_LIST,
    payload: axios.get<ICpsHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICpsHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CPSHIST,
    payload: axios.get<ICpsHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICpsHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CPSHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICpsHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CPSHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICpsHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CPSHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
