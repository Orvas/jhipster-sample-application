import { Moment } from 'moment';

export interface IValve {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  valveHistId?: number;
}

export const defaultValue: Readonly<IValve> = {};
