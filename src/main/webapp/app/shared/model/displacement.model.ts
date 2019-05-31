import { Moment } from 'moment';

export interface IDisplacement {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  displacementHistId?: number;
}

export const defaultValue: Readonly<IDisplacement> = {};
