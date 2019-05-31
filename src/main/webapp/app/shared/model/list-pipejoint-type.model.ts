import { Moment } from 'moment';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';

export interface IListPipejointType {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  pipejointHists?: IPipejointHist[];
}

export const defaultValue: Readonly<IListPipejointType> = {};
