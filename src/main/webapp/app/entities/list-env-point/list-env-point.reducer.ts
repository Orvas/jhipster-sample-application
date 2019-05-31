import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListEnvPoint, defaultValue } from 'app/shared/model/list-env-point.model';

export const ACTION_TYPES = {
  FETCH_LISTENVPOINT_LIST: 'listEnvPoint/FETCH_LISTENVPOINT_LIST',
  FETCH_LISTENVPOINT: 'listEnvPoint/FETCH_LISTENVPOINT',
  CREATE_LISTENVPOINT: 'listEnvPoint/CREATE_LISTENVPOINT',
  UPDATE_LISTENVPOINT: 'listEnvPoint/UPDATE_LISTENVPOINT',
  DELETE_LISTENVPOINT: 'listEnvPoint/DELETE_LISTENVPOINT',
  RESET: 'listEnvPoint/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListEnvPoint>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListEnvPointState = Readonly<typeof initialState>;

// Reducer

export default (state: ListEnvPointState = initialState, action): ListEnvPointState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTENVPOINT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTENVPOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTENVPOINT):
    case REQUEST(ACTION_TYPES.UPDATE_LISTENVPOINT):
    case REQUEST(ACTION_TYPES.DELETE_LISTENVPOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTENVPOINT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTENVPOINT):
    case FAILURE(ACTION_TYPES.CREATE_LISTENVPOINT):
    case FAILURE(ACTION_TYPES.UPDATE_LISTENVPOINT):
    case FAILURE(ACTION_TYPES.DELETE_LISTENVPOINT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTENVPOINT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTENVPOINT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTENVPOINT):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTENVPOINT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTENVPOINT):
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

const apiUrl = 'api/list-env-points';

// Actions

export const getEntities: ICrudGetAllAction<IListEnvPoint> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTENVPOINT_LIST,
    payload: axios.get<IListEnvPoint>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListEnvPoint> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTENVPOINT,
    payload: axios.get<IListEnvPoint>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListEnvPoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTENVPOINT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListEnvPoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTENVPOINT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListEnvPoint> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTENVPOINT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
