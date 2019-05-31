import { Moment } from 'moment';

export interface IPipeHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  num?: string;
  diameterOuterSteel?: number;
  internalCoatThickness?: number;
  externalCoatThickness?: number;
  isConcrCoating?: number;
  concrCoatThickness?: number;
  concrCoatDensity?: number;
  measWallThickness?: number;
  length?: number;
  weight?: number;
  smts?: number;
  smys?: number;
  sdbm?: number;
  sdaf?: number;
  sdcs?: number;
  allowTensStrain?: number;
  corrosionAllowance?: number;
  fabricationAllowance?: number;
  isBurial?: number;
  burialDepth?: number;
  factBurialDepth?: number;
  dateManufactured?: Moment;
  millTestPressure?: number;
  designPressure?: number;
  incidentalPressure?: number;
  dateInstalled?: Moment;
  seamOrient?: number;
  seamAngle?: number;
  azimuth?: number;
  seabInstallTemp?: number;
  verticalAngle?: number;
  heatTreatDurat?: number;
  maxDesignTemp?: number;
  heatNumber?: string;
  coord?: string;
  designCoord?: string;
  kpStart?: number;
  kpEnd?: number;
  isCurrentFlag?: number;
  description?: string;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  pipeId?: number;
  idPipelineSectionId?: number;
  idInternalCoatTypeId?: number;
  idExternalCoatTypeId?: number;
  idNominalWallThicknessId?: number;
  idPipeJointId?: number;
  idManufacturerId?: number;
  idSpecificationId?: number;
  idLongSeamWeldTypeId?: number;
  idFabricationTypeId?: number;
  idMaterialId?: number;
  idMillLocationId?: number;
  idSteelGradeId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IPipeHist> = {};
