import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFreeSpanSupport } from 'app/shared/model/free-span-support.model';
import { getEntities as getFreeSpanSupports } from 'app/entities/free-span-support/free-span-support.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './free-span-support-hist.reducer';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFreeSpanSupportHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFreeSpanSupportHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idStatusId: string;
}

export class FreeSpanSupportHistUpdate extends React.Component<IFreeSpanSupportHistUpdateProps, IFreeSpanSupportHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineSectionId: '0',
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

    this.props.getFreeSpanSupports();
    this.props.getPipelineSections();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { freeSpanSupportHistEntity } = this.props;
      const entity = {
        ...freeSpanSupportHistEntity,
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
    this.props.history.push('/entity/free-span-support-hist');
  };

  render() {
    const { freeSpanSupportHistEntity, freeSpanSupports, pipelineSections, listObjectStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.freeSpanSupportHist.home.createOrEditLabel">Create or edit a FreeSpanSupportHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : freeSpanSupportHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="free-span-support-hist-id">ID</Label>
                    <AvInput id="free-span-support-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="free-span-support-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="free-span-support-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="free-span-support-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="free-span-support-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="free-span-support-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="free-span-support-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightLabel" for="free-span-support-hist-height">
                    Height
                  </Label>
                  <AvField id="free-span-support-hist-height" type="text" name="height" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpInstLabel" for="free-span-support-hist-kpInst">
                    Kp Inst
                  </Label>
                  <AvField id="free-span-support-hist-kpInst" type="text" name="kpInst" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="free-span-support-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="free-span-support-hist-isCurrentFlag"
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
                  <Label id="commentLabel" for="free-span-support-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="free-span-support-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="free-span-support-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="free-span-support-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanSupportHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="free-span-support-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="free-span-support-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanSupportHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="free-span-support-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="free-span-support-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="free-span-support-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="free-span-support-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-support-hist-id">Id</Label>
                  <AvInput id="free-span-support-hist-id" type="select" className="form-control" name="idId" required>
                    {freeSpanSupports
                      ? freeSpanSupports.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-support-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput
                    id="free-span-support-hist-idPipelineSection"
                    type="select"
                    className="form-control"
                    name="idPipelineSectionId"
                    required
                  >
                    {pipelineSections
                      ? pipelineSections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-support-hist-idStatus">Id Status</Label>
                  <AvInput id="free-span-support-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/free-span-support-hist" replace color="info">
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
  freeSpanSupports: storeState.freeSpanSupport.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  freeSpanSupportHistEntity: storeState.freeSpanSupportHist.entity,
  loading: storeState.freeSpanSupportHist.loading,
  updating: storeState.freeSpanSupportHist.updating,
  updateSuccess: storeState.freeSpanSupportHist.updateSuccess
});

const mapDispatchToProps = {
  getFreeSpanSupports,
  getPipelineSections,
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
)(FreeSpanSupportHistUpdate);
