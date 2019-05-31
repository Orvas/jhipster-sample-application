import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBucklarrSpecification, defaultValue } from 'app/shared/model/list-bucklarr-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTBUCKLARRSPECIFICATION_LIST: 'listBucklarrSpecification/FETCH_LISTBUCKLARRSPECIFICATION_LIST',
  FETCH_LISTBUCKLARRSPECIFICATION: 'listBucklarrSpecification/FETCH_LISTBUCKLARRSPECIFICATION',
  CREATE_LISTBUCKLARRSPECIFICATION: 'listBucklarrSpecification/CREATE_LISTBUCKLARRSPECIFICATION',
  UPDATE_LISTBUCKLARRSPECIFICATION: 'listBucklarrSpecification/UPDATE_LISTBUCKLARRSPECIFICATION',
  DELETE_LISTBUCKLARRSPECIFICATION: 'listBucklarrSpecification/DELETE_LISTBUCKLARRSPECIFICATION',
  RESET: 'listBucklarrSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBucklarrSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBucklarrSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBucklarrSpecificationState = initialState, action): ListBucklarrSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBUCKLARRSPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBUCKLARRSPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTBUCKLARRSPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTBUCKLARRSPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBUCKLARRSPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTBUCKLARRSPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBUCKLARRSPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBUCKLARRSPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBUCKLARRSPECIFICATION):
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

const apiUrl = 'api/list-bucklarr-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListBucklarrSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION_LIST,
    payload: axios.get<IListBucklarrSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBucklarrSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBUCKLARRSPECIFICATION,
    payload: axios.get<IListBucklarrSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBucklarrSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBUCKLARRSPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBucklarrSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBUCKLARRSPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBucklarrSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBUCKLARRSPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
