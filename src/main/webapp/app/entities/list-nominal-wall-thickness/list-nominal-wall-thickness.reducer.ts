import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListNominalWallThickness, defaultValue } from 'app/shared/model/list-nominal-wall-thickness.model';

export const ACTION_TYPES = {
  FETCH_LISTNOMINALWALLTHICKNESS_LIST: 'listNominalWallThickness/FETCH_LISTNOMINALWALLTHICKNESS_LIST',
  FETCH_LISTNOMINALWALLTHICKNESS: 'listNominalWallThickness/FETCH_LISTNOMINALWALLTHICKNESS',
  CREATE_LISTNOMINALWALLTHICKNESS: 'listNominalWallThickness/CREATE_LISTNOMINALWALLTHICKNESS',
  UPDATE_LISTNOMINALWALLTHICKNESS: 'listNominalWallThickness/UPDATE_LISTNOMINALWALLTHICKNESS',
  DELETE_LISTNOMINALWALLTHICKNESS: 'listNominalWallThickness/DELETE_LISTNOMINALWALLTHICKNESS',
  RESET: 'listNominalWallThickness/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListNominalWallThickness>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListNominalWallThicknessState = Readonly<typeof initialState>;

// Reducer

export default (state: ListNominalWallThicknessState = initialState, action): ListNominalWallThicknessState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTNOMINALWALLTHICKNESS):
    case REQUEST(ACTION_TYPES.UPDATE_LISTNOMINALWALLTHICKNESS):
    case REQUEST(ACTION_TYPES.DELETE_LISTNOMINALWALLTHICKNESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS):
    case FAILURE(ACTION_TYPES.CREATE_LISTNOMINALWALLTHICKNESS):
    case FAILURE(ACTION_TYPES.UPDATE_LISTNOMINALWALLTHICKNESS):
    case FAILURE(ACTION_TYPES.DELETE_LISTNOMINALWALLTHICKNESS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTNOMINALWALLTHICKNESS):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTNOMINALWALLTHICKNESS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTNOMINALWALLTHICKNESS):
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

const apiUrl = 'api/list-nominal-wall-thicknesses';

// Actions

export const getEntities: ICrudGetAllAction<IListNominalWallThickness> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS_LIST,
    payload: axios.get<IListNominalWallThickness>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListNominalWallThickness> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTNOMINALWALLTHICKNESS,
    payload: axios.get<IListNominalWallThickness>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListNominalWallThickness> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTNOMINALWALLTHICKNESS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListNominalWallThickness> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTNOMINALWALLTHICKNESS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListNominalWallThickness> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTNOMINALWALLTHICKNESS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
