import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './valve-hist.reducer';
import { IValveHist } from 'app/shared/model/valve-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IValveHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ValveHistDetail extends React.Component<IValveHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { valveHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ValveHist [<b>{valveHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{valveHistEntity.idWrk}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{valveHistEntity.name}</dd>
            <dt>
              <span id="serialNum">Serial Num</span>
            </dt>
            <dd>{valveHistEntity.serialNum}</dd>
            <dt>
              <span id="model">Model</span>
            </dt>
            <dd>{valveHistEntity.model}</dd>
            <dt>
              <span id="diameterOuterSteel">Diameter Outer Steel</span>
            </dt>
            <dd>{valveHistEntity.diameterOuterSteel}</dd>
            <dt>
              <span id="internalCoatThickness">Internal Coat Thickness</span>
            </dt>
            <dd>{valveHistEntity.internalCoatThickness}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{valveHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="isConcrCoating">Is Concr Coating</span>
            </dt>
            <dd>{valveHistEntity.isConcrCoating}</dd>
            <dt>
              <span id="concrCoatThickness">Concr Coat Thickness</span>
            </dt>
            <dd>{valveHistEntity.concrCoatThickness}</dd>
            <dt>
              <span id="concrCoatDensity">Concr Coat Density</span>
            </dt>
            <dd>{valveHistEntity.concrCoatDensity}</dd>
            <dt>
              <span id="measWallThickness">Meas Wall Thickness</span>
            </dt>
            <dd>{valveHistEntity.measWallThickness}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{valveHistEntity.length}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{valveHistEntity.weight}</dd>
            <dt>
              <span id="smts">Smts</span>
            </dt>
            <dd>{valveHistEntity.smts}</dd>
            <dt>
              <span id="smys">Smys</span>
            </dt>
            <dd>{valveHistEntity.smys}</dd>
            <dt>
              <span id="sdbm">Sdbm</span>
            </dt>
            <dd>{valveHistEntity.sdbm}</dd>
            <dt>
              <span id="sdaf">Sdaf</span>
            </dt>
            <dd>{valveHistEntity.sdaf}</dd>
            <dt>
              <span id="sdcs">Sdcs</span>
            </dt>
            <dd>{valveHistEntity.sdcs}</dd>
            <dt>
              <span id="allowTensStrain">Allow Tens Strain</span>
            </dt>
            <dd>{valveHistEntity.allowTensStrain}</dd>
            <dt>
              <span id="corrosionAllowance">Corrosion Allowance</span>
            </dt>
            <dd>{valveHistEntity.corrosionAllowance}</dd>
            <dt>
              <span id="fabricationAllowance">Fabrication Allowance</span>
            </dt>
            <dd>{valveHistEntity.fabricationAllowance}</dd>
            <dt>
              <span id="dateManufactured">Date Manufactured</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateManufactured} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="millTestPressure">Mill Test Pressure</span>
            </dt>
            <dd>{valveHistEntity.millTestPressure}</dd>
            <dt>
              <span id="designPressure">Design Pressure</span>
            </dt>
            <dd>{valveHistEntity.designPressure}</dd>
            <dt>
              <span id="designPressureIn">Design Pressure In</span>
            </dt>
            <dd>{valveHistEntity.designPressureIn}</dd>
            <dt>
              <span id="designPressureOut">Design Pressure Out</span>
            </dt>
            <dd>{valveHistEntity.designPressureOut}</dd>
            <dt>
              <span id="incidentalPressure">Incidental Pressure</span>
            </dt>
            <dd>{valveHistEntity.incidentalPressure}</dd>
            <dt>
              <span id="dateInstalled">Date Installed</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateInstalled} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="seamOrient">Seam Orient</span>
            </dt>
            <dd>{valveHistEntity.seamOrient}</dd>
            <dt>
              <span id="seamAngle">Seam Angle</span>
            </dt>
            <dd>{valveHistEntity.seamAngle}</dd>
            <dt>
              <span id="azimuth">Azimuth</span>
            </dt>
            <dd>{valveHistEntity.azimuth}</dd>
            <dt>
              <span id="seabInstallTemp">Seab Install Temp</span>
            </dt>
            <dd>{valveHistEntity.seabInstallTemp}</dd>
            <dt>
              <span id="verticalAngle">Vertical Angle</span>
            </dt>
            <dd>{valveHistEntity.verticalAngle}</dd>
            <dt>
              <span id="heatTreatDurat">Heat Treat Durat</span>
            </dt>
            <dd>{valveHistEntity.heatTreatDurat}</dd>
            <dt>
              <span id="maxDesignTemp">Max Design Temp</span>
            </dt>
            <dd>{valveHistEntity.maxDesignTemp}</dd>
            <dt>
              <span id="heatNumber">Heat Number</span>
            </dt>
            <dd>{valveHistEntity.heatNumber}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{valveHistEntity.coord}</dd>
            <dt>
              <span id="designCoord">Design Coord</span>
            </dt>
            <dd>{valveHistEntity.designCoord}</dd>
            <dt>
              <span id="kpInst">Kp Inst</span>
            </dt>
            <dd>{valveHistEntity.kpInst}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{valveHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{valveHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{valveHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={valveHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{valveHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{valveHistEntity.editor}</dd>
            <dt>Valve</dt>
            <dd>{valveHistEntity.valveId ? valveHistEntity.valveId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{valveHistEntity.idPipelineSectionId ? valveHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Type</dt>
            <dd>{valveHistEntity.idTypeId ? valveHistEntity.idTypeId : ''}</dd>
            <dt>Id Internal Coat Type</dt>
            <dd>{valveHistEntity.idInternalCoatTypeId ? valveHistEntity.idInternalCoatTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{valveHistEntity.idExternalCoatTypeId ? valveHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Nominal Wall Thickness</dt>
            <dd>{valveHistEntity.idNominalWallThicknessId ? valveHistEntity.idNominalWallThicknessId : ''}</dd>
            <dt>Id Pipe Joint</dt>
            <dd>{valveHistEntity.idPipeJointId ? valveHistEntity.idPipeJointId : ''}</dd>
            <dt>Id Manufacturer</dt>
            <dd>{valveHistEntity.idManufacturerId ? valveHistEntity.idManufacturerId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{valveHistEntity.idSpecificationId ? valveHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Function</dt>
            <dd>{valveHistEntity.idFunctionId ? valveHistEntity.idFunctionId : ''}</dd>
            <dt>Id Long Seam Weld Type</dt>
            <dd>{valveHistEntity.idLongSeamWeldTypeId ? valveHistEntity.idLongSeamWeldTypeId : ''}</dd>
            <dt>Id Fabrication Type</dt>
            <dd>{valveHistEntity.idFabricationTypeId ? valveHistEntity.idFabricationTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{valveHistEntity.idMaterialId ? valveHistEntity.idMaterialId : ''}</dd>
            <dt>Id Mill Location</dt>
            <dd>{valveHistEntity.idMillLocationId ? valveHistEntity.idMillLocationId : ''}</dd>
            <dt>Id Steel Grade</dt>
            <dd>{valveHistEntity.idSteelGradeId ? valveHistEntity.idSteelGradeId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{valveHistEntity.idStatusId ? valveHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/valve-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/valve-hist/${valveHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ valveHist }: IRootState) => ({
  valveHistEntity: valveHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ValveHistDetail);
