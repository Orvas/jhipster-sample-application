import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDisplacement, defaultValue } from 'app/shared/model/displacement.model';

export const ACTION_TYPES = {
  FETCH_DISPLACEMENT_LIST: 'displacement/FETCH_DISPLACEMENT_LIST',
  FETCH_DISPLACEMENT: 'displacement/FETCH_DISPLACEMENT',
  CREATE_DISPLACEMENT: 'displacement/CREATE_DISPLACEMENT',
  UPDATE_DISPLACEMENT: 'displacement/UPDATE_DISPLACEMENT',
  DELETE_DISPLACEMENT: 'displacement/DELETE_DISPLACEMENT',
  RESET: 'displacement/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDisplacement>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DisplacementState = Readonly<typeof initialState>;

// Reducer

export default (state: DisplacementState = initialState, action): DisplacementState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DISPLACEMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DISPLACEMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DISPLACEMENT):
    case REQUEST(ACTION_TYPES.UPDATE_DISPLACEMENT):
    case REQUEST(ACTION_TYPES.DELETE_DISPLACEMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DISPLACEMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DISPLACEMENT):
    case FAILURE(ACTION_TYPES.CREATE_DISPLACEMENT):
    case FAILURE(ACTION_TYPES.UPDATE_DISPLACEMENT):
    case FAILURE(ACTION_TYPES.DELETE_DISPLACEMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPLACEMENT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPLACEMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DISPLACEMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_DISPLACEMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DISPLACEMENT):
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

const apiUrl = 'api/displacements';

// Actions

export const getEntities: ICrudGetAllAction<IDisplacement> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DISPLACEMENT_LIST,
    payload: axios.get<IDisplacement>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDisplacement> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DISPLACEMENT,
    payload: axios.get<IDisplacement>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDisplacement> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DISPLACEMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDisplacement> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DISPLACEMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDisplacement> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DISPLACEMENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
