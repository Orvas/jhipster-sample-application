import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListMinpressUcase, defaultValue } from 'app/shared/model/list-minpress-ucase.model';

export const ACTION_TYPES = {
  FETCH_LISTMINPRESSUCASE_LIST: 'listMinpressUcase/FETCH_LISTMINPRESSUCASE_LIST',
  FETCH_LISTMINPRESSUCASE: 'listMinpressUcase/FETCH_LISTMINPRESSUCASE',
  CREATE_LISTMINPRESSUCASE: 'listMinpressUcase/CREATE_LISTMINPRESSUCASE',
  UPDATE_LISTMINPRESSUCASE: 'listMinpressUcase/UPDATE_LISTMINPRESSUCASE',
  DELETE_LISTMINPRESSUCASE: 'listMinpressUcase/DELETE_LISTMINPRESSUCASE',
  RESET: 'listMinpressUcase/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListMinpressUcase>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListMinpressUcaseState = Readonly<typeof initialState>;

// Reducer

export default (state: ListMinpressUcaseState = initialState, action): ListMinpressUcaseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTMINPRESSUCASE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTMINPRESSUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTMINPRESSUCASE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTMINPRESSUCASE):
    case REQUEST(ACTION_TYPES.DELETE_LISTMINPRESSUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTMINPRESSUCASE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTMINPRESSUCASE):
    case FAILURE(ACTION_TYPES.CREATE_LISTMINPRESSUCASE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTMINPRESSUCASE):
    case FAILURE(ACTION_TYPES.DELETE_LISTMINPRESSUCASE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTMINPRESSUCASE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTMINPRESSUCASE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTMINPRESSUCASE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTMINPRESSUCASE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTMINPRESSUCASE):
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

const apiUrl = 'api/list-minpress-ucases';

// Actions

export const getEntities: ICrudGetAllAction<IListMinpressUcase> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTMINPRESSUCASE_LIST,
    payload: axios.get<IListMinpressUcase>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListMinpressUcase> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTMINPRESSUCASE,
    payload: axios.get<IListMinpressUcase>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListMinpressUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTMINPRESSUCASE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListMinpressUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTMINPRESSUCASE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListMinpressUcase> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTMINPRESSUCASE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
