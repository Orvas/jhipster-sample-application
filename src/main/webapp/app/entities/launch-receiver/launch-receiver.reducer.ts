import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILaunchReceiver, defaultValue } from 'app/shared/model/launch-receiver.model';

export const ACTION_TYPES = {
  FETCH_LAUNCHRECEIVER_LIST: 'launchReceiver/FETCH_LAUNCHRECEIVER_LIST',
  FETCH_LAUNCHRECEIVER: 'launchReceiver/FETCH_LAUNCHRECEIVER',
  CREATE_LAUNCHRECEIVER: 'launchReceiver/CREATE_LAUNCHRECEIVER',
  UPDATE_LAUNCHRECEIVER: 'launchReceiver/UPDATE_LAUNCHRECEIVER',
  DELETE_LAUNCHRECEIVER: 'launchReceiver/DELETE_LAUNCHRECEIVER',
  RESET: 'launchReceiver/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILaunchReceiver>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LaunchReceiverState = Readonly<typeof initialState>;

// Reducer

export default (state: LaunchReceiverState = initialState, action): LaunchReceiverState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LAUNCHRECEIVER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LAUNCHRECEIVER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LAUNCHRECEIVER):
    case REQUEST(ACTION_TYPES.UPDATE_LAUNCHRECEIVER):
    case REQUEST(ACTION_TYPES.DELETE_LAUNCHRECEIVER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LAUNCHRECEIVER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LAUNCHRECEIVER):
    case FAILURE(ACTION_TYPES.CREATE_LAUNCHRECEIVER):
    case FAILURE(ACTION_TYPES.UPDATE_LAUNCHRECEIVER):
    case FAILURE(ACTION_TYPES.DELETE_LAUNCHRECEIVER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LAUNCHRECEIVER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LAUNCHRECEIVER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LAUNCHRECEIVER):
    case SUCCESS(ACTION_TYPES.UPDATE_LAUNCHRECEIVER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LAUNCHRECEIVER):
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

const apiUrl = 'api/launch-receivers';

// Actions

export const getEntities: ICrudGetAllAction<ILaunchReceiver> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LAUNCHRECEIVER_LIST,
    payload: axios.get<ILaunchReceiver>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILaunchReceiver> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LAUNCHRECEIVER,
    payload: axios.get<ILaunchReceiver>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILaunchReceiver> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LAUNCHRECEIVER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILaunchReceiver> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LAUNCHRECEIVER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILaunchReceiver> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LAUNCHRECEIVER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
