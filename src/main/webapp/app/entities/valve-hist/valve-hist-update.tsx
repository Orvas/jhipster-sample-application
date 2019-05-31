import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IValve } from 'app/shared/model/valve.model';
import { getEntities as getValves } from 'app/entities/valve/valve.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListValveType } from 'app/shared/model/list-valve-type.model';
import { getEntities as getListValveTypes } from 'app/entities/list-valve-type/list-valve-type.reducer';
import { IListInternalCoating } from 'app/shared/model/list-internal-coating.model';
import { getEntities as getListInternalCoatings } from 'app/entities/list-internal-coating/list-internal-coating.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListNominalWallThickness } from 'app/shared/model/list-nominal-wall-thickness.model';
import { getEntities as getListNominalWallThicknesses } from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListValveManufacturer } from 'app/shared/model/list-valve-manufacturer.model';
import { getEntities as getListValveManufacturers } from 'app/entities/list-valve-manufacturer/list-valve-manufacturer.reducer';
import { IListValveSpecification } from 'app/shared/model/list-valve-specification.model';
import { getEntities as getListValveSpecifications } from 'app/entities/list-valve-specification/list-valve-specification.reducer';
import { IListValveFunction } from 'app/shared/model/list-valve-function.model';
import { getEntities as getListValveFunctions } from 'app/entities/list-valve-function/list-valve-function.reducer';
import { IListLongSeamWeldType } from 'app/shared/model/list-long-seam-weld-type.model';
import { getEntities as getListLongSeamWeldTypes } from 'app/entities/list-long-seam-weld-type/list-long-seam-weld-type.reducer';
import { IListFabricationType } from 'app/shared/model/list-fabrication-type.model';
import { getEntities as getListFabricationTypes } from 'app/entities/list-fabrication-type/list-fabrication-type.reducer';
import { IListMaterial } from 'app/shared/model/list-material.model';
import { getEntities as getListMaterials } from 'app/entities/list-material/list-material.reducer';
import { IListMillLocation } from 'app/shared/model/list-mill-location.model';
import { getEntities as getListMillLocations } from 'app/entities/list-mill-location/list-mill-location.reducer';
import { IListSteelGrade } from 'app/shared/model/list-steel-grade.model';
import { getEntities as getListSteelGrades } from 'app/entities/list-steel-grade/list-steel-grade.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './valve-hist.reducer';
import { IValveHist } from 'app/shared/model/valve-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IValveHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IValveHistUpdateState {
  isNew: boolean;
  valveId: string;
  idPipelineSectionId: string;
  idTypeId: string;
  idInternalCoatTypeId: string;
  idExternalCoatTypeId: string;
  idNominalWallThicknessId: string;
  idPipeJointId: string;
  idManufacturerId: string;
  idSpecificationId: string;
  idFunctionId: string;
  idLongSeamWeldTypeId: string;
  idFabricationTypeId: string;
  idMaterialId: string;
  idMillLocationId: string;
  idSteelGradeId: string;
  idStatusId: string;
}

