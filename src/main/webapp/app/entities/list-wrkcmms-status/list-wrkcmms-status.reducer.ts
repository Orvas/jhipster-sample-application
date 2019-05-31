import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListWrkcmmsStatus, defaultValue } from 'app/shared/model/list-wrkcmms-status.model';

export const ACTION_TYPES = {
  FETCH_LISTWRKCMMSSTATUS_LIST: 'listWrkcmmsStatus/FETCH_LISTWRKCMMSSTATUS_LIST',
  FETCH_LISTWRKCMMSSTATUS: 'listWrkcmmsStatus/FETCH_LISTWRKCMMSSTATUS',
  CREATE_LISTWRKCMMSSTATUS: 'listWrkcmmsStatus/CREATE_LISTWRKCMMSSTATUS',
  UPDATE_LISTWRKCMMSSTATUS: 'listWrkcmmsStatus/UPDATE_LISTWRKCMMSSTATUS',
  DELETE_LISTWRKCMMSSTATUS: 'listWrkcmmsStatus/DELETE_LISTWRKCMMSSTATUS',
  RESET: 'listWrkcmmsStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListWrkcmmsStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListWrkcmmsStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: ListWrkcmmsStatusState = initialState, action): ListWrkcmmsStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTWRKCMMSSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_LISTWRKCMMSSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_LISTWRKCMMSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_LISTWRKCMMSSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_LISTWRKCMMSSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_LISTWRKCMMSSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTWRKCMMSSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTWRKCMMSSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTWRKCMMSSTATUS):
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

const apiUrl = 'api/list-wrkcmms-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IListWrkcmmsStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS_LIST,
    payload: axios.get<IListWrkcmmsStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListWrkcmmsStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKCMMSSTATUS,
    payload: axios.get<IListWrkcmmsStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListWrkcmmsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTWRKCMMSSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListWrkcmmsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTWRKCMMSSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListWrkcmmsStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTWRKCMMSSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
