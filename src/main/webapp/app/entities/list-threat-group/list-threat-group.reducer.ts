import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListThreatGroup, defaultValue } from 'app/shared/model/list-threat-group.model';

export const ACTION_TYPES = {
  FETCH_LISTTHREATGROUP_LIST: 'listThreatGroup/FETCH_LISTTHREATGROUP_LIST',
  FETCH_LISTTHREATGROUP: 'listThreatGroup/FETCH_LISTTHREATGROUP',
  CREATE_LISTTHREATGROUP: 'listThreatGroup/CREATE_LISTTHREATGROUP',
  UPDATE_LISTTHREATGROUP: 'listThreatGroup/UPDATE_LISTTHREATGROUP',
  DELETE_LISTTHREATGROUP: 'listThreatGroup/DELETE_LISTTHREATGROUP',
  RESET: 'listThreatGroup/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListThreatGroup>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListThreatGroupState = Readonly<typeof initialState>;

// Reducer

export default (state: ListThreatGroupState = initialState, action): ListThreatGroupState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTTHREATGROUP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTTHREATGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTTHREATGROUP):
    case REQUEST(ACTION_TYPES.UPDATE_LISTTHREATGROUP):
    case REQUEST(ACTION_TYPES.DELETE_LISTTHREATGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTTHREATGROUP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTTHREATGROUP):
    case FAILURE(ACTION_TYPES.CREATE_LISTTHREATGROUP):
    case FAILURE(ACTION_TYPES.UPDATE_LISTTHREATGROUP):
    case FAILURE(ACTION_TYPES.DELETE_LISTTHREATGROUP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTHREATGROUP_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTHREATGROUP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTTHREATGROUP):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTTHREATGROUP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTTHREATGROUP):
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

const apiUrl = 'api/list-threat-groups';

// Actions

export const getEntities: ICrudGetAllAction<IListThreatGroup> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTHREATGROUP_LIST,
    payload: axios.get<IListThreatGroup>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListThreatGroup> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTHREATGROUP,
    payload: axios.get<IListThreatGroup>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListThreatGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTTHREATGROUP,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListThreatGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTTHREATGROUP,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListThreatGroup> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTTHREATGROUP,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
