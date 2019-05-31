import { Moment } from 'moment';

export interface IPipelineSegment {
  id?: number;
  num?: string;
  name?: string;
  kpStart1?: number;
  kpEnd1?: number;
  kpStart4?: number;
  kpEnd4?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IPipelineSegment> = {};
