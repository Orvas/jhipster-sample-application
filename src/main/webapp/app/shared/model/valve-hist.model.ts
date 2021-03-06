import { Moment } from 'moment';

export interface IValveHist {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  idWrk?: number;
  name?: string;
  serialNum?: string;
  model?: string;
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
  dateManufactured?: Moment;
  millTestPressure?: number;
  designPressure?: number;
  designPressureIn?: number;
  designPressureOut?: number;
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
  designCoord?: number;
  kpInst?: number;
  isCurrentFlag?: number;
  description?: string;
  comment?: string;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  valveId?: number;
  idPipelineSectionId?: number;
  idTypeId?: number;
  idInternalCoatTypeId?: number;
  idExternalCoatTypeId?: number;
  idNominalWallThicknessId?: number;
  idPipeJointId?: number;
  idManufacturerId?: number;
  idSpecificationId?: number;
  idFunctionId?: number;
  idLongSeamWeldTypeId?: number;
  idFabricationTypeId?: number;
  idMaterialId?: number;
  idMillLocationId?: number;
  idSteelGradeId?: number;
  idStatusId?: number;
}

export const defaultValue: Readonly<IValveHist> = {};
