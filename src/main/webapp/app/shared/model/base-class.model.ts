import { Moment } from 'moment';

export interface IBaseClass {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idTypeId?: number;
  anodeId?: number;
  bendId?: number;
  buckleArrestorId?: number;
  cpsId?: number;
  displacementId?: number;
  freeSpanId?: number;
  freeSpanSupportId?: number;
  launchReceiverId?: number;
  pipeId?: number;
  pipejointId?: number;
  pipelineId?: number;
  pipelineSectionId?: number;
  teeId?: number;
  valveId?: number;
}

export const defaultValue: Readonly<IBaseClass> = {};
