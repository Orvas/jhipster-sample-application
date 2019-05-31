import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListRiskProbability, defaultValue } from 'app/shared/model/list-risk-probability.model';

export const ACTION_TYPES = {
  FETCH_LISTRISKPROBABILITY_LIST: 'listRiskProbability/FETCH_LISTRISKPROBABILITY_LIST',
  FETCH_LISTRISKPROBABILITY: 'listRiskProbability/FETCH_LISTRISKPROBABILITY',
  CREATE_LISTRISKPROBABILITY: 'listRiskProbability/CREATE_LISTRISKPROBABILITY',
  UPDATE_LISTRISKPROBABILITY: 'listRiskProbability/UPDATE_LISTRISKPROBABILITY',
  DELETE_LISTRISKPROBABILITY: 'listRiskProbability/DELETE_LISTRISKPROBABILITY',
  RESET: 'listRiskProbability/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListRiskProbability>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListRiskProbabilityState = Readonly<typeof initialState>;

// Reducer

export default (state: ListRiskProbabilityState = initialState, action): ListRiskProbabilityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTRISKPROBABILITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTRISKPROBABILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTRISKPROBABILITY):
    case REQUEST(ACTION_TYPES.UPDATE_LISTRISKPROBABILITY):
    case REQUEST(ACTION_TYPES.DELETE_LISTRISKPROBABILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTRISKPROBABILITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTRISKPROBABILITY):
    case FAILURE(ACTION_TYPES.CREATE_LISTRISKPROBABILITY):
    case FAILURE(ACTION_TYPES.UPDATE_LISTRISKPROBABILITY):
    case FAILURE(ACTION_TYPES.DELETE_LISTRISKPROBABILITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTRISKPROBABILITY_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTRISKPROBABILITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTRISKPROBABILITY):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTRISKPROBABILITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTRISKPROBABILITY):
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

const apiUrl = 'api/list-risk-probabilities';

// Actions

export const getEntities: ICrudGetAllAction<IListRiskProbability> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTRISKPROBABILITY_LIST,
    payload: axios.get<IListRiskProbability>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListRiskProbability> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTRISKPROBABILITY,
    payload: axios.get<IListRiskProbability>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListRiskProbability> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTRISKPROBABILITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListRiskProbability> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTRISKPROBABILITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListRiskProbability> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTRISKPROBABILITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
