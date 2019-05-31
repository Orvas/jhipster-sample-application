import { Moment } from 'moment';
import { IBaseClass } from 'app/shared/model/base-class.model';

export interface IListObjectType {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  tableName?: string;
  tableNameHist?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClasses?: IBaseClass[];
}

export const defaultValue: Readonly<IListObjectType> = {};
