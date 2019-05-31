import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListIliPigType, defaultValue } from 'app/shared/model/list-ili-pig-type.model';

export const ACTION_TYPES = {
  FETCH_LISTILIPIGTYPE_LIST: 'listIliPigType/FETCH_LISTILIPIGTYPE_LIST',
  FETCH_LISTILIPIGTYPE: 'listIliPigType/FETCH_LISTILIPIGTYPE',
  CREATE_LISTILIPIGTYPE: 'listIliPigType/CREATE_LISTILIPIGTYPE',
  UPDATE_LISTILIPIGTYPE: 'listIliPigType/UPDATE_LISTILIPIGTYPE',
  DELETE_LISTILIPIGTYPE: 'listIliPigType/DELETE_LISTILIPIGTYPE',
  RESET: 'listIliPigType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListIliPigType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListIliPigTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListIliPigTypeState = initialState, action): ListIliPigTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTILIPIGTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTILIPIGTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTILIPIGTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTILIPIGTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTILIPIGTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTILIPIGTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTILIPIGTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTILIPIGTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTILIPIGTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTILIPIGTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTILIPIGTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTILIPIGTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTILIPIGTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTILIPIGTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTILIPIGTYPE):
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

const apiUrl = 'api/list-ili-pig-types';

// Actions

export const getEntities: ICrudGetAllAction<IListIliPigType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTILIPIGTYPE_LIST,
    payload: axios.get<IListIliPigType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListIliPigType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTILIPIGTYPE,
    payload: axios.get<IListIliPigType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListIliPigType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTILIPIGTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListIliPigType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTILIPIGTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListIliPigType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTILIPIGTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
