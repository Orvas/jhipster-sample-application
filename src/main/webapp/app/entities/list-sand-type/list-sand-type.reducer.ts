import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListSandType, defaultValue } from 'app/shared/model/list-sand-type.model';

export const ACTION_TYPES = {
  FETCH_LISTSANDTYPE_LIST: 'listSandType/FETCH_LISTSANDTYPE_LIST',
  FETCH_LISTSANDTYPE: 'listSandType/FETCH_LISTSANDTYPE',
  CREATE_LISTSANDTYPE: 'listSandType/CREATE_LISTSANDTYPE',
  UPDATE_LISTSANDTYPE: 'listSandType/UPDATE_LISTSANDTYPE',
  DELETE_LISTSANDTYPE: 'listSandType/DELETE_LISTSANDTYPE',
  RESET: 'listSandType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListSandType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListSandTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListSandTypeState = initialState, action): ListSandTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTSANDTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTSANDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTSANDTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTSANDTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTSANDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTSANDTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTSANDTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTSANDTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTSANDTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTSANDTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSANDTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSANDTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTSANDTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTSANDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTSANDTYPE):
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

const apiUrl = 'api/list-sand-types';

// Actions

export const getEntities: ICrudGetAllAction<IListSandType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSANDTYPE_LIST,
    payload: axios.get<IListSandType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListSandType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSANDTYPE,
    payload: axios.get<IListSandType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListSandType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTSANDTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListSandType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTSANDTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListSandType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTSANDTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
