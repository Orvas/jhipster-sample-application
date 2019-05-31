import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListBoundaryCondUcase, defaultValue } from 'app/shared/model/list-boundary-cond-ucase.model';

export const ACTION_TYPES = {
  FETCH_LISTBOUNDARYCONDUCASE_LIST: 'listBoundaryCondUcase/FETCH_LISTBOUNDARYCONDUCASE_LIST',
  FETCH_LISTBOUNDARYCONDUCASE: 'listBoundaryCondUcase/FETCH_LISTBOUNDARYCONDUCASE',
  CREATE_LISTBOUNDARYCONDUCASE: 'listBoundaryCondUcase/CREATE_LISTBOUNDARYCONDUCASE',
  UPDATE_LISTBOUNDARYCONDUCASE: 'listBoundaryCondUcase/UPDATE_LISTBOUNDARYCONDUCASE',
  DELETE_LISTBOUNDARYCONDUCASE: 'listBoundaryCondUcase/DELETE_LISTBOUNDARYCONDUCASE',
  RESET: 'listBoundaryCondUcase/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListBoundaryCondUcase>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListBoundaryCondUcaseState = Readonly<typeof initialState>;

// Reducer

export default (state: ListBoundaryCondUcaseState = initialState, action): ListBoundaryCondUcaseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTBOUNDARYCONDUCASE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTBOUNDARYCONDUCASE):
    case REQUEST(ACTION_TYPES.DELETE_LISTBOUNDARYCONDUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE):
    case FAILURE(ACTION_TYPES.CREATE_LISTBOUNDARYCONDUCASE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTBOUNDARYCONDUCASE):
    case FAILURE(ACTION_TYPES.DELETE_LISTBOUNDARYCONDUCASE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTBOUNDARYCONDUCASE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTBOUNDARYCONDUCASE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTBOUNDARYCONDUCASE):
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

const apiUrl = 'api/list-boundary-cond-ucases';

// Actions

export const getEntities: ICrudGetAllAction<IListBoundaryCondUcase> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE_LIST,
    payload: axios.get<IListBoundaryCondUcase>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListBoundaryCondUcase> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTBOUNDARYCONDUCASE,
    payload: axios.get<IListBoundaryCondUcase>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListBoundaryCondUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTBOUNDARYCONDUCASE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListBoundaryCondUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTBOUNDARYCONDUCASE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListBoundaryCondUcase> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTBOUNDARYCONDUCASE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
