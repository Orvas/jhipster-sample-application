import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListEffAxforceUcase, defaultValue } from 'app/shared/model/list-eff-axforce-ucase.model';

export const ACTION_TYPES = {
  FETCH_LISTEFFAXFORCEUCASE_LIST: 'listEffAxforceUcase/FETCH_LISTEFFAXFORCEUCASE_LIST',
  FETCH_LISTEFFAXFORCEUCASE: 'listEffAxforceUcase/FETCH_LISTEFFAXFORCEUCASE',
  CREATE_LISTEFFAXFORCEUCASE: 'listEffAxforceUcase/CREATE_LISTEFFAXFORCEUCASE',
  UPDATE_LISTEFFAXFORCEUCASE: 'listEffAxforceUcase/UPDATE_LISTEFFAXFORCEUCASE',
  DELETE_LISTEFFAXFORCEUCASE: 'listEffAxforceUcase/DELETE_LISTEFFAXFORCEUCASE',
  RESET: 'listEffAxforceUcase/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListEffAxforceUcase>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListEffAxforceUcaseState = Readonly<typeof initialState>;

// Reducer

export default (state: ListEffAxforceUcaseState = initialState, action): ListEffAxforceUcaseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTEFFAXFORCEUCASE):
    case REQUEST(ACTION_TYPES.UPDATE_LISTEFFAXFORCEUCASE):
    case REQUEST(ACTION_TYPES.DELETE_LISTEFFAXFORCEUCASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE):
    case FAILURE(ACTION_TYPES.CREATE_LISTEFFAXFORCEUCASE):
    case FAILURE(ACTION_TYPES.UPDATE_LISTEFFAXFORCEUCASE):
    case FAILURE(ACTION_TYPES.DELETE_LISTEFFAXFORCEUCASE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTEFFAXFORCEUCASE):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTEFFAXFORCEUCASE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTEFFAXFORCEUCASE):
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

const apiUrl = 'api/list-eff-axforce-ucases';

// Actions

export const getEntities: ICrudGetAllAction<IListEffAxforceUcase> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE_LIST,
    payload: axios.get<IListEffAxforceUcase>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListEffAxforceUcase> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTEFFAXFORCEUCASE,
    payload: axios.get<IListEffAxforceUcase>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListEffAxforceUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTEFFAXFORCEUCASE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListEffAxforceUcase> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTEFFAXFORCEUCASE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListEffAxforceUcase> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTEFFAXFORCEUCASE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
