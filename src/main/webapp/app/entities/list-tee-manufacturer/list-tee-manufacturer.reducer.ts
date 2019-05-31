import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListTeeManufacturer, defaultValue } from 'app/shared/model/list-tee-manufacturer.model';

export const ACTION_TYPES = {
  FETCH_LISTTEEMANUFACTURER_LIST: 'listTeeManufacturer/FETCH_LISTTEEMANUFACTURER_LIST',
  FETCH_LISTTEEMANUFACTURER: 'listTeeManufacturer/FETCH_LISTTEEMANUFACTURER',
  CREATE_LISTTEEMANUFACTURER: 'listTeeManufacturer/CREATE_LISTTEEMANUFACTURER',
  UPDATE_LISTTEEMANUFACTURER: 'listTeeManufacturer/UPDATE_LISTTEEMANUFACTURER',
  DELETE_LISTTEEMANUFACTURER: 'listTeeManufacturer/DELETE_LISTTEEMANUFACTURER',
  RESET: 'listTeeManufacturer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListTeeManufacturer>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListTeeManufacturerState = Readonly<typeof initialState>;

// Reducer

export default (state: ListTeeManufacturerState = initialState, action): ListTeeManufacturerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTTEEMANUFACTURER):
    case REQUEST(ACTION_TYPES.UPDATE_LISTTEEMANUFACTURER):
    case REQUEST(ACTION_TYPES.DELETE_LISTTEEMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER):
    case FAILURE(ACTION_TYPES.CREATE_LISTTEEMANUFACTURER):
    case FAILURE(ACTION_TYPES.UPDATE_LISTTEEMANUFACTURER):
    case FAILURE(ACTION_TYPES.DELETE_LISTTEEMANUFACTURER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEEMANUFACTURER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTTEEMANUFACTURER):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTTEEMANUFACTURER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTTEEMANUFACTURER):
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

const apiUrl = 'api/list-tee-manufacturers';

// Actions

export const getEntities: ICrudGetAllAction<IListTeeManufacturer> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEEMANUFACTURER_LIST,
    payload: axios.get<IListTeeManufacturer>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListTeeManufacturer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEEMANUFACTURER,
    payload: axios.get<IListTeeManufacturer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListTeeManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTTEEMANUFACTURER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListTeeManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTTEEMANUFACTURER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListTeeManufacturer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTTEEMANUFACTURER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
