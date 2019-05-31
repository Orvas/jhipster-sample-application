import { Moment } from 'moment';
import { IPipelineHist } from 'app/shared/model/pipeline-hist.model';

export interface IListPipelineLocation {
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
  pipelineHists?: IPipelineHist[];
}

export const defaultValue: Readonly<IListPipelineLocation> = {};
