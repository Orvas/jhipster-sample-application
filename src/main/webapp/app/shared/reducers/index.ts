import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import listObjectStatus, {
  ListObjectStatusState
} from 'app/entities/list-object-status/list-object-status.reducer';
// prettier-ignore
import pipelineSection, {
  PipelineSectionState
} from 'app/entities/pipeline-section/pipeline-section.reducer';
// prettier-ignore
import freeSpanHistory, {
  FreeSpanHistoryState
} from 'app/entities/free-span-history/free-span-history.reducer';
// prettier-ignore
import anode, {
  AnodeState
} from 'app/entities/anode/anode.reducer';
// prettier-ignore
import anodeHist, {
  AnodeHistState
} from 'app/entities/anode-hist/anode-hist.reducer';
// prettier-ignore
import baseClass, {
  BaseClassState
} from 'app/entities/base-class/base-class.reducer';
// prettier-ignore
import bend, {
  BendState
} from 'app/entities/bend/bend.reducer';
// prettier-ignore
import bendHist, {
  BendHistState
} from 'app/entities/bend-hist/bend-hist.reducer';
// prettier-ignore
import buckleArrestor, {
  BuckleArrestorState
} from 'app/entities/buckle-arrestor/buckle-arrestor.reducer';
// prettier-ignore
import buckleArrestorHist, {
  BuckleArrestorHistState
} from 'app/entities/buckle-arrestor-hist/buckle-arrestor-hist.reducer';
// prettier-ignore
import cps, {
  CpsState
} from 'app/entities/cps/cps.reducer';
// prettier-ignore
import cpsHist, {
  CpsHistState
} from 'app/entities/cps-hist/cps-hist.reducer';
// prettier-ignore
import cpsRange, {
  CpsRangeState
} from 'app/entities/cps-range/cps-range.reducer';
// prettier-ignore
import displacement, {
  DisplacementState
} from 'app/entities/displacement/displacement.reducer';
// prettier-ignore
import displacementHist, {
  DisplacementHistState
} from 'app/entities/displacement-hist/displacement-hist.reducer';
// prettier-ignore
import freeSpan, {
  FreeSpanState
} from 'app/entities/free-span/free-span.reducer';
// prettier-ignore
import freeSpanHist, {
  FreeSpanHistState
} from 'app/entities/free-span-hist/free-span-hist.reducer';
// prettier-ignore
import freeSpanSupport, {
  FreeSpanSupportState
} from 'app/entities/free-span-support/free-span-support.reducer';
// prettier-ignore
import freeSpanSupportHist, {
  FreeSpanSupportHistState
} from 'app/entities/free-span-support-hist/free-span-support-hist.reducer';
// prettier-ignore
import launchReceiver, {
  LaunchReceiverState
} from 'app/entities/launch-receiver/launch-receiver.reducer';
// prettier-ignore
import launchReceiverHist, {
  LaunchReceiverHistState
} from 'app/entities/launch-receiver-hist/launch-receiver-hist.reducer';
// prettier-ignore
import listAnodeBraceleteType, {
  ListAnodeBraceleteTypeState
} from 'app/entities/list-anode-bracelete-type/list-anode-bracelete-type.reducer';
// prettier-ignore
import listBendManufacturer, {
  ListBendManufacturerState
} from 'app/entities/list-bend-manufacturer/list-bend-manufacturer.reducer';
// prettier-ignore
import listBendSpecification, {
  ListBendSpecificationState
} from 'app/entities/list-bend-specification/list-bend-specification.reducer';
// prettier-ignore
import listBendType, {
  ListBendTypeState
} from 'app/entities/list-bend-type/list-bend-type.reducer';
// prettier-ignore
import listBoundaryCondUcase, {
  ListBoundaryCondUcaseState
} from 'app/entities/list-boundary-cond-ucase/list-boundary-cond-ucase.reducer';
// prettier-ignore
import listBucklarrManufacturer, {
  ListBucklarrManufacturerState
} from 'app/entities/list-bucklarr-manufacturer/list-bucklarr-manufacturer.reducer';
// prettier-ignore
import listBucklarrSpecification, {
  ListBucklarrSpecificationState
} from 'app/entities/list-bucklarr-specification/list-bucklarr-specification.reducer';
// prettier-ignore
import listBucklarrType, {
  ListBucklarrTypeState
} from 'app/entities/list-bucklarr-type/list-bucklarr-type.reducer';
// prettier-ignore
import listClayType, {
  ListClayTypeState
} from 'app/entities/list-clay-type/list-clay-type.reducer';
// prettier-ignore
import listClcKind, {
  ListClcKindState
} from 'app/entities/list-clc-kind/list-clc-kind.reducer';
// prettier-ignore
import listClcLvl, {
  ListClcLvlState
} from 'app/entities/list-clc-lvl/list-clc-lvl.reducer';
// prettier-ignore
import listClcResult, {
  ListClcResultState
} from 'app/entities/list-clc-result/list-clc-result.reducer';
// prettier-ignore
import listClcType, {
  ListClcTypeState
} from 'app/entities/list-clc-type/list-clc-type.reducer';
// prettier-ignore
import listDfctGroup, {
  ListDfctGroupState
} from 'app/entities/list-dfct-group/list-dfct-group.reducer';
// prettier-ignore
import listDfctPosType, {
  ListDfctPosTypeState
} from 'app/entities/list-dfct-pos-type/list-dfct-pos-type.reducer';
// prettier-ignore
import listDfctType, {
  ListDfctTypeState
} from 'app/entities/list-dfct-type/list-dfct-type.reducer';
// prettier-ignore
import listEffAxforceUcase, {
  ListEffAxforceUcaseState
} from 'app/entities/list-eff-axforce-ucase/list-eff-axforce-ucase.reducer';
// prettier-ignore
import listEnvDirection, {
  ListEnvDirectionState
} from 'app/entities/list-env-direction/list-env-direction.reducer';
// prettier-ignore
import listEnvPoint, {
  ListEnvPointState
} from 'app/entities/list-env-point/list-env-point.reducer';
// prettier-ignore
import listExternalCoating, {
  ListExternalCoatingState
} from 'app/entities/list-external-coating/list-external-coating.reducer';
// prettier-ignore
import listFabricationType, {
  ListFabricationTypeState
} from 'app/entities/list-fabrication-type/list-fabrication-type.reducer';
// prettier-ignore
import listIliPigType, {
  ListIliPigTypeState
} from 'app/entities/list-ili-pig-type/list-ili-pig-type.reducer';
// prettier-ignore
import listInternalCoating, {
  ListInternalCoatingState
} from 'app/entities/list-internal-coating/list-internal-coating.reducer';
// prettier-ignore
import listInternalPressUcase, {
  ListInternalPressUcaseState
} from 'app/entities/list-internal-press-ucase/list-internal-press-ucase.reducer';
// prettier-ignore
import listLongSeamWeldType, {
  ListLongSeamWeldTypeState
} from 'app/entities/list-long-seam-weld-type/list-long-seam-weld-type.reducer';
// prettier-ignore
import listMaterial, {
  ListMaterialState
} from 'app/entities/list-material/list-material.reducer';
// prettier-ignore
import listMillLocation, {
  ListMillLocationState
} from 'app/entities/list-mill-location/list-mill-location.reducer';
// prettier-ignore
import listMinpressUcase, {
  ListMinpressUcaseState
} from 'app/entities/list-minpress-ucase/list-minpress-ucase.reducer';
// prettier-ignore
import listNominalWallThickness, {
  ListNominalWallThicknessState
} from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
// prettier-ignore
import listObjectType, {
  ListObjectTypeState
} from 'app/entities/list-object-type/list-object-type.reducer';
// prettier-ignore
import listPipeManufacturer, {
  ListPipeManufacturerState
} from 'app/entities/list-pipe-manufacturer/list-pipe-manufacturer.reducer';
// prettier-ignore
import listPipeSpecification, {
  ListPipeSpecificationState
} from 'app/entities/list-pipe-specification/list-pipe-specification.reducer';
// prettier-ignore
import listPipejointSpecification, {
  ListPipejointSpecificationState
} from 'app/entities/list-pipejoint-specification/list-pipejoint-specification.reducer';
// prettier-ignore
import listPipejointType, {
  ListPipejointTypeState
} from 'app/entities/list-pipejoint-type/list-pipejoint-type.reducer';
// prettier-ignore
import listPipelineLocation, {
  ListPipelineLocationState
} from 'app/entities/list-pipeline-location/list-pipeline-location.reducer';
// prettier-ignore
import listRiskConsequence, {
  ListRiskConsequenceState
} from 'app/entities/list-risk-consequence/list-risk-consequence.reducer';
// prettier-ignore
import listRiskProbability, {
  ListRiskProbabilityState
} from 'app/entities/list-risk-probability/list-risk-probability.reducer';
// prettier-ignore
import listSafetyClass, {
  ListSafetyClassState
} from 'app/entities/list-safety-class/list-safety-class.reducer';
// prettier-ignore
import listSandType, {
  ListSandTypeState
} from 'app/entities/list-sand-type/list-sand-type.reducer';
// prettier-ignore
import listSoilType, {
  ListSoilTypeState
} from 'app/entities/list-soil-type/list-soil-type.reducer';
// prettier-ignore
import listSteelGrade, {
  ListSteelGradeState
} from 'app/entities/list-steel-grade/list-steel-grade.reducer';
// prettier-ignore
import listTeeManufacturer, {
  ListTeeManufacturerState
} from 'app/entities/list-tee-manufacturer/list-tee-manufacturer.reducer';
// prettier-ignore
import listTeeSpecification, {
  ListTeeSpecificationState
} from 'app/entities/list-tee-specification/list-tee-specification.reducer';
// prettier-ignore
import listTeeType, {
  ListTeeTypeState
} from 'app/entities/list-tee-type/list-tee-type.reducer';
// prettier-ignore
import listThreat, {
  ListThreatState
} from 'app/entities/list-threat/list-threat.reducer';
// prettier-ignore
import listThreatGroup, {
  ListThreatGroupState
} from 'app/entities/list-threat-group/list-threat-group.reducer';
// prettier-ignore
import listValveFunction, {
  ListValveFunctionState
} from 'app/entities/list-valve-function/list-valve-function.reducer';
// prettier-ignore
import listValveManufacturer, {
  ListValveManufacturerState
} from 'app/entities/list-valve-manufacturer/list-valve-manufacturer.reducer';
// prettier-ignore
import listValveSpecification, {
  ListValveSpecificationState
} from 'app/entities/list-valve-specification/list-valve-specification.reducer';
// prettier-ignore
import listValveType, {
  ListValveTypeState
} from 'app/entities/list-valve-type/list-valve-type.reducer';
// prettier-ignore
import listWrkKind, {
  ListWrkKindState
} from 'app/entities/list-wrk-kind/list-wrk-kind.reducer';
// prettier-ignore
import listWrkPurpose, {
  ListWrkPurposeState
} from 'app/entities/list-wrk-purpose/list-wrk-purpose.reducer';
// prettier-ignore
import listWrkStatus, {
  ListWrkStatusState
} from 'app/entities/list-wrk-status/list-wrk-status.reducer';
// prettier-ignore
import listWrkcmmsStatus, {
  ListWrkcmmsStatusState
} from 'app/entities/list-wrkcmms-status/list-wrkcmms-status.reducer';
// prettier-ignore
import metaList, {
  MetaListState
} from 'app/entities/meta-list/meta-list.reducer';
// prettier-ignore
import pipe, {
  PipeState
} from 'app/entities/pipe/pipe.reducer';
// prettier-ignore
import pipeHist, {
  PipeHistState
} from 'app/entities/pipe-hist/pipe-hist.reducer';
// prettier-ignore
import pipejoint, {
  PipejointState
} from 'app/entities/pipejoint/pipejoint.reducer';
// prettier-ignore
import pipejointHist, {
  PipejointHistState
} from 'app/entities/pipejoint-hist/pipejoint-hist.reducer';
// prettier-ignore
import pipeline, {
  PipelineState
} from 'app/entities/pipeline/pipeline.reducer';
// prettier-ignore
import pipelineHist, {
  PipelineHistState
} from 'app/entities/pipeline-hist/pipeline-hist.reducer';
// prettier-ignore
import pipelineSegment, {
  PipelineSegmentState
} from 'app/entities/pipeline-segment/pipeline-segment.reducer';
// prettier-ignore
import tee, {
  TeeState
} from 'app/entities/tee/tee.reducer';
// prettier-ignore
import teeHist, {
  TeeHistState
} from 'app/entities/tee-hist/tee-hist.reducer';
// prettier-ignore
import valve, {
  ValveState
} from 'app/entities/valve/valve.reducer';
// prettier-ignore
import valveHist, {
  ValveHistState
} from 'app/entities/valve-hist/valve-hist.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly listObjectStatus: ListObjectStatusState;
  readonly pipelineSection: PipelineSectionState;
  readonly freeSpanHistory: FreeSpanHistoryState;
  readonly anode: AnodeState;
  readonly anodeHist: AnodeHistState;
  readonly baseClass: BaseClassState;
  readonly bend: BendState;
  readonly bendHist: BendHistState;
  readonly buckleArrestor: BuckleArrestorState;
  readonly buckleArrestorHist: BuckleArrestorHistState;
  readonly cps: CpsState;
  readonly cpsHist: CpsHistState;
  readonly cpsRange: CpsRangeState;
  readonly displacement: DisplacementState;
  readonly displacementHist: DisplacementHistState;
  readonly freeSpan: FreeSpanState;
  readonly freeSpanHist: FreeSpanHistState;
  readonly freeSpanSupport: FreeSpanSupportState;
  readonly freeSpanSupportHist: FreeSpanSupportHistState;
  readonly launchReceiver: LaunchReceiverState;
  readonly launchReceiverHist: LaunchReceiverHistState;
  readonly listAnodeBraceleteType: ListAnodeBraceleteTypeState;
  readonly listBendManufacturer: ListBendManufacturerState;
  readonly listBendSpecification: ListBendSpecificationState;
  readonly listBendType: ListBendTypeState;
  readonly listBoundaryCondUcase: ListBoundaryCondUcaseState;
  readonly listBucklarrManufacturer: ListBucklarrManufacturerState;
  readonly listBucklarrSpecification: ListBucklarrSpecificationState;
  readonly listBucklarrType: ListBucklarrTypeState;
  readonly listClayType: ListClayTypeState;
  readonly listClcKind: ListClcKindState;
  readonly listClcLvl: ListClcLvlState;
  readonly listClcResult: ListClcResultState;
  readonly listClcType: ListClcTypeState;
  readonly listDfctGroup: ListDfctGroupState;
  readonly listDfctPosType: ListDfctPosTypeState;
  readonly listDfctType: ListDfctTypeState;
  readonly listEffAxforceUcase: ListEffAxforceUcaseState;
  readonly listEnvDirection: ListEnvDirectionState;
  readonly listEnvPoint: ListEnvPointState;
  readonly listExternalCoating: ListExternalCoatingState;
  readonly listFabricationType: ListFabricationTypeState;
  readonly listIliPigType: ListIliPigTypeState;
  readonly listInternalCoating: ListInternalCoatingState;
  readonly listInternalPressUcase: ListInternalPressUcaseState;
  readonly listLongSeamWeldType: ListLongSeamWeldTypeState;
  readonly listMaterial: ListMaterialState;
  readonly listMillLocation: ListMillLocationState;
  readonly listMinpressUcase: ListMinpressUcaseState;
  readonly listNominalWallThickness: ListNominalWallThicknessState;
  readonly listObjectType: ListObjectTypeState;
  readonly listPipeManufacturer: ListPipeManufacturerState;
  readonly listPipeSpecification: ListPipeSpecificationState;
  readonly listPipejointSpecification: ListPipejointSpecificationState;
  readonly listPipejointType: ListPipejointTypeState;
  readonly listPipelineLocation: ListPipelineLocationState;
  readonly listRiskConsequence: ListRiskConsequenceState;
  readonly listRiskProbability: ListRiskProbabilityState;
  readonly listSafetyClass: ListSafetyClassState;
  readonly listSandType: ListSandTypeState;
  readonly listSoilType: ListSoilTypeState;
  readonly listSteelGrade: ListSteelGradeState;
  readonly listTeeManufacturer: ListTeeManufacturerState;
  readonly listTeeSpecification: ListTeeSpecificationState;
  readonly listTeeType: ListTeeTypeState;
  readonly listThreat: ListThreatState;
  readonly listThreatGroup: ListThreatGroupState;
  readonly listValveFunction: ListValveFunctionState;
  readonly listValveManufacturer: ListValveManufacturerState;
  readonly listValveSpecification: ListValveSpecificationState;
  readonly listValveType: ListValveTypeState;
  readonly listWrkKind: ListWrkKindState;
  readonly listWrkPurpose: ListWrkPurposeState;
  readonly listWrkStatus: ListWrkStatusState;
  readonly listWrkcmmsStatus: ListWrkcmmsStatusState;
  readonly metaList: MetaListState;
  readonly pipe: PipeState;
  readonly pipeHist: PipeHistState;
  readonly pipejoint: PipejointState;
  readonly pipejointHist: PipejointHistState;
  readonly pipeline: PipelineState;
  readonly pipelineHist: PipelineHistState;
  readonly pipelineSegment: PipelineSegmentState;
  readonly tee: TeeState;
  readonly teeHist: TeeHistState;
  readonly valve: ValveState;
  readonly valveHist: ValveHistState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  listObjectStatus,
  pipelineSection,
  freeSpanHistory,
  anode,
  anodeHist,
  baseClass,
  bend,
  bendHist,
  buckleArrestor,
  buckleArrestorHist,
  cps,
  cpsHist,
  cpsRange,
  displacement,
  displacementHist,
  freeSpan,
  freeSpanHist,
  freeSpanSupport,
  freeSpanSupportHist,
  launchReceiver,
  launchReceiverHist,
  listAnodeBraceleteType,
  listBendManufacturer,
  listBendSpecification,
  listBendType,
  listBoundaryCondUcase,
  listBucklarrManufacturer,
  listBucklarrSpecification,
  listBucklarrType,
  listClayType,
  listClcKind,
  listClcLvl,
  listClcResult,
  listClcType,
  listDfctGroup,
  listDfctPosType,
  listDfctType,
  listEffAxforceUcase,
  listEnvDirection,
  listEnvPoint,
  listExternalCoating,
  listFabricationType,
  listIliPigType,
  listInternalCoating,
  listInternalPressUcase,
  listLongSeamWeldType,
  listMaterial,
  listMillLocation,
  listMinpressUcase,
  listNominalWallThickness,
  listObjectType,
  listPipeManufacturer,
  listPipeSpecification,
  listPipejointSpecification,
  listPipejointType,
  listPipelineLocation,
  listRiskConsequence,
  listRiskProbability,
  listSafetyClass,
  listSandType,
  listSoilType,
  listSteelGrade,
  listTeeManufacturer,
  listTeeSpecification,
  listTeeType,
  listThreat,
  listThreatGroup,
  listValveFunction,
  listValveManufacturer,
  listValveSpecification,
  listValveType,
  listWrkKind,
  listWrkPurpose,
  listWrkStatus,
  listWrkcmmsStatus,
  metaList,
  pipe,
  pipeHist,
  pipejoint,
  pipejointHist,
  pipeline,
  pipelineHist,
  pipelineSegment,
  tee,
  teeHist,
  valve,
  valveHist,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
