import { Moment } from 'moment';
import { ITeeHist } from 'app/shared/model/tee-hist.model';

export interface IListTeeType {
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
  teeHists?: ITeeHist[];
}

export const defaultValue: Readonly<IListTeeType> = {};
