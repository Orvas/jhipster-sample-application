import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListAnodeBraceleteType, defaultValue } from 'app/shared/model/list-anode-bracelete-type.model';

export const ACTION_TYPES = {
  FETCH_LISTANODEBRACELETETYPE_LIST: 'listAnodeBraceleteType/FETCH_LISTANODEBRACELETETYPE_LIST',
  FETCH_LISTANODEBRACELETETYPE: 'listAnodeBraceleteType/FETCH_LISTANODEBRACELETETYPE',
  CREATE_LISTANODEBRACELETETYPE: 'listAnodeBraceleteType/CREATE_LISTANODEBRACELETETYPE',
  UPDATE_LISTANODEBRACELETETYPE: 'listAnodeBraceleteType/UPDATE_LISTANODEBRACELETETYPE',
  DELETE_LISTANODEBRACELETETYPE: 'listAnodeBraceleteType/DELETE_LISTANODEBRACELETETYPE',
  RESET: 'listAnodeBraceleteType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListAnodeBraceleteType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListAnodeBraceleteTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListAnodeBraceleteTypeState = initialState, action): ListAnodeBraceleteTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTANODEBRACELETETYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTANODEBRACELETETYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTANODEBRACELETETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTANODEBRACELETETYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTANODEBRACELETETYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTANODEBRACELETETYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTANODEBRACELETETYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTANODEBRACELETETYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTANODEBRACELETETYPE):
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

const apiUrl = 'api/list-anode-bracelete-types';

// Actions

export const getEntities: ICrudGetAllAction<IListAnodeBraceleteType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE_LIST,
    payload: axios.get<IListAnodeBraceleteType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListAnodeBraceleteType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTANODEBRACELETETYPE,
    payload: axios.get<IListAnodeBraceleteType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListAnodeBraceleteType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTANODEBRACELETETYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListAnodeBraceleteType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTANODEBRACELETETYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListAnodeBraceleteType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTANODEBRACELETETYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
