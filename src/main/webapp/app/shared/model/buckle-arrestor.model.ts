import { Moment } from 'moment';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';

export interface IBuckleArrestor {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  buckleArrestorHists?: IBuckleArrestorHist[];
}

export const defaultValue: Readonly<IBuckleArrestor> = {};
