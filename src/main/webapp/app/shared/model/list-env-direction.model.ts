import { Moment } from 'moment';

export interface IListEnvDirection {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  degreeStart?: number;
  degreeEnd?: number;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IListEnvDirection> = {};
