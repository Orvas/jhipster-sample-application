import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPipeline } from 'app/shared/model/pipeline.model';
import { getEntities as getPipelines } from 'app/entities/pipeline/pipeline.reducer';
import { IListPipelineLocation } from 'app/shared/model/list-pipeline-location.model';
import { getEntities as getListPipelineLocations } from 'app/entities/list-pipeline-location/list-pipeline-location.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pipeline-hist.reducer';
import { IPipelineHist } from 'app/shared/model/pipeline-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPipelineHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPipelineHistUpdateState {
  isNew: boolean;
  idId: string;
  idLocationId: string;
  idStatusId: string;
}

export class PipelineHistUpdate extends React.Component<IPipelineHistUpdateProps, IPipelineHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idLocationId: '0',
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

    this.props.getPipelines();
    this.props.getListPipelineLocations();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { pipelineHistEntity } = this.props;
      const entity = {
        ...pipelineHistEntity,
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
    this.props.history.push('/entity/pipeline-hist');
  };

  render() {
    const { pipelineHistEntity, pipelines, listPipelineLocations, listObjectStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.pipelineHist.home.createOrEditLabel">Create or edit a PipelineHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pipelineHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pipeline-hist-id">ID</Label>
                    <AvInput id="pipeline-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="pipeline-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="pipeline-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="pipeline-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="pipeline-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="pipeline-hist-name">
                    Name
                  </Label>
                  <AvField
                    id="pipeline-hist-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designLifeLabel" for="pipeline-hist-designLife">
                    Design Life
                  </Label>
                  <AvField id="pipeline-hist-designLife" type="string" className="form-control" name="designLife" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="pipeline-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="pipeline-hist-isCurrentFlag"
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
                  <Label id="commentLabel" for="pipeline-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="pipeline-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="pipeline-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="pipeline-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipelineHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="pipeline-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="pipeline-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipelineHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="pipeline-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="pipeline-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="pipeline-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="pipeline-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="pipeline-hist-id">Id</Label>
                  <AvInput id="pipeline-hist-id" type="select" className="form-control" name="idId" required>
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
                  <Label for="pipeline-hist-idLocation">Id Location</Label>
                  <AvInput id="pipeline-hist-idLocation" type="select" className="form-control" name="idLocationId">
                    <option value="" key="0" />
                    {listPipelineLocations
                      ? listPipelineLocations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipeline-hist-idStatus">Id Status</Label>
                  <AvInput id="pipeline-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/pipeline-hist" replace color="info">
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
  pipelines: storeState.pipeline.entities,
  listPipelineLocations: storeState.listPipelineLocation.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  pipelineHistEntity: storeState.pipelineHist.entity,
  loading: storeState.pipelineHist.loading,
  updating: storeState.pipelineHist.updating,
  updateSuccess: storeState.pipelineHist.updateSuccess
});

const mapDispatchToProps = {
  getPipelines,
  getListPipelineLocations,
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
)(PipelineHistUpdate);
