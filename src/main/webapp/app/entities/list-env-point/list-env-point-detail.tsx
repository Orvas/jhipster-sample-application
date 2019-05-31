import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-env-point.reducer';
import { IListEnvPoint } from 'app/shared/model/list-env-point.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListEnvPointDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListEnvPointDetail extends React.Component<IListEnvPointDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listEnvPointEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListEnvPoint [<b>{listEnvPointEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listEnvPointEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listEnvPointEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listEnvPointEntity.fullName}</dd>
            <dt>
              <span id="degreeStart">Degree Start</span>
            </dt>
            <dd>{listEnvPointEntity.degreeStart}</dd>
            <dt>
              <span id="degreeEnd">Degree End</span>
            </dt>
            <dd>{listEnvPointEntity.degreeEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listEnvPointEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listEnvPointEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listEnvPointEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listEnvPointEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listEnvPointEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listEnvPointEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-env-point" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-env-point/${listEnvPointEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listEnvPoint }: IRootState) => ({
  listEnvPointEntity: listEnvPoint.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListEnvPointDetail);
