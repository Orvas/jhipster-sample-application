import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListLongSeamWeldType, defaultValue } from 'app/shared/model/list-long-seam-weld-type.model';

export const ACTION_TYPES = {
  FETCH_LISTLONGSEAMWELDTYPE_LIST: 'listLongSeamWeldType/FETCH_LISTLONGSEAMWELDTYPE_LIST',
  FETCH_LISTLONGSEAMWELDTYPE: 'listLongSeamWeldType/FETCH_LISTLONGSEAMWELDTYPE',
  CREATE_LISTLONGSEAMWELDTYPE: 'listLongSeamWeldType/CREATE_LISTLONGSEAMWELDTYPE',
  UPDATE_LISTLONGSEAMWELDTYPE: 'listLongSeamWeldType/UPDATE_LISTLONGSEAMWELDTYPE',
  DELETE_LISTLONGSEAMWELDTYPE: 'listLongSeamWeldType/DELETE_LISTLONGSEAMWELDTYPE',
  RESET: 'listLongSeamWeldType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListLongSeamWeldType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListLongSeamWeldTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListLongSeamWeldTypeState = initialState, action): ListLongSeamWeldTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTLONGSEAMWELDTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTLONGSEAMWELDTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTLONGSEAMWELDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTLONGSEAMWELDTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTLONGSEAMWELDTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTLONGSEAMWELDTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTLONGSEAMWELDTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTLONGSEAMWELDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTLONGSEAMWELDTYPE):
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

const apiUrl = 'api/list-long-seam-weld-types';

// Actions

export const getEntities: ICrudGetAllAction<IListLongSeamWeldType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE_LIST,
    payload: axios.get<IListLongSeamWeldType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListLongSeamWeldType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTLONGSEAMWELDTYPE,
    payload: axios.get<IListLongSeamWeldType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListLongSeamWeldType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTLONGSEAMWELDTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListLongSeamWeldType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTLONGSEAMWELDTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListLongSeamWeldType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTLONGSEAMWELDTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
