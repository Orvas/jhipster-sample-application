import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipeline-hist.reducer';
import { IPipelineHist } from 'app/shared/model/pipeline-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipelineHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipelineHistDetail extends React.Component<IPipelineHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipelineHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PipelineHist [<b>{pipelineHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={pipelineHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={pipelineHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{pipelineHistEntity.name}</dd>
            <dt>
              <span id="designLife">Design Life</span>
            </dt>
            <dd>{pipelineHistEntity.designLife}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{pipelineHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{pipelineHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipelineHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipelineHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipelineHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipelineHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{pipelineHistEntity.idId ? pipelineHistEntity.idId : ''}</dd>
            <dt>Id Location</dt>
            <dd>{pipelineHistEntity.idLocationId ? pipelineHistEntity.idLocationId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{pipelineHistEntity.idStatusId ? pipelineHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipeline-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipeline-hist/${pipelineHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipelineHist }: IRootState) => ({
  pipelineHistEntity: pipelineHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipelineHistDetail);