export class ValveHistUpdate extends React.Component<IValveHistUpdateProps, IValveHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      valveId: '0',
      idPipelineSectionId: '0',
      idTypeId: '0',
      idInternalCoatTypeId: '0',
      idExternalCoatTypeId: '0',
      idNominalWallThicknessId: '0',
      idPipeJointId: '0',
      idManufacturerId: '0',
      idSpecificationId: '0',
      idFunctionId: '0',
      idLongSeamWeldTypeId: '0',
      idFabricationTypeId: '0',
      idMaterialId: '0',
      idMillLocationId: '0',
      idSteelGradeId: '0',
      idStatusId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getValves();
    this.props.getPipelineSections();
    this.props.getListValveTypes();
    this.props.getListInternalCoatings();
    this.props.getListExternalCoatings();
    this.props.getListNominalWallThicknesses();
    this.props.getPipejoints();
    this.props.getListValveManufacturers();
    this.props.getListValveSpecifications();
    this.props.getListValveFunctions();
    this.props.getListLongSeamWeldTypes();
    this.props.getListFabricationTypes();
    this.props.getListMaterials();
    this.props.getListMillLocations();
    this.props.getListSteelGrades();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { valveHistEntity } = this.props;
      const entity = {
        ...valveHistEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/valve-hist');
  };

  render() {
    const {
      valveHistEntity,
      valves,
      pipelineSections,
      listValveTypes,
      listInternalCoatings,
      listExternalCoatings,
      listNominalWallThicknesses,
      pipejoints,
      listValveManufacturers,
      listValveSpecifications,
      listValveFunctions,
      listLongSeamWeldTypes,
      listFabricationTypes,
      listMaterials,
      listMillLocations,
      listSteelGrades,
      listObjectStatuses,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.valveHist.home.createOrEditLabel">Create or edit a ValveHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : valveHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="valve-hist-id">ID</Label>
                    <AvInput id="valve-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="valve-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="valve-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="valve-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="valve-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="valve-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="valve-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="valve-hist-name">
                    Name
                  </Label>
                  <AvField
                    id="valve-hist-name"
                    type="text"
                    name="name"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="serialNumLabel" for="valve-hist-serialNum">
                    Serial Num
                  </Label>
                  <AvField
                    id="valve-hist-serialNum"
                    type="text"
                    name="serialNum"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modelLabel" for="valve-hist-model">
                    Model
                  </Label>
                  <AvField
                    id="valve-hist-model"
                    type="text"
                    name="model"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelLabel" for="valve-hist-diameterOuterSteel">
                    Diameter Outer Steel
                  </Label>
                  <AvField
                    id="valve-hist-diameterOuterSteel"
                    type="string"
                    className="form-control"
                    name="diameterOuterSteel"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="internalCoatThicknessLabel" for="valve-hist-internalCoatThickness">
                    Internal Coat Thickness
                  </Label>
                  <AvField id="valve-hist-internalCoatThickness" type="text" name="internalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="valve-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="valve-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="isConcrCoatingLabel" for="valve-hist-isConcrCoating">
                    Is Concr Coating
                  </Label>
                  <AvField id="valve-hist-isConcrCoating" type="string" className="form-control" name="isConcrCoating" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatThicknessLabel" for="valve-hist-concrCoatThickness">
                    Concr Coat Thickness
                  </Label>
                  <AvField id="valve-hist-concrCoatThickness" type="text" name="concrCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatDensityLabel" for="valve-hist-concrCoatDensity">
                    Concr Coat Density
                  </Label>
                  <AvField id="valve-hist-concrCoatDensity" type="text" name="concrCoatDensity" />
                </AvGroup>
                <AvGroup>
                  <Label id="measWallThicknessLabel" for="valve-hist-measWallThickness">
                    Meas Wall Thickness
                  </Label>
                  <AvField id="valve-hist-measWallThickness" type="text" name="measWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="valve-hist-length">
                    Length
                  </Label>
                  <AvField id="valve-hist-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="valve-hist-weight">
                    Weight
                  </Label>
                  <AvField id="valve-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="smtsLabel" for="valve-hist-smts">
                    Smts
                  </Label>
                  <AvField id="valve-hist-smts" type="text" name="smts" />
                </AvGroup>
                <AvGroup>
                  <Label id="smysLabel" for="valve-hist-smys">
                    Smys
                  </Label>
                  <AvField id="valve-hist-smys" type="text" name="smys" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdbmLabel" for="valve-hist-sdbm">
                    Sdbm
                  </Label>
                  <AvField id="valve-hist-sdbm" type="text" name="sdbm" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdafLabel" for="valve-hist-sdaf">
                    Sdaf
                  </Label>
                  <AvField id="valve-hist-sdaf" type="text" name="sdaf" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdcsLabel" for="valve-hist-sdcs">
                    Sdcs
                  </Label>
                  <AvField id="valve-hist-sdcs" type="text" name="sdcs" />
                </AvGroup>
                <AvGroup>
                  <Label id="allowTensStrainLabel" for="valve-hist-allowTensStrain">
                    Allow Tens Strain
                  </Label>
                  <AvField id="valve-hist-allowTensStrain" type="text" name="allowTensStrain" />
                </AvGroup>
                <AvGroup>
                  <Label id="corrosionAllowanceLabel" for="valve-hist-corrosionAllowance">
                    Corrosion Allowance
                  </Label>
                  <AvField id="valve-hist-corrosionAllowance" type="string" className="form-control" name="corrosionAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="fabricationAllowanceLabel" for="valve-hist-fabricationAllowance">
                    Fabrication Allowance
                  </Label>
                  <AvField id="valve-hist-fabricationAllowance" type="string" className="form-control" name="fabricationAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateManufacturedLabel" for="valve-hist-dateManufactured">
                    Date Manufactured
                  </Label>
                  <AvField id="valve-hist-dateManufactured" type="date" className="form-control" name="dateManufactured" />
                </AvGroup>
                <AvGroup>
                  <Label id="millTestPressureLabel" for="valve-hist-millTestPressure">
                    Mill Test Pressure
                  </Label>
                  <AvField id="valve-hist-millTestPressure" type="text" name="millTestPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureLabel" for="valve-hist-designPressure">
                    Design Pressure
                  </Label>
                  <AvField id="valve-hist-designPressure" type="text" name="designPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureInLabel" for="valve-hist-designPressureIn">
                    Design Pressure In
                  </Label>
                  <AvField id="valve-hist-designPressureIn" type="text" name="designPressureIn" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureOutLabel" for="valve-hist-designPressureOut">
                    Design Pressure Out
                  </Label>
                  <AvField id="valve-hist-designPressureOut" type="text" name="designPressureOut" />
                </AvGroup>
                <AvGroup>
                  <Label id="incidentalPressureLabel" for="valve-hist-incidentalPressure">
                    Incidental Pressure
                  </Label>
                  <AvField id="valve-hist-incidentalPressure" type="text" name="incidentalPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateInstalledLabel" for="valve-hist-dateInstalled">
                    Date Installed
                  </Label>
                  <AvField id="valve-hist-dateInstalled" type="date" className="form-control" name="dateInstalled" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamOrientLabel" for="valve-hist-seamOrient">
                    Seam Orient
                  </Label>
                  <AvField id="valve-hist-seamOrient" type="text" name="seamOrient" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamAngleLabel" for="valve-hist-seamAngle">
                    Seam Angle
                  </Label>
                  <AvField id="valve-hist-seamAngle" type="text" name="seamAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="azimuthLabel" for="valve-hist-azimuth">
                    Azimuth
                  </Label>
                  <AvField id="valve-hist-azimuth" type="text" name="azimuth" />
                </AvGroup>
                <AvGroup>
                  <Label id="seabInstallTempLabel" for="valve-hist-seabInstallTemp">
                    Seab Install Temp
                  </Label>
                  <AvField id="valve-hist-seabInstallTemp" type="text" name="seabInstallTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="verticalAngleLabel" for="valve-hist-verticalAngle">
                    Vertical Angle
                  </Label>
                  <AvField id="valve-hist-verticalAngle" type="text" name="verticalAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatTreatDuratLabel" for="valve-hist-heatTreatDurat">
                    Heat Treat Durat
                  </Label>
                  <AvField id="valve-hist-heatTreatDurat" type="text" name="heatTreatDurat" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxDesignTempLabel" for="valve-hist-maxDesignTemp">
                    Max Design Temp
                  </Label>
                  <AvField id="valve-hist-maxDesignTemp" type="text" name="maxDesignTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatNumberLabel" for="valve-hist-heatNumber">
                    Heat Number
                  </Label>
                  <AvField
                    id="valve-hist-heatNumber"
                    type="text"
                    name="heatNumber"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="valve-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="valve-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designCoordLabel" for="valve-hist-designCoord">
                    Design Coord
                  </Label>
                  <AvField id="valve-hist-designCoord" type="text" name="designCoord" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpInstLabel" for="valve-hist-kpInst">
                    Kp Inst
                  </Label>
                  <AvField id="valve-hist-kpInst" type="text" name="kpInst" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="valve-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="valve-hist-isCurrentFlag"
                    type="string"
                    className="form-control"
                    name="isCurrentFlag"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="valve-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="valve-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="valve-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="valve-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="valve-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="valve-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.valveHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="valve-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="valve-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.valveHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="valve-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="valve-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="valve-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="valve-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-valve">Valve</Label>
                  <AvInput id="valve-hist-valve" type="select" className="form-control" name="valveId">
                    <option value="" key="0" />
                    {valves
                      ? valves.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="valve-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
                    {pipelineSections
                      ? pipelineSections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idType">Id Type</Label>
                  <AvInput id="valve-hist-idType" type="select" className="form-control" name="idTypeId">
                    <option value="" key="0" />
                    {listValveTypes
                      ? listValveTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idInternalCoatType">Id Internal Coat Type</Label>
                  <AvInput id="valve-hist-idInternalCoatType" type="select" className="form-control" name="idInternalCoatTypeId">
                    <option value="" key="0" />
                    {listInternalCoatings
                      ? listInternalCoatings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput id="valve-hist-idExternalCoatType" type="select" className="form-control" name="idExternalCoatTypeId">
                    <option value="" key="0" />
                    {listExternalCoatings
                      ? listExternalCoatings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idNominalWallThickness">Id Nominal Wall Thickness</Label>
                  <AvInput id="valve-hist-idNominalWallThickness" type="select" className="form-control" name="idNominalWallThicknessId">
                    <option value="" key="0" />
                    {listNominalWallThicknesses
                      ? listNominalWallThicknesses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idPipeJoint">Id Pipe Joint</Label>
                  <AvInput id="valve-hist-idPipeJoint" type="select" className="form-control" name="idPipeJointId">
                    <option value="" key="0" />
                    {pipejoints
                      ? pipejoints.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idManufacturer">Id Manufacturer</Label>
                  <AvInput id="valve-hist-idManufacturer" type="select" className="form-control" name="idManufacturerId">
                    <option value="" key="0" />
                    {listValveManufacturers
                      ? listValveManufacturers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idSpecification">Id Specification</Label>
                  <AvInput id="valve-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listValveSpecifications
                      ? listValveSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idFunction">Id Function</Label>
                  <AvInput id="valve-hist-idFunction" type="select" className="form-control" name="idFunctionId">
                    <option value="" key="0" />
                    {listValveFunctions
                      ? listValveFunctions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idLongSeamWeldType">Id Long Seam Weld Type</Label>
                  <AvInput id="valve-hist-idLongSeamWeldType" type="select" className="form-control" name="idLongSeamWeldTypeId">
                    <option value="" key="0" />
                    {listLongSeamWeldTypes
                      ? listLongSeamWeldTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idFabricationType">Id Fabrication Type</Label>
                  <AvInput id="valve-hist-idFabricationType" type="select" className="form-control" name="idFabricationTypeId">
                    <option value="" key="0" />
                    {listFabricationTypes
                      ? listFabricationTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idMaterial">Id Material</Label>
                  <AvInput id="valve-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
                    <option value="" key="0" />
                    {listMaterials
                      ? listMaterials.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idMillLocation">Id Mill Location</Label>
                  <AvInput id="valve-hist-idMillLocation" type="select" className="form-control" name="idMillLocationId">
                    <option value="" key="0" />
                    {listMillLocations
                      ? listMillLocations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idSteelGrade">Id Steel Grade</Label>
                  <AvInput id="valve-hist-idSteelGrade" type="select" className="form-control" name="idSteelGradeId">
                    <option value="" key="0" />
                    {listSteelGrades
                      ? listSteelGrades.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="valve-hist-idStatus">Id Status</Label>
                  <AvInput id="valve-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
                    {listObjectStatuses
                      ? listObjectStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/valve-hist" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  valves: storeState.valve.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listValveTypes: storeState.listValveType.entities,
  listInternalCoatings: storeState.listInternalCoating.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listNominalWallThicknesses: storeState.listNominalWallThickness.entities,
  pipejoints: storeState.pipejoint.entities,
  listValveManufacturers: storeState.listValveManufacturer.entities,
  listValveSpecifications: storeState.listValveSpecification.entities,
  listValveFunctions: storeState.listValveFunction.entities,
  listLongSeamWeldTypes: storeState.listLongSeamWeldType.entities,
  listFabricationTypes: storeState.listFabricationType.entities,
  listMaterials: storeState.listMaterial.entities,
  listMillLocations: storeState.listMillLocation.entities,
  listSteelGrades: storeState.listSteelGrade.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  valveHistEntity: storeState.valveHist.entity,
  loading: storeState.valveHist.loading,
  updating: storeState.valveHist.updating,
  updateSuccess: storeState.valveHist.updateSuccess
});

const mapDispatchToProps = {
  getValves,
  getPipelineSections,
  getListValveTypes,
  getListInternalCoatings,
  getListExternalCoatings,
  getListNominalWallThicknesses,
  getPipejoints,
  getListValveManufacturers,
  getListValveSpecifications,
  getListValveFunctions,
  getListLongSeamWeldTypes,
  getListFabricationTypes,
  getListMaterials,
  getListMillLocations,
  getListSteelGrades,
  getListObjectStatuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ValveHistUpdate);
