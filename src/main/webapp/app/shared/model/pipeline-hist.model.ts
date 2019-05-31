import { Moment } from 'moment';

export interface IPipelineHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  name?: string;
  designLife?: number;
  isCurrentFlag?: number;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  pipelineId?: number;
  idLocationId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IPipelineHist> = {};
