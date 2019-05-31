import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBendType, defaultValue } from 'app/shared/model/list-bend-type.model';

export const ACTION_TYPES = {
  FETCH_LISTBENDTYPE_LIST: 'listBendType/FETCH_LISTBENDTYPE_LIST',
  FETCH_LISTBENDTYPE: 'listBendType/FETCH_LISTBENDTYPE',
  CREATE_LISTBENDTYPE: 'listBendType/CREATE_LISTBENDTYPE',
  UPDATE_LISTBENDTYPE: 'listBendType/UPDATE_LISTBENDTYPE',
  DELETE_LISTBENDTYPE: 'listBendType/DELETE_LISTBENDTYPE',
  RESET: 'listBendType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBendType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBendTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBendTypeState = initialState, action): ListBendTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBENDTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBENDTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTBENDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTBENDTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBENDTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTBENDTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBENDTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBENDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBENDTYPE):
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

const apiUrl = 'api/list-bend-types';

// Actions

export const getEntities: ICrudGetAllAction<IListBendType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDTYPE_LIST,
    payload: axios.get<IListBendType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBendType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDTYPE,
    payload: axios.get<IListBendType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBendType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBENDTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBendType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBENDTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBendType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBENDTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
