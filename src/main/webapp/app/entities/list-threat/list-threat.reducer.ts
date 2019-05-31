import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListThreat, defaultValue } from 'app/shared/model/list-threat.model';

export const ACTION_TYPES = {
  FETCH_LISTTHREAT_LIST: 'listThreat/FETCH_LISTTHREAT_LIST',
  FETCH_LISTTHREAT: 'listThreat/FETCH_LISTTHREAT',
  CREATE_LISTTHREAT: 'listThreat/CREATE_LISTTHREAT',
  UPDATE_LISTTHREAT: 'listThreat/UPDATE_LISTTHREAT',
  DELETE_LISTTHREAT: 'listThreat/DELETE_LISTTHREAT',
  RESET: 'listThreat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListThreat>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListThreatState = Readonly<typeof initialState>;

// Reducer

export default (state: ListThreatState = initialState, action): ListThreatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTTHREAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTTHREAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTTHREAT):
    case REQUEST(ACTION_TYPES.UPDATE_LISTTHREAT):
    case REQUEST(ACTION_TYPES.DELETE_LISTTHREAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTTHREAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTTHREAT):
    case FAILURE(ACTION_TYPES.CREATE_LISTTHREAT):
    case FAILURE(ACTION_TYPES.UPDATE_LISTTHREAT):
    case FAILURE(ACTION_TYPES.DELETE_LISTTHREAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTHREAT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTHREAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTTHREAT):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTTHREAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTTHREAT):
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

const apiUrl = 'api/list-threats';

// Actions

export const getEntities: ICrudGetAllAction<IListThreat> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTHREAT_LIST,
    payload: axios.get<IListThreat>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListThreat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTHREAT,
    payload: axios.get<IListThreat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListThreat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTTHREAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListThreat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTTHREAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListThreat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTTHREAT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
