import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cps-hist.reducer';
import { ICpsHist } from 'app/shared/model/cps-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICpsHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CpsHistDetail extends React.Component<ICpsHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { cpsHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CpsHist [<b>{cpsHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={cpsHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={cpsHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{cpsHistEntity.idWrk}</dd>
            <dt>
              <span id="current">Current</span>
            </dt>
            <dd>{cpsHistEntity.current}</dd>
            <dt>
              <span id="potential">Potential</span>
            </dt>
            <dd>{cpsHistEntity.potential}</dd>
            <dt>
              <span id="downtime">Downtime</span>
            </dt>
            <dd>{cpsHistEntity.downtime}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{cpsHistEntity.coord}</dd>
            <dt>
              <span id="kpInst">Kp Inst</span>
            </dt>
            <dd>{cpsHistEntity.kpInst}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{cpsHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{cpsHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{cpsHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={cpsHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={cpsHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{cpsHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{cpsHistEntity.editor}</dd>
            <dt>Cps</dt>
            <dd>{cpsHistEntity.cpsId ? cpsHistEntity.cpsId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{cpsHistEntity.idPipelineSectionId ? cpsHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{cpsHistEntity.idStatusId ? cpsHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/cps-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/cps-hist/${cpsHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ cpsHist }: IRootState) => ({
  cpsHistEntity: cpsHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CpsHistDetail);
