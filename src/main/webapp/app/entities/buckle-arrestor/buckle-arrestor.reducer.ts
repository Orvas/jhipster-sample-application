import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBuckleArrestor, defaultValue } from 'app/shared/model/buckle-arrestor.model';

export const ACTION_TYPES = {
  FETCH_BUCKLEARRESTOR_LIST: 'buckleArrestor/FETCH_BUCKLEARRESTOR_LIST',
  FETCH_BUCKLEARRESTOR: 'buckleArrestor/FETCH_BUCKLEARRESTOR',
  CREATE_BUCKLEARRESTOR: 'buckleArrestor/CREATE_BUCKLEARRESTOR',
  UPDATE_BUCKLEARRESTOR: 'buckleArrestor/UPDATE_BUCKLEARRESTOR',
  DELETE_BUCKLEARRESTOR: 'buckleArrestor/DELETE_BUCKLEARRESTOR',
  RESET: 'buckleArrestor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBuckleArrestor>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type BuckleArrestorState = Readonly<typeof initialState>;

// Reducer

export default (state: BuckleArrestorState = initialState, action): BuckleArrestorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BUCKLEARRESTOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BUCKLEARRESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BUCKLEARRESTOR):
    case REQUEST(ACTION_TYPES.UPDATE_BUCKLEARRESTOR):
    case REQUEST(ACTION_TYPES.DELETE_BUCKLEARRESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BUCKLEARRESTOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BUCKLEARRESTOR):
    case FAILURE(ACTION_TYPES.CREATE_BUCKLEARRESTOR):
    case FAILURE(ACTION_TYPES.UPDATE_BUCKLEARRESTOR):
    case FAILURE(ACTION_TYPES.DELETE_BUCKLEARRESTOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BUCKLEARRESTOR_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BUCKLEARRESTOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BUCKLEARRESTOR):
    case SUCCESS(ACTION_TYPES.UPDATE_BUCKLEARRESTOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BUCKLEARRESTOR):
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

const apiUrl = 'api/buckle-arrestors';

// Actions

export const getEntities: ICrudGetAllAction<IBuckleArrestor> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BUCKLEARRESTOR_LIST,
    payload: axios.get<IBuckleArrestor>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IBuckleArrestor> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BUCKLEARRESTOR,
    payload: axios.get<IBuckleArrestor>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBuckleArrestor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BUCKLEARRESTOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBuckleArrestor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BUCKLEARRESTOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBuckleArrestor> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BUCKLEARRESTOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
