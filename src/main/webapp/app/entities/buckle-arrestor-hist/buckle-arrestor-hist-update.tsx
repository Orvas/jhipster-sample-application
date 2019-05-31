import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBuckleArrestor } from 'app/shared/model/buckle-arrestor.model';
import { getEntities as getBuckleArrestors } from 'app/entities/buckle-arrestor/buckle-arrestor.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListBucklarrType } from 'app/shared/model/list-bucklarr-type.model';
import { getEntities as getListBucklarrTypes } from 'app/entities/list-bucklarr-type/list-bucklarr-type.reducer';
import { IListInternalCoating } from 'app/shared/model/list-internal-coating.model';
import { getEntities as getListInternalCoatings } from 'app/entities/list-internal-coating/list-internal-coating.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListNominalWallThickness } from 'app/shared/model/list-nominal-wall-thickness.model';
import { getEntities as getListNominalWallThicknesses } from 'app/entities/list-nominal-wall-thickness/list-nominal-wall-thickness.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListBucklarrManufacturer } from 'app/shared/model/list-bucklarr-manufacturer.model';
import { getEntities as getListBucklarrManufacturers } from 'app/entities/list-bucklarr-manufacturer/list-bucklarr-manufacturer.reducer';
import { IListBucklarrSpecification } from 'app/shared/model/list-bucklarr-specification.model';
import { getEntities as getListBucklarrSpecifications } from 'app/entities/list-bucklarr-specification/list-bucklarr-specification.reducer';
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
import { getEntity, updateEntity, createEntity, reset } from './buckle-arrestor-hist.reducer';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBuckleArrestorHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBuckleArrestorHistUpdateState {
  isNew: boolean;
  buckleArrestorId: string;
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
}

