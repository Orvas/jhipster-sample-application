import { Moment } from 'moment';

export interface IFreeSpanSupport {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  freeSpanSupportHistId?: number;
}

export const defaultValue: Readonly<IFreeSpanSupport> = {};
