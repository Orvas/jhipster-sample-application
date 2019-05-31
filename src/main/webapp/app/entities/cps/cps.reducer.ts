import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICps, defaultValue } from 'app/shared/model/cps.model';

export const ACTION_TYPES = {
  FETCH_CPS_LIST: 'cps/FETCH_CPS_LIST',
  FETCH_CPS: 'cps/FETCH_CPS',
  CREATE_CPS: 'cps/CREATE_CPS',
  UPDATE_CPS: 'cps/UPDATE_CPS',
  DELETE_CPS: 'cps/DELETE_CPS',
  RESET: 'cps/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICps>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CpsState = Readonly<typeof initialState>;

// Reducer

export default (state: CpsState = initialState, action): CpsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CPS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CPS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CPS):
    case REQUEST(ACTION_TYPES.UPDATE_CPS):
    case REQUEST(ACTION_TYPES.DELETE_CPS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CPS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CPS):
    case FAILURE(ACTION_TYPES.CREATE_CPS):
    case FAILURE(ACTION_TYPES.UPDATE_CPS):
    case FAILURE(ACTION_TYPES.DELETE_CPS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CPS):
    case SUCCESS(ACTION_TYPES.UPDATE_CPS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CPS):
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

const apiUrl = 'api/cps';

// Actions

export const getEntities: ICrudGetAllAction<ICps> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CPS_LIST,
    payload: axios.get<ICps>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICps> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CPS,
    payload: axios.get<ICps>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICps> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CPS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICps> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CPS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICps> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CPS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
