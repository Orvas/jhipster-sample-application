import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListClcResult, defaultValue } from 'app/shared/model/list-clc-result.model';

export const ACTION_TYPES = {
  FETCH_LISTCLCRESULT_LIST: 'listClcResult/FETCH_LISTCLCRESULT_LIST',
  FETCH_LISTCLCRESULT: 'listClcResult/FETCH_LISTCLCRESULT',
  CREATE_LISTCLCRESULT: 'listClcResult/CREATE_LISTCLCRESULT',
  UPDATE_LISTCLCRESULT: 'listClcResult/UPDATE_LISTCLCRESULT',
  DELETE_LISTCLCRESULT: 'listClcResult/DELETE_LISTCLCRESULT',
  RESET: 'listClcResult/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListClcResult>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListClcResultState = Readonly<typeof initialState>;

// Reducer

export default (state: ListClcResultState = initialState, action): ListClcResultState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCRESULT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTCLCRESULT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTCLCRESULT):
    case REQUEST(ACTION_TYPES.UPDATE_LISTCLCRESULT):
    case REQUEST(ACTION_TYPES.DELETE_LISTCLCRESULT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCRESULT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTCLCRESULT):
    case FAILURE(ACTION_TYPES.CREATE_LISTCLCRESULT):
    case FAILURE(ACTION_TYPES.UPDATE_LISTCLCRESULT):
    case FAILURE(ACTION_TYPES.DELETE_LISTCLCRESULT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCRESULT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTCLCRESULT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTCLCRESULT):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTCLCRESULT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTCLCRESULT):
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

const apiUrl = 'api/list-clc-results';

// Actions

export const getEntities: ICrudGetAllAction<IListClcResult> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCRESULT_LIST,
    payload: axios.get<IListClcResult>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListClcResult> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTCLCRESULT,
    payload: axios.get<IListClcResult>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListClcResult> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTCLCRESULT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListClcResult> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTCLCRESULT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListClcResult> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTCLCRESULT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
