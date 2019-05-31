import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipejoint, defaultValue } from 'app/shared/model/pipejoint.model';

export const ACTION_TYPES = {
  FETCH_PIPEJOINT_LIST: 'pipejoint/FETCH_PIPEJOINT_LIST',
  FETCH_PIPEJOINT: 'pipejoint/FETCH_PIPEJOINT',
  CREATE_PIPEJOINT: 'pipejoint/CREATE_PIPEJOINT',
  UPDATE_PIPEJOINT: 'pipejoint/UPDATE_PIPEJOINT',
  DELETE_PIPEJOINT: 'pipejoint/DELETE_PIPEJOINT',
  RESET: 'pipejoint/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipejoint>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipejointState = Readonly<typeof initialState>;

// Reducer

export default (state: PipejointState = initialState, action): PipejointState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPEJOINT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPEJOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPEJOINT):
    case REQUEST(ACTION_TYPES.UPDATE_PIPEJOINT):
    case REQUEST(ACTION_TYPES.DELETE_PIPEJOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPEJOINT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPEJOINT):
    case FAILURE(ACTION_TYPES.CREATE_PIPEJOINT):
    case FAILURE(ACTION_TYPES.UPDATE_PIPEJOINT):
    case FAILURE(ACTION_TYPES.DELETE_PIPEJOINT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEJOINT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEJOINT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPEJOINT):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPEJOINT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPEJOINT):
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

const apiUrl = 'api/pipejoints';

// Actions

export const getEntities: ICrudGetAllAction<IPipejoint> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEJOINT_LIST,
    payload: axios.get<IPipejoint>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipejoint> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEJOINT,
    payload: axios.get<IPipejoint>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipejoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPEJOINT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipejoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPEJOINT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipejoint> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPEJOINT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
