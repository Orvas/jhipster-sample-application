import { Moment } from 'moment';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';

export interface IListSafetyClass {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  isCurrentFlag?: number;
  description?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  pipelineSections?: IPipelineSection[];
}

export const defaultValue: Readonly<IListSafetyClass> = {};
