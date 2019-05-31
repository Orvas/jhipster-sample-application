import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListValveType, defaultValue } from 'app/shared/model/list-valve-type.model';

export const ACTION_TYPES = {
  FETCH_LISTVALVETYPE_LIST: 'listValveType/FETCH_LISTVALVETYPE_LIST',
  FETCH_LISTVALVETYPE: 'listValveType/FETCH_LISTVALVETYPE',
  CREATE_LISTVALVETYPE: 'listValveType/CREATE_LISTVALVETYPE',
  UPDATE_LISTVALVETYPE: 'listValveType/UPDATE_LISTVALVETYPE',
  DELETE_LISTVALVETYPE: 'listValveType/DELETE_LISTVALVETYPE',
  RESET: 'listValveType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListValveType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListValveTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListValveTypeState = initialState, action): ListValveTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVETYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTVALVETYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTVALVETYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTVALVETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVETYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVETYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTVALVETYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTVALVETYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTVALVETYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVETYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVETYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTVALVETYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTVALVETYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTVALVETYPE):
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

const apiUrl = 'api/list-valve-types';

// Actions

export const getEntities: ICrudGetAllAction<IListValveType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVETYPE_LIST,
    payload: axios.get<IListValveType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListValveType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVETYPE,
    payload: axios.get<IListValveType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListValveType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTVALVETYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListValveType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTVALVETYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListValveType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTVALVETYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
