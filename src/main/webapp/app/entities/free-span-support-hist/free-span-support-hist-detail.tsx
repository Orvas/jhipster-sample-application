import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './free-span-support-hist.reducer';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFreeSpanSupportHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FreeSpanSupportHistDetail extends React.Component<IFreeSpanSupportHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { freeSpanSupportHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FreeSpanSupportHist [<b>{freeSpanSupportHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.idWrk}</dd>
            <dt>
              <span id="height">Height</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.height}</dd>
            <dt>
              <span id="kpInst">Kp Inst</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.kpInst}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{freeSpanSupportHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{freeSpanSupportHistEntity.idId ? freeSpanSupportHistEntity.idId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{freeSpanSupportHistEntity.idPipelineSectionId ? freeSpanSupportHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{freeSpanSupportHistEntity.idStatusId ? freeSpanSupportHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/free-span-support-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/free-span-support-hist/${freeSpanSupportHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ freeSpanSupportHist }: IRootState) => ({
  freeSpanSupportHistEntity: freeSpanSupportHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanSupportHistDetail);
