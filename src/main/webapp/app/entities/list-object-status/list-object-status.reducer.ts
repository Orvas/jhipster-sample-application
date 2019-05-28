import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListObjectStatus, defaultValue } from 'app/shared/model/list-object-status.model';

export const ACTION_TYPES = {
  FETCH_LISTOBJECTSTATUS_LIST: 'listObjectStatus/FETCH_LISTOBJECTSTATUS_LIST',
  FETCH_LISTOBJECTSTATUS: 'listObjectStatus/FETCH_LISTOBJECTSTATUS',
  CREATE_LISTOBJECTSTATUS: 'listObjectStatus/CREATE_LISTOBJECTSTATUS',
  UPDATE_LISTOBJECTSTATUS: 'listObjectStatus/UPDATE_LISTOBJECTSTATUS',
  DELETE_LISTOBJECTSTATUS: 'listObjectStatus/DELETE_LISTOBJECTSTATUS',
  RESET: 'listObjectStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListObjectStatus>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ListObjectStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: ListObjectStatusState = initialState, action): ListObjectStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTOBJECTSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTOBJECTSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTOBJECTSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_LISTOBJECTSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_LISTOBJECTSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTOBJECTSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTOBJECTSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_LISTOBJECTSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_LISTOBJECTSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_LISTOBJECTSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTOBJECTSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTOBJECTSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTOBJECTSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTOBJECTSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTOBJECTSTATUS):
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

const apiUrl = 'api/list-object-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IListObjectStatus> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LISTOBJECTSTATUS_LIST,
  payload: axios.get<IListObjectStatus>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IListObjectStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTOBJECTSTATUS,
    payload: axios.get<IListObjectStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListObjectStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTOBJECTSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListObjectStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTOBJECTSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListObjectStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTOBJECTSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
