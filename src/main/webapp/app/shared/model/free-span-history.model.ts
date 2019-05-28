import { Moment } from 'moment';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';

export interface IFreeSpanHistory {
  id?: number;
  dateForm?: Moment;
  dateTo?: Moment;
  workId?: number;
  length?: number;
  kpStart?: number;
  kpEnd?: number;
  isCurrentFlag?: boolean;
  statusId?: number;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  status?: IListObjectStatus;
  pipelineSection?: IPipelineSection;
}

export const defaultValue: Readonly<IFreeSpanHistory> = {
  isCurrentFlag: false
};
