import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListWrkPurpose, defaultValue } from 'app/shared/model/list-wrk-purpose.model';

export const ACTION_TYPES = {
  FETCH_LISTWRKPURPOSE_LIST: 'listWrkPurpose/FETCH_LISTWRKPURPOSE_LIST',
  FETCH_LISTWRKPURPOSE: 'listWrkPurpose/FETCH_LISTWRKPURPOSE',
  CREATE_LISTWRKPURPOSE: 'listWrkPurpose/CREATE_LISTWRKPURPOSE',
  UPDATE_LISTWRKPURPOSE: 'listWrkPurpose/UPDATE_LISTWRKPURPOSE',
  DELETE_LISTWRKPURPOSE: 'listWrkPurpose/DELETE_LISTWRKPURPOSE',
  RESET: 'listWrkPurpose/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListWrkPurpose>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListWrkPurposeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListWrkPurposeState = initialState, action): ListWrkPurposeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKPURPOSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKPURPOSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTWRKPURPOSE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTWRKPURPOSE):
    case REQUEST(ACTION_TYPES.DELETE_LISTWRKPURPOSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKPURPOSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKPURPOSE):
    case FAILURE(ACTION_TYPES.CREATE_LISTWRKPURPOSE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTWRKPURPOSE):
    case FAILURE(ACTION_TYPES.DELETE_LISTWRKPURPOSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKPURPOSE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKPURPOSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTWRKPURPOSE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTWRKPURPOSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTWRKPURPOSE):
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

const apiUrl = 'api/list-wrk-purposes';

// Actions

export const getEntities: ICrudGetAllAction<IListWrkPurpose> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKPURPOSE_LIST,
    payload: axios.get<IListWrkPurpose>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListWrkPurpose> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKPURPOSE,
    payload: axios.get<IListWrkPurpose>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListWrkPurpose> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTWRKPURPOSE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListWrkPurpose> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTWRKPURPOSE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListWrkPurpose> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTWRKPURPOSE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
