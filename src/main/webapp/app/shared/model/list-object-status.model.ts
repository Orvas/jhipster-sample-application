import { Moment } from 'moment';
import { IBendHist } from 'app/shared/model/bend-hist.model';
import { ICpsHist } from 'app/shared/model/cps-hist.model';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';
import { IPipelineHist } from 'app/shared/model/pipeline-hist.model';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IListObjectStatus {
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
  bendHists?: IBendHist[];
  cpsHists?: ICpsHist[];
  freeSpanHists?: IFreeSpanHist[];
  freeSpanSupportHists?: IFreeSpanSupportHist[];
  launchReceiverHists?: ILaunchReceiverHist[];
  pipeHists?: IPipeHist[];
  pipejointHists?: IPipejointHist[];
  pipelineHists?: IPipelineHist[];
  teeHists?: ITeeHist[];
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IListObjectStatus> = {};
