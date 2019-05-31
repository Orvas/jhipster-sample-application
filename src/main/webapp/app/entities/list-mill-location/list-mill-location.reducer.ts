import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListMillLocation, defaultValue } from 'app/shared/model/list-mill-location.model';

export const ACTION_TYPES = {
  FETCH_LISTMILLLOCATION_LIST: 'listMillLocation/FETCH_LISTMILLLOCATION_LIST',
  FETCH_LISTMILLLOCATION: 'listMillLocation/FETCH_LISTMILLLOCATION',
  CREATE_LISTMILLLOCATION: 'listMillLocation/CREATE_LISTMILLLOCATION',
  UPDATE_LISTMILLLOCATION: 'listMillLocation/UPDATE_LISTMILLLOCATION',
  DELETE_LISTMILLLOCATION: 'listMillLocation/DELETE_LISTMILLLOCATION',
  RESET: 'listMillLocation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListMillLocation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListMillLocationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListMillLocationState = initialState, action): ListMillLocationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTMILLLOCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTMILLLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTMILLLOCATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTMILLLOCATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTMILLLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTMILLLOCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTMILLLOCATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTMILLLOCATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTMILLLOCATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTMILLLOCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTMILLLOCATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTMILLLOCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTMILLLOCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTMILLLOCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTMILLLOCATION):
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

const apiUrl = 'api/list-mill-locations';

// Actions

export const getEntities: ICrudGetAllAction<IListMillLocation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTMILLLOCATION_LIST,
    payload: axios.get<IListMillLocation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListMillLocation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTMILLLOCATION,
    payload: axios.get<IListMillLocation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListMillLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTMILLLOCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListMillLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTMILLLOCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListMillLocation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTMILLLOCATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
