import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBendSpecification, defaultValue } from 'app/shared/model/list-bend-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTBENDSPECIFICATION_LIST: 'listBendSpecification/FETCH_LISTBENDSPECIFICATION_LIST',
  FETCH_LISTBENDSPECIFICATION: 'listBendSpecification/FETCH_LISTBENDSPECIFICATION',
  CREATE_LISTBENDSPECIFICATION: 'listBendSpecification/CREATE_LISTBENDSPECIFICATION',
  UPDATE_LISTBENDSPECIFICATION: 'listBendSpecification/UPDATE_LISTBENDSPECIFICATION',
  DELETE_LISTBENDSPECIFICATION: 'listBendSpecification/DELETE_LISTBENDSPECIFICATION',
  RESET: 'listBendSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBendSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBendSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBendSpecificationState = initialState, action): ListBendSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBENDSPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBENDSPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTBENDSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTBENDSPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBENDSPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTBENDSPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBENDSPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBENDSPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBENDSPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBENDSPECIFICATION):
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

const apiUrl = 'api/list-bend-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListBendSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDSPECIFICATION_LIST,
    payload: axios.get<IListBendSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBendSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBENDSPECIFICATION,
    payload: axios.get<IListBendSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBendSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBENDSPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBendSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBENDSPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBendSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBENDSPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
