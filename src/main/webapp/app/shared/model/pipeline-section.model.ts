import { Moment } from 'moment';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';
import { IBendHist } from 'app/shared/model/bend-hist.model';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
import { ICpsHist } from 'app/shared/model/cps-hist.model';
import { ICpsRange } from 'app/shared/model/cps-range.model';
import { IDisplacementHist } from 'app/shared/model/displacement-hist.model';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IPipelineSection {
  id?: number;
  name?: string;
  isOnshore?: number;
  kpStart?: number;
  kpEnd?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  idPipelineId?: number;
  idSafetyClassId?: number;
  anodeHists?: IAnodeHist[];
  bendHists?: IBendHist[];
  buckleArrestorHists?: IBuckleArrestorHist[];
  cpsHists?: ICpsHist[];
  cpsRanges?: ICpsRange[];
  displacementHists?: IDisplacementHist[];
  freeSpanHists?: IFreeSpanHist[];
  freeSpanSupportHists?: IFreeSpanSupportHist[];
  pipeHists?: IPipeHist[];
  teeHists?: ITeeHist[];
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IPipelineSection> = {};
