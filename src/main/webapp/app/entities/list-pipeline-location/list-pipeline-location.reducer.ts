import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IListPipelineLocation, defaultValue } from 'app/shared/model/list-pipeline-location.model';

export const ACTION_TYPES = {
  FETCH_LISTPIPELINELOCATION_LIST: 'listPipelineLocation/FETCH_LISTPIPELINELOCATION_LIST',
  FETCH_LISTPIPELINELOCATION: 'listPipelineLocation/FETCH_LISTPIPELINELOCATION',
  CREATE_LISTPIPELINELOCATION: 'listPipelineLocation/CREATE_LISTPIPELINELOCATION',
  UPDATE_LISTPIPELINELOCATION: 'listPipelineLocation/UPDATE_LISTPIPELINELOCATION',
  DELETE_LISTPIPELINELOCATION: 'listPipelineLocation/DELETE_LISTPIPELINELOCATION',
  RESET: 'listPipelineLocation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IListPipelineLocation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ListPipelineLocationState = Readonly<typeof initialState>;

// Reducer

export default (state: ListPipelineLocationState = initialState, action): ListPipelineLocationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPELINELOCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LISTPIPELINELOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LISTPIPELINELOCATION):
    case REQUEST(ACTION_TYPES.UPDATE_LISTPIPELINELOCATION):
    case REQUEST(ACTION_TYPES.DELETE_LISTPIPELINELOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPELINELOCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LISTPIPELINELOCATION):
    case FAILURE(ACTION_TYPES.CREATE_LISTPIPELINELOCATION):
    case FAILURE(ACTION_TYPES.UPDATE_LISTPIPELINELOCATION):
    case FAILURE(ACTION_TYPES.DELETE_LISTPIPELINELOCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPELINELOCATION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LISTPIPELINELOCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LISTPIPELINELOCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LISTPIPELINELOCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LISTPIPELINELOCATION):
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

const apiUrl = 'api/list-pipeline-locations';

// Actions

export const getEntities: ICrudGetAllAction<IListPipelineLocation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPELINELOCATION_LIST,
    payload: axios.get<IListPipelineLocation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IListPipelineLocation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LISTPIPELINELOCATION,
    payload: axios.get<IListPipelineLocation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IListPipelineLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LISTPIPELINELOCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IListPipelineLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LISTPIPELINELOCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IListPipelineLocation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LISTPIPELINELOCATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
