import { Moment } from 'moment';
import { IDisplacementHist } from 'app/shared/model/displacement-hist.model';

export interface IDisplacement {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  displacementHists?: IDisplacementHist[];
}

export const defaultValue: Readonly<IDisplacement> = {};
