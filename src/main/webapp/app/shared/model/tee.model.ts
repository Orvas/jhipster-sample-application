import { Moment } from 'moment';
import { ITeeHist } from 'app/shared/model/tee-hist.model';

export interface ITee {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  teeHists?: ITeeHist[];
}

export const defaultValue: Readonly<ITee> = {};
