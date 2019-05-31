import { Moment } from 'moment';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';

export interface IPipe {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  pipeHists?: IPipeHist[];
}

export const defaultValue: Readonly<IPipe> = {};
