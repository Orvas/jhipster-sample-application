import { Moment } from 'moment';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';

export interface ILaunchReceiver {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  launchReceiverHists?: ILaunchReceiverHist[];
}

export const defaultValue: Readonly<ILaunchReceiver> = {};
