import { Moment } from 'moment';
import { ICpsHist } from 'app/shared/model/cps-hist.model';
import { ICpsRange } from 'app/shared/model/cps-range.model';

export interface ICps {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  cpsHists?: ICpsHist[];
  cpsRanges?: ICpsRange[];
}

export const defaultValue: Readonly<ICps> = {};
