import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListDfctGroup, defaultValue } from 'app/shared/model/list-dfct-group.model';

export const ACTION_TYPES = {
  FETCH_LISTDFCTGROUP_LIST: 'listDfctGroup/FETCH_LISTDFCTGROUP_LIST',
  FETCH_LISTDFCTGROUP: 'listDfctGroup/FETCH_LISTDFCTGROUP',
  CREATE_LISTDFCTGROUP: 'listDfctGroup/CREATE_LISTDFCTGROUP',
  UPDATE_LISTDFCTGROUP: 'listDfctGroup/UPDATE_LISTDFCTGROUP',
  DELETE_LISTDFCTGROUP: 'listDfctGroup/DELETE_LISTDFCTGROUP',
  RESET: 'listDfctGroup/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListDfctGroup>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListDfctGroupState = Readonly<typeof initialState>;

// Reducer

export default (state: ListDfctGroupState = initialState, action): ListDfctGroupState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTGROUP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTDFCTGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTDFCTGROUP):
    case REQUEST(ACTION_TYPES.UPDATE_LISTDFCTGROUP):
    case REQUEST(ACTION_TYPES.DELETE_LISTDFCTGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTGROUP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTDFCTGROUP):
    case FAILURE(ACTION_TYPES.CREATE_LISTDFCTGROUP):
    case FAILURE(ACTION_TYPES.UPDATE_LISTDFCTGROUP):
    case FAILURE(ACTION_TYPES.DELETE_LISTDFCTGROUP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTGROUP_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTDFCTGROUP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTDFCTGROUP):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTDFCTGROUP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTDFCTGROUP):
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

const apiUrl = 'api/list-dfct-groups';

// Actions

export const getEntities: ICrudGetAllAction<IListDfctGroup> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTGROUP_LIST,
    payload: axios.get<IListDfctGroup>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListDfctGroup> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTDFCTGROUP,
    payload: axios.get<IListDfctGroup>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListDfctGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTDFCTGROUP,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListDfctGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTDFCTGROUP,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListDfctGroup> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTDFCTGROUP,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
