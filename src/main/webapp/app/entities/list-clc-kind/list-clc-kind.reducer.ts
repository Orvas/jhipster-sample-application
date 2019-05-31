import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListClcKind, defaultValue } from 'app/shared/model/list-clc-kind.model';

export const ACTION_TYPES = {
  FETCH_LISTCLCKIND_LIST: 'listClcKind/FETCH_LISTCLCKIND_LIST',
  FETCH_LISTCLCKIND: 'listClcKind/FETCH_LISTCLCKIND',
  CREATE_LISTCLCKIND: 'listClcKind/CREATE_LISTCLCKIND',
  UPDATE_LISTCLCKIND: 'listClcKind/UPDATE_LISTCLCKIND',
  DELETE_LISTCLCKIND: 'listClcKind/DELETE_LISTCLCKIND',
  RESET: 'listClcKind/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListClcKind>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListClcKindState = Readonly<typeof initialState>;

// Reducer

export default (state: ListClcKindState = initialState, action): ListClcKindState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCKIND_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCKIND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTCLCKIND):
    case REQUEST(ACTION_TYPES.UPDATE_LISTCLCKIND):
    case REQUEST(ACTION_TYPES.DELETE_LISTCLCKIND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCKIND_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCKIND):
    case FAILURE(ACTION_TYPES.CREATE_LISTCLCKIND):
    case FAILURE(ACTION_TYPES.UPDATE_LISTCLCKIND):
    case FAILURE(ACTION_TYPES.DELETE_LISTCLCKIND):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCKIND_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCKIND):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTCLCKIND):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTCLCKIND):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTCLCKIND):
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

const apiUrl = 'api/list-clc-kinds';

// Actions

export const getEntities: ICrudGetAllAction<IListClcKind> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCKIND_LIST,
    payload: axios.get<IListClcKind>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListClcKind> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCKIND,
    payload: axios.get<IListClcKind>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListClcKind> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTCLCKIND,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListClcKind> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTCLCKIND,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListClcKind> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTCLCKIND,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
