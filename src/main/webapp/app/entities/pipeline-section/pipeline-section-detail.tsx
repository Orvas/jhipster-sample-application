import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipeline-section.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipelineSectionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipelineSectionDetail extends React.Component<IPipelineSectionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipelineSectionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PipelineSection [<b>{pipelineSectionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{pipelineSectionEntity.name}</dd>
            <dt>
              <span id="isOnshore">Is Onshore</span>
            </dt>
            <dd>{pipelineSectionEntity.isOnshore}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{pipelineSectionEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{pipelineSectionEntity.kpEnd}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipelineSectionEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipelineSectionEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipelineSectionEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipelineSectionEntity.editor}</dd>
            <dt>Base Class</dt>
            <dd>{pipelineSectionEntity.baseClassId ? pipelineSectionEntity.baseClassId : ''}</dd>
            <dt>Id Pipeline</dt>
            <dd>{pipelineSectionEntity.idPipelineId ? pipelineSectionEntity.idPipelineId : ''}</dd>
            <dt>Id Safety Class</dt>
            <dd>{pipelineSectionEntity.idSafetyClassId ? pipelineSectionEntity.idSafetyClassId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipeline-section" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipeline-section/${pipelineSectionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipelineSection }: IRootState) => ({
  pipelineSectionEntity: pipelineSection.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipelineSectionDetail);
