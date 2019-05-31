import { Moment } from 'moment';

export interface IFreeSpan {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  freeSpanHistId?: number;
}

export const defaultValue: Readonly<IFreeSpan> = {};
