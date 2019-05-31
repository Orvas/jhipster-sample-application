import { Moment } from 'moment';
import { IBendHist } from 'app/shared/model/bend-hist.model';

export interface IListBendManufacturer {
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
  bendHists?: IBendHist[];
}

export const defaultValue: Readonly<IListBendManufacturer> = {};
