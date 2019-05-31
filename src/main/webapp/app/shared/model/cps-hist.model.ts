import { Moment } from 'moment';

export interface ICpsHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  current?: number;
  potential?: number;
  downtime?: number;
  coord?: string;
  kpInst?: number;
  isCurrentFlag?: number;
  description?: string;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  cpsId?: number;
  idPipelineSectionId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<ICpsHist> = {};
