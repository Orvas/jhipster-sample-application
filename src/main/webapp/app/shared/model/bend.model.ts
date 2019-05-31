import { Moment } from 'moment';
import { IBendHist } from 'app/shared/model/bend-hist.model';

export interface IBend {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  bendHists?: IBendHist[];
}

export const defaultValue: Readonly<IBend> = {};
