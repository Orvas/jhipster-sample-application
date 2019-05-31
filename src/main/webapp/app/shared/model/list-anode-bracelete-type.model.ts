import { Moment } from 'moment';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';

export interface IListAnodeBraceleteType {
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
  anodeHists?: IAnodeHist[];
}

export const defaultValue: Readonly<IListAnodeBraceleteType> = {};
