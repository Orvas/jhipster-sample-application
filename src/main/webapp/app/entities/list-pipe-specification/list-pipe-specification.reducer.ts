import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListPipeSpecification, defaultValue } from 'app/shared/model/list-pipe-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTPIPESPECIFICATION_LIST: 'listPipeSpecification/FETCH_LISTPIPESPECIFICATION_LIST',
  FETCH_LISTPIPESPECIFICATION: 'listPipeSpecification/FETCH_LISTPIPESPECIFICATION',
  CREATE_LISTPIPESPECIFICATION: 'listPipeSpecification/CREATE_LISTPIPESPECIFICATION',
  UPDATE_LISTPIPESPECIFICATION: 'listPipeSpecification/UPDATE_LISTPIPESPECIFICATION',
  DELETE_LISTPIPESPECIFICATION: 'listPipeSpecification/DELETE_LISTPIPESPECIFICATION',
  RESET: 'listPipeSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListPipeSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListPipeSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListPipeSpecificationState = initialState, action): ListPipeSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTPIPESPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTPIPESPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTPIPESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTPIPESPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTPIPESPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTPIPESPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPESPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTPIPESPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTPIPESPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTPIPESPECIFICATION):
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

const apiUrl = 'api/list-pipe-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListPipeSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPESPECIFICATION_LIST,
    payload: axios.get<IListPipeSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListPipeSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPESPECIFICATION,
    payload: axios.get<IListPipeSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListPipeSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTPIPESPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListPipeSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTPIPESPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListPipeSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTPIPESPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
