import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPipe } from 'app/shared/model/pipe.model';
import { getEntities as getPipes } from 'app/entities/pipe/pipe.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListInternalCoating } from 'app/shared/model/list-internal-coating.model';
import { getEntities as getListInternalCoatings } from 'app/entities/list-internal-coating/list-internal-coating.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListNominalWallThickness } from 'app/shared/model/list-nominal-wall-thickness.model';
import { getEntities as getListNominalWallThicknesses } from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListPipeManufacturer } from 'app/shared/model/list-pipe-manufacturer.model';
import { getEntities as getListPipeManufacturers } from 'app/entities/list-pipe-manufacturer/list-pipe-manufacturer.reducer';
import { IListPipeSpecification } from 'app/shared/model/list-pipe-specification.model';
import { getEntities as getListPipeSpecifications } from 'app/entities/list-pipe-specification/list-pipe-specification.reducer';
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
import { getEntity, updateEntity, createEntity, reset } from './pipe-hist.reducer';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPipeHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPipeHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idInternalCoatTypeId: string;
  idExternalCoatTypeId: string;
  idNominalWallThicknessId: string;
  idPipeJointId: string;
  idManufacturerId: string;
  idSpecificationId: string;
  idLongSeamWeldTypeId: string;
  idFabricationTypeId: string;
  idMaterialId: string;
  idMillLocationId: string;
  idSteelGradeId: string;
  idStatusId: string;
}

