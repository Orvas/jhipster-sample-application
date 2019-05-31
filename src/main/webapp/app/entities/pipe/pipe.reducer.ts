import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipe, defaultValue } from 'app/shared/model/pipe.model';

export const ACTION_TYPES = {
  FETCH_PIPE_LIST: 'pipe/FETCH_PIPE_LIST',
  FETCH_PIPE: 'pipe/FETCH_PIPE',
  CREATE_PIPE: 'pipe/CREATE_PIPE',
  UPDATE_PIPE: 'pipe/UPDATE_PIPE',
  DELETE_PIPE: 'pipe/DELETE_PIPE',
  RESET: 'pipe/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipe>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipeState = Readonly<typeof initialState>;

// Reducer

export default (state: PipeState = initialState, action): PipeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPE):
    case REQUEST(ACTION_TYPES.UPDATE_PIPE):
    case REQUEST(ACTION_TYPES.DELETE_PIPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPE):
    case FAILURE(ACTION_TYPES.CREATE_PIPE):
    case FAILURE(ACTION_TYPES.UPDATE_PIPE):
    case FAILURE(ACTION_TYPES.DELETE_PIPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPE):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPE):
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

const apiUrl = 'api/pipes';

// Actions

export const getEntities: ICrudGetAllAction<IPipe> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPE_LIST,
    payload: axios.get<IPipe>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipe> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPE,
    payload: axios.get<IPipe>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipe> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipe> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipe> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
