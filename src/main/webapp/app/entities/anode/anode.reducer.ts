import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAnode, defaultValue } from 'app/shared/model/anode.model';

export const ACTION_TYPES = {
  FETCH_ANODE_LIST: 'anode/FETCH_ANODE_LIST',
  FETCH_ANODE: 'anode/FETCH_ANODE',
  CREATE_ANODE: 'anode/CREATE_ANODE',
  UPDATE_ANODE: 'anode/UPDATE_ANODE',
  DELETE_ANODE: 'anode/DELETE_ANODE',
  RESET: 'anode/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAnode>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AnodeState = Readonly<typeof initialState>;

// Reducer

export default (state: AnodeState = initialState, action): AnodeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ANODE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ANODE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ANODE):
    case REQUEST(ACTION_TYPES.UPDATE_ANODE):
    case REQUEST(ACTION_TYPES.DELETE_ANODE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ANODE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ANODE):
    case FAILURE(ACTION_TYPES.CREATE_ANODE):
    case FAILURE(ACTION_TYPES.UPDATE_ANODE):
    case FAILURE(ACTION_TYPES.DELETE_ANODE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANODE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANODE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ANODE):
    case SUCCESS(ACTION_TYPES.UPDATE_ANODE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ANODE):
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

const apiUrl = 'api/anodes';

// Actions

export const getEntities: ICrudGetAllAction<IAnode> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ANODE_LIST,
    payload: axios.get<IAnode>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAnode> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ANODE,
    payload: axios.get<IAnode>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAnode> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ANODE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAnode> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ANODE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAnode> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ANODE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
