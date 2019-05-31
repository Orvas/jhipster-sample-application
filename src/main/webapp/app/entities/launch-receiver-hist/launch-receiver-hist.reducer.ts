import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILaunchReceiverHist, defaultValue } from 'app/shared/model/launch-receiver-hist.model';

export const ACTION_TYPES = {
  FETCH_LAUNCHRECEIVERHIST_LIST: 'launchReceiverHist/FETCH_LAUNCHRECEIVERHIST_LIST',
  FETCH_LAUNCHRECEIVERHIST: 'launchReceiverHist/FETCH_LAUNCHRECEIVERHIST',
  CREATE_LAUNCHRECEIVERHIST: 'launchReceiverHist/CREATE_LAUNCHRECEIVERHIST',
  UPDATE_LAUNCHRECEIVERHIST: 'launchReceiverHist/UPDATE_LAUNCHRECEIVERHIST',
  DELETE_LAUNCHRECEIVERHIST: 'launchReceiverHist/DELETE_LAUNCHRECEIVERHIST',
  RESET: 'launchReceiverHist/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILaunchReceiverHist>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LaunchReceiverHistState = Readonly<typeof initialState>;

// Reducer

export default (state: LaunchReceiverHistState = initialState, action): LaunchReceiverHistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LAUNCHRECEIVERHIST):
    case REQUEST(ACTION_TYPES.UPDATE_LAUNCHRECEIVERHIST):
    case REQUEST(ACTION_TYPES.DELETE_LAUNCHRECEIVERHIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST):
    case FAILURE(ACTION_TYPES.CREATE_LAUNCHRECEIVERHIST):
    case FAILURE(ACTION_TYPES.UPDATE_LAUNCHRECEIVERHIST):
    case FAILURE(ACTION_TYPES.DELETE_LAUNCHRECEIVERHIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LAUNCHRECEIVERHIST):
    case SUCCESS(ACTION_TYPES.UPDATE_LAUNCHRECEIVERHIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LAUNCHRECEIVERHIST):
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

const apiUrl = 'api/launch-receiver-hists';

// Actions

export const getEntities: ICrudGetAllAction<ILaunchReceiverHist> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST_LIST,
    payload: axios.get<ILaunchReceiverHist>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILaunchReceiverHist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LAUNCHRECEIVERHIST,
    payload: axios.get<ILaunchReceiverHist>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILaunchReceiverHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LAUNCHRECEIVERHIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILaunchReceiverHist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LAUNCHRECEIVERHIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILaunchReceiverHist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LAUNCHRECEIVERHIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
