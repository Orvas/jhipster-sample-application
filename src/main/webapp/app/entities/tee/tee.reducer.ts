import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITee, defaultValue } from 'app/shared/model/tee.model';

export const ACTION_TYPES = {
  FETCH_TEE_LIST: 'tee/FETCH_TEE_LIST',
  FETCH_TEE: 'tee/FETCH_TEE',
  CREATE_TEE: 'tee/CREATE_TEE',
  UPDATE_TEE: 'tee/UPDATE_TEE',
  DELETE_TEE: 'tee/DELETE_TEE',
  RESET: 'tee/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITee>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TeeState = Readonly<typeof initialState>;

// Reducer

export default (state: TeeState = initialState, action): TeeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TEE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEE):
    case REQUEST(ACTION_TYPES.UPDATE_TEE):
    case REQUEST(ACTION_TYPES.DELETE_TEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TEE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEE):
    case FAILURE(ACTION_TYPES.CREATE_TEE):
    case FAILURE(ACTION_TYPES.UPDATE_TEE):
    case FAILURE(ACTION_TYPES.DELETE_TEE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEE):
    case SUCCESS(ACTION_TYPES.UPDATE_TEE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEE):
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

const apiUrl = 'api/tees';

// Actions

export const getEntities: ICrudGetAllAction<ITee> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TEE_LIST,
    payload: axios.get<ITee>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITee> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEE,
    payload: axios.get<ITee>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITee> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
