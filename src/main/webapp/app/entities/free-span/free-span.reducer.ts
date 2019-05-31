import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFreeSpan, defaultValue } from 'app/shared/model/free-span.model';

export const ACTION_TYPES = {
  FETCH_FREESPAN_LIST: 'freeSpan/FETCH_FREESPAN_LIST',
  FETCH_FREESPAN: 'freeSpan/FETCH_FREESPAN',
  CREATE_FREESPAN: 'freeSpan/CREATE_FREESPAN',
  UPDATE_FREESPAN: 'freeSpan/UPDATE_FREESPAN',
  DELETE_FREESPAN: 'freeSpan/DELETE_FREESPAN',
  RESET: 'freeSpan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFreeSpan>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type FreeSpanState = Readonly<typeof initialState>;

// Reducer

export default (state: FreeSpanState = initialState, action): FreeSpanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FREESPAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FREESPAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FREESPAN):
    case REQUEST(ACTION_TYPES.UPDATE_FREESPAN):
    case REQUEST(ACTION_TYPES.DELETE_FREESPAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FREESPAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FREESPAN):
    case FAILURE(ACTION_TYPES.CREATE_FREESPAN):
    case FAILURE(ACTION_TYPES.UPDATE_FREESPAN):
    case FAILURE(ACTION_TYPES.DELETE_FREESPAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPAN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FREESPAN):
    case SUCCESS(ACTION_TYPES.UPDATE_FREESPAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FREESPAN):
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

const apiUrl = 'api/free-spans';

// Actions

export const getEntities: ICrudGetAllAction<IFreeSpan> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPAN_LIST,
    payload: axios.get<IFreeSpan>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IFreeSpan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPAN,
    payload: axios.get<IFreeSpan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFreeSpan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FREESPAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFreeSpan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FREESPAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFreeSpan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FREESPAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