export class PipeHistUpdate extends React.Component<IPipeHistUpdateProps, IPipeHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineSectionId: '0',
      idInternalCoatTypeId: '0',
      idExternalCoatTypeId: '0',
      idNominalWallThicknessId: '0',
      idPipeJointId: '0',
      idManufacturerId: '0',
      idSpecificationId: '0',
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

    this.props.getPipes();
    this.props.getPipelineSections();
    this.props.getListInternalCoatings();
    this.props.getListExternalCoatings();
    this.props.getListNominalWallThicknesses();
    this.props.getPipejoints();
    this.props.getListPipeManufacturers();
    this.props.getListPipeSpecifications();
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
      const { pipeHistEntity } = this.props;
      const entity = {
        ...pipeHistEntity,
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
    this.props.history.push('/entity/pipe-hist');
  };

  render() {
    const {
      pipeHistEntity,
      pipes,
      pipelineSections,
      listInternalCoatings,
      listExternalCoatings,
      listNominalWallThicknesses,
      pipejoints,
      listPipeManufacturers,
      listPipeSpecifications,
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
            <h2 id="jhipsterSampleApplicationApp.pipeHist.home.createOrEditLabel">Create or edit a PipeHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pipeHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pipe-hist-id">ID</Label>
                    <AvInput id="pipe-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="pipe-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="pipe-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="pipe-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="pipe-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="pipe-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="pipe-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="numLabel" for="pipe-hist-num">
                    Num
                  </Label>
                  <AvField
                    id="pipe-hist-num"
                    type="text"
                    name="num"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelLabel" for="pipe-hist-diameterOuterSteel">
                    Diameter Outer Steel
                  </Label>
                  <AvField id="pipe-hist-diameterOuterSteel" type="string" className="form-control" name="diameterOuterSteel" />
                </AvGroup>
                <AvGroup>
                  <Label id="internalCoatThicknessLabel" for="pipe-hist-internalCoatThickness">
                    Internal Coat Thickness
                  </Label>
                  <AvField id="pipe-hist-internalCoatThickness" type="text" name="internalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="pipe-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="pipe-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="isConcrCoatingLabel" for="pipe-hist-isConcrCoating">
                    Is Concr Coating
                  </Label>
                  <AvField id="pipe-hist-isConcrCoating" type="string" className="form-control" name="isConcrCoating" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatThicknessLabel" for="pipe-hist-concrCoatThickness">
                    Concr Coat Thickness
                  </Label>
                  <AvField id="pipe-hist-concrCoatThickness" type="text" name="concrCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatDensityLabel" for="pipe-hist-concrCoatDensity">
                    Concr Coat Density
                  </Label>
                  <AvField id="pipe-hist-concrCoatDensity" type="text" name="concrCoatDensity" />
                </AvGroup>
                <AvGroup>
                  <Label id="measWallThicknessLabel" for="pipe-hist-measWallThickness">
                    Meas Wall Thickness
                  </Label>
                  <AvField id="pipe-hist-measWallThickness" type="text" name="measWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="pipe-hist-length">
                    Length
                  </Label>
                  <AvField id="pipe-hist-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="pipe-hist-weight">
                    Weight
                  </Label>
                  <AvField id="pipe-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="smtsLabel" for="pipe-hist-smts">
                    Smts
                  </Label>
                  <AvField id="pipe-hist-smts" type="text" name="smts" />
                </AvGroup>
                <AvGroup>
                  <Label id="smysLabel" for="pipe-hist-smys">
                    Smys
                  </Label>
                  <AvField id="pipe-hist-smys" type="text" name="smys" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdbmLabel" for="pipe-hist-sdbm">
                    Sdbm
                  </Label>
                  <AvField id="pipe-hist-sdbm" type="text" name="sdbm" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdafLabel" for="pipe-hist-sdaf">
                    Sdaf
                  </Label>
                  <AvField id="pipe-hist-sdaf" type="text" name="sdaf" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdcsLabel" for="pipe-hist-sdcs">
                    Sdcs
                  </Label>
                  <AvField id="pipe-hist-sdcs" type="text" name="sdcs" />
                </AvGroup>
                <AvGroup>
                  <Label id="allowTensStrainLabel" for="pipe-hist-allowTensStrain">
                    Allow Tens Strain
                  </Label>
                  <AvField id="pipe-hist-allowTensStrain" type="text" name="allowTensStrain" />
                </AvGroup>
                <AvGroup>
                  <Label id="corrosionAllowanceLabel" for="pipe-hist-corrosionAllowance">
                    Corrosion Allowance
                  </Label>
                  <AvField id="pipe-hist-corrosionAllowance" type="string" className="form-control" name="corrosionAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="fabricationAllowanceLabel" for="pipe-hist-fabricationAllowance">
                    Fabrication Allowance
                  </Label>
                  <AvField id="pipe-hist-fabricationAllowance" type="string" className="form-control" name="fabricationAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="isBurialLabel" for="pipe-hist-isBurial">
                    Is Burial
                  </Label>
                  <AvField id="pipe-hist-isBurial" type="string" className="form-control" name="isBurial" />
                </AvGroup>
                <AvGroup>
                  <Label id="burialDepthLabel" for="pipe-hist-burialDepth">
                    Burial Depth
                  </Label>
                  <AvField id="pipe-hist-burialDepth" type="string" className="form-control" name="burialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="factBurialDepthLabel" for="pipe-hist-factBurialDepth">
                    Fact Burial Depth
                  </Label>
                  <AvField id="pipe-hist-factBurialDepth" type="string" className="form-control" name="factBurialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateManufacturedLabel" for="pipe-hist-dateManufactured">
                    Date Manufactured
                  </Label>
                  <AvField id="pipe-hist-dateManufactured" type="date" className="form-control" name="dateManufactured" />
                </AvGroup>
                <AvGroup>
                  <Label id="millTestPressureLabel" for="pipe-hist-millTestPressure">
                    Mill Test Pressure
                  </Label>
                  <AvField id="pipe-hist-millTestPressure" type="text" name="millTestPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureLabel" for="pipe-hist-designPressure">
                    Design Pressure
                  </Label>
                  <AvField id="pipe-hist-designPressure" type="text" name="designPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="incidentalPressureLabel" for="pipe-hist-incidentalPressure">
                    Incidental Pressure
                  </Label>
                  <AvField id="pipe-hist-incidentalPressure" type="text" name="incidentalPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateInstalledLabel" for="pipe-hist-dateInstalled">
                    Date Installed
                  </Label>
                  <AvField id="pipe-hist-dateInstalled" type="date" className="form-control" name="dateInstalled" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamOrientLabel" for="pipe-hist-seamOrient">
                    Seam Orient
                  </Label>
                  <AvField id="pipe-hist-seamOrient" type="text" name="seamOrient" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamAngleLabel" for="pipe-hist-seamAngle">
                    Seam Angle
                  </Label>
                  <AvField id="pipe-hist-seamAngle" type="text" name="seamAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="azimuthLabel" for="pipe-hist-azimuth">
                    Azimuth
                  </Label>
                  <AvField id="pipe-hist-azimuth" type="text" name="azimuth" />
                </AvGroup>
                <AvGroup>
                  <Label id="seabInstallTempLabel" for="pipe-hist-seabInstallTemp">
                    Seab Install Temp
                  </Label>
                  <AvField id="pipe-hist-seabInstallTemp" type="text" name="seabInstallTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="verticalAngleLabel" for="pipe-hist-verticalAngle">
                    Vertical Angle
                  </Label>
                  <AvField id="pipe-hist-verticalAngle" type="text" name="verticalAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatTreatDuratLabel" for="pipe-hist-heatTreatDurat">
                    Heat Treat Durat
                  </Label>
                  <AvField id="pipe-hist-heatTreatDurat" type="text" name="heatTreatDurat" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxDesignTempLabel" for="pipe-hist-maxDesignTemp">
                    Max Design Temp
                  </Label>
                  <AvField id="pipe-hist-maxDesignTemp" type="text" name="maxDesignTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatNumberLabel" for="pipe-hist-heatNumber">
                    Heat Number
                  </Label>
                  <AvField
                    id="pipe-hist-heatNumber"
                    type="text"
                    name="heatNumber"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="pipe-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="pipe-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designCoordLabel" for="pipe-hist-designCoord">
                    Design Coord
                  </Label>
                  <AvField
                    id="pipe-hist-designCoord"
                    type="text"
                    name="designCoord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="pipe-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="pipe-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="pipe-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="pipe-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="pipe-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="pipe-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="pipe-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="pipe-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="pipe-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="pipe-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="pipe-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="pipe-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipeHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="pipe-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="pipe-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipeHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="pipe-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="pipe-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="pipe-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="pipe-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="pipe-hist-id">Id</Label>
                  <AvInput id="pipe-hist-id" type="select" className="form-control" name="idId" required>
                    {pipes
                      ? pipes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="pipe-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="pipe-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="pipe-hist-idInternalCoatType">Id Internal Coat Type</Label>
                  <AvInput id="pipe-hist-idInternalCoatType" type="select" className="form-control" name="idInternalCoatTypeId">
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
                  <Label for="pipe-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput id="pipe-hist-idExternalCoatType" type="select" className="form-control" name="idExternalCoatTypeId">
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
                  <Label for="pipe-hist-idNominalWallThickness">Id Nominal Wall Thickness</Label>
                  <AvInput id="pipe-hist-idNominalWallThickness" type="select" className="form-control" name="idNominalWallThicknessId">
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
                  <Label for="pipe-hist-idPipeJoint">Id Pipe Joint</Label>
                  <AvInput id="pipe-hist-idPipeJoint" type="select" className="form-control" name="idPipeJointId">
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
                  <Label for="pipe-hist-idManufacturer">Id Manufacturer</Label>
                  <AvInput id="pipe-hist-idManufacturer" type="select" className="form-control" name="idManufacturerId">
                    <option value="" key="0" />
                    {listPipeManufacturers
                      ? listPipeManufacturers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipe-hist-idSpecification">Id Specification</Label>
                  <AvInput id="pipe-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listPipeSpecifications
                      ? listPipeSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipe-hist-idLongSeamWeldType">Id Long Seam Weld Type</Label>
                  <AvInput id="pipe-hist-idLongSeamWeldType" type="select" className="form-control" name="idLongSeamWeldTypeId">
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
                  <Label for="pipe-hist-idFabricationType">Id Fabrication Type</Label>
                  <AvInput id="pipe-hist-idFabricationType" type="select" className="form-control" name="idFabricationTypeId">
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
                  <Label for="pipe-hist-idMaterial">Id Material</Label>
                  <AvInput id="pipe-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
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
                  <Label for="pipe-hist-idMillLocation">Id Mill Location</Label>
                  <AvInput id="pipe-hist-idMillLocation" type="select" className="form-control" name="idMillLocationId">
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
                  <Label for="pipe-hist-idSteelGrade">Id Steel Grade</Label>
                  <AvInput id="pipe-hist-idSteelGrade" type="select" className="form-control" name="idSteelGradeId">
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
                  <Label for="pipe-hist-idStatus">Id Status</Label>
                  <AvInput id="pipe-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/pipe-hist" replace color="info">
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
  pipes: storeState.pipe.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listInternalCoatings: storeState.listInternalCoating.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listNominalWallThicknesses: storeState.listNominalWallThickness.entities,
  pipejoints: storeState.pipejoint.entities,
  listPipeManufacturers: storeState.listPipeManufacturer.entities,
  listPipeSpecifications: storeState.listPipeSpecification.entities,
  listLongSeamWeldTypes: storeState.listLongSeamWeldType.entities,
  listFabricationTypes: storeState.listFabricationType.entities,
  listMaterials: storeState.listMaterial.entities,
  listMillLocations: storeState.listMillLocation.entities,
  listSteelGrades: storeState.listSteelGrade.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  pipeHistEntity: storeState.pipeHist.entity,
  loading: storeState.pipeHist.loading,
  updating: storeState.pipeHist.updating,
  updateSuccess: storeState.pipeHist.updateSuccess
});

const mapDispatchToProps = {
  getPipes,
  getPipelineSections,
  getListInternalCoatings,
  getListExternalCoatings,
  getListNominalWallThicknesses,
  getPipejoints,
  getListPipeManufacturers,
  getListPipeSpecifications,
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
)(PipeHistUpdate);
