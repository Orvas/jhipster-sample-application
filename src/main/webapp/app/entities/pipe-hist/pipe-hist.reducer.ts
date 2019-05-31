import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPipeHist, defaultValue } from 'app/shared/model/pipe-hist.model';

export const ACTION_TYPES = {
  FETCH_PIPEHIST_LIST: 'pipeHist/FETCH_PIPEHIST_LIST',
  FETCH_PIPEHIST: 'pipeHist/FETCH_PIPEHIST',
  CREATE_PIPEHIST: 'pipeHist/CREATE_PIPEHIST',
  UPDATE_PIPEHIST: 'pipeHist/UPDATE_PIPEHIST',
  DELETE_PIPEHIST: 'pipeHist/DELETE_PIPEHIST',
  RESET: 'pipeHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPipeHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PipeHistState = Readonly<typeof initialState>;

// Reducer

export default (state: PipeHistState = initialState, action): PipeHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIPEHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIPEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PIPEHIST):
    case REQUEST(ACTION_TYPES.UPDATE_PIPEHIST):
    case REQUEST(ACTION_TYPES.DELETE_PIPEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PIPEHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIPEHIST):
    case FAILURE(ACTION_TYPES.CREATE_PIPEHIST):
    case FAILURE(ACTION_TYPES.UPDATE_PIPEHIST):
    case FAILURE(ACTION_TYPES.DELETE_PIPEHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIPEHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIPEHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_PIPEHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIPEHIST):
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

const apiUrl = 'api/pipe-hists';

// Actions

export const getEntities: ICrudGetAllAction<IPipeHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEHIST_LIST,
    payload: axios.get<IPipeHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPipeHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIPEHIST,
    payload: axios.get<IPipeHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPipeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIPEHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPipeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIPEHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPipeHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIPEHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
