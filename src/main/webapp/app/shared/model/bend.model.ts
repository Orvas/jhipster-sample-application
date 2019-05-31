import { Moment } from 'moment';

export interface IBend {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  bendHistId?: number;
}

export const defaultValue: Readonly<IBend> = {};
