import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListClayType, defaultValue } from 'app/shared/model/list-clay-type.model';

export const ACTION_TYPES = {
  FETCH_LISTCLAYTYPE_LIST: 'listClayType/FETCH_LISTCLAYTYPE_LIST',
  FETCH_LISTCLAYTYPE: 'listClayType/FETCH_LISTCLAYTYPE',
  CREATE_LISTCLAYTYPE: 'listClayType/CREATE_LISTCLAYTYPE',
  UPDATE_LISTCLAYTYPE: 'listClayType/UPDATE_LISTCLAYTYPE',
  DELETE_LISTCLAYTYPE: 'listClayType/DELETE_LISTCLAYTYPE',
  RESET: 'listClayType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListClayType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListClayTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListClayTypeState = initialState, action): ListClayTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTCLAYTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTCLAYTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTCLAYTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTCLAYTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTCLAYTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTCLAYTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTCLAYTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTCLAYTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTCLAYTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTCLAYTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLAYTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLAYTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTCLAYTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTCLAYTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTCLAYTYPE):
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

const apiUrl = 'api/list-clay-types';

// Actions

export const getEntities: ICrudGetAllAction<IListClayType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLAYTYPE_LIST,
    payload: axios.get<IListClayType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListClayType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLAYTYPE,
    payload: axios.get<IListClayType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListClayType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTCLAYTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListClayType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTCLAYTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListClayType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTCLAYTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
