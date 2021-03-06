import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IValve, defaultValue } from 'app/shared/model/valve.model';

export const ACTION_TYPES = {
  FETCH_VALVE_LIST: 'valve/FETCH_VALVE_LIST',
  FETCH_VALVE: 'valve/FETCH_VALVE',
  CREATE_VALVE: 'valve/CREATE_VALVE',
  UPDATE_VALVE: 'valve/UPDATE_VALVE',
  DELETE_VALVE: 'valve/DELETE_VALVE',
  RESET: 'valve/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IValve>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ValveState = Readonly<typeof initialState>;

// Reducer

export default (state: ValveState = initialState, action): ValveState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VALVE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VALVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VALVE):
    case REQUEST(ACTION_TYPES.UPDATE_VALVE):
    case REQUEST(ACTION_TYPES.DELETE_VALVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VALVE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VALVE):
    case FAILURE(ACTION_TYPES.CREATE_VALVE):
    case FAILURE(ACTION_TYPES.UPDATE_VALVE):
    case FAILURE(ACTION_TYPES.DELETE_VALVE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VALVE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VALVE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VALVE):
    case SUCCESS(ACTION_TYPES.UPDATE_VALVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VALVE):
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

const apiUrl = 'api/valves';

// Actions

export const getEntities: ICrudGetAllAction<IValve> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_VALVE_LIST,
    payload: axios.get<IValve>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IValve> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VALVE,
    payload: axios.get<IValve>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IValve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VALVE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IValve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VALVE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IValve> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VALVE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
