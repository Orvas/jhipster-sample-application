import { Moment } from 'moment';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';

export interface IListWrkStatus {
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
  idWrkKindId?: number;
  anodeHists?: IAnodeHist[];
}

export const defaultValue: Readonly<IListWrkStatus> = {};
