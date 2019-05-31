import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bend-hist.reducer';
import { IBendHist } from 'app/shared/model/bend-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBendHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BendHistDetail extends React.Component<IBendHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bendHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            BendHist [<b>{bendHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{bendHistEntity.idWrk}</dd>
            <dt>
              <span id="num">Num</span>
            </dt>
            <dd>{bendHistEntity.num}</dd>
            <dt>
              <span id="radius">Radius</span>
            </dt>
            <dd>{bendHistEntity.radius}</dd>
            <dt>
              <span id="diameterOuterSteel">Diameter Outer Steel</span>
            </dt>
            <dd>{bendHistEntity.diameterOuterSteel}</dd>
            <dt>
              <span id="internalCoatThickness">Internal Coat Thickness</span>
            </dt>
            <dd>{bendHistEntity.internalCoatThickness}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{bendHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="isConcrCoating">Is Concr Coating</span>
            </dt>
            <dd>{bendHistEntity.isConcrCoating}</dd>
            <dt>
              <span id="concrCoatThickness">Concr Coat Thickness</span>
            </dt>
            <dd>{bendHistEntity.concrCoatThickness}</dd>
            <dt>
              <span id="concrCoatDensity">Concr Coat Density</span>
            </dt>
            <dd>{bendHistEntity.concrCoatDensity}</dd>
            <dt>
              <span id="measWallThickness">Meas Wall Thickness</span>
            </dt>
            <dd>{bendHistEntity.measWallThickness}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{bendHistEntity.length}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{bendHistEntity.weight}</dd>
            <dt>
              <span id="smts">Smts</span>
            </dt>
            <dd>{bendHistEntity.smts}</dd>
            <dt>
              <span id="smys">Smys</span>
            </dt>
            <dd>{bendHistEntity.smys}</dd>
            <dt>
              <span id="sdbm">Sdbm</span>
            </dt>
            <dd>{bendHistEntity.sdbm}</dd>
            <dt>
              <span id="sdaf">Sdaf</span>
            </dt>
            <dd>{bendHistEntity.sdaf}</dd>
            <dt>
              <span id="sdcs">Sdcs</span>
            </dt>
            <dd>{bendHistEntity.sdcs}</dd>
            <dt>
              <span id="allowTensStrain">Allow Tens Strain</span>
            </dt>
            <dd>{bendHistEntity.allowTensStrain}</dd>
            <dt>
              <span id="corrosionAllowance">Corrosion Allowance</span>
            </dt>
            <dd>{bendHistEntity.corrosionAllowance}</dd>
            <dt>
              <span id="fabricationAllowance">Fabrication Allowance</span>
            </dt>
            <dd>{bendHistEntity.fabricationAllowance}</dd>
            <dt>
              <span id="isBurial">Is Burial</span>
            </dt>
            <dd>{bendHistEntity.isBurial}</dd>
            <dt>
              <span id="burialDepth">Burial Depth</span>
            </dt>
            <dd>{bendHistEntity.burialDepth}</dd>
            <dt>
              <span id="factBurialDepth">Fact Burial Depth</span>
            </dt>
            <dd>{bendHistEntity.factBurialDepth}</dd>
            <dt>
              <span id="dateManufactured">Date Manufactured</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateManufactured} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="millTestPressure">Mill Test Pressure</span>
            </dt>
            <dd>{bendHistEntity.millTestPressure}</dd>
            <dt>
              <span id="designPressure">Design Pressure</span>
            </dt>
            <dd>{bendHistEntity.designPressure}</dd>
            <dt>
              <span id="incidentalPressure">Incidental Pressure</span>
            </dt>
            <dd>{bendHistEntity.incidentalPressure}</dd>
            <dt>
              <span id="dateInstalled">Date Installed</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateInstalled} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="seamOrient">Seam Orient</span>
            </dt>
            <dd>{bendHistEntity.seamOrient}</dd>
            <dt>
              <span id="seamAngle">Seam Angle</span>
            </dt>
            <dd>{bendHistEntity.seamAngle}</dd>
            <dt>
              <span id="azimuth">Azimuth</span>
            </dt>
            <dd>{bendHistEntity.azimuth}</dd>
            <dt>
              <span id="seabInstallTemp">Seab Install Temp</span>
            </dt>
            <dd>{bendHistEntity.seabInstallTemp}</dd>
            <dt>
              <span id="verticalAngle">Vertical Angle</span>
            </dt>
            <dd>{bendHistEntity.verticalAngle}</dd>
            <dt>
              <span id="heatTreatDurat">Heat Treat Durat</span>
            </dt>
            <dd>{bendHistEntity.heatTreatDurat}</dd>
            <dt>
              <span id="maxDesignTemp">Max Design Temp</span>
            </dt>
            <dd>{bendHistEntity.maxDesignTemp}</dd>
            <dt>
              <span id="heatNumber">Heat Number</span>
            </dt>
            <dd>{bendHistEntity.heatNumber}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{bendHistEntity.coord}</dd>
            <dt>
              <span id="designCoord">Design Coord</span>
            </dt>
            <dd>{bendHistEntity.designCoord}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{bendHistEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{bendHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{bendHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{bendHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{bendHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={bendHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{bendHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{bendHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{bendHistEntity.idId ? bendHistEntity.idId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{bendHistEntity.idPipelineSectionId ? bendHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Type</dt>
            <dd>{bendHistEntity.idTypeId ? bendHistEntity.idTypeId : ''}</dd>
            <dt>Id Internal Coat Type</dt>
            <dd>{bendHistEntity.idInternalCoatTypeId ? bendHistEntity.idInternalCoatTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{bendHistEntity.idExternalCoatTypeId ? bendHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Nominal Wall Thickness</dt>
            <dd>{bendHistEntity.idNominalWallThicknessId ? bendHistEntity.idNominalWallThicknessId : ''}</dd>
            <dt>Id Pipe Joint</dt>
            <dd>{bendHistEntity.idPipeJointId ? bendHistEntity.idPipeJointId : ''}</dd>
            <dt>Id Manufacturer</dt>
            <dd>{bendHistEntity.idManufacturerId ? bendHistEntity.idManufacturerId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{bendHistEntity.idSpecificationId ? bendHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Long Seam Weld Type</dt>
            <dd>{bendHistEntity.idLongSeamWeldTypeId ? bendHistEntity.idLongSeamWeldTypeId : ''}</dd>
            <dt>Id Fabrication Type</dt>
            <dd>{bendHistEntity.idFabricationTypeId ? bendHistEntity.idFabricationTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{bendHistEntity.idMaterialId ? bendHistEntity.idMaterialId : ''}</dd>
            <dt>Id Mill Location</dt>
            <dd>{bendHistEntity.idMillLocationId ? bendHistEntity.idMillLocationId : ''}</dd>
            <dt>Id Steel Grade</dt>
            <dd>{bendHistEntity.idSteelGradeId ? bendHistEntity.idSteelGradeId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{bendHistEntity.idStatusId ? bendHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/bend-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/bend-hist/${bendHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ bendHist }: IRootState) => ({
  bendHistEntity: bendHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BendHistDetail);
