import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListPipejointSpecification, defaultValue } from 'app/shared/model/list-pipejoint-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTPIPEJOINTSPECIFICATION_LIST: 'listPipejointSpecification/FETCH_LISTPIPEJOINTSPECIFICATION_LIST',
  FETCH_LISTPIPEJOINTSPECIFICATION: 'listPipejointSpecification/FETCH_LISTPIPEJOINTSPECIFICATION',
  CREATE_LISTPIPEJOINTSPECIFICATION: 'listPipejointSpecification/CREATE_LISTPIPEJOINTSPECIFICATION',
  UPDATE_LISTPIPEJOINTSPECIFICATION: 'listPipejointSpecification/UPDATE_LISTPIPEJOINTSPECIFICATION',
  DELETE_LISTPIPEJOINTSPECIFICATION: 'listPipejointSpecification/DELETE_LISTPIPEJOINTSPECIFICATION',
  RESET: 'listPipejointSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListPipejointSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListPipejointSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListPipejointSpecificationState = initialState, action): ListPipejointSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTPIPEJOINTSPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTPIPEJOINTSPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTPIPEJOINTSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTPIPEJOINTSPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTPIPEJOINTSPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTPIPEJOINTSPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTPIPEJOINTSPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTPIPEJOINTSPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTPIPEJOINTSPECIFICATION):
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

const apiUrl = 'api/list-pipejoint-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListPipejointSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION_LIST,
    payload: axios.get<IListPipejointSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListPipejointSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPEJOINTSPECIFICATION,
    payload: axios.get<IListPipejointSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListPipejointSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTPIPEJOINTSPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListPipejointSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTPIPEJOINTSPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListPipejointSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTPIPEJOINTSPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
