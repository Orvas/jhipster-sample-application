import { Moment } from 'moment';

export interface ICpsRange {
  id?: number;
  kpStart?: number;
  kpEnd?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idCpsId?: number;
  idPipelineSectionId?: number;
}

export const defaultValue: Readonly<ICpsRange> = {};
