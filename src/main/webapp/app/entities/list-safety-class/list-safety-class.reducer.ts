import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListSafetyClass, defaultValue } from 'app/shared/model/list-safety-class.model';

export const ACTION_TYPES = {
  FETCH_LISTSAFETYCLASS_LIST: 'listSafetyClass/FETCH_LISTSAFETYCLASS_LIST',
  FETCH_LISTSAFETYCLASS: 'listSafetyClass/FETCH_LISTSAFETYCLASS',
  CREATE_LISTSAFETYCLASS: 'listSafetyClass/CREATE_LISTSAFETYCLASS',
  UPDATE_LISTSAFETYCLASS: 'listSafetyClass/UPDATE_LISTSAFETYCLASS',
  DELETE_LISTSAFETYCLASS: 'listSafetyClass/DELETE_LISTSAFETYCLASS',
  RESET: 'listSafetyClass/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListSafetyClass>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListSafetyClassState = Readonly<typeof initialState>;

// Reducer

export default (state: ListSafetyClassState = initialState, action): ListSafetyClassState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTSAFETYCLASS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTSAFETYCLASS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTSAFETYCLASS):
    case REQUEST(ACTION_TYPES.UPDATE_LISTSAFETYCLASS):
    case REQUEST(ACTION_TYPES.DELETE_LISTSAFETYCLASS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTSAFETYCLASS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTSAFETYCLASS):
    case FAILURE(ACTION_TYPES.CREATE_LISTSAFETYCLASS):
    case FAILURE(ACTION_TYPES.UPDATE_LISTSAFETYCLASS):
    case FAILURE(ACTION_TYPES.DELETE_LISTSAFETYCLASS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSAFETYCLASS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSAFETYCLASS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTSAFETYCLASS):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTSAFETYCLASS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTSAFETYCLASS):
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

const apiUrl = 'api/list-safety-classes';

// Actions

export const getEntities: ICrudGetAllAction<IListSafetyClass> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSAFETYCLASS_LIST,
    payload: axios.get<IListSafetyClass>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListSafetyClass> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSAFETYCLASS,
    payload: axios.get<IListSafetyClass>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListSafetyClass> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTSAFETYCLASS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListSafetyClass> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTSAFETYCLASS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListSafetyClass> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTSAFETYCLASS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
