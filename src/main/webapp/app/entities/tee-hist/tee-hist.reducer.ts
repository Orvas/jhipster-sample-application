import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITeeHist, defaultValue } from 'app/shared/model/tee-hist.model';

export const ACTION_TYPES = {
  FETCH_TEEHIST_LIST: 'teeHist/FETCH_TEEHIST_LIST',
  FETCH_TEEHIST: 'teeHist/FETCH_TEEHIST',
  CREATE_TEEHIST: 'teeHist/CREATE_TEEHIST',
  UPDATE_TEEHIST: 'teeHist/UPDATE_TEEHIST',
  DELETE_TEEHIST: 'teeHist/DELETE_TEEHIST',
  RESET: 'teeHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITeeHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TeeHistState = Readonly<typeof initialState>;

// Reducer

export default (state: TeeHistState = initialState, action): TeeHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TEEHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEEHIST):
    case REQUEST(ACTION_TYPES.UPDATE_TEEHIST):
    case REQUEST(ACTION_TYPES.DELETE_TEEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TEEHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEEHIST):
    case FAILURE(ACTION_TYPES.CREATE_TEEHIST):
    case FAILURE(ACTION_TYPES.UPDATE_TEEHIST):
    case FAILURE(ACTION_TYPES.DELETE_TEEHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEEHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEEHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEEHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_TEEHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEEHIST):
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

const apiUrl = 'api/tee-hists';

// Actions

export const getEntities: ICrudGetAllAction<ITeeHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TEEHIST_LIST,
    payload: axios.get<ITeeHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITeeHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEEHIST,
    payload: axios.get<ITeeHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITeeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEEHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITeeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEEHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITeeHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEEHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
