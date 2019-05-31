import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListTeeSpecification, defaultValue } from 'app/shared/model/list-tee-specification.model';

export const ACTION_TYPES = {
  FETCH_LISTTEESPECIFICATION_LIST: 'listTeeSpecification/FETCH_LISTTEESPECIFICATION_LIST',
  FETCH_LISTTEESPECIFICATION: 'listTeeSpecification/FETCH_LISTTEESPECIFICATION',
  CREATE_LISTTEESPECIFICATION: 'listTeeSpecification/CREATE_LISTTEESPECIFICATION',
  UPDATE_LISTTEESPECIFICATION: 'listTeeSpecification/UPDATE_LISTTEESPECIFICATION',
  DELETE_LISTTEESPECIFICATION: 'listTeeSpecification/DELETE_LISTTEESPECIFICATION',
  RESET: 'listTeeSpecification/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListTeeSpecification>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListTeeSpecificationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListTeeSpecificationState = initialState, action): ListTeeSpecificationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTTEESPECIFICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTTEESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTTEESPECIFICATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTTEESPECIFICATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTTEESPECIFICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTTEESPECIFICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTTEESPECIFICATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTTEESPECIFICATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTTEESPECIFICATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTTEESPECIFICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEESPECIFICATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTTEESPECIFICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTTEESPECIFICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTTEESPECIFICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTTEESPECIFICATION):
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

const apiUrl = 'api/list-tee-specifications';

// Actions

export const getEntities: ICrudGetAllAction<IListTeeSpecification> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEESPECIFICATION_LIST,
    payload: axios.get<IListTeeSpecification>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListTeeSpecification> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTTEESPECIFICATION,
    payload: axios.get<IListTeeSpecification>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListTeeSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTTEESPECIFICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListTeeSpecification> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTTEESPECIFICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListTeeSpecification> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTTEESPECIFICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
