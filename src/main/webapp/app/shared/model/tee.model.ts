import { Moment } from 'moment';

export interface ITee {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  teeHistId?: number;
}

export const defaultValue: Readonly<ITee> = {};
