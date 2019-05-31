import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListDfctPosType, defaultValue } from 'app/shared/model/list-dfct-pos-type.model';

export const ACTION_TYPES = {
  FETCH_LISTDFCTPOSTYPE_LIST: 'listDfctPosType/FETCH_LISTDFCTPOSTYPE_LIST',
  FETCH_LISTDFCTPOSTYPE: 'listDfctPosType/FETCH_LISTDFCTPOSTYPE',
  CREATE_LISTDFCTPOSTYPE: 'listDfctPosType/CREATE_LISTDFCTPOSTYPE',
  UPDATE_LISTDFCTPOSTYPE: 'listDfctPosType/UPDATE_LISTDFCTPOSTYPE',
  DELETE_LISTDFCTPOSTYPE: 'listDfctPosType/DELETE_LISTDFCTPOSTYPE',
  RESET: 'listDfctPosType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListDfctPosType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListDfctPosTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListDfctPosTypeState = initialState, action): ListDfctPosTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTDFCTPOSTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTDFCTPOSTYPE):
    case REQUEST(ACTION_TYPES.DELETE_LISTDFCTPOSTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE):
    case FAILURE(ACTION_TYPES.CREATE_LISTDFCTPOSTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTDFCTPOSTYPE):
    case FAILURE(ACTION_TYPES.DELETE_LISTDFCTPOSTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTPOSTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTDFCTPOSTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTDFCTPOSTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTDFCTPOSTYPE):
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

const apiUrl = 'api/list-dfct-pos-types';

// Actions

export const getEntities: ICrudGetAllAction<IListDfctPosType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTPOSTYPE_LIST,
    payload: axios.get<IListDfctPosType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListDfctPosType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTPOSTYPE,
    payload: axios.get<IListDfctPosType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListDfctPosType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTDFCTPOSTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListDfctPosType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTDFCTPOSTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListDfctPosType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTDFCTPOSTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
