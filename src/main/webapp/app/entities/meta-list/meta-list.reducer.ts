import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMetaList, defaultValue } from 'app/shared/model/meta-list.model';

export const ACTION_TYPES = {
  FETCH_METALIST_LIST: 'metaList/FETCH_METALIST_LIST',
  FETCH_METALIST: 'metaList/FETCH_METALIST',
  CREATE_METALIST: 'metaList/CREATE_METALIST',
  UPDATE_METALIST: 'metaList/UPDATE_METALIST',
  DELETE_METALIST: 'metaList/DELETE_METALIST',
  RESET: 'metaList/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMetaList>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type MetaListState = Readonly<typeof initialState>;

// Reducer

export default (state: MetaListState = initialState, action): MetaListState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_METALIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_METALIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_METALIST):
    case REQUEST(ACTION_TYPES.UPDATE_METALIST):
    case REQUEST(ACTION_TYPES.DELETE_METALIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_METALIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_METALIST):
    case FAILURE(ACTION_TYPES.CREATE_METALIST):
    case FAILURE(ACTION_TYPES.UPDATE_METALIST):
    case FAILURE(ACTION_TYPES.DELETE_METALIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_METALIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_METALIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_METALIST):
    case SUCCESS(ACTION_TYPES.UPDATE_METALIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_METALIST):
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

const apiUrl = 'api/meta-lists';

// Actions

export const getEntities: ICrudGetAllAction<IMetaList> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_METALIST_LIST,
    payload: axios.get<IMetaList>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IMetaList> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_METALIST,
    payload: axios.get<IMetaList>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMetaList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_METALIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMetaList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_METALIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMetaList> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_METALIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
