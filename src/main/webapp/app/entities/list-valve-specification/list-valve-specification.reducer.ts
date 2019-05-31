import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListValveSpecification, defaultValue } from 'app/shared/model/list-valve-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTVALVESPECIFICATION_LIST: 'listValveSpecification/FETCH_LISTVALVESPECIFICATION_LIST',
  FETCH_LISTVALVESPECIFICATION: 'listValveSpecification/FETCH_LISTVALVESPECIFICATION',
  CREATE_LISTVALVESPECIFICATION: 'listValveSpecification/CREATE_LISTVALVESPECIFICATION',
  UPDATE_LISTVALVESPECIFICATION: 'listValveSpecification/UPDATE_LISTVALVESPECIFICATION',
  DELETE_LISTVALVESPECIFICATION: 'listValveSpecification/DELETE_LISTVALVESPECIFICATION',
  RESET: 'listValveSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListValveSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListValveSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListValveSpecificationState = initialState, action): ListValveSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTVALVESPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTVALVESPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTVALVESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTVALVESPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTVALVESPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTVALVESPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTVALVESPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTVALVESPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTVALVESPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTVALVESPECIFICATION):
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

const apiUrl = 'api/list-valve-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListValveSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVESPECIFICATION_LIST,
    payload: axios.get<IListValveSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListValveSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTVALVESPECIFICATION,
    payload: axios.get<IListValveSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListValveSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTVALVESPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListValveSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTVALVESPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListValveSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTVALVESPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
