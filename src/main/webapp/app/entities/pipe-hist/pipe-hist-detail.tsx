import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipe-hist.reducer';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipeHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipeHistDetail extends React.Component<IPipeHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipeHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PipeHist [<b>{pipeHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{pipeHistEntity.idWrk}</dd>
            <dt>
              <span id="num">Num</span>
            </dt>
            <dd>{pipeHistEntity.num}</dd>
            <dt>
              <span id="diameterOuterSteel">Diameter Outer Steel</span>
            </dt>
            <dd>{pipeHistEntity.diameterOuterSteel}</dd>
            <dt>
              <span id="internalCoatThickness">Internal Coat Thickness</span>
            </dt>
            <dd>{pipeHistEntity.internalCoatThickness}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{pipeHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="isConcrCoating">Is Concr Coating</span>
            </dt>
            <dd>{pipeHistEntity.isConcrCoating}</dd>
            <dt>
              <span id="concrCoatThickness">Concr Coat Thickness</span>
            </dt>
            <dd>{pipeHistEntity.concrCoatThickness}</dd>
            <dt>
              <span id="concrCoatDensity">Concr Coat Density</span>
            </dt>
            <dd>{pipeHistEntity.concrCoatDensity}</dd>
            <dt>
              <span id="measWallThickness">Meas Wall Thickness</span>
            </dt>
            <dd>{pipeHistEntity.measWallThickness}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{pipeHistEntity.length}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{pipeHistEntity.weight}</dd>
            <dt>
              <span id="smts">Smts</span>
            </dt>
            <dd>{pipeHistEntity.smts}</dd>
            <dt>
              <span id="smys">Smys</span>
            </dt>
            <dd>{pipeHistEntity.smys}</dd>
            <dt>
              <span id="sdbm">Sdbm</span>
            </dt>
            <dd>{pipeHistEntity.sdbm}</dd>
            <dt>
              <span id="sdaf">Sdaf</span>
            </dt>
            <dd>{pipeHistEntity.sdaf}</dd>
            <dt>
              <span id="sdcs">Sdcs</span>
            </dt>
            <dd>{pipeHistEntity.sdcs}</dd>
            <dt>
              <span id="allowTensStrain">Allow Tens Strain</span>
            </dt>
            <dd>{pipeHistEntity.allowTensStrain}</dd>
            <dt>
              <span id="corrosionAllowance">Corrosion Allowance</span>
            </dt>
            <dd>{pipeHistEntity.corrosionAllowance}</dd>
            <dt>
              <span id="fabricationAllowance">Fabrication Allowance</span>
            </dt>
            <dd>{pipeHistEntity.fabricationAllowance}</dd>
            <dt>
              <span id="isBurial">Is Burial</span>
            </dt>
            <dd>{pipeHistEntity.isBurial}</dd>
            <dt>
              <span id="burialDepth">Burial Depth</span>
            </dt>
            <dd>{pipeHistEntity.burialDepth}</dd>
            <dt>
              <span id="factBurialDepth">Fact Burial Depth</span>
            </dt>
            <dd>{pipeHistEntity.factBurialDepth}</dd>
            <dt>
              <span id="dateManufactured">Date Manufactured</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateManufactured} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="millTestPressure">Mill Test Pressure</span>
            </dt>
            <dd>{pipeHistEntity.millTestPressure}</dd>
            <dt>
              <span id="designPressure">Design Pressure</span>
            </dt>
            <dd>{pipeHistEntity.designPressure}</dd>
            <dt>
              <span id="incidentalPressure">Incidental Pressure</span>
            </dt>
            <dd>{pipeHistEntity.incidentalPressure}</dd>
            <dt>
              <span id="dateInstalled">Date Installed</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateInstalled} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="seamOrient">Seam Orient</span>
            </dt>
            <dd>{pipeHistEntity.seamOrient}</dd>
            <dt>
              <span id="seamAngle">Seam Angle</span>
            </dt>
            <dd>{pipeHistEntity.seamAngle}</dd>
            <dt>
              <span id="azimuth">Azimuth</span>
            </dt>
            <dd>{pipeHistEntity.azimuth}</dd>
            <dt>
              <span id="seabInstallTemp">Seab Install Temp</span>
            </dt>
            <dd>{pipeHistEntity.seabInstallTemp}</dd>
            <dt>
              <span id="verticalAngle">Vertical Angle</span>
            </dt>
            <dd>{pipeHistEntity.verticalAngle}</dd>
            <dt>
              <span id="heatTreatDurat">Heat Treat Durat</span>
            </dt>
            <dd>{pipeHistEntity.heatTreatDurat}</dd>
            <dt>
              <span id="maxDesignTemp">Max Design Temp</span>
            </dt>
            <dd>{pipeHistEntity.maxDesignTemp}</dd>
            <dt>
              <span id="heatNumber">Heat Number</span>
            </dt>
            <dd>{pipeHistEntity.heatNumber}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{pipeHistEntity.coord}</dd>
            <dt>
              <span id="designCoord">Design Coord</span>
            </dt>
            <dd>{pipeHistEntity.designCoord}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{pipeHistEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{pipeHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{pipeHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{pipeHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{pipeHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipeHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipeHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipeHistEntity.editor}</dd>
            <dt>Pipe</dt>
            <dd>{pipeHistEntity.pipeId ? pipeHistEntity.pipeId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{pipeHistEntity.idPipelineSectionId ? pipeHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Internal Coat Type</dt>
            <dd>{pipeHistEntity.idInternalCoatTypeId ? pipeHistEntity.idInternalCoatTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{pipeHistEntity.idExternalCoatTypeId ? pipeHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Nominal Wall Thickness</dt>
            <dd>{pipeHistEntity.idNominalWallThicknessId ? pipeHistEntity.idNominalWallThicknessId : ''}</dd>
            <dt>Id Pipe Joint</dt>
            <dd>{pipeHistEntity.idPipeJointId ? pipeHistEntity.idPipeJointId : ''}</dd>
            <dt>Id Manufacturer</dt>
            <dd>{pipeHistEntity.idManufacturerId ? pipeHistEntity.idManufacturerId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{pipeHistEntity.idSpecificationId ? pipeHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Long Seam Weld Type</dt>
            <dd>{pipeHistEntity.idLongSeamWeldTypeId ? pipeHistEntity.idLongSeamWeldTypeId : ''}</dd>
            <dt>Id Fabrication Type</dt>
            <dd>{pipeHistEntity.idFabricationTypeId ? pipeHistEntity.idFabricationTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{pipeHistEntity.idMaterialId ? pipeHistEntity.idMaterialId : ''}</dd>
            <dt>Id Mill Location</dt>
            <dd>{pipeHistEntity.idMillLocationId ? pipeHistEntity.idMillLocationId : ''}</dd>
            <dt>Id Steel Grade</dt>
            <dd>{pipeHistEntity.idSteelGradeId ? pipeHistEntity.idSteelGradeId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{pipeHistEntity.idStatusId ? pipeHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipe-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipe-hist/${pipeHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipeHist }: IRootState) => ({
  pipeHistEntity: pipeHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipeHistDetail);
