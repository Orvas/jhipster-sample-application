import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAnodeHist, defaultValue } from 'app/shared/model/anode-hist.model';

export const ACTION_TYPES = {
  FETCH_ANODEHIST_LIST: 'anodeHist/FETCH_ANODEHIST_LIST',
  FETCH_ANODEHIST: 'anodeHist/FETCH_ANODEHIST',
  CREATE_ANODEHIST: 'anodeHist/CREATE_ANODEHIST',
  UPDATE_ANODEHIST: 'anodeHist/UPDATE_ANODEHIST',
  DELETE_ANODEHIST: 'anodeHist/DELETE_ANODEHIST',
  RESET: 'anodeHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAnodeHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AnodeHistState = Readonly<typeof initialState>;

// Reducer

export default (state: AnodeHistState = initialState, action): AnodeHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ANODEHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ANODEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ANODEHIST):
    case REQUEST(ACTION_TYPES.UPDATE_ANODEHIST):
    case REQUEST(ACTION_TYPES.DELETE_ANODEHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ANODEHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ANODEHIST):
    case FAILURE(ACTION_TYPES.CREATE_ANODEHIST):
    case FAILURE(ACTION_TYPES.UPDATE_ANODEHIST):
    case FAILURE(ACTION_TYPES.DELETE_ANODEHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANODEHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANODEHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ANODEHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_ANODEHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ANODEHIST):
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

const apiUrl = 'api/anode-hists';

// Actions

export const getEntities: ICrudGetAllAction<IAnodeHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ANODEHIST_LIST,
    payload: axios.get<IAnodeHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAnodeHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ANODEHIST,
    payload: axios.get<IAnodeHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAnodeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ANODEHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAnodeHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ANODEHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAnodeHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ANODEHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
