import { Moment } from 'moment';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';

export interface IFreeSpanSupport {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  freeSpanSupportHists?: IFreeSpanSupportHist[];
}

export const defaultValue: Readonly<IFreeSpanSupport> = {};
