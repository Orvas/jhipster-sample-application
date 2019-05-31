import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListDfctType, defaultValue } from 'app/shared/model/list-dfct-type.model';

export const ACTION_TYPES = {
  FETCH_LISTDFCTTYPE_LIST: 'listDfctType/FETCH_LISTDFCTTYPE_LIST',
  FETCH_LISTDFCTTYPE: 'listDfctType/FETCH_LISTDFCTTYPE',
  CREATE_LISTDFCTTYPE: 'listDfctType/CREATE_LISTDFCTTYPE',
  UPDATE_LISTDFCTTYPE: 'listDfctType/UPDATE_LISTDFCTTYPE',
  DELETE_LISTDFCTTYPE: 'listDfctType/DELETE_LISTDFCTTYPE',
  RESET: 'listDfctType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListDfctType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListDfctTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListDfctTypeState = initialState, action): ListDfctTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTDFCTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTDFCTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTDFCTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTDFCTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTDFCTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTDFCTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTDFCTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTDFCTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTDFCTTYPE):
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

const apiUrl = 'api/list-dfct-types';

// Actions

export const getEntities: ICrudGetAllAction<IListDfctType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTTYPE_LIST,
    payload: axios.get<IListDfctType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListDfctType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTTYPE,
    payload: axios.get<IListDfctType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListDfctType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTDFCTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListDfctType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTDFCTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListDfctType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTDFCTTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
