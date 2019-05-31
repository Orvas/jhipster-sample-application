import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListRiskConsequence, defaultValue } from 'app/shared/model/list-risk-consequence.model';

export const ACTION_TYPES = {
  FETCH_LISTRISKCONSEQUENCE_LIST: 'listRiskConsequence/FETCH_LISTRISKCONSEQUENCE_LIST',
  FETCH_LISTRISKCONSEQUENCE: 'listRiskConsequence/FETCH_LISTRISKCONSEQUENCE',
  CREATE_LISTRISKCONSEQUENCE: 'listRiskConsequence/CREATE_LISTRISKCONSEQUENCE',
  UPDATE_LISTRISKCONSEQUENCE: 'listRiskConsequence/UPDATE_LISTRISKCONSEQUENCE',
  DELETE_LISTRISKCONSEQUENCE: 'listRiskConsequence/DELETE_LISTRISKCONSEQUENCE',
  RESET: 'listRiskConsequence/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListRiskConsequence>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListRiskConsequenceState = Readonly<typeof initialState>;

// Reducer

export default (state: ListRiskConsequenceState = initialState, action): ListRiskConsequenceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTRISKCONSEQUENCE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTRISKCONSEQUENCE):
    case REQUEST(ACTION_TYPES.DELETE_LISTRISKCONSEQUENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE):
    case FAILURE(ACTION_TYPES.CREATE_LISTRISKCONSEQUENCE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTRISKCONSEQUENCE):
    case FAILURE(ACTION_TYPES.DELETE_LISTRISKCONSEQUENCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTRISKCONSEQUENCE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTRISKCONSEQUENCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTRISKCONSEQUENCE):
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

const apiUrl = 'api/list-risk-consequences';

// Actions

export const getEntities: ICrudGetAllAction<IListRiskConsequence> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE_LIST,
    payload: axios.get<IListRiskConsequence>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListRiskConsequence> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTRISKCONSEQUENCE,
    payload: axios.get<IListRiskConsequence>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListRiskConsequence> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTRISKCONSEQUENCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListRiskConsequence> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTRISKCONSEQUENCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListRiskConsequence> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTRISKCONSEQUENCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
