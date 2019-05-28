import { Moment } from 'moment';

export interface IPipelineSection {
  id?: number;
  name?: string;
  pipelineId?: number;
  isOnshore?: boolean;
  safetyClassId?: number;
  kpStart?: number;
  kpEnd?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
}

export const defaultValue: Readonly<IPipelineSection> = {
  isOnshore: false
};
