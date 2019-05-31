import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListPipejointType, defaultValue } from 'app/shared/model/list-pipejoint-type.model';

export const ACTION_TYPES = {
  FETCH_LISTPIPEJOINTTYPE_LIST: 'listPipejointType/FETCH_LISTPIPEJOINTTYPE_LIST',
  FETCH_LISTPIPEJOINTTYPE: 'listPipejointType/FETCH_LISTPIPEJOINTTYPE',
  CREATE_LISTPIPEJOINTTYPE: 'listPipejointType/CREATE_LISTPIPEJOINTTYPE',
  UPDATE_LISTPIPEJOINTTYPE: 'listPipejointType/UPDATE_LISTPIPEJOINTTYPE',
  DELETE_LISTPIPEJOINTTYPE: 'listPipejointType/DELETE_LISTPIPEJOINTTYPE',
  RESET: 'listPipejointType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListPipejointType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListPipejointTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListPipejointTypeState = initialState, action): ListPipejointTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTPIPEJOINTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTPIPEJOINTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTPIPEJOINTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTPIPEJOINTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTPIPEJOINTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTPIPEJOINTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTPIPEJOINTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTPIPEJOINTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTPIPEJOINTTYPE):
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

const apiUrl = 'api/list-pipejoint-types';

// Actions

export const getEntities: ICrudGetAllAction<IListPipejointType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE_LIST,
    payload: axios.get<IListPipejointType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListPipejointType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPEJOINTTYPE,
    payload: axios.get<IListPipejointType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListPipejointType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTPIPEJOINTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListPipejointType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTPIPEJOINTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListPipejointType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTPIPEJOINTTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
