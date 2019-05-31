import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBucklarrType, defaultValue } from 'app/shared/model/list-bucklarr-type.model';

export const ACTION_TYPES = {
  FETCH_LISTBUCKLARRTYPE_LIST: 'listBucklarrType/FETCH_LISTBUCKLARRTYPE_LIST',
  FETCH_LISTBUCKLARRTYPE: 'listBucklarrType/FETCH_LISTBUCKLARRTYPE',
  CREATE_LISTBUCKLARRTYPE: 'listBucklarrType/CREATE_LISTBUCKLARRTYPE',
  UPDATE_LISTBUCKLARRTYPE: 'listBucklarrType/UPDATE_LISTBUCKLARRTYPE',
  DELETE_LISTBUCKLARRTYPE: 'listBucklarrType/DELETE_LISTBUCKLARRTYPE',
  RESET: 'listBucklarrType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBucklarrType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBucklarrTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBucklarrTypeState = initialState, action): ListBucklarrTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBUCKLARRTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBUCKLARRTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTBUCKLARRTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTBUCKLARRTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBUCKLARRTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTBUCKLARRTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBUCKLARRTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBUCKLARRTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBUCKLARRTYPE):
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

const apiUrl = 'api/list-bucklarr-types';

// Actions

export const getEntities: ICrudGetAllAction<IListBucklarrType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRTYPE_LIST,
    payload: axios.get<IListBucklarrType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBucklarrType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRTYPE,
    payload: axios.get<IListBucklarrType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBucklarrType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBUCKLARRTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBucklarrType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBUCKLARRTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBucklarrType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBUCKLARRTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
