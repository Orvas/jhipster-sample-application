import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './launch-receiver-hist.reducer';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILaunchReceiverHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LaunchReceiverHistDetail extends React.Component<ILaunchReceiverHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { launchReceiverHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            LaunchReceiverHist [<b>{launchReceiverHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={launchReceiverHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={launchReceiverHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{launchReceiverHistEntity.name}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{launchReceiverHistEntity.coord}</dd>
            <dt>
              <span id="kpInst">Kp Inst</span>
            </dt>
            <dd>{launchReceiverHistEntity.kpInst}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{launchReceiverHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{launchReceiverHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={launchReceiverHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={launchReceiverHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{launchReceiverHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{launchReceiverHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{launchReceiverHistEntity.idId ? launchReceiverHistEntity.idId : ''}</dd>
            <dt>Id Pipeline</dt>
            <dd>{launchReceiverHistEntity.idPipelineId ? launchReceiverHistEntity.idPipelineId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{launchReceiverHistEntity.idStatusId ? launchReceiverHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/launch-receiver-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/launch-receiver-hist/${launchReceiverHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ launchReceiverHist }: IRootState) => ({
  launchReceiverHistEntity: launchReceiverHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LaunchReceiverHistDetail);
