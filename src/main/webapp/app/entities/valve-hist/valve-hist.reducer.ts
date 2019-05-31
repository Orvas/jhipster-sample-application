import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IValveHist, defaultValue } from 'app/shared/model/valve-hist.model';

export const ACTION_TYPES = {
  FETCH_VALVEHIST_LIST: 'valveHist/FETCH_VALVEHIST_LIST',
  FETCH_VALVEHIST: 'valveHist/FETCH_VALVEHIST',
  CREATE_VALVEHIST: 'valveHist/CREATE_VALVEHIST',
  UPDATE_VALVEHIST: 'valveHist/UPDATE_VALVEHIST',
  DELETE_VALVEHIST: 'valveHist/DELETE_VALVEHIST',
  RESET: 'valveHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IValveHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ValveHistState = Readonly<typeof initialState>;

// Reducer

export default (state: ValveHistState = initialState, action): ValveHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VALVEHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VALVEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VALVEHIST):
    case REQUEST(ACTION_TYPES.UPDATE_VALVEHIST):
    case REQUEST(ACTION_TYPES.DELETE_VALVEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VALVEHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VALVEHIST):
    case FAILURE(ACTION_TYPES.CREATE_VALVEHIST):
    case FAILURE(ACTION_TYPES.UPDATE_VALVEHIST):
    case FAILURE(ACTION_TYPES.DELETE_VALVEHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VALVEHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VALVEHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VALVEHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_VALVEHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VALVEHIST):
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

const apiUrl = 'api/valve-hists';

// Actions

export const getEntities: ICrudGetAllAction<IValveHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_VALVEHIST_LIST,
    payload: axios.get<IValveHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IValveHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VALVEHIST,
    payload: axios.get<IValveHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IValveHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VALVEHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IValveHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VALVEHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IValveHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VALVEHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
