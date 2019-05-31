import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFreeSpanHist, defaultValue } from 'app/shared/model/free-span-hist.model';

export const ACTION_TYPES = {
  FETCH_FREESPANHIST_LIST: 'freeSpanHist/FETCH_FREESPANHIST_LIST',
  FETCH_FREESPANHIST: 'freeSpanHist/FETCH_FREESPANHIST',
  CREATE_FREESPANHIST: 'freeSpanHist/CREATE_FREESPANHIST',
  UPDATE_FREESPANHIST: 'freeSpanHist/UPDATE_FREESPANHIST',
  DELETE_FREESPANHIST: 'freeSpanHist/DELETE_FREESPANHIST',
  RESET: 'freeSpanHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFreeSpanHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type FreeSpanHistState = Readonly<typeof initialState>;

// Reducer

export default (state: FreeSpanHistState = initialState, action): FreeSpanHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FREESPANHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FREESPANHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FREESPANHIST):
    case REQUEST(ACTION_TYPES.UPDATE_FREESPANHIST):
    case REQUEST(ACTION_TYPES.DELETE_FREESPANHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FREESPANHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FREESPANHIST):
    case FAILURE(ACTION_TYPES.CREATE_FREESPANHIST):
    case FAILURE(ACTION_TYPES.UPDATE_FREESPANHIST):
    case FAILURE(ACTION_TYPES.DELETE_FREESPANHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FREESPANHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_FREESPANHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FREESPANHIST):
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

const apiUrl = 'api/free-span-hists';

// Actions

export const getEntities: ICrudGetAllAction<IFreeSpanHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANHIST_LIST,
    payload: axios.get<IFreeSpanHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IFreeSpanHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANHIST,
    payload: axios.get<IFreeSpanHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFreeSpanHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FREESPANHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFreeSpanHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FREESPANHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFreeSpanHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FREESPANHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
