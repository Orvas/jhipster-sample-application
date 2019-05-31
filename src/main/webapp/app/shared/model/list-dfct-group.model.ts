import { Moment } from 'moment';
import { IListDfctType } from 'app/shared/model/list-dfct-type.model';

export interface IListDfctGroup {
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
  listDfctTypes?: IListDfctType[];
}

export const defaultValue: Readonly<IListDfctGroup> = {};
