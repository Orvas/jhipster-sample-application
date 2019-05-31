import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipeline-segment.reducer';
import { IPipelineSegment } from 'app/shared/model/pipeline-segment.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipelineSegmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipelineSegmentDetail extends React.Component<IPipelineSegmentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipelineSegmentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PipelineSegment [<b>{pipelineSegmentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="num">Num</span>
            </dt>
            <dd>{pipelineSegmentEntity.num}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{pipelineSegmentEntity.name}</dd>
            <dt>
              <span id="kpStart1">Kp Start 1</span>
            </dt>
            <dd>{pipelineSegmentEntity.kpStart1}</dd>
            <dt>
              <span id="kpEnd1">Kp End 1</span>
            </dt>
            <dd>{pipelineSegmentEntity.kpEnd1}</dd>
            <dt>
              <span id="kpStart4">Kp Start 4</span>
            </dt>
            <dd>{pipelineSegmentEntity.kpStart4}</dd>
            <dt>
              <span id="kpEnd4">Kp End 4</span>
            </dt>
            <dd>{pipelineSegmentEntity.kpEnd4}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipelineSegmentEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipelineSegmentEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipelineSegmentEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipelineSegmentEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipeline-segment" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipeline-segment/${pipelineSegmentEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipelineSegment }: IRootState) => ({
  pipelineSegmentEntity: pipelineSegment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipelineSegmentDetail);
