import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITee } from 'app/shared/model/tee.model';
import { getEntities as getTees } from 'app/entities/tee/tee.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListTeeType } from 'app/shared/model/list-tee-type.model';
import { getEntities as getListTeeTypes } from 'app/entities/list-tee-type/list-tee-type.reducer';
import { IListInternalCoating } from 'app/shared/model/list-internal-coating.model';
import { getEntities as getListInternalCoatings } from 'app/entities/list-internal-coating/list-internal-coating.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListNominalWallThickness } from 'app/shared/model/list-nominal-wall-thickness.model';
import { getEntities as getListNominalWallThicknesses } from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListTeeManufacturer } from 'app/shared/model/list-tee-manufacturer.model';
import { getEntities as getListTeeManufacturers } from 'app/entities/list-tee-manufacturer/list-tee-manufacturer.reducer';
import { IListTeeSpecification } from 'app/shared/model/list-tee-specification.model';
import { getEntities as getListTeeSpecifications } from 'app/entities/list-tee-specification/list-tee-specification.reducer';
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
import { getEntity, updateEntity, createEntity, reset } from './tee-hist.reducer';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITeeHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITeeHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idTypeId: string;
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

export class TeeHistUpdate extends React.Component<ITeeHistUpdateProps, ITeeHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineSectionId: '0',
      idTypeId: '0',
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

    this.props.getTees();
    this.props.getPipelineSections();
    this.props.getListTeeTypes();
    this.props.getListInternalCoatings();
    this.props.getListExternalCoatings();
    this.props.getListNominalWallThicknesses();
    this.props.getPipejoints();
    this.props.getListTeeManufacturers();
    this.props.getListTeeSpecifications();
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
      const { teeHistEntity } = this.props;
      const entity = {
        ...teeHistEntity,
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
    this.props.history.push('/entity/tee-hist');
  };

  render() {
    const {
      teeHistEntity,
      tees,
      pipelineSections,
      listTeeTypes,
      listInternalCoatings,
      listExternalCoatings,
      listNominalWallThicknesses,
      pipejoints,
      listTeeManufacturers,
      listTeeSpecifications,
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
            <h2 id="jhipsterSampleApplicationApp.teeHist.home.createOrEditLabel">Create or edit a TeeHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : teeHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="tee-hist-id">ID</Label>
                    <AvInput id="tee-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="tee-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="tee-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="tee-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="tee-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="tee-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="tee-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="tee-hist-name">
                    Name
                  </Label>
                  <AvField
                    id="tee-hist-name"
                    type="text"
                    name="name"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelLabel" for="tee-hist-diameterOuterSteel">
                    Diameter Outer Steel
                  </Label>
                  <AvField id="tee-hist-diameterOuterSteel" type="string" className="form-control" name="diameterOuterSteel" />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelBrLabel" for="tee-hist-diameterOuterSteelBr">
                    Diameter Outer Steel Br
                  </Label>
                  <AvField id="tee-hist-diameterOuterSteelBr" type="string" className="form-control" name="diameterOuterSteelBr" />
                </AvGroup>
                <AvGroup>
                  <Label id="internalCoatThicknessLabel" for="tee-hist-internalCoatThickness">
                    Internal Coat Thickness
                  </Label>
                  <AvField id="tee-hist-internalCoatThickness" type="text" name="internalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="tee-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="tee-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="isConcrCoatingLabel" for="tee-hist-isConcrCoating">
                    Is Concr Coating
                  </Label>
                  <AvField id="tee-hist-isConcrCoating" type="string" className="form-control" name="isConcrCoating" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatThicknessLabel" for="tee-hist-concrCoatThickness">
                    Concr Coat Thickness
                  </Label>
                  <AvField id="tee-hist-concrCoatThickness" type="text" name="concrCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatDensityLabel" for="tee-hist-concrCoatDensity">
                    Concr Coat Density
                  </Label>
                  <AvField id="tee-hist-concrCoatDensity" type="text" name="concrCoatDensity" />
                </AvGroup>
                <AvGroup>
                  <Label id="measWallThicknessLabel" for="tee-hist-measWallThickness">
                    Meas Wall Thickness
                  </Label>
                  <AvField id="tee-hist-measWallThickness" type="text" name="measWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="tee-hist-length">
                    Length
                  </Label>
                  <AvField id="tee-hist-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="tee-hist-weight">
                    Weight
                  </Label>
                  <AvField id="tee-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="smtsLabel" for="tee-hist-smts">
                    Smts
                  </Label>
                  <AvField id="tee-hist-smts" type="text" name="smts" />
                </AvGroup>
                <AvGroup>
                  <Label id="smysLabel" for="tee-hist-smys">
                    Smys
                  </Label>
                  <AvField id="tee-hist-smys" type="text" name="smys" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdbmLabel" for="tee-hist-sdbm">
                    Sdbm
                  </Label>
                  <AvField id="tee-hist-sdbm" type="text" name="sdbm" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdafLabel" for="tee-hist-sdaf">
                    Sdaf
                  </Label>
                  <AvField id="tee-hist-sdaf" type="text" name="sdaf" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdcsLabel" for="tee-hist-sdcs">
                    Sdcs
                  </Label>
                  <AvField id="tee-hist-sdcs" type="text" name="sdcs" />
                </AvGroup>
                <AvGroup>
                  <Label id="allowTensStrainLabel" for="tee-hist-allowTensStrain">
                    Allow Tens Strain
                  </Label>
                  <AvField id="tee-hist-allowTensStrain" type="text" name="allowTensStrain" />
                </AvGroup>
                <AvGroup>
                  <Label id="corrosionAllowanceLabel" for="tee-hist-corrosionAllowance">
                    Corrosion Allowance
                  </Label>
                  <AvField id="tee-hist-corrosionAllowance" type="string" className="form-control" name="corrosionAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="fabricationAllowanceLabel" for="tee-hist-fabricationAllowance">
                    Fabrication Allowance
                  </Label>
                  <AvField id="tee-hist-fabricationAllowance" type="string" className="form-control" name="fabricationAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="isBurialLabel" for="tee-hist-isBurial">
                    Is Burial
                  </Label>
                  <AvField id="tee-hist-isBurial" type="string" className="form-control" name="isBurial" />
                </AvGroup>
                <AvGroup>
                  <Label id="burialDepthLabel" for="tee-hist-burialDepth">
                    Burial Depth
                  </Label>
                  <AvField id="tee-hist-burialDepth" type="string" className="form-control" name="burialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="factBurialDepthLabel" for="tee-hist-factBurialDepth">
                    Fact Burial Depth
                  </Label>
                  <AvField id="tee-hist-factBurialDepth" type="string" className="form-control" name="factBurialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateManufacturedLabel" for="tee-hist-dateManufactured">
                    Date Manufactured
                  </Label>
                  <AvField id="tee-hist-dateManufactured" type="date" className="form-control" name="dateManufactured" />
                </AvGroup>
                <AvGroup>
                  <Label id="millTestPressureLabel" for="tee-hist-millTestPressure">
                    Mill Test Pressure
                  </Label>
                  <AvField id="tee-hist-millTestPressure" type="text" name="millTestPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureLabel" for="tee-hist-designPressure">
                    Design Pressure
                  </Label>
                  <AvField id="tee-hist-designPressure" type="text" name="designPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="incidentalPressureLabel" for="tee-hist-incidentalPressure">
                    Incidental Pressure
                  </Label>
                  <AvField id="tee-hist-incidentalPressure" type="text" name="incidentalPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateInstalledLabel" for="tee-hist-dateInstalled">
                    Date Installed
                  </Label>
                  <AvField id="tee-hist-dateInstalled" type="date" className="form-control" name="dateInstalled" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamOrientLabel" for="tee-hist-seamOrient">
                    Seam Orient
                  </Label>
                  <AvField id="tee-hist-seamOrient" type="text" name="seamOrient" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamAngleLabel" for="tee-hist-seamAngle">
                    Seam Angle
                  </Label>
                  <AvField id="tee-hist-seamAngle" type="text" name="seamAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="azimuthLabel" for="tee-hist-azimuth">
                    Azimuth
                  </Label>
                  <AvField id="tee-hist-azimuth" type="text" name="azimuth" />
                </AvGroup>
                <AvGroup>
                  <Label id="seabInstallTempLabel" for="tee-hist-seabInstallTemp">
                    Seab Install Temp
                  </Label>
                  <AvField id="tee-hist-seabInstallTemp" type="text" name="seabInstallTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="verticalAngleLabel" for="tee-hist-verticalAngle">
                    Vertical Angle
                  </Label>
                  <AvField id="tee-hist-verticalAngle" type="text" name="verticalAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatTreatDuratLabel" for="tee-hist-heatTreatDurat">
                    Heat Treat Durat
                  </Label>
                  <AvField id="tee-hist-heatTreatDurat" type="text" name="heatTreatDurat" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxDesignTempLabel" for="tee-hist-maxDesignTemp">
                    Max Design Temp
                  </Label>
                  <AvField id="tee-hist-maxDesignTemp" type="text" name="maxDesignTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatNumberLabel" for="tee-hist-heatNumber">
                    Heat Number
                  </Label>
                  <AvField
                    id="tee-hist-heatNumber"
                    type="text"
                    name="heatNumber"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="tee-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="tee-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designCoordLabel" for="tee-hist-designCoord">
                    Design Coord
                  </Label>
                  <AvField id="tee-hist-designCoord" type="text" name="designCoord" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpInstLabel" for="tee-hist-kpInst">
                    Kp Inst
                  </Label>
                  <AvField id="tee-hist-kpInst" type="text" name="kpInst" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="tee-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="tee-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="tee-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="tee-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="tee-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="tee-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="tee-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="tee-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.teeHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="tee-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="tee-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.teeHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="tee-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="tee-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="tee-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="tee-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="tee-hist-id">Id</Label>
                  <AvInput id="tee-hist-id" type="select" className="form-control" name="idId" required>
                    {tees
                      ? tees.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="tee-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="tee-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="tee-hist-idType">Id Type</Label>
                  <AvInput id="tee-hist-idType" type="select" className="form-control" name="idTypeId">
                    <option value="" key="0" />
                    {listTeeTypes
                      ? listTeeTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tee-hist-idInternalCoatType">Id Internal Coat Type</Label>
                  <AvInput id="tee-hist-idInternalCoatType" type="select" className="form-control" name="idInternalCoatTypeId">
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
                  <Label for="tee-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput id="tee-hist-idExternalCoatType" type="select" className="form-control" name="idExternalCoatTypeId">
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
                  <Label for="tee-hist-idNominalWallThickness">Id Nominal Wall Thickness</Label>
                  <AvInput id="tee-hist-idNominalWallThickness" type="select" className="form-control" name="idNominalWallThicknessId">
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
                  <Label for="tee-hist-idPipeJoint">Id Pipe Joint</Label>
                  <AvInput id="tee-hist-idPipeJoint" type="select" className="form-control" name="idPipeJointId">
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
                  <Label for="tee-hist-idManufacturer">Id Manufacturer</Label>
                  <AvInput id="tee-hist-idManufacturer" type="select" className="form-control" name="idManufacturerId">
                    <option value="" key="0" />
                    {listTeeManufacturers
                      ? listTeeManufacturers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tee-hist-idSpecification">Id Specification</Label>
                  <AvInput id="tee-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listTeeSpecifications
                      ? listTeeSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tee-hist-idLongSeamWeldType">Id Long Seam Weld Type</Label>
                  <AvInput id="tee-hist-idLongSeamWeldType" type="select" className="form-control" name="idLongSeamWeldTypeId">
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
                  <Label for="tee-hist-idFabricationType">Id Fabrication Type</Label>
                  <AvInput id="tee-hist-idFabricationType" type="select" className="form-control" name="idFabricationTypeId">
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
                  <Label for="tee-hist-idMaterial">Id Material</Label>
                  <AvInput id="tee-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
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
                  <Label for="tee-hist-idMillLocation">Id Mill Location</Label>
                  <AvInput id="tee-hist-idMillLocation" type="select" className="form-control" name="idMillLocationId">
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
                  <Label for="tee-hist-idSteelGrade">Id Steel Grade</Label>
                  <AvInput id="tee-hist-idSteelGrade" type="select" className="form-control" name="idSteelGradeId">
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
                  <Label for="tee-hist-idStatus">Id Status</Label>
                  <AvInput id="tee-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/tee-hist" replace color="info">
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
  tees: storeState.tee.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listTeeTypes: storeState.listTeeType.entities,
  listInternalCoatings: storeState.listInternalCoating.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listNominalWallThicknesses: storeState.listNominalWallThickness.entities,
  pipejoints: storeState.pipejoint.entities,
  listTeeManufacturers: storeState.listTeeManufacturer.entities,
  listTeeSpecifications: storeState.listTeeSpecification.entities,
  listLongSeamWeldTypes: storeState.listLongSeamWeldType.entities,
  listFabricationTypes: storeState.listFabricationType.entities,
  listMaterials: storeState.listMaterial.entities,
  listMillLocations: storeState.listMillLocation.entities,
  listSteelGrades: storeState.listSteelGrade.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  teeHistEntity: storeState.teeHist.entity,
  loading: storeState.teeHist.loading,
  updating: storeState.teeHist.updating,
  updateSuccess: storeState.teeHist.updateSuccess
});

const mapDispatchToProps = {
  getTees,
  getPipelineSections,
  getListTeeTypes,
  getListInternalCoatings,
  getListExternalCoatings,
  getListNominalWallThicknesses,
  getPipejoints,
  getListTeeManufacturers,
  getListTeeSpecifications,
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
)(TeeHistUpdate);
