import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBend } from 'app/shared/model/bend.model';
import { getEntities as getBends } from 'app/entities/bend/bend.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListBendType } from 'app/shared/model/list-bend-type.model';
import { getEntities as getListBendTypes } from 'app/entities/list-bend-type/list-bend-type.reducer';
import { IListInternalCoating } from 'app/shared/model/list-internal-coating.model';
import { getEntities as getListInternalCoatings } from 'app/entities/list-internal-coating/list-internal-coating.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListNominalWallThickness } from 'app/shared/model/list-nominal-wall-thickness.model';
import { getEntities as getListNominalWallThicknesses } from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListBendManufacturer } from 'app/shared/model/list-bend-manufacturer.model';
import { getEntities as getListBendManufacturers } from 'app/entities/list-bend-manufacturer/list-bend-manufacturer.reducer';
import { IListBendSpecification } from 'app/shared/model/list-bend-specification.model';
import { getEntities as getListBendSpecifications } from 'app/entities/list-bend-specification/list-bend-specification.reducer';
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
import { getEntity, updateEntity, createEntity, reset } from './bend-hist.reducer';
import { IBendHist } from 'app/shared/model/bend-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBendHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBendHistUpdateState {
  isNew: boolean;
  bendId: string;
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

export class BendHistUpdate extends React.Component<IBendHistUpdateProps, IBendHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      bendId: '0',
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

    this.props.getBends();
    this.props.getPipelineSections();
    this.props.getListBendTypes();
    this.props.getListInternalCoatings();
    this.props.getListExternalCoatings();
    this.props.getListNominalWallThicknesses();
    this.props.getPipejoints();
    this.props.getListBendManufacturers();
    this.props.getListBendSpecifications();
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
      const { bendHistEntity } = this.props;
      const entity = {
        ...bendHistEntity,
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
    this.props.history.push('/entity/bend-hist');
  };

  render() {
    const {
      bendHistEntity,
      bends,
      pipelineSections,
      listBendTypes,
      listInternalCoatings,
      listExternalCoatings,
      listNominalWallThicknesses,
      pipejoints,
      listBendManufacturers,
      listBendSpecifications,
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
            <h2 id="jhipsterSampleApplicationApp.bendHist.home.createOrEditLabel">Create or edit a BendHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bendHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="bend-hist-id">ID</Label>
                    <AvInput id="bend-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="bend-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="bend-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="bend-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="bend-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="bend-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="bend-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="numLabel" for="bend-hist-num">
                    Num
                  </Label>
                  <AvField
                    id="bend-hist-num"
                    type="text"
                    name="num"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="radiusLabel" for="bend-hist-radius">
                    Radius
                  </Label>
                  <AvField id="bend-hist-radius" type="text" name="radius" />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelLabel" for="bend-hist-diameterOuterSteel">
                    Diameter Outer Steel
                  </Label>
                  <AvField id="bend-hist-diameterOuterSteel" type="string" className="form-control" name="diameterOuterSteel" />
                </AvGroup>
                <AvGroup>
                  <Label id="internalCoatThicknessLabel" for="bend-hist-internalCoatThickness">
                    Internal Coat Thickness
                  </Label>
                  <AvField id="bend-hist-internalCoatThickness" type="text" name="internalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="bend-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="bend-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="isConcrCoatingLabel" for="bend-hist-isConcrCoating">
                    Is Concr Coating
                  </Label>
                  <AvField id="bend-hist-isConcrCoating" type="string" className="form-control" name="isConcrCoating" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatThicknessLabel" for="bend-hist-concrCoatThickness">
                    Concr Coat Thickness
                  </Label>
                  <AvField id="bend-hist-concrCoatThickness" type="text" name="concrCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatDensityLabel" for="bend-hist-concrCoatDensity">
                    Concr Coat Density
                  </Label>
                  <AvField id="bend-hist-concrCoatDensity" type="text" name="concrCoatDensity" />
                </AvGroup>
                <AvGroup>
                  <Label id="measWallThicknessLabel" for="bend-hist-measWallThickness">
                    Meas Wall Thickness
                  </Label>
                  <AvField id="bend-hist-measWallThickness" type="text" name="measWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="bend-hist-length">
                    Length
                  </Label>
                  <AvField id="bend-hist-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="bend-hist-weight">
                    Weight
                  </Label>
                  <AvField id="bend-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="smtsLabel" for="bend-hist-smts">
                    Smts
                  </Label>
                  <AvField id="bend-hist-smts" type="text" name="smts" />
                </AvGroup>
                <AvGroup>
                  <Label id="smysLabel" for="bend-hist-smys">
                    Smys
                  </Label>
                  <AvField id="bend-hist-smys" type="text" name="smys" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdbmLabel" for="bend-hist-sdbm">
                    Sdbm
                  </Label>
                  <AvField id="bend-hist-sdbm" type="text" name="sdbm" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdafLabel" for="bend-hist-sdaf">
                    Sdaf
                  </Label>
                  <AvField id="bend-hist-sdaf" type="text" name="sdaf" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdcsLabel" for="bend-hist-sdcs">
                    Sdcs
                  </Label>
                  <AvField id="bend-hist-sdcs" type="text" name="sdcs" />
                </AvGroup>
                <AvGroup>
                  <Label id="allowTensStrainLabel" for="bend-hist-allowTensStrain">
                    Allow Tens Strain
                  </Label>
                  <AvField id="bend-hist-allowTensStrain" type="text" name="allowTensStrain" />
                </AvGroup>
                <AvGroup>
                  <Label id="corrosionAllowanceLabel" for="bend-hist-corrosionAllowance">
                    Corrosion Allowance
                  </Label>
                  <AvField id="bend-hist-corrosionAllowance" type="string" className="form-control" name="corrosionAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="fabricationAllowanceLabel" for="bend-hist-fabricationAllowance">
                    Fabrication Allowance
                  </Label>
                  <AvField id="bend-hist-fabricationAllowance" type="string" className="form-control" name="fabricationAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="isBurialLabel" for="bend-hist-isBurial">
                    Is Burial
                  </Label>
                  <AvField id="bend-hist-isBurial" type="string" className="form-control" name="isBurial" />
                </AvGroup>
                <AvGroup>
                  <Label id="burialDepthLabel" for="bend-hist-burialDepth">
                    Burial Depth
                  </Label>
                  <AvField id="bend-hist-burialDepth" type="string" className="form-control" name="burialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="factBurialDepthLabel" for="bend-hist-factBurialDepth">
                    Fact Burial Depth
                  </Label>
                  <AvField id="bend-hist-factBurialDepth" type="string" className="form-control" name="factBurialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateManufacturedLabel" for="bend-hist-dateManufactured">
                    Date Manufactured
                  </Label>
                  <AvField id="bend-hist-dateManufactured" type="date" className="form-control" name="dateManufactured" />
                </AvGroup>
                <AvGroup>
                  <Label id="millTestPressureLabel" for="bend-hist-millTestPressure">
                    Mill Test Pressure
                  </Label>
                  <AvField id="bend-hist-millTestPressure" type="text" name="millTestPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureLabel" for="bend-hist-designPressure">
                    Design Pressure
                  </Label>
                  <AvField id="bend-hist-designPressure" type="text" name="designPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="incidentalPressureLabel" for="bend-hist-incidentalPressure">
                    Incidental Pressure
                  </Label>
                  <AvField id="bend-hist-incidentalPressure" type="text" name="incidentalPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateInstalledLabel" for="bend-hist-dateInstalled">
                    Date Installed
                  </Label>
                  <AvField id="bend-hist-dateInstalled" type="date" className="form-control" name="dateInstalled" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamOrientLabel" for="bend-hist-seamOrient">
                    Seam Orient
                  </Label>
                  <AvField id="bend-hist-seamOrient" type="text" name="seamOrient" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamAngleLabel" for="bend-hist-seamAngle">
                    Seam Angle
                  </Label>
                  <AvField id="bend-hist-seamAngle" type="text" name="seamAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="azimuthLabel" for="bend-hist-azimuth">
                    Azimuth
                  </Label>
                  <AvField id="bend-hist-azimuth" type="text" name="azimuth" />
                </AvGroup>
                <AvGroup>
                  <Label id="seabInstallTempLabel" for="bend-hist-seabInstallTemp">
                    Seab Install Temp
                  </Label>
                  <AvField id="bend-hist-seabInstallTemp" type="text" name="seabInstallTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="verticalAngleLabel" for="bend-hist-verticalAngle">
                    Vertical Angle
                  </Label>
                  <AvField id="bend-hist-verticalAngle" type="text" name="verticalAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatTreatDuratLabel" for="bend-hist-heatTreatDurat">
                    Heat Treat Durat
                  </Label>
                  <AvField id="bend-hist-heatTreatDurat" type="text" name="heatTreatDurat" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxDesignTempLabel" for="bend-hist-maxDesignTemp">
                    Max Design Temp
                  </Label>
                  <AvField id="bend-hist-maxDesignTemp" type="text" name="maxDesignTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatNumberLabel" for="bend-hist-heatNumber">
                    Heat Number
                  </Label>
                  <AvField
                    id="bend-hist-heatNumber"
                    type="text"
                    name="heatNumber"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="bend-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="bend-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designCoordLabel" for="bend-hist-designCoord">
                    Design Coord
                  </Label>
                  <AvField id="bend-hist-designCoord" type="text" name="designCoord" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="bend-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="bend-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="bend-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="bend-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="bend-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="bend-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="bend-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="bend-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="bend-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="bend-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="bend-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="bend-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.bendHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="bend-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="bend-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.bendHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="bend-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="bend-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="bend-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="bend-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="bend-hist-bend">Bend</Label>
                  <AvInput id="bend-hist-bend" type="select" className="form-control" name="bendId">
                    <option value="" key="0" />
                    {bends
                      ? bends.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="bend-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="bend-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="bend-hist-idType">Id Type</Label>
                  <AvInput id="bend-hist-idType" type="select" className="form-control" name="idTypeId">
                    <option value="" key="0" />
                    {listBendTypes
                      ? listBendTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="bend-hist-idInternalCoatType">Id Internal Coat Type</Label>
                  <AvInput id="bend-hist-idInternalCoatType" type="select" className="form-control" name="idInternalCoatTypeId">
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
                  <Label for="bend-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput id="bend-hist-idExternalCoatType" type="select" className="form-control" name="idExternalCoatTypeId">
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
                  <Label for="bend-hist-idNominalWallThickness">Id Nominal Wall Thickness</Label>
                  <AvInput id="bend-hist-idNominalWallThickness" type="select" className="form-control" name="idNominalWallThicknessId">
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
                  <Label for="bend-hist-idPipeJoint">Id Pipe Joint</Label>
                  <AvInput id="bend-hist-idPipeJoint" type="select" className="form-control" name="idPipeJointId">
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
                  <Label for="bend-hist-idManufacturer">Id Manufacturer</Label>
                  <AvInput id="bend-hist-idManufacturer" type="select" className="form-control" name="idManufacturerId">
                    <option value="" key="0" />
                    {listBendManufacturers
                      ? listBendManufacturers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="bend-hist-idSpecification">Id Specification</Label>
                  <AvInput id="bend-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listBendSpecifications
                      ? listBendSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="bend-hist-idLongSeamWeldType">Id Long Seam Weld Type</Label>
                  <AvInput id="bend-hist-idLongSeamWeldType" type="select" className="form-control" name="idLongSeamWeldTypeId">
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
                  <Label for="bend-hist-idFabricationType">Id Fabrication Type</Label>
                  <AvInput id="bend-hist-idFabricationType" type="select" className="form-control" name="idFabricationTypeId">
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
                  <Label for="bend-hist-idMaterial">Id Material</Label>
                  <AvInput id="bend-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
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
                  <Label for="bend-hist-idMillLocation">Id Mill Location</Label>
                  <AvInput id="bend-hist-idMillLocation" type="select" className="form-control" name="idMillLocationId">
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
                  <Label for="bend-hist-idSteelGrade">Id Steel Grade</Label>
                  <AvInput id="bend-hist-idSteelGrade" type="select" className="form-control" name="idSteelGradeId">
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
                  <Label for="bend-hist-idStatus">Id Status</Label>
                  <AvInput id="bend-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/bend-hist" replace color="info">
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
  bends: storeState.bend.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listBendTypes: storeState.listBendType.entities,
  listInternalCoatings: storeState.listInternalCoating.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listNominalWallThicknesses: storeState.listNominalWallThickness.entities,
  pipejoints: storeState.pipejoint.entities,
  listBendManufacturers: storeState.listBendManufacturer.entities,
  listBendSpecifications: storeState.listBendSpecification.entities,
  listLongSeamWeldTypes: storeState.listLongSeamWeldType.entities,
  listFabricationTypes: storeState.listFabricationType.entities,
  listMaterials: storeState.listMaterial.entities,
  listMillLocations: storeState.listMillLocation.entities,
  listSteelGrades: storeState.listSteelGrade.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  bendHistEntity: storeState.bendHist.entity,
  loading: storeState.bendHist.loading,
  updating: storeState.bendHist.updating,
  updateSuccess: storeState.bendHist.updateSuccess
});

const mapDispatchToProps = {
  getBends,
  getPipelineSections,
  getListBendTypes,
  getListInternalCoatings,
  getListExternalCoatings,
  getListNominalWallThicknesses,
  getPipejoints,
  getListBendManufacturers,
  getListBendSpecifications,
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
)(BendHistUpdate);
