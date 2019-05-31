import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListWrkKind, defaultValue } from 'app/shared/model/list-wrk-kind.model';

export const ACTION_TYPES = {
  FETCH_LISTWRKKIND_LIST: 'listWrkKind/FETCH_LISTWRKKIND_LIST',
  FETCH_LISTWRKKIND: 'listWrkKind/FETCH_LISTWRKKIND',
  CREATE_LISTWRKKIND: 'listWrkKind/CREATE_LISTWRKKIND',
  UPDATE_LISTWRKKIND: 'listWrkKind/UPDATE_LISTWRKKIND',
  DELETE_LISTWRKKIND: 'listWrkKind/DELETE_LISTWRKKIND',
  RESET: 'listWrkKind/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListWrkKind>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListWrkKindState = Readonly<typeof initialState>;

// Reducer

export default (state: ListWrkKindState = initialState, action): ListWrkKindState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKKIND_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKKIND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTWRKKIND):
    case REQUEST(ACTION_TYPES.UPDATE_LISTWRKKIND):
    case REQUEST(ACTION_TYPES.DELETE_LISTWRKKIND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKKIND_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKKIND):
    case FAILURE(ACTION_TYPES.CREATE_LISTWRKKIND):
    case FAILURE(ACTION_TYPES.UPDATE_LISTWRKKIND):
    case FAILURE(ACTION_TYPES.DELETE_LISTWRKKIND):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKKIND_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKKIND):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTWRKKIND):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTWRKKIND):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTWRKKIND):
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

const apiUrl = 'api/list-wrk-kinds';

// Actions

export const getEntities: ICrudGetAllAction<IListWrkKind> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKKIND_LIST,
    payload: axios.get<IListWrkKind>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListWrkKind> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKKIND,
    payload: axios.get<IListWrkKind>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListWrkKind> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTWRKKIND,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListWrkKind> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTWRKKIND,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListWrkKind> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTWRKKIND,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
