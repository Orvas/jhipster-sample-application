import { Moment } from 'moment';

export interface IAnodeHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  designLife?: number;
  dmcd?: number;
  l1?: number;
  l2?: number;
  length?: number;
  electrCapac?: number;
  designWeight?: number;
  weight?: number;
  isBurial?: number;
  chemicalComposition?: string;
  density?: number;
  spacing?: number;
  coatCutbackLength?: number;
  coatDmgArea?: number;
  h2sSoil?: number;
  remain?: number;
  intFldTemp?: number;
  minPrc?: number;
  thickness?: number;
  coord?: string;
  kpStart?: number;
  coatThickness?: number;
  kpEnd?: number;
  isCurrentFlag?: number;
  description?: string;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  anodeId?: number;
  idPipelineSectionId?: number;
  idBraceleteTypeId?: number;
  idMaterialId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IAnodeHist> = {};
