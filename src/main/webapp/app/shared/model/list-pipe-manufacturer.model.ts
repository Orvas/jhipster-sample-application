import { Moment } from 'moment';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';

export interface IListPipeManufacturer {
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
  pipeHists?: IPipeHist[];
}

export const defaultValue: Readonly<IListPipeManufacturer> = {};
