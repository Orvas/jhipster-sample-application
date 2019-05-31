import { Moment } from 'moment';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';

export interface IAnode {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  anodeHists?: IAnodeHist[];
}

export const defaultValue: Readonly<IAnode> = {};
