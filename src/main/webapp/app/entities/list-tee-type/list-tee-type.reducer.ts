import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListTeeType, defaultValue } from 'app/shared/model/list-tee-type.model';

export const ACTION_TYPES = {
  FETCH_LISTTEETYPE_LIST: 'listTeeType/FETCH_LISTTEETYPE_LIST',
  FETCH_LISTTEETYPE: 'listTeeType/FETCH_LISTTEETYPE',
  CREATE_LISTTEETYPE: 'listTeeType/CREATE_LISTTEETYPE',
  UPDATE_LISTTEETYPE: 'listTeeType/UPDATE_LISTTEETYPE',
  DELETE_LISTTEETYPE: 'listTeeType/DELETE_LISTTEETYPE',
  RESET: 'listTeeType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListTeeType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListTeeTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListTeeTypeState = initialState, action): ListTeeTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTTEETYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTTEETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTTEETYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTTEETYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTTEETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTTEETYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTTEETYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTTEETYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTTEETYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTTEETYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEETYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEETYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTTEETYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTTEETYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTTEETYPE):
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

const apiUrl = 'api/list-tee-types';

// Actions

export const getEntities: ICrudGetAllAction<IListTeeType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEETYPE_LIST,
    payload: axios.get<IListTeeType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListTeeType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEETYPE,
    payload: axios.get<IListTeeType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListTeeType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTTEETYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListTeeType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTTEETYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListTeeType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTTEETYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
