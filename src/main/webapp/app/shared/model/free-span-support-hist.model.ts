import { Moment } from 'moment';

export interface IFreeSpanSupportHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  height?: number;
  kpInst?: number;
  isCurrentFlag?: number;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  idPipelineSectionId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IFreeSpanSupportHist> = {};
