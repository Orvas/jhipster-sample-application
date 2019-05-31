import { Moment } from 'moment';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';

export interface IListBucklarrSpecification {
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
  buckleArrestorHists?: IBuckleArrestorHist[];
}

export const defaultValue: Readonly<IListBucklarrSpecification> = {};
