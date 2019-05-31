import { Moment } from 'moment';
import { IListWrkStatus } from 'app/shared/model/list-wrk-status.model';

export interface IListWrkKind {
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
  listWrkStatuses?: IListWrkStatus[];
}

export const defaultValue: Readonly<IListWrkKind> = {};
