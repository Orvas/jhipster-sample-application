import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-object-status.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListObjectStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListObjectStatusDetail extends React.Component<IListObjectStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listObjectStatusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListObjectStatus [<b>{listObjectStatusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listObjectStatusEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listObjectStatusEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listObjectStatusEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listObjectStatusEntity.isCurrentFlag ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-object-status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-object-status/${listObjectStatusEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listObjectStatus }: IRootState) => ({
  listObjectStatusEntity: listObjectStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListObjectStatusDetail);
