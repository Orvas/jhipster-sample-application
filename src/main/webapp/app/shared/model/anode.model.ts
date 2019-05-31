import { Moment } from 'moment';

export interface IAnode {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  anodeHistId?: number;
}

export const defaultValue: Readonly<IAnode> = {};
