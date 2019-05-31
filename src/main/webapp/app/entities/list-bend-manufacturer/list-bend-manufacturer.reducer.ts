import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBendManufacturer, defaultValue } from 'app/shared/model/list-bend-manufacturer.model';

export const ACTION_TYPES = {
  FETCH_LISTBENDMANUFACTURER_LIST: 'listBendManufacturer/FETCH_LISTBENDMANUFACTURER_LIST',
  FETCH_LISTBENDMANUFACTURER: 'listBendManufacturer/FETCH_LISTBENDMANUFACTURER',
  CREATE_LISTBENDMANUFACTURER: 'listBendManufacturer/CREATE_LISTBENDMANUFACTURER',
  UPDATE_LISTBENDMANUFACTURER: 'listBendManufacturer/UPDATE_LISTBENDMANUFACTURER',
  DELETE_LISTBENDMANUFACTURER: 'listBendManufacturer/DELETE_LISTBENDMANUFACTURER',
  RESET: 'listBendManufacturer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBendManufacturer>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBendManufacturerState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBendManufacturerState = initialState, action): ListBendManufacturerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBENDMANUFACTURER):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBENDMANUFACTURER):
    case REQUEST(ACTION_TYPES.DELETE_LISTBENDMANUFACTURER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER):
    case FAILURE(ACTION_TYPES.CREATE_LISTBENDMANUFACTURER):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBENDMANUFACTURER):
    case FAILURE(ACTION_TYPES.DELETE_LISTBENDMANUFACTURER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDMANUFACTURER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBENDMANUFACTURER):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBENDMANUFACTURER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBENDMANUFACTURER):
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

const apiUrl = 'api/list-bend-manufacturers';

// Actions

export const getEntities: ICrudGetAllAction<IListBendManufacturer> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDMANUFACTURER_LIST,
    payload: axios.get<IListBendManufacturer>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBendManufacturer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDMANUFACTURER,
    payload: axios.get<IListBendManufacturer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBendManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBENDMANUFACTURER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBendManufacturer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBENDMANUFACTURER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBendManufacturer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBENDMANUFACTURER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
