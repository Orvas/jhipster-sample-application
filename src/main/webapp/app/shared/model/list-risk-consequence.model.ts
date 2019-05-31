import { Moment } from 'moment';

export interface IListRiskConsequence {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  safety?: string;
  commerImpact?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IListRiskConsequence> = {};
