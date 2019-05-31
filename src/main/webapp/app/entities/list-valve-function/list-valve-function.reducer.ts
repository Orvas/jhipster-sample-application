import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListValveFunction, defaultValue } from 'app/shared/model/list-valve-function.model';

export const ACTION_TYPES = {
  FETCH_LISTVALVEFUNCTION_LIST: 'listValveFunction/FETCH_LISTVALVEFUNCTION_LIST',
  FETCH_LISTVALVEFUNCTION: 'listValveFunction/FETCH_LISTVALVEFUNCTION',
  CREATE_LISTVALVEFUNCTION: 'listValveFunction/CREATE_LISTVALVEFUNCTION',
  UPDATE_LISTVALVEFUNCTION: 'listValveFunction/UPDATE_LISTVALVEFUNCTION',
  DELETE_LISTVALVEFUNCTION: 'listValveFunction/DELETE_LISTVALVEFUNCTION',
  RESET: 'listValveFunction/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListValveFunction>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListValveFunctionState = Readonly<typeof initialState>;

// Reducer

export default (state: ListValveFunctionState = initialState, action): ListValveFunctionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVEFUNCTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVEFUNCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTVALVEFUNCTION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTVALVEFUNCTION):
    case REQUEST(ACTION_TYPES.DELETE_LISTVALVEFUNCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVEFUNCTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVEFUNCTION):
    case FAILURE(ACTION_TYPES.CREATE_LISTVALVEFUNCTION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTVALVEFUNCTION):
    case FAILURE(ACTION_TYPES.DELETE_LISTVALVEFUNCTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVEFUNCTION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVEFUNCTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTVALVEFUNCTION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTVALVEFUNCTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTVALVEFUNCTION):
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

const apiUrl = 'api/list-valve-functions';

// Actions

export const getEntities: ICrudGetAllAction<IListValveFunction> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVEFUNCTION_LIST,
    payload: axios.get<IListValveFunction>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListValveFunction> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVEFUNCTION,
    payload: axios.get<IListValveFunction>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListValveFunction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTVALVEFUNCTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListValveFunction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTVALVEFUNCTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListValveFunction> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTVALVEFUNCTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
