import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFreeSpanSupport, defaultValue } from 'app/shared/model/free-span-support.model';

export const ACTION_TYPES = {
  FETCH_FREESPANSUPPORT_LIST: 'freeSpanSupport/FETCH_FREESPANSUPPORT_LIST',
  FETCH_FREESPANSUPPORT: 'freeSpanSupport/FETCH_FREESPANSUPPORT',
  CREATE_FREESPANSUPPORT: 'freeSpanSupport/CREATE_FREESPANSUPPORT',
  UPDATE_FREESPANSUPPORT: 'freeSpanSupport/UPDATE_FREESPANSUPPORT',
  DELETE_FREESPANSUPPORT: 'freeSpanSupport/DELETE_FREESPANSUPPORT',
  RESET: 'freeSpanSupport/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFreeSpanSupport>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type FreeSpanSupportState = Readonly<typeof initialState>;

// Reducer

export default (state: FreeSpanSupportState = initialState, action): FreeSpanSupportState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FREESPANSUPPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FREESPANSUPPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FREESPANSUPPORT):
    case REQUEST(ACTION_TYPES.UPDATE_FREESPANSUPPORT):
    case REQUEST(ACTION_TYPES.DELETE_FREESPANSUPPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FREESPANSUPPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FREESPANSUPPORT):
    case FAILURE(ACTION_TYPES.CREATE_FREESPANSUPPORT):
    case FAILURE(ACTION_TYPES.UPDATE_FREESPANSUPPORT):
    case FAILURE(ACTION_TYPES.DELETE_FREESPANSUPPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANSUPPORT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FREESPANSUPPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FREESPANSUPPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_FREESPANSUPPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FREESPANSUPPORT):
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

const apiUrl = 'api/free-span-supports';

// Actions

export const getEntities: ICrudGetAllAction<IFreeSpanSupport> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANSUPPORT_LIST,
    payload: axios.get<IFreeSpanSupport>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IFreeSpanSupport> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FREESPANSUPPORT,
    payload: axios.get<IFreeSpanSupport>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFreeSpanSupport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FREESPANSUPPORT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFreeSpanSupport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FREESPANSUPPORT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFreeSpanSupport> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FREESPANSUPPORT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
