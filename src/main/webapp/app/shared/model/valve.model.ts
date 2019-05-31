import { Moment } from 'moment';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IValve {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IValve> = {};
