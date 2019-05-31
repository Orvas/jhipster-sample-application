import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './buckle-arrestor-hist.reducer';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBuckleArrestorHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BuckleArrestorHistDetail extends React.Component<IBuckleArrestorHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { buckleArrestorHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            BuckleArrestorHist [<b>{buckleArrestorHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{buckleArrestorHistEntity.idWrk}</dd>
            <dt>
              <span id="serialNum">Serial Num</span>
            </dt>
            <dd>{buckleArrestorHistEntity.serialNum}</dd>
            <dt>
              <span id="diameterOuterSteel">Diameter Outer Steel</span>
            </dt>
            <dd>{buckleArrestorHistEntity.diameterOuterSteel}</dd>
            <dt>
              <span id="internalCoatThickness">Internal Coat Thickness</span>
            </dt>
            <dd>{buckleArrestorHistEntity.internalCoatThickness}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{buckleArrestorHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="isConcrCoating">Is Concr Coating</span>
            </dt>
            <dd>{buckleArrestorHistEntity.isConcrCoating}</dd>
            <dt>
              <span id="concrCoatThickness">Concr Coat Thickness</span>
            </dt>
            <dd>{buckleArrestorHistEntity.concrCoatThickness}</dd>
            <dt>
              <span id="concrCoatDensity">Concr Coat Density</span>
            </dt>
            <dd>{buckleArrestorHistEntity.concrCoatDensity}</dd>
            <dt>
              <span id="measColWallThickness">Meas Col Wall Thickness</span>
            </dt>
            <dd>{buckleArrestorHistEntity.measColWallThickness}</dd>
            <dt>
              <span id="measPipeWallThickness">Meas Pipe Wall Thickness</span>
            </dt>
            <dd>{buckleArrestorHistEntity.measPipeWallThickness}</dd>
            <dt>
              <span id="colLength">Col Length</span>
            </dt>
            <dd>{buckleArrestorHistEntity.colLength}</dd>
            <dt>
              <span id="pipeLength">Pipe Length</span>
            </dt>
            <dd>{buckleArrestorHistEntity.pipeLength}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{buckleArrestorHistEntity.weight}</dd>
            <dt>
              <span id="smts">Smts</span>
            </dt>
            <dd>{buckleArrestorHistEntity.smts}</dd>
            <dt>
              <span id="smys">Smys</span>
            </dt>
            <dd>{buckleArrestorHistEntity.smys}</dd>
            <dt>
              <span id="sdbm">Sdbm</span>
            </dt>
            <dd>{buckleArrestorHistEntity.sdbm}</dd>
            <dt>
              <span id="sdaf">Sdaf</span>
            </dt>
            <dd>{buckleArrestorHistEntity.sdaf}</dd>
            <dt>
              <span id="sdcs">Sdcs</span>
            </dt>
            <dd>{buckleArrestorHistEntity.sdcs}</dd>
            <dt>
              <span id="allowTensStrain">Allow Tens Strain</span>
            </dt>
            <dd>{buckleArrestorHistEntity.allowTensStrain}</dd>
            <dt>
              <span id="corrosionAllowance">Corrosion Allowance</span>
            </dt>
            <dd>{buckleArrestorHistEntity.corrosionAllowance}</dd>
            <dt>
              <span id="fabricationAllowance">Fabrication Allowance</span>
            </dt>
            <dd>{buckleArrestorHistEntity.fabricationAllowance}</dd>
            <dt>
              <span id="isBurial">Is Burial</span>
            </dt>
            <dd>{buckleArrestorHistEntity.isBurial}</dd>
            <dt>
              <span id="burialDepth">Burial Depth</span>
            </dt>
            <dd>{buckleArrestorHistEntity.burialDepth}</dd>
            <dt>
              <span id="factBurialDepth">Fact Burial Depth</span>
            </dt>
            <dd>{buckleArrestorHistEntity.factBurialDepth}</dd>
            <dt>
              <span id="dateManufactured">Date Manufactured</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateManufactured} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="millTestPressure">Mill Test Pressure</span>
            </dt>
            <dd>{buckleArrestorHistEntity.millTestPressure}</dd>
            <dt>
              <span id="designPressure">Design Pressure</span>
            </dt>
            <dd>{buckleArrestorHistEntity.designPressure}</dd>
            <dt>
              <span id="incidentalPressure">Incidental Pressure</span>
            </dt>
            <dd>{buckleArrestorHistEntity.incidentalPressure}</dd>
            <dt>
              <span id="dateInstalled">Date Installed</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateInstalled} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="seamOrient">Seam Orient</span>
            </dt>
            <dd>{buckleArrestorHistEntity.seamOrient}</dd>
            <dt>
              <span id="seamAngle">Seam Angle</span>
            </dt>
            <dd>{buckleArrestorHistEntity.seamAngle}</dd>
            <dt>
              <span id="azimuth">Azimuth</span>
            </dt>
            <dd>{buckleArrestorHistEntity.azimuth}</dd>
            <dt>
              <span id="seabInstallTemp">Seab Install Temp</span>
            </dt>
            <dd>{buckleArrestorHistEntity.seabInstallTemp}</dd>
            <dt>
              <span id="verticalAngle">Vertical Angle</span>
            </dt>
            <dd>{buckleArrestorHistEntity.verticalAngle}</dd>
            <dt>
              <span id="heatTreatDurat">Heat Treat Durat</span>
            </dt>
            <dd>{buckleArrestorHistEntity.heatTreatDurat}</dd>
            <dt>
              <span id="maxDesignTemp">Max Design Temp</span>
            </dt>
            <dd>{buckleArrestorHistEntity.maxDesignTemp}</dd>
            <dt>
              <span id="heatNumber">Heat Number</span>
            </dt>
            <dd>{buckleArrestorHistEntity.heatNumber}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{buckleArrestorHistEntity.coord}</dd>
            <dt>
              <span id="designCoord">Design Coord</span>
            </dt>
            <dd>{buckleArrestorHistEntity.designCoord}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{buckleArrestorHistEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{buckleArrestorHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{buckleArrestorHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="idStatus">Id Status</span>
            </dt>
            <dd>{buckleArrestorHistEntity.idStatus}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{buckleArrestorHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{buckleArrestorHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={buckleArrestorHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{buckleArrestorHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{buckleArrestorHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{buckleArrestorHistEntity.idId ? buckleArrestorHistEntity.idId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{buckleArrestorHistEntity.idPipelineSectionId ? buckleArrestorHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Type</dt>
            <dd>{buckleArrestorHistEntity.idTypeId ? buckleArrestorHistEntity.idTypeId : ''}</dd>
            <dt>Id Internal Coat Type</dt>
            <dd>{buckleArrestorHistEntity.idInternalCoatTypeId ? buckleArrestorHistEntity.idInternalCoatTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{buckleArrestorHistEntity.idExternalCoatTypeId ? buckleArrestorHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Nominal Wall Thickness</dt>
            <dd>{buckleArrestorHistEntity.idNominalWallThicknessId ? buckleArrestorHistEntity.idNominalWallThicknessId : ''}</dd>
            <dt>Id Pipe Joint</dt>
            <dd>{buckleArrestorHistEntity.idPipeJointId ? buckleArrestorHistEntity.idPipeJointId : ''}</dd>
            <dt>Id Manufacturer</dt>
            <dd>{buckleArrestorHistEntity.idManufacturerId ? buckleArrestorHistEntity.idManufacturerId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{buckleArrestorHistEntity.idSpecificationId ? buckleArrestorHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Long Seam Weld Type</dt>
            <dd>{buckleArrestorHistEntity.idLongSeamWeldTypeId ? buckleArrestorHistEntity.idLongSeamWeldTypeId : ''}</dd>
            <dt>Id Fabrication Type</dt>
            <dd>{buckleArrestorHistEntity.idFabricationTypeId ? buckleArrestorHistEntity.idFabricationTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{buckleArrestorHistEntity.idMaterialId ? buckleArrestorHistEntity.idMaterialId : ''}</dd>
            <dt>Id Mill Location</dt>
            <dd>{buckleArrestorHistEntity.idMillLocationId ? buckleArrestorHistEntity.idMillLocationId : ''}</dd>
            <dt>Id Steel Grade</dt>
            <dd>{buckleArrestorHistEntity.idSteelGradeId ? buckleArrestorHistEntity.idSteelGradeId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/buckle-arrestor-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/buckle-arrestor-hist/${buckleArrestorHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ buckleArrestorHist }: IRootState) => ({
  buckleArrestorHistEntity: buckleArrestorHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BuckleArrestorHistDetail);
