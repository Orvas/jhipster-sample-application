import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './free-span-history.reducer';
import { IFreeSpanHistory } from 'app/shared/model/free-span-history.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFreeSpanHistoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FreeSpanHistoryDetail extends React.Component<IFreeSpanHistoryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { freeSpanHistoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FreeSpanHistory [<b>{freeSpanHistoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateForm">Date Form</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistoryEntity.dateForm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistoryEntity.dateTo} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="workId">Work Id</span>
            </dt>
            <dd>{freeSpanHistoryEntity.workId}</dd>
            <dt>
              <span id="length">Length</span>
            </dt>
            <dd>{freeSpanHistoryEntity.length}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{freeSpanHistoryEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{freeSpanHistoryEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{freeSpanHistoryEntity.isCurrentFlag ? 'true' : 'false'}</dd>
            <dt>
              <span id="statusId">Status Id</span>
            </dt>
            <dd>{freeSpanHistoryEntity.statusId}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{freeSpanHistoryEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistoryEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistoryEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{freeSpanHistoryEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{freeSpanHistoryEntity.editor}</dd>
            <dt>Status</dt>
            <dd>{freeSpanHistoryEntity.status ? freeSpanHistoryEntity.status.id : ''}</dd>
            <dt>Pipeline Section</dt>
            <dd>{freeSpanHistoryEntity.pipelineSection ? freeSpanHistoryEntity.pipelineSection.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/free-span-history" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/free-span-history/${freeSpanHistoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ freeSpanHistory }: IRootState) => ({
  freeSpanHistoryEntity: freeSpanHistory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanHistoryDetail);
