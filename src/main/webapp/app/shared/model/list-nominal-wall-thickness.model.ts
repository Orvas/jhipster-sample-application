import { Moment } from 'moment';
import { IBendHist } from 'app/shared/model/bend-hist.model';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
import { IValveHist } from 'app/shared/model/valve-hist.model';

export interface IListNominalWallThickness {
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
  bendHists?: IBendHist[];
  buckleArrestorHists?: IBuckleArrestorHist[];
  pipeHists?: IPipeHist[];
  teeHists?: ITeeHist[];
  valveHists?: IValveHist[];
}

export const defaultValue: Readonly<IListNominalWallThickness> = {};
