import { Moment } from 'moment';
import { IListThreat } from 'app/shared/model/list-threat.model';

export interface IListThreatGroup {
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
  listThreats?: IListThreat[];
}

export const defaultValue: Readonly<IListThreatGroup> = {};
