import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListObjectType, defaultValue } from 'app/shared/model/list-object-type.model';

export const ACTION_TYPES = {
  FETCH_LISTOBJECTTYPE_LIST: 'listObjectType/FETCH_LISTOBJECTTYPE_LIST',
  FETCH_LISTOBJECTTYPE: 'listObjectType/FETCH_LISTOBJECTTYPE',
  CREATE_LISTOBJECTTYPE: 'listObjectType/CREATE_LISTOBJECTTYPE',
  UPDATE_LISTOBJECTTYPE: 'listObjectType/UPDATE_LISTOBJECTTYPE',
  DELETE_LISTOBJECTTYPE: 'listObjectType/DELETE_LISTOBJECTTYPE',
  RESET: 'listObjectType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListObjectType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListObjectTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListObjectTypeState = initialState, action): ListObjectTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTOBJECTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTOBJECTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTOBJECTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTOBJECTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTOBJECTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTOBJECTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTOBJECTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTOBJECTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTOBJECTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTOBJECTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTOBJECTTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTOBJECTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTOBJECTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTOBJECTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTOBJECTTYPE):
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

const apiUrl = 'api/list-object-types';

// Actions

export const getEntities: ICrudGetAllAction<IListObjectType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTOBJECTTYPE_LIST,
    payload: axios.get<IListObjectType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListObjectType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTOBJECTTYPE,
    payload: axios.get<IListObjectType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListObjectType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTOBJECTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListObjectType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTOBJECTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListObjectType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTOBJECTTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
