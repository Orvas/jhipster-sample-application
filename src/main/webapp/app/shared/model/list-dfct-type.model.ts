import { Moment } from 'moment';

export interface IListDfctType {
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
  idGroupId?: number;
}

export const defaultValue: Readonly<IListDfctType> = {};
