import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListClcType, defaultValue } from 'app/shared/model/list-clc-type.model';

export const ACTION_TYPES = {
  FETCH_LISTCLCTYPE_LIST: 'listClcType/FETCH_LISTCLCTYPE_LIST',
  FETCH_LISTCLCTYPE: 'listClcType/FETCH_LISTCLCTYPE',
  CREATE_LISTCLCTYPE: 'listClcType/CREATE_LISTCLCTYPE',
  UPDATE_LISTCLCTYPE: 'listClcType/UPDATE_LISTCLCTYPE',
  DELETE_LISTCLCTYPE: 'listClcType/DELETE_LISTCLCTYPE',
  RESET: 'listClcType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListClcType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListClcTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListClcTypeState = initialState, action): ListClcTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTCLCTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTCLCTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTCLCTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTCLCTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTCLCTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTCLCTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTCLCTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTCLCTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTCLCTYPE):
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

const apiUrl = 'api/list-clc-types';

// Actions

export const getEntities: ICrudGetAllAction<IListClcType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCTYPE_LIST,
    payload: axios.get<IListClcType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListClcType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCTYPE,
    payload: axios.get<IListClcType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListClcType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTCLCTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListClcType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTCLCTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListClcType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTCLCTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
