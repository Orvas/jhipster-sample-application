import { Moment } from 'moment';

export interface IDisplacementHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  deltaX?: number;
  deltaY?: number;
  deltaZ?: number;
  deltaTotal?: number;
  kpStart?: number;
  kpEnd?: number;
  isCurrentFlag?: number;
  idStatus?: number;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  idPipelineSectionId?: number;
}

export const defaultValue: Readonly<IDisplacementHist> = {};
