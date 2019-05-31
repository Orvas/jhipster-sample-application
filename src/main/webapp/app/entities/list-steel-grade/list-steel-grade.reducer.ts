import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListSteelGrade, defaultValue } from 'app/shared/model/list-steel-grade.model';

export const ACTION_TYPES = {
  FETCH_LISTSTEELGRADE_LIST: 'listSteelGrade/FETCH_LISTSTEELGRADE_LIST',
  FETCH_LISTSTEELGRADE: 'listSteelGrade/FETCH_LISTSTEELGRADE',
  CREATE_LISTSTEELGRADE: 'listSteelGrade/CREATE_LISTSTEELGRADE',
  UPDATE_LISTSTEELGRADE: 'listSteelGrade/UPDATE_LISTSTEELGRADE',
  DELETE_LISTSTEELGRADE: 'listSteelGrade/DELETE_LISTSTEELGRADE',
  RESET: 'listSteelGrade/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListSteelGrade>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListSteelGradeState = Readonly<typeof initialState>;

// Reducer

export default (state: ListSteelGradeState = initialState, action): ListSteelGradeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTSTEELGRADE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTSTEELGRADE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTSTEELGRADE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTSTEELGRADE):
    case REQUEST(ACTION_TYPES.DELETE_LISTSTEELGRADE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTSTEELGRADE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTSTEELGRADE):
    case FAILURE(ACTION_TYPES.CREATE_LISTSTEELGRADE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTSTEELGRADE):
    case FAILURE(ACTION_TYPES.DELETE_LISTSTEELGRADE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSTEELGRADE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTSTEELGRADE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTSTEELGRADE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTSTEELGRADE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTSTEELGRADE):
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

const apiUrl = 'api/list-steel-grades';

// Actions

export const getEntities: ICrudGetAllAction<IListSteelGrade> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSTEELGRADE_LIST,
    payload: axios.get<IListSteelGrade>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListSteelGrade> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTSTEELGRADE,
    payload: axios.get<IListSteelGrade>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListSteelGrade> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTSTEELGRADE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListSteelGrade> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTSTEELGRADE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListSteelGrade> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTSTEELGRADE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
