import { Moment } from 'moment';

export interface IBuckleArrestor {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  buckleArrestorHistId?: number;
}

export const defaultValue: Readonly<IBuckleArrestor> = {};
