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
import { IPipeline } from 'app/shared/model/pipeline.model';
import { getEntities as getPipelines } from 'app/entities/pipeline/pipeline.reducer';
import { IListSafetyClass } from 'app/shared/model/list-safety-class.model';
import { getEntities as getListSafetyClasses } from 'app/entities/list-safety-class/list-safety-class.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pipeline-section.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPipelineSectionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPipelineSectionUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineId: string;
  idSafetyClassId: string;
}

export class PipelineSectionUpdate extends React.Component<IPipelineSectionUpdateProps, IPipelineSectionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineId: '0',
      idSafetyClassId: '0',
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
    this.props.getPipelines();
    this.props.getListSafetyClasses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { pipelineSectionEntity } = this.props;
      const entity = {
        ...pipelineSectionEntity,
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
    this.props.history.push('/entity/pipeline-section');
  };

  render() {
    const { pipelineSectionEntity, baseClasses, pipelines, listSafetyClasses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.pipelineSection.home.createOrEditLabel">Create or edit a PipelineSection</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pipelineSectionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pipeline-section-id">ID</Label>
                    <AvInput id="pipeline-section-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="pipeline-section-name">
                    Name
                  </Label>
                  <AvField
                    id="pipeline-section-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isOnshoreLabel" for="pipeline-section-isOnshore">
                    Is Onshore
                  </Label>
                  <AvField
                    id="pipeline-section-isOnshore"
                    type="string"
                    className="form-control"
                    name="isOnshore"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="pipeline-section-kpStart">
                    Kp Start
                  </Label>
                  <AvField
                    id="pipeline-section-kpStart"
                    type="text"
                    name="kpStart"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="pipeline-section-kpEnd">
                    Kp End
                  </Label>
                  <AvField
                    id="pipeline-section-kpEnd"
                    type="text"
                    name="kpEnd"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="pipeline-section-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="pipeline-section-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipelineSectionEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="pipeline-section-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="pipeline-section-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipelineSectionEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="pipeline-section-creator">
                    Creator
                  </Label>
                  <AvField
                    id="pipeline-section-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="pipeline-section-editor">
                    Editor
                  </Label>
                  <AvField
                    id="pipeline-section-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="pipeline-section-id">Id</Label>
                  <AvInput id="pipeline-section-id" type="select" className="form-control" name="idId" required>
                    {baseClasses
                      ? baseClasses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="pipeline-section-idPipeline">Id Pipeline</Label>
                  <AvInput id="pipeline-section-idPipeline" type="select" className="form-control" name="idPipelineId" required>
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
                  <Label for="pipeline-section-idSafetyClass">Id Safety Class</Label>
                  <AvInput id="pipeline-section-idSafetyClass" type="select" className="form-control" name="idSafetyClassId" required>
                    {listSafetyClasses
                      ? listSafetyClasses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pipeline-section" replace color="info">
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
  pipelines: storeState.pipeline.entities,
  listSafetyClasses: storeState.listSafetyClass.entities,
  pipelineSectionEntity: storeState.pipelineSection.entity,
  loading: storeState.pipelineSection.loading,
  updating: storeState.pipelineSection.updating,
  updateSuccess: storeState.pipelineSection.updateSuccess
});

const mapDispatchToProps = {
  getBaseClasses,
  getPipelines,
  getListSafetyClasses,
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
)(PipelineSectionUpdate);
