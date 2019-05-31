import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListValveManufacturer, defaultValue } from 'app/shared/model/list-valve-manufacturer.model';

export const ACTION_TYPES = {
  FETCH_LISTVALVEMANUFACTURER_LIST: 'listValveManufacturer/FETCH_LISTVALVEMANUFACTURER_LIST',
  FETCH_LISTVALVEMANUFACTURER: 'listValveManufacturer/FETCH_LISTVALVEMANUFACTURER',
  CREATE_LISTVALVEMANUFACTURER: 'listValveManufacturer/CREATE_LISTVALVEMANUFACTURER',
  UPDATE_LISTVALVEMANUFACTURER: 'listValveManufacturer/UPDATE_LISTVALVEMANUFACTURER',
  DELETE_LISTVALVEMANUFACTURER: 'listValveManufacturer/DELETE_LISTVALVEMANUFACTURER',
  RESET: 'listValveManufacturer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListValveManufacturer>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListValveManufacturerState = Readonly<typeof initialState>;

// Reducer

export default (state: ListValveManufacturerState = initialState, action): ListValveManufacturerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTVALVEMANUFACTURER):
    case REQUEST(ACTION_TYPES.UPDATE_LISTVALVEMANUFACTURER):
    case REQUEST(ACTION_TYPES.DELETE_LISTVALVEMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER):
    case FAILURE(ACTION_TYPES.CREATE_LISTVALVEMANUFACTURER):
    case FAILURE(ACTION_TYPES.UPDATE_LISTVALVEMANUFACTURER):
    case FAILURE(ACTION_TYPES.DELETE_LISTVALVEMANUFACTURER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTVALVEMANUFACTURER):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTVALVEMANUFACTURER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTVALVEMANUFACTURER):
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

const apiUrl = 'api/list-valve-manufacturers';

// Actions

export const getEntities: ICrudGetAllAction<IListValveManufacturer> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER_LIST,
    payload: axios.get<IListValveManufacturer>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListValveManufacturer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVEMANUFACTURER,
    payload: axios.get<IListValveManufacturer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListValveManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTVALVEMANUFACTURER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListValveManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTVALVEMANUFACTURER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListValveManufacturer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTVALVEMANUFACTURER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
