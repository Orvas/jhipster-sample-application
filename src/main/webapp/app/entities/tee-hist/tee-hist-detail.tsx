import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tee-hist.reducer';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeeHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TeeHistDetail extends React.Component<ITeeHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { teeHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TeeHist [<b>{teeHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{teeHistEntity.idWrk}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{teeHistEntity.name}</dd>
            <dt>
              <span id="diameterOuterSteel">Diameter Outer Steel</span>
            </dt>
            <dd>{teeHistEntity.diameterOuterSteel}</dd>
            <dt>
              <span id="diameterOuterSteelBr">Diameter Outer Steel Br</span>
            </dt>
            <dd>{teeHistEntity.diameterOuterSteelBr}</dd>
            <dt>
              <span id="internalCoatThickness">Internal Coat Thickness</span>
            </dt>
            <dd>{teeHistEntity.internalCoatThickness}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{teeHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="isConcrCoating">Is Concr Coating</span>
            </dt>
            <dd>{teeHistEntity.isConcrCoating}</dd>
            <dt>
              <span id="concrCoatThickness">Concr Coat Thickness</span>
            </dt>
            <dd>{teeHistEntity.concrCoatThickness}</dd>
            <dt>
              <span id="concrCoatDensity">Concr Coat Density</span>
            </dt>
            <dd>{teeHistEntity.concrCoatDensity}</dd>
            <dt>
              <span id="measWallThickness">Meas Wall Thickness</span>
            </dt>
            <dd>{teeHistEntity.measWallThickness}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{teeHistEntity.length}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{teeHistEntity.weight}</dd>
            <dt>
              <span id="smts">Smts</span>
            </dt>
            <dd>{teeHistEntity.smts}</dd>
            <dt>
              <span id="smys">Smys</span>
            </dt>
            <dd>{teeHistEntity.smys}</dd>
            <dt>
              <span id="sdbm">Sdbm</span>
            </dt>
            <dd>{teeHistEntity.sdbm}</dd>
            <dt>
              <span id="sdaf">Sdaf</span>
            </dt>
            <dd>{teeHistEntity.sdaf}</dd>
            <dt>
              <span id="sdcs">Sdcs</span>
            </dt>
            <dd>{teeHistEntity.sdcs}</dd>
            <dt>
              <span id="allowTensStrain">Allow Tens Strain</span>
            </dt>
            <dd>{teeHistEntity.allowTensStrain}</dd>
            <dt>
              <span id="corrosionAllowance">Corrosion Allowance</span>
            </dt>
            <dd>{teeHistEntity.corrosionAllowance}</dd>
            <dt>
              <span id="fabricationAllowance">Fabrication Allowance</span>
            </dt>
            <dd>{teeHistEntity.fabricationAllowance}</dd>
            <dt>
              <span id="isBurial">Is Burial</span>
            </dt>
            <dd>{teeHistEntity.isBurial}</dd>
            <dt>
              <span id="burialDepth">Burial Depth</span>
            </dt>
            <dd>{teeHistEntity.burialDepth}</dd>
            <dt>
              <span id="factBurialDepth">Fact Burial Depth</span>
            </dt>
            <dd>{teeHistEntity.factBurialDepth}</dd>
            <dt>
              <span id="dateManufactured">Date Manufactured</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateManufactured} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="millTestPressure">Mill Test Pressure</span>
            </dt>
            <dd>{teeHistEntity.millTestPressure}</dd>
            <dt>
              <span id="designPressure">Design Pressure</span>
            </dt>
            <dd>{teeHistEntity.designPressure}</dd>
            <dt>
              <span id="incidentalPressure">Incidental Pressure</span>
            </dt>
            <dd>{teeHistEntity.incidentalPressure}</dd>
            <dt>
              <span id="dateInstalled">Date Installed</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateInstalled} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="seamOrient">Seam Orient</span>
            </dt>
            <dd>{teeHistEntity.seamOrient}</dd>
            <dt>
              <span id="seamAngle">Seam Angle</span>
            </dt>
            <dd>{teeHistEntity.seamAngle}</dd>
            <dt>
              <span id="azimuth">Azimuth</span>
            </dt>
            <dd>{teeHistEntity.azimuth}</dd>
            <dt>
              <span id="seabInstallTemp">Seab Install Temp</span>
            </dt>
            <dd>{teeHistEntity.seabInstallTemp}</dd>
            <dt>
              <span id="verticalAngle">Vertical Angle</span>
            </dt>
            <dd>{teeHistEntity.verticalAngle}</dd>
            <dt>
              <span id="heatTreatDurat">Heat Treat Durat</span>
            </dt>
            <dd>{teeHistEntity.heatTreatDurat}</dd>
            <dt>
              <span id="maxDesignTemp">Max Design Temp</span>
            </dt>
            <dd>{teeHistEntity.maxDesignTemp}</dd>
            <dt>
              <span id="heatNumber">Heat Number</span>
            </dt>
            <dd>{teeHistEntity.heatNumber}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{teeHistEntity.coord}</dd>
            <dt>
              <span id="designCoord">Design Coord</span>
            </dt>
            <dd>{teeHistEntity.designCoord}</dd>
            <dt>
              <span id="kpInst">Kp Inst</span>
            </dt>
            <dd>{teeHistEntity.kpInst}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{teeHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{teeHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{teeHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={teeHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{teeHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{teeHistEntity.editor}</dd>
            <dt>Tee</dt>
            <dd>{teeHistEntity.teeId ? teeHistEntity.teeId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{teeHistEntity.idPipelineSectionId ? teeHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Type</dt>
            <dd>{teeHistEntity.idTypeId ? teeHistEntity.idTypeId : ''}</dd>
            <dt>Id Internal Coat Type</dt>
            <dd>{teeHistEntity.idInternalCoatTypeId ? teeHistEntity.idInternalCoatTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{teeHistEntity.idExternalCoatTypeId ? teeHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Nominal Wall Thickness</dt>
            <dd>{teeHistEntity.idNominalWallThicknessId ? teeHistEntity.idNominalWallThicknessId : ''}</dd>
            <dt>Id Pipe Joint</dt>
            <dd>{teeHistEntity.idPipeJointId ? teeHistEntity.idPipeJointId : ''}</dd>
            <dt>Id Manufacturer</dt>
            <dd>{teeHistEntity.idManufacturerId ? teeHistEntity.idManufacturerId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{teeHistEntity.idSpecificationId ? teeHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Long Seam Weld Type</dt>
            <dd>{teeHistEntity.idLongSeamWeldTypeId ? teeHistEntity.idLongSeamWeldTypeId : ''}</dd>
            <dt>Id Fabrication Type</dt>
            <dd>{teeHistEntity.idFabricationTypeId ? teeHistEntity.idFabricationTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{teeHistEntity.idMaterialId ? teeHistEntity.idMaterialId : ''}</dd>
            <dt>Id Mill Location</dt>
            <dd>{teeHistEntity.idMillLocationId ? teeHistEntity.idMillLocationId : ''}</dd>
            <dt>Id Steel Grade</dt>
            <dd>{teeHistEntity.idSteelGradeId ? teeHistEntity.idSteelGradeId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{teeHistEntity.idStatusId ? teeHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/tee-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/tee-hist/${teeHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ teeHist }: IRootState) => ({
  teeHistEntity: teeHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeeHistDetail);
