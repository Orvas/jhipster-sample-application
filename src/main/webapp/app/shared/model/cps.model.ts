import { Moment } from 'moment';
import { ICpsRange } from 'app/shared/model/cps-range.model';

export interface ICps {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  cpsHistId?: number;
  cpsRanges?: ICpsRange[];
}

export const defaultValue: Readonly<ICps> = {};
