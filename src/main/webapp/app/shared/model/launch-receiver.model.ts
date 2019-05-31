import { Moment } from 'moment';

export interface ILaunchReceiver {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  baseClassId?: number;
  launchReceiverHistId?: number;
}

export const defaultValue: Readonly<ILaunchReceiver> = {};
