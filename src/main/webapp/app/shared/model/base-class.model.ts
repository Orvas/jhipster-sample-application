import { Moment } from 'moment';
import { IAnode } from 'app/shared/model/anode.model';
import { IBend } from 'app/shared/model/bend.model';
import { IBuckleArrestor } from 'app/shared/model/buckle-arrestor.model';
import { ICps } from 'app/shared/model/cps.model';
import { IDisplacement } from 'app/shared/model/displacement.model';
import { IFreeSpan } from 'app/shared/model/free-span.model';
import { IFreeSpanSupport } from 'app/shared/model/free-span-support.model';
import { ILaunchReceiver } from 'app/shared/model/launch-receiver.model';
import { IPipe } from 'app/shared/model/pipe.model';
import { IPipeline } from 'app/shared/model/pipeline.model';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { ITee } from 'app/shared/model/tee.model';
import { IValve } from 'app/shared/model/valve.model';

export interface IBaseClass {
  id?: number;
  dateCreate?: Moment;
  dateEdit?: Moment;
  creator?: string;
  editor?: string;
  idTypeId?: number;
  pipejointId?: number;
  anodes?: IAnode[];
  bends?: IBend[];
  buckleArrestors?: IBuckleArrestor[];
  cps?: ICps[];
  displacements?: IDisplacement[];
  freeSpans?: IFreeSpan[];
  freeSpanSupports?: IFreeSpanSupport[];
  launchReceivers?: ILaunchReceiver[];
  pipes?: IPipe[];
  pipelines?: IPipeline[];
  pipelineSections?: IPipelineSection[];
  tees?: ITee[];
  valves?: IValve[];
}

export const defaultValue: Readonly<IBaseClass> = {};
