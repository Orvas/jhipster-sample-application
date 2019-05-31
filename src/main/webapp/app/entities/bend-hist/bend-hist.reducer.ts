import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBendHist, defaultValue } from 'app/shared/model/bend-hist.model';

export const ACTION_TYPES = {
  FETCH_BENDHIST_LIST: 'bendHist/FETCH_BENDHIST_LIST',
  FETCH_BENDHIST: 'bendHist/FETCH_BENDHIST',
  CREATE_BENDHIST: 'bendHist/CREATE_BENDHIST',
  UPDATE_BENDHIST: 'bendHist/UPDATE_BENDHIST',
  DELETE_BENDHIST: 'bendHist/DELETE_BENDHIST',
  RESET: 'bendHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBendHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type BendHistState = Readonly<typeof initialState>;

// Reducer

export default (state: BendHistState = initialState, action): BendHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BENDHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BENDHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BENDHIST):
    case REQUEST(ACTION_TYPES.UPDATE_BENDHIST):
    case REQUEST(ACTION_TYPES.DELETE_BENDHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BENDHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BENDHIST):
    case FAILURE(ACTION_TYPES.CREATE_BENDHIST):
    case FAILURE(ACTION_TYPES.UPDATE_BENDHIST):
    case FAILURE(ACTION_TYPES.DELETE_BENDHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BENDHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BENDHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BENDHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_BENDHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BENDHIST):
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

const apiUrl = 'api/bend-hists';

// Actions

export const getEntities: ICrudGetAllAction<IBendHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BENDHIST_LIST,
    payload: axios.get<IBendHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IBendHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BENDHIST,
    payload: axios.get<IBendHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBendHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BENDHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBendHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BENDHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBendHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BENDHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
