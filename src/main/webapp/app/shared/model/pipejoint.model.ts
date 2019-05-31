import { Moment } from 'moment';
import { IBendHist } from 'app/shared/model/bend-hist.model';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IPipejoint {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  bendHists?: IBendHist[];
  buckleArrestorHists?: IBuckleArrestorHist[];
  pipeHists?: IPipeHist[];
  pipejointHists?: IPipejointHist[];
  teeHists?: ITeeHist[];
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IPipejoint> = {};
