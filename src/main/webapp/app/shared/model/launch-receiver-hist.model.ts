import { Moment } from 'moment';

export interface ILaunchReceiverHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  name?: string;
  coord?: string;
  kpInst?: number;
  isCurrentFlag?: number;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  launchReceiverId?: number;
  idPipelineId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<ILaunchReceiverHist> = {};
