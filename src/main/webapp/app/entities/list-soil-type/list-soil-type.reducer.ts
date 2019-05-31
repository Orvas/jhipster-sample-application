import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListSoilType, defaultValue } from 'app/shared/model/list-soil-type.model';

export const ACTION_TYPES = {
  FETCH_LISTSOILTYPE_LIST: 'listSoilType/FETCH_LISTSOILTYPE_LIST',
  FETCH_LISTSOILTYPE: 'listSoilType/FETCH_LISTSOILTYPE',
  CREATE_LISTSOILTYPE: 'listSoilType/CREATE_LISTSOILTYPE',
  UPDATE_LISTSOILTYPE: 'listSoilType/UPDATE_LISTSOILTYPE',
  DELETE_LISTSOILTYPE: 'listSoilType/DELETE_LISTSOILTYPE',
  RESET: 'listSoilType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListSoilType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListSoilTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListSoilTypeState = initialState, action): ListSoilTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTSOILTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTSOILTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTSOILTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTSOILTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTSOILTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTSOILTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTSOILTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTSOILTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTSOILTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTSOILTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSOILTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSOILTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTSOILTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTSOILTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTSOILTYPE):
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

const apiUrl = 'api/list-soil-types';

// Actions

export const getEntities: ICrudGetAllAction<IListSoilType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSOILTYPE_LIST,
    payload: axios.get<IListSoilType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListSoilType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSOILTYPE,
    payload: axios.get<IListSoilType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListSoilType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTSOILTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListSoilType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTSOILTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListSoilType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTSOILTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
