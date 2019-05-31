import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListExternalCoating, defaultValue } from 'app/shared/model/list-external-coating.model';

export const ACTION_TYPES = {
  FETCH_LISTEXTERNALCOATING_LIST: 'listExternalCoating/FETCH_LISTEXTERNALCOATING_LIST',
  FETCH_LISTEXTERNALCOATING: 'listExternalCoating/FETCH_LISTEXTERNALCOATING',
  CREATE_LISTEXTERNALCOATING: 'listExternalCoating/CREATE_LISTEXTERNALCOATING',
  UPDATE_LISTEXTERNALCOATING: 'listExternalCoating/UPDATE_LISTEXTERNALCOATING',
  DELETE_LISTEXTERNALCOATING: 'listExternalCoating/DELETE_LISTEXTERNALCOATING',
  RESET: 'listExternalCoating/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListExternalCoating>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListExternalCoatingState = Readonly<typeof initialState>;

// Reducer

export default (state: ListExternalCoatingState = initialState, action): ListExternalCoatingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTEXTERNALCOATING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTEXTERNALCOATING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTEXTERNALCOATING):
    case REQUEST(ACTION_TYPES.UPDATE_LISTEXTERNALCOATING):
    case REQUEST(ACTION_TYPES.DELETE_LISTEXTERNALCOATING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTEXTERNALCOATING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTEXTERNALCOATING):
    case FAILURE(ACTION_TYPES.CREATE_LISTEXTERNALCOATING):
    case FAILURE(ACTION_TYPES.UPDATE_LISTEXTERNALCOATING):
    case FAILURE(ACTION_TYPES.DELETE_LISTEXTERNALCOATING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTEXTERNALCOATING_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTEXTERNALCOATING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTEXTERNALCOATING):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTEXTERNALCOATING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTEXTERNALCOATING):
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

const apiUrl = 'api/list-external-coatings';

// Actions

export const getEntities: ICrudGetAllAction<IListExternalCoating> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTEXTERNALCOATING_LIST,
    payload: axios.get<IListExternalCoating>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListExternalCoating> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTEXTERNALCOATING,
    payload: axios.get<IListExternalCoating>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListExternalCoating> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTEXTERNALCOATING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListExternalCoating> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTEXTERNALCOATING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListExternalCoating> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTEXTERNALCOATING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
