import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBucklarrManufacturer, defaultValue } from 'app/shared/model/list-bucklarr-manufacturer.model';

export const ACTION_TYPES = {
  FETCH_LISTBUCKLARRMANUFACTURER_LIST: 'listBucklarrManufacturer/FETCH_LISTBUCKLARRMANUFACTURER_LIST',
  FETCH_LISTBUCKLARRMANUFACTURER: 'listBucklarrManufacturer/FETCH_LISTBUCKLARRMANUFACTURER',
  CREATE_LISTBUCKLARRMANUFACTURER: 'listBucklarrManufacturer/CREATE_LISTBUCKLARRMANUFACTURER',
  UPDATE_LISTBUCKLARRMANUFACTURER: 'listBucklarrManufacturer/UPDATE_LISTBUCKLARRMANUFACTURER',
  DELETE_LISTBUCKLARRMANUFACTURER: 'listBucklarrManufacturer/DELETE_LISTBUCKLARRMANUFACTURER',
  RESET: 'listBucklarrManufacturer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBucklarrManufacturer>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBucklarrManufacturerState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBucklarrManufacturerState = initialState, action): ListBucklarrManufacturerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBUCKLARRMANUFACTURER):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBUCKLARRMANUFACTURER):
    case REQUEST(ACTION_TYPES.DELETE_LISTBUCKLARRMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER):
    case FAILURE(ACTION_TYPES.CREATE_LISTBUCKLARRMANUFACTURER):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBUCKLARRMANUFACTURER):
    case FAILURE(ACTION_TYPES.DELETE_LISTBUCKLARRMANUFACTURER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBUCKLARRMANUFACTURER):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBUCKLARRMANUFACTURER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBUCKLARRMANUFACTURER):
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

const apiUrl = 'api/list-bucklarr-manufacturers';

// Actions

export const getEntities: ICrudGetAllAction<IListBucklarrManufacturer> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER_LIST,
    payload: axios.get<IListBucklarrManufacturer>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBucklarrManufacturer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRMANUFACTURER,
    payload: axios.get<IListBucklarrManufacturer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBucklarrManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBUCKLARRMANUFACTURER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBucklarrManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBUCKLARRMANUFACTURER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBucklarrManufacturer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBUCKLARRMANUFACTURER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