export class BuckleArrestorHistUpdate extends React.Component<IBuckleArrestorHistUpdateProps, IBuckleArrestorHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      buckleArrestorId: '0',
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

    this.props.getBuckleArrestors();
    this.props.getPipelineSections();
    this.props.getListBucklarrTypes();
    this.props.getListInternalCoatings();
    this.props.getListExternalCoatings();
    this.props.getListNominalWallThicknesses();
    this.props.getPipejoints();
    this.props.getListBucklarrManufacturers();
    this.props.getListBucklarrSpecifications();
    this.props.getListLongSeamWeldTypes();
    this.props.getListFabricationTypes();
    this.props.getListMaterials();
    this.props.getListMillLocations();
    this.props.getListSteelGrades();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { buckleArrestorHistEntity } = this.props;
      const entity = {
        ...buckleArrestorHistEntity,
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
    this.props.history.push('/entity/buckle-arrestor-hist');
  };

  render() {
    const {
      buckleArrestorHistEntity,
      buckleArrestors,
      pipelineSections,
      listBucklarrTypes,
      listInternalCoatings,
      listExternalCoatings,
      listNominalWallThicknesses,
      pipejoints,
      listBucklarrManufacturers,
      listBucklarrSpecifications,
      listLongSeamWeldTypes,
      listFabricationTypes,
      listMaterials,
      listMillLocations,
      listSteelGrades,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.buckleArrestorHist.home.createOrEditLabel">Create or edit a BuckleArrestorHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : buckleArrestorHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="buckle-arrestor-hist-id">ID</Label>
                    <AvInput id="buckle-arrestor-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="buckle-arrestor-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="buckle-arrestor-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="buckle-arrestor-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="buckle-arrestor-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="serialNumLabel" for="buckle-arrestor-hist-serialNum">
                    Serial Num
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-serialNum"
                    type="text"
                    name="serialNum"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diameterOuterSteelLabel" for="buckle-arrestor-hist-diameterOuterSteel">
                    Diameter Outer Steel
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-diameterOuterSteel"
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
                  <Label id="internalCoatThicknessLabel" for="buckle-arrestor-hist-internalCoatThickness">
                    Internal Coat Thickness
                  </Label>
                  <AvField id="buckle-arrestor-hist-internalCoatThickness" type="text" name="internalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="buckle-arrestor-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="buckle-arrestor-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="isConcrCoatingLabel" for="buckle-arrestor-hist-isConcrCoating">
                    Is Concr Coating
                  </Label>
                  <AvField id="buckle-arrestor-hist-isConcrCoating" type="string" className="form-control" name="isConcrCoating" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatThicknessLabel" for="buckle-arrestor-hist-concrCoatThickness">
                    Concr Coat Thickness
                  </Label>
                  <AvField id="buckle-arrestor-hist-concrCoatThickness" type="text" name="concrCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="concrCoatDensityLabel" for="buckle-arrestor-hist-concrCoatDensity">
                    Concr Coat Density
                  </Label>
                  <AvField id="buckle-arrestor-hist-concrCoatDensity" type="text" name="concrCoatDensity" />
                </AvGroup>
                <AvGroup>
                  <Label id="measColWallThicknessLabel" for="buckle-arrestor-hist-measColWallThickness">
                    Meas Col Wall Thickness
                  </Label>
                  <AvField id="buckle-arrestor-hist-measColWallThickness" type="text" name="measColWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="measPipeWallThicknessLabel" for="buckle-arrestor-hist-measPipeWallThickness">
                    Meas Pipe Wall Thickness
                  </Label>
                  <AvField id="buckle-arrestor-hist-measPipeWallThickness" type="text" name="measPipeWallThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="colLengthLabel" for="buckle-arrestor-hist-colLength">
                    Col Length
                  </Label>
                  <AvField id="buckle-arrestor-hist-colLength" type="string" className="form-control" name="colLength" />
                </AvGroup>
                <AvGroup>
                  <Label id="pipeLengthLabel" for="buckle-arrestor-hist-pipeLength">
                    Pipe Length
                  </Label>
                  <AvField id="buckle-arrestor-hist-pipeLength" type="string" className="form-control" name="pipeLength" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="buckle-arrestor-hist-weight">
                    Weight
                  </Label>
                  <AvField id="buckle-arrestor-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="smtsLabel" for="buckle-arrestor-hist-smts">
                    Smts
                  </Label>
                  <AvField id="buckle-arrestor-hist-smts" type="text" name="smts" />
                </AvGroup>
                <AvGroup>
                  <Label id="smysLabel" for="buckle-arrestor-hist-smys">
                    Smys
                  </Label>
                  <AvField id="buckle-arrestor-hist-smys" type="text" name="smys" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdbmLabel" for="buckle-arrestor-hist-sdbm">
                    Sdbm
                  </Label>
                  <AvField id="buckle-arrestor-hist-sdbm" type="text" name="sdbm" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdafLabel" for="buckle-arrestor-hist-sdaf">
                    Sdaf
                  </Label>
                  <AvField id="buckle-arrestor-hist-sdaf" type="text" name="sdaf" />
                </AvGroup>
                <AvGroup>
                  <Label id="sdcsLabel" for="buckle-arrestor-hist-sdcs">
                    Sdcs
                  </Label>
                  <AvField id="buckle-arrestor-hist-sdcs" type="text" name="sdcs" />
                </AvGroup>
                <AvGroup>
                  <Label id="allowTensStrainLabel" for="buckle-arrestor-hist-allowTensStrain">
                    Allow Tens Strain
                  </Label>
                  <AvField id="buckle-arrestor-hist-allowTensStrain" type="text" name="allowTensStrain" />
                </AvGroup>
                <AvGroup>
                  <Label id="corrosionAllowanceLabel" for="buckle-arrestor-hist-corrosionAllowance">
                    Corrosion Allowance
                  </Label>
                  <AvField id="buckle-arrestor-hist-corrosionAllowance" type="string" className="form-control" name="corrosionAllowance" />
                </AvGroup>
                <AvGroup>
                  <Label id="fabricationAllowanceLabel" for="buckle-arrestor-hist-fabricationAllowance">
                    Fabrication Allowance
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-fabricationAllowance"
                    type="string"
                    className="form-control"
                    name="fabricationAllowance"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isBurialLabel" for="buckle-arrestor-hist-isBurial">
                    Is Burial
                  </Label>
                  <AvField id="buckle-arrestor-hist-isBurial" type="string" className="form-control" name="isBurial" />
                </AvGroup>
                <AvGroup>
                  <Label id="burialDepthLabel" for="buckle-arrestor-hist-burialDepth">
                    Burial Depth
                  </Label>
                  <AvField id="buckle-arrestor-hist-burialDepth" type="string" className="form-control" name="burialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="factBurialDepthLabel" for="buckle-arrestor-hist-factBurialDepth">
                    Fact Burial Depth
                  </Label>
                  <AvField id="buckle-arrestor-hist-factBurialDepth" type="string" className="form-control" name="factBurialDepth" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateManufacturedLabel" for="buckle-arrestor-hist-dateManufactured">
                    Date Manufactured
                  </Label>
                  <AvField id="buckle-arrestor-hist-dateManufactured" type="date" className="form-control" name="dateManufactured" />
                </AvGroup>
                <AvGroup>
                  <Label id="millTestPressureLabel" for="buckle-arrestor-hist-millTestPressure">
                    Mill Test Pressure
                  </Label>
                  <AvField id="buckle-arrestor-hist-millTestPressure" type="text" name="millTestPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="designPressureLabel" for="buckle-arrestor-hist-designPressure">
                    Design Pressure
                  </Label>
                  <AvField id="buckle-arrestor-hist-designPressure" type="text" name="designPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="incidentalPressureLabel" for="buckle-arrestor-hist-incidentalPressure">
                    Incidental Pressure
                  </Label>
                  <AvField id="buckle-arrestor-hist-incidentalPressure" type="text" name="incidentalPressure" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateInstalledLabel" for="buckle-arrestor-hist-dateInstalled">
                    Date Installed
                  </Label>
                  <AvField id="buckle-arrestor-hist-dateInstalled" type="date" className="form-control" name="dateInstalled" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamOrientLabel" for="buckle-arrestor-hist-seamOrient">
                    Seam Orient
                  </Label>
                  <AvField id="buckle-arrestor-hist-seamOrient" type="text" name="seamOrient" />
                </AvGroup>
                <AvGroup>
                  <Label id="seamAngleLabel" for="buckle-arrestor-hist-seamAngle">
                    Seam Angle
                  </Label>
                  <AvField id="buckle-arrestor-hist-seamAngle" type="text" name="seamAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="azimuthLabel" for="buckle-arrestor-hist-azimuth">
                    Azimuth
                  </Label>
                  <AvField id="buckle-arrestor-hist-azimuth" type="text" name="azimuth" />
                </AvGroup>
                <AvGroup>
                  <Label id="seabInstallTempLabel" for="buckle-arrestor-hist-seabInstallTemp">
                    Seab Install Temp
                  </Label>
                  <AvField id="buckle-arrestor-hist-seabInstallTemp" type="text" name="seabInstallTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="verticalAngleLabel" for="buckle-arrestor-hist-verticalAngle">
                    Vertical Angle
                  </Label>
                  <AvField id="buckle-arrestor-hist-verticalAngle" type="text" name="verticalAngle" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatTreatDuratLabel" for="buckle-arrestor-hist-heatTreatDurat">
                    Heat Treat Durat
                  </Label>
                  <AvField id="buckle-arrestor-hist-heatTreatDurat" type="text" name="heatTreatDurat" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxDesignTempLabel" for="buckle-arrestor-hist-maxDesignTemp">
                    Max Design Temp
                  </Label>
                  <AvField id="buckle-arrestor-hist-maxDesignTemp" type="text" name="maxDesignTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="heatNumberLabel" for="buckle-arrestor-hist-heatNumber">
                    Heat Number
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-heatNumber"
                    type="text"
                    name="heatNumber"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="buckle-arrestor-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designCoordLabel" for="buckle-arrestor-hist-designCoord">
                    Design Coord
                  </Label>
                  <AvField id="buckle-arrestor-hist-designCoord" type="text" name="designCoord" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="buckle-arrestor-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="buckle-arrestor-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="buckle-arrestor-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="buckle-arrestor-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="buckle-arrestor-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-isCurrentFlag"
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
                  <Label id="idStatusLabel" for="buckle-arrestor-hist-idStatus">
                    Id Status
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-idStatus"
                    type="string"
                    className="form-control"
                    name="idStatus"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="buckle-arrestor-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="buckle-arrestor-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="buckle-arrestor-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="buckle-arrestor-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.buckleArrestorHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="buckle-arrestor-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="buckle-arrestor-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.buckleArrestorHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="buckle-arrestor-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="buckle-arrestor-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="buckle-arrestor-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-buckleArrestor">Buckle Arrestor</Label>
                  <AvInput id="buckle-arrestor-hist-buckleArrestor" type="select" className="form-control" name="buckleArrestorId">
                    <option value="" key="0" />
                    {buckleArrestors
                      ? buckleArrestors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput
                    id="buckle-arrestor-hist-idPipelineSection"
                    type="select"
                    className="form-control"
                    name="idPipelineSectionId"
                    required
                  >
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
                  <Label for="buckle-arrestor-hist-idType">Id Type</Label>
                  <AvInput id="buckle-arrestor-hist-idType" type="select" className="form-control" name="idTypeId" required>
                    {listBucklarrTypes
                      ? listBucklarrTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idInternalCoatType">Id Internal Coat Type</Label>
                  <AvInput
                    id="buckle-arrestor-hist-idInternalCoatType"
                    type="select"
                    className="form-control"
                    name="idInternalCoatTypeId"
                    required
                  >
                    {listInternalCoatings
                      ? listInternalCoatings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput
                    id="buckle-arrestor-hist-idExternalCoatType"
                    type="select"
                    className="form-control"
                    name="idExternalCoatTypeId"
                    required
                  >
                    {listExternalCoatings
                      ? listExternalCoatings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idNominalWallThickness">Id Nominal Wall Thickness</Label>
                  <AvInput
                    id="buckle-arrestor-hist-idNominalWallThickness"
                    type="select"
                    className="form-control"
                    name="idNominalWallThicknessId"
                  >
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
                  <Label for="buckle-arrestor-hist-idPipeJoint">Id Pipe Joint</Label>
                  <AvInput id="buckle-arrestor-hist-idPipeJoint" type="select" className="form-control" name="idPipeJointId">
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
                  <Label for="buckle-arrestor-hist-idManufacturer">Id Manufacturer</Label>
                  <AvInput id="buckle-arrestor-hist-idManufacturer" type="select" className="form-control" name="idManufacturerId">
                    <option value="" key="0" />
                    {listBucklarrManufacturers
                      ? listBucklarrManufacturers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idSpecification">Id Specification</Label>
                  <AvInput id="buckle-arrestor-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listBucklarrSpecifications
                      ? listBucklarrSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="buckle-arrestor-hist-idLongSeamWeldType">Id Long Seam Weld Type</Label>
                  <AvInput id="buckle-arrestor-hist-idLongSeamWeldType" type="select" className="form-control" name="idLongSeamWeldTypeId">
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
                  <Label for="buckle-arrestor-hist-idFabricationType">Id Fabrication Type</Label>
                  <AvInput id="buckle-arrestor-hist-idFabricationType" type="select" className="form-control" name="idFabricationTypeId">
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
                  <Label for="buckle-arrestor-hist-idMaterial">Id Material</Label>
                  <AvInput id="buckle-arrestor-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
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
                  <Label for="buckle-arrestor-hist-idMillLocation">Id Mill Location</Label>
                  <AvInput id="buckle-arrestor-hist-idMillLocation" type="select" className="form-control" name="idMillLocationId">
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
                  <Label for="buckle-arrestor-hist-idSteelGrade">Id Steel Grade</Label>
                  <AvInput id="buckle-arrestor-hist-idSteelGrade" type="select" className="form-control" name="idSteelGradeId">
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
                <Button tag={Link} id="cancel-save" to="/entity/buckle-arrestor-hist" replace color="info">
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
  buckleArrestors: storeState.buckleArrestor.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listBucklarrTypes: storeState.listBucklarrType.entities,
  listInternalCoatings: storeState.listInternalCoating.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listNominalWallThicknesses: storeState.listNominalWallThickness.entities,
  pipejoints: storeState.pipejoint.entities,
  listBucklarrManufacturers: storeState.listBucklarrManufacturer.entities,
  listBucklarrSpecifications: storeState.listBucklarrSpecification.entities,
  listLongSeamWeldTypes: storeState.listLongSeamWeldType.entities,
  listFabricationTypes: storeState.listFabricationType.entities,
  listMaterials: storeState.listMaterial.entities,
  listMillLocations: storeState.listMillLocation.entities,
  listSteelGrades: storeState.listSteelGrade.entities,
  buckleArrestorHistEntity: storeState.buckleArrestorHist.entity,
  loading: storeState.buckleArrestorHist.loading,
  updating: storeState.buckleArrestorHist.updating,
  updateSuccess: storeState.buckleArrestorHist.updateSuccess
});

const mapDispatchToProps = {
  getBuckleArrestors,
  getPipelineSections,
  getListBucklarrTypes,
  getListInternalCoatings,
  getListExternalCoatings,
  getListNominalWallThicknesses,
  getPipejoints,
  getListBucklarrManufacturers,
  getListBucklarrSpecifications,
  getListLongSeamWeldTypes,
  getListFabricationTypes,
  getListMaterials,
  getListMillLocations,
  getListSteelGrades,
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
)(BuckleArrestorHistUpdate);
