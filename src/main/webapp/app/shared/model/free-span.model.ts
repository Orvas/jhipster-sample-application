import { Moment } from 'moment';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';

export interface IFreeSpan {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  freeSpanHists?: IFreeSpanHist[];
}

export const defaultValue: Readonly<IFreeSpan> = {};
