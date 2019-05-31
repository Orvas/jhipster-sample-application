import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './anode-hist.reducer';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAnodeHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AnodeHistDetail extends React.Component<IAnodeHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { anodeHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AnodeHist [<b>{anodeHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={anodeHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={anodeHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{anodeHistEntity.idWrk}</dd>
            <dt>
              <span id="designLife">Design Life</span>
            </dt>
            <dd>{anodeHistEntity.designLife}</dd>
            <dt>
              <span id="dmcd">Dmcd</span>
            </dt>
            <dd>{anodeHistEntity.dmcd}</dd>
            <dt>
              <span id="l1">L 1</span>
            </dt>
            <dd>{anodeHistEntity.l1}</dd>
            <dt>
              <span id="l2">L 2</span>
            </dt>
            <dd>{anodeHistEntity.l2}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{anodeHistEntity.length}</dd>
            <dt>
              <span id="electrCapac">Electr Capac</span>
            </dt>
            <dd>{anodeHistEntity.electrCapac}</dd>
            <dt>
              <span id="designWeight">Design Weight</span>
            </dt>
            <dd>{anodeHistEntity.designWeight}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{anodeHistEntity.weight}</dd>
            <dt>
              <span id="isBurial">Is Burial</span>
            </dt>
            <dd>{anodeHistEntity.isBurial}</dd>
            <dt>
              <span id="chemicalComposition">Chemical Composition</span>
            </dt>
            <dd>{anodeHistEntity.chemicalComposition}</dd>
            <dt>
              <span id="density">Density</span>
            </dt>
            <dd>{anodeHistEntity.density}</dd>
            <dt>
              <span id="spacing">Spacing</span>
            </dt>
            <dd>{anodeHistEntity.spacing}</dd>
            <dt>
              <span id="coatCutbackLength">Coat Cutback Length</span>
            </dt>
            <dd>{anodeHistEntity.coatCutbackLength}</dd>
            <dt>
              <span id="coatDmgArea">Coat Dmg Area</span>
            </dt>
            <dd>{anodeHistEntity.coatDmgArea}</dd>
            <dt>
              <span id="h2sSoil">H 2 S Soil</span>
            </dt>
            <dd>{anodeHistEntity.h2sSoil}</dd>
            <dt>
              <span id="remain">Remain</span>
            </dt>
            <dd>{anodeHistEntity.remain}</dd>
            <dt>
              <span id="intFldTemp">Int Fld Temp</span>
            </dt>
            <dd>{anodeHistEntity.intFldTemp}</dd>
            <dt>
              <span id="minPrc">Min Prc</span>
            </dt>
            <dd>{anodeHistEntity.minPrc}</dd>
            <dt>
              <span id="thickness">Thickness</span>
            </dt>
            <dd>{anodeHistEntity.thickness}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{anodeHistEntity.coord}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{anodeHistEntity.kpStart}</dd>
            <dt>
              <span id="coatThickness">Coat Thickness</span>
            </dt>
            <dd>{anodeHistEntity.coatThickness}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{anodeHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{anodeHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{anodeHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{anodeHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={anodeHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={anodeHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{anodeHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{anodeHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{anodeHistEntity.idId ? anodeHistEntity.idId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{anodeHistEntity.idPipelineSectionId ? anodeHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Bracelete Type</dt>
            <dd>{anodeHistEntity.idBraceleteTypeId ? anodeHistEntity.idBraceleteTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{anodeHistEntity.idMaterialId ? anodeHistEntity.idMaterialId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{anodeHistEntity.idStatusId ? anodeHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/anode-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/anode-hist/${anodeHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ anodeHist }: IRootState) => ({
  anodeHistEntity: anodeHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AnodeHistDetail);
