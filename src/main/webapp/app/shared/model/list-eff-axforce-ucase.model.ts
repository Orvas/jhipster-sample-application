import { Moment } from 'moment';

export interface IListEffAxforceUcase {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IListEffAxforceUcase> = {};
