import { Moment } from 'moment';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IListValveType {
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
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IListValveType> = {};
