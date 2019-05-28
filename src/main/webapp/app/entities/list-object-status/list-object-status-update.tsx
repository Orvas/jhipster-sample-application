import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './list-object-status.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IListObjectStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IListObjectStatusUpdateState {
  isNew: boolean;
}

export class ListObjectStatusUpdate extends React.Component<IListObjectStatusUpdateProps, IListObjectStatusUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { listObjectStatusEntity } = this.props;
      const entity = {
        ...listObjectStatusEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/list-object-status');
  };

  render() {
    const { listObjectStatusEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.listObjectStatus.home.createOrEditLabel">Create or edit a ListObjectStatus</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : listObjectStatusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="list-object-status-id">ID</Label>
                    <AvInput id="list-object-status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="list-object-status-code">
                    Code
                  </Label>
                  <AvField id="list-object-status-code" type="text" name="code" />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="list-object-status-name">
                    Name
                  </Label>
                  <AvField id="list-object-status-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="list-object-status-fullName">
                    Full Name
                  </Label>
                  <AvField id="list-object-status-fullName" type="text" name="fullName" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" check>
                    <AvInput id="list-object-status-isCurrentFlag" type="checkbox" className="form-control" name="isCurrentFlag" />
                    Is Current Flag
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/list-object-status" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  listObjectStatusEntity: storeState.listObjectStatus.entity,
  loading: storeState.listObjectStatus.loading,
  updating: storeState.listObjectStatus.updating,
  updateSuccess: storeState.listObjectStatus.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListObjectStatusUpdate);
