import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBaseClass } from 'app/shared/model/base-class.model';
import { getEntities as getBaseClasses } from 'app/entities/base-class/base-class.reducer';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
import { getEntities as getLaunchReceiverHists } from 'app/entities/launch-receiver-hist/launch-receiver-hist.reducer';
import { getEntity, updateEntity, createEntity, reset } from './launch-receiver.reducer';
import { ILaunchReceiver } from 'app/shared/model/launch-receiver.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILaunchReceiverUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILaunchReceiverUpdateState {
  isNew: boolean;
  baseClassId: string;
  launchReceiverHistId: string;
}

export class LaunchReceiverUpdate extends React.Component<ILaunchReceiverUpdateProps, ILaunchReceiverUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      baseClassId: '0',
      launchReceiverHistId: '0',
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

    this.props.getBaseClasses();
    this.props.getLaunchReceiverHists();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { launchReceiverEntity } = this.props;
      const entity = {
        ...launchReceiverEntity,
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
    this.props.history.push('/entity/launch-receiver');
  };

  render() {
    const { launchReceiverEntity, baseClasses, launchReceiverHists, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.launchReceiver.home.createOrEditLabel">Create or edit a LaunchReceiver</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : launchReceiverEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="launch-receiver-id">ID</Label>
                    <AvInput id="launch-receiver-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateCreateLabel" for="launch-receiver-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="launch-receiver-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.launchReceiverEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="launch-receiver-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="launch-receiver-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.launchReceiverEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="launch-receiver-creator">
                    Creator
                  </Label>
                  <AvField
                    id="launch-receiver-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="launch-receiver-editor">
                    Editor
                  </Label>
                  <AvField
                    id="launch-receiver-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="launch-receiver-baseClass">Base Class</Label>
                  <AvInput id="launch-receiver-baseClass" type="select" className="form-control" name="baseClassId">
                    <option value="" key="0" />
                    {baseClasses
                      ? baseClasses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/launch-receiver" replace color="info">
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
  baseClasses: storeState.baseClass.entities,
  launchReceiverHists: storeState.launchReceiverHist.entities,
  launchReceiverEntity: storeState.launchReceiver.entity,
  loading: storeState.launchReceiver.loading,
  updating: storeState.launchReceiver.updating,
  updateSuccess: storeState.launchReceiver.updateSuccess
});

const mapDispatchToProps = {
  getBaseClasses,
  getLaunchReceiverHists,
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
)(LaunchReceiverUpdate);
