import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDisplacementHist, defaultValue } from 'app/shared/model/displacement-hist.model';

export const ACTION_TYPES = {
  FETCH_DISPLACEMENTHIST_LIST: 'displacementHist/FETCH_DISPLACEMENTHIST_LIST',
  FETCH_DISPLACEMENTHIST: 'displacementHist/FETCH_DISPLACEMENTHIST',
  CREATE_DISPLACEMENTHIST: 'displacementHist/CREATE_DISPLACEMENTHIST',
  UPDATE_DISPLACEMENTHIST: 'displacementHist/UPDATE_DISPLACEMENTHIST',
  DELETE_DISPLACEMENTHIST: 'displacementHist/DELETE_DISPLACEMENTHIST',
  RESET: 'displacementHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDisplacementHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DisplacementHistState = Readonly<typeof initialState>;

// Reducer

export default (state: DisplacementHistState = initialState, action): DisplacementHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DISPLACEMENTHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DISPLACEMENTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DISPLACEMENTHIST):
    case REQUEST(ACTION_TYPES.UPDATE_DISPLACEMENTHIST):
    case REQUEST(ACTION_TYPES.DELETE_DISPLACEMENTHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DISPLACEMENTHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DISPLACEMENTHIST):
    case FAILURE(ACTION_TYPES.CREATE_DISPLACEMENTHIST):
    case FAILURE(ACTION_TYPES.UPDATE_DISPLACEMENTHIST):
    case FAILURE(ACTION_TYPES.DELETE_DISPLACEMENTHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPLACEMENTHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPLACEMENTHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DISPLACEMENTHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_DISPLACEMENTHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DISPLACEMENTHIST):
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

const apiUrl = 'api/displacement-hists';

// Actions

export const getEntities: ICrudGetAllAction<IDisplacementHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DISPLACEMENTHIST_LIST,
    payload: axios.get<IDisplacementHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDisplacementHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DISPLACEMENTHIST,
    payload: axios.get<IDisplacementHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDisplacementHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DISPLACEMENTHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDisplacementHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DISPLACEMENTHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDisplacementHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DISPLACEMENTHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
