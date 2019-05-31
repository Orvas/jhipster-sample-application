import { Moment } from 'moment';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';

export interface IPipeline {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  pipelineHistId?: number;
  launchReceiverHists?: ILaunchReceiverHist[];
  pipelineSections?: IPipelineSection[];
}

export const defaultValue: Readonly<IPipeline> = {};
