import { Moment } from 'moment';

export interface IFreeSpanHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  lenght?: number;
  lenghtAllow?: number;
  height?: number;
  isMultispan?: number;
  gap?: number;
  kpStart?: number;
  kpEnd?: number;
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

export const defaultValue: Readonly<IFreeSpanHist> = {};
