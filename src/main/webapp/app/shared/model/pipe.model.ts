import { Moment } from 'moment';

export interface IPipe {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  pipeHistId?: number;
}

export const defaultValue: Readonly<IPipe> = {};
