import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFreeSpanHistory, defaultValue } from 'app/shared/model/free-span-history.model';

export const ACTION_TYPES = {
  FETCH_FREESPANHISTORY_LIST: 'freeSpanHistory/FETCH_FREESPANHISTORY_LIST',
  FETCH_FREESPANHISTORY: 'freeSpanHistory/FETCH_FREESPANHISTORY',
  CREATE_FREESPANHISTORY: 'freeSpanHistory/CREATE_FREESPANHISTORY',
  UPDATE_FREESPANHISTORY: 'freeSpanHistory/UPDATE_FREESPANHISTORY',
  DELETE_FREESPANHISTORY: 'freeSpanHistory/DELETE_FREESPANHISTORY',
  RESET: 'freeSpanHistory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFreeSpanHistory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FreeSpanHistoryState = Readonly<typeof initialState>;

// Reducer

export default (state: FreeSpanHistoryState = initialState, action): FreeSpanHistoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FREESPANHISTORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FREESPANHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FREESPANHISTORY):
    case REQUEST(ACTION_TYPES.UPDATE_FREESPANHISTORY):
    case REQUEST(ACTION_TYPES.DELETE_FREESPANHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FREESPANHISTORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FREESPANHISTORY):
    case FAILURE(ACTION_TYPES.CREATE_FREESPANHISTORY):
    case FAILURE(ACTION_TYPES.UPDATE_FREESPANHISTORY):
    case FAILURE(ACTION_TYPES.DELETE_FREESPANHISTORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANHISTORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANHISTORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FREESPANHISTORY):
    case SUCCESS(ACTION_TYPES.UPDATE_FREESPANHISTORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FREESPANHISTORY):
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

const apiUrl = 'api/free-span-histories';

// Actions

export const getEntities: ICrudGetAllAction<IFreeSpanHistory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FREESPANHISTORY_LIST,
  payload: axios.get<IFreeSpanHistory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFreeSpanHistory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANHISTORY,
    payload: axios.get<IFreeSpanHistory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFreeSpanHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FREESPANHISTORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFreeSpanHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FREESPANHISTORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFreeSpanHistory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FREESPANHISTORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
