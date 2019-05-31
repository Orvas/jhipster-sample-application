import { Moment } from 'moment';

export interface IMetaList {
  id?: number;
  name?: string;
  schemaName?: string;
  tableName?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IMetaList> = {};
