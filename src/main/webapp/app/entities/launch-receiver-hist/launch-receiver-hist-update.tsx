import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILaunchReceiver } from 'app/shared/model/launch-receiver.model';
import { getEntities as getLaunchReceivers } from 'app/entities/launch-receiver/launch-receiver.reducer';
import { IPipeline } from 'app/shared/model/pipeline.model';
import { getEntities as getPipelines } from 'app/entities/pipeline/pipeline.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './launch-receiver-hist.reducer';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILaunchReceiverHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILaunchReceiverHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineId: string;
  idStatusId: string;
}

export class LaunchReceiverHistUpdate extends React.Component<ILaunchReceiverHistUpdateProps, ILaunchReceiverHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineId: '0',
      idStatusId: '0',
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

    this.props.getLaunchReceivers();
    this.props.getPipelines();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { launchReceiverHistEntity } = this.props;
      const entity = {
        ...launchReceiverHistEntity,
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
    this.props.history.push('/entity/launch-receiver-hist');
  };

  render() {
    const { launchReceiverHistEntity, launchReceivers, pipelines, listObjectStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.launchReceiverHist.home.createOrEditLabel">Create or edit a LaunchReceiverHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : launchReceiverHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="launch-receiver-hist-id">ID</Label>
                    <AvInput id="launch-receiver-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="launch-receiver-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="launch-receiver-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="launch-receiver-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="launch-receiver-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="launch-receiver-hist-name">
                    Name
                  </Label>
                  <AvField
                    id="launch-receiver-hist-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="launch-receiver-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="launch-receiver-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpInstLabel" for="launch-receiver-hist-kpInst">
                    Kp Inst
                  </Label>
                  <AvField id="launch-receiver-hist-kpInst" type="text" name="kpInst" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="launch-receiver-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="launch-receiver-hist-isCurrentFlag"
                    type="string"
                    className="form-control"
                    name="isCurrentFlag"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="launch-receiver-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="launch-receiver-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="launch-receiver-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="launch-receiver-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.launchReceiverHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="launch-receiver-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="launch-receiver-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.launchReceiverHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="launch-receiver-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="launch-receiver-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="launch-receiver-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="launch-receiver-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="launch-receiver-hist-id">Id</Label>
                  <AvInput id="launch-receiver-hist-id" type="select" className="form-control" name="idId" required>
                    {launchReceivers
                      ? launchReceivers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="launch-receiver-hist-idPipeline">Id Pipeline</Label>
                  <AvInput id="launch-receiver-hist-idPipeline" type="select" className="form-control" name="idPipelineId" required>
                    {pipelines
                      ? pipelines.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="launch-receiver-hist-idStatus">Id Status</Label>
                  <AvInput id="launch-receiver-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
                    {listObjectStatuses
                      ? listObjectStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/launch-receiver-hist" replace color="info">
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
  launchReceivers: storeState.launchReceiver.entities,
  pipelines: storeState.pipeline.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  launchReceiverHistEntity: storeState.launchReceiverHist.entity,
  loading: storeState.launchReceiverHist.loading,
  updating: storeState.launchReceiverHist.updating,
  updateSuccess: storeState.launchReceiverHist.updateSuccess
});

const mapDispatchToProps = {
  getLaunchReceivers,
  getPipelines,
  getListObjectStatuses,
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
)(LaunchReceiverHistUpdate);
