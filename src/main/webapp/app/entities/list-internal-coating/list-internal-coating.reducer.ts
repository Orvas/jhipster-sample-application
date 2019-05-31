import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListInternalCoating, defaultValue } from 'app/shared/model/list-internal-coating.model';

export const ACTION_TYPES = {
  FETCH_LISTINTERNALCOATING_LIST: 'listInternalCoating/FETCH_LISTINTERNALCOATING_LIST',
  FETCH_LISTINTERNALCOATING: 'listInternalCoating/FETCH_LISTINTERNALCOATING',
  CREATE_LISTINTERNALCOATING: 'listInternalCoating/CREATE_LISTINTERNALCOATING',
  UPDATE_LISTINTERNALCOATING: 'listInternalCoating/UPDATE_LISTINTERNALCOATING',
  DELETE_LISTINTERNALCOATING: 'listInternalCoating/DELETE_LISTINTERNALCOATING',
  RESET: 'listInternalCoating/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListInternalCoating>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListInternalCoatingState = Readonly<typeof initialState>;

// Reducer

export default (state: ListInternalCoatingState = initialState, action): ListInternalCoatingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTINTERNALCOATING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTINTERNALCOATING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTINTERNALCOATING):
    case REQUEST(ACTION_TYPES.UPDATE_LISTINTERNALCOATING):
    case REQUEST(ACTION_TYPES.DELETE_LISTINTERNALCOATING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTINTERNALCOATING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTINTERNALCOATING):
    case FAILURE(ACTION_TYPES.CREATE_LISTINTERNALCOATING):
    case FAILURE(ACTION_TYPES.UPDATE_LISTINTERNALCOATING):
    case FAILURE(ACTION_TYPES.DELETE_LISTINTERNALCOATING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTINTERNALCOATING_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTINTERNALCOATING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTINTERNALCOATING):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTINTERNALCOATING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTINTERNALCOATING):
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

const apiUrl = 'api/list-internal-coatings';

// Actions

export const getEntities: ICrudGetAllAction<IListInternalCoating> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTINTERNALCOATING_LIST,
    payload: axios.get<IListInternalCoating>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListInternalCoating> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTINTERNALCOATING,
    payload: axios.get<IListInternalCoating>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListInternalCoating> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTINTERNALCOATING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListInternalCoating> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTINTERNALCOATING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListInternalCoating> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTINTERNALCOATING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
