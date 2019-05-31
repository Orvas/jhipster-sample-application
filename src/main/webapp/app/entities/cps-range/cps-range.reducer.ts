import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICpsRange, defaultValue } from 'app/shared/model/cps-range.model';

export const ACTION_TYPES = {
  FETCH_CPSRANGE_LIST: 'cpsRange/FETCH_CPSRANGE_LIST',
  FETCH_CPSRANGE: 'cpsRange/FETCH_CPSRANGE',
  CREATE_CPSRANGE: 'cpsRange/CREATE_CPSRANGE',
  UPDATE_CPSRANGE: 'cpsRange/UPDATE_CPSRANGE',
  DELETE_CPSRANGE: 'cpsRange/DELETE_CPSRANGE',
  RESET: 'cpsRange/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICpsRange>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CpsRangeState = Readonly<typeof initialState>;

// Reducer

export default (state: CpsRangeState = initialState, action): CpsRangeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CPSRANGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CPSRANGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CPSRANGE):
    case REQUEST(ACTION_TYPES.UPDATE_CPSRANGE):
    case REQUEST(ACTION_TYPES.DELETE_CPSRANGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CPSRANGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CPSRANGE):
    case FAILURE(ACTION_TYPES.CREATE_CPSRANGE):
    case FAILURE(ACTION_TYPES.UPDATE_CPSRANGE):
    case FAILURE(ACTION_TYPES.DELETE_CPSRANGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPSRANGE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPSRANGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CPSRANGE):
    case SUCCESS(ACTION_TYPES.UPDATE_CPSRANGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CPSRANGE):
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

const apiUrl = 'api/cps-ranges';

// Actions

export const getEntities: ICrudGetAllAction<ICpsRange> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CPSRANGE_LIST,
    payload: axios.get<ICpsRange>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICpsRange> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CPSRANGE,
    payload: axios.get<ICpsRange>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICpsRange> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CPSRANGE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICpsRange> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CPSRANGE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICpsRange> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CPSRANGE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
