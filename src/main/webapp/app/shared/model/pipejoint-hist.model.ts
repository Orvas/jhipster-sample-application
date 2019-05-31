import { Moment } from 'moment';

export interface IPipejointHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  name?: string;
  externalCoatThickness?: number;
  coord?: string;
  isCurrentFlag?: number;
  description?: string;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idId?: number;
  idTypeId?: number;
  idExternalCoatTypeId?: number;
  idMaterialId?: number;
  idSpecificationId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IPipejointHist> = {};
