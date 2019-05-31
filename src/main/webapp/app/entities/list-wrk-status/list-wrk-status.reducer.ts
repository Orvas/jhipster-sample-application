import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListWrkStatus, defaultValue } from 'app/shared/model/list-wrk-status.model';

export const ACTION_TYPES = {
  FETCH_LISTWRKSTATUS_LIST: 'listWrkStatus/FETCH_LISTWRKSTATUS_LIST',
  FETCH_LISTWRKSTATUS: 'listWrkStatus/FETCH_LISTWRKSTATUS',
  CREATE_LISTWRKSTATUS: 'listWrkStatus/CREATE_LISTWRKSTATUS',
  UPDATE_LISTWRKSTATUS: 'listWrkStatus/UPDATE_LISTWRKSTATUS',
  DELETE_LISTWRKSTATUS: 'listWrkStatus/DELETE_LISTWRKSTATUS',
  RESET: 'listWrkStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListWrkStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListWrkStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: ListWrkStatusState = initialState, action): ListWrkStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTWRKSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTWRKSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_LISTWRKSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_LISTWRKSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTWRKSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_LISTWRKSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_LISTWRKSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_LISTWRKSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKSTATUS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTWRKSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTWRKSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTWRKSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTWRKSTATUS):
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

const apiUrl = 'api/list-wrk-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IListWrkStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKSTATUS_LIST,
    payload: axios.get<IListWrkStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListWrkStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTWRKSTATUS,
    payload: axios.get<IListWrkStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListWrkStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTWRKSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListWrkStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTWRKSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListWrkStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTWRKSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
