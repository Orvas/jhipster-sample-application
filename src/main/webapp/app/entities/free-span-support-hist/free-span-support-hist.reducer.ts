import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFreeSpanSupportHist, defaultValue } from 'app/shared/model/free-span-support-hist.model';

export const ACTION_TYPES = {
  FETCH_FREESPANSUPPORTHIST_LIST: 'freeSpanSupportHist/FETCH_FREESPANSUPPORTHIST_LIST',
  FETCH_FREESPANSUPPORTHIST: 'freeSpanSupportHist/FETCH_FREESPANSUPPORTHIST',
  CREATE_FREESPANSUPPORTHIST: 'freeSpanSupportHist/CREATE_FREESPANSUPPORTHIST',
  UPDATE_FREESPANSUPPORTHIST: 'freeSpanSupportHist/UPDATE_FREESPANSUPPORTHIST',
  DELETE_FREESPANSUPPORTHIST: 'freeSpanSupportHist/DELETE_FREESPANSUPPORTHIST',
  RESET: 'freeSpanSupportHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFreeSpanSupportHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type FreeSpanSupportHistState = Readonly<typeof initialState>;

// Reducer

export default (state: FreeSpanSupportHistState = initialState, action): FreeSpanSupportHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FREESPANSUPPORTHIST):
    case REQUEST(ACTION_TYPES.UPDATE_FREESPANSUPPORTHIST):
    case REQUEST(ACTION_TYPES.DELETE_FREESPANSUPPORTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST):
    case FAILURE(ACTION_TYPES.CREATE_FREESPANSUPPORTHIST):
    case FAILURE(ACTION_TYPES.UPDATE_FREESPANSUPPORTHIST):
    case FAILURE(ACTION_TYPES.DELETE_FREESPANSUPPORTHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANSUPPORTHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FREESPANSUPPORTHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_FREESPANSUPPORTHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FREESPANSUPPORTHIST):
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

const apiUrl = 'api/free-span-support-hists';

// Actions

export const getEntities: ICrudGetAllAction<IFreeSpanSupportHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANSUPPORTHIST_LIST,
    payload: axios.get<IFreeSpanSupportHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IFreeSpanSupportHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANSUPPORTHIST,
    payload: axios.get<IFreeSpanSupportHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFreeSpanSupportHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FREESPANSUPPORTHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFreeSpanSupportHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FREESPANSUPPORTHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFreeSpanSupportHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FREESPANSUPPORTHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
