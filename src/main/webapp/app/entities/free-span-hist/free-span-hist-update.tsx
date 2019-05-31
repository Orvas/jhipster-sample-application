import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFreeSpan } from 'app/shared/model/free-span.model';
import { getEntities as getFreeSpans } from 'app/entities/free-span/free-span.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './free-span-hist.reducer';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFreeSpanHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFreeSpanHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idStatusId: string;
}

export class FreeSpanHistUpdate extends React.Component<IFreeSpanHistUpdateProps, IFreeSpanHistUpdateState> {
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

    this.props.getFreeSpans();
    this.props.getPipelineSections();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { freeSpanHistEntity } = this.props;
      const entity = {
        ...freeSpanHistEntity,
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
    this.props.history.push('/entity/free-span-hist');
  };

  render() {
    const { freeSpanHistEntity, freeSpans, pipelineSections, listObjectStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.freeSpanHist.home.createOrEditLabel">Create or edit a FreeSpanHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : freeSpanHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="free-span-hist-id">ID</Label>
                    <AvInput id="free-span-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="free-span-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="free-span-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="free-span-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="free-span-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="free-span-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="free-span-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="lenghtLabel" for="free-span-hist-lenght">
                    Lenght
                  </Label>
                  <AvField id="free-span-hist-lenght" type="text" name="lenght" />
                </AvGroup>
                <AvGroup>
                  <Label id="lenghtAllowLabel" for="free-span-hist-lenghtAllow">
                    Lenght Allow
                  </Label>
                  <AvField id="free-span-hist-lenghtAllow" type="text" name="lenghtAllow" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightLabel" for="free-span-hist-height">
                    Height
                  </Label>
                  <AvField id="free-span-hist-height" type="text" name="height" />
                </AvGroup>
                <AvGroup>
                  <Label id="isMultispanLabel" for="free-span-hist-isMultispan">
                    Is Multispan
                  </Label>
                  <AvField id="free-span-hist-isMultispan" type="string" className="form-control" name="isMultispan" />
                </AvGroup>
                <AvGroup>
                  <Label id="gapLabel" for="free-span-hist-gap">
                    Gap
                  </Label>
                  <AvField id="free-span-hist-gap" type="text" name="gap" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="free-span-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="free-span-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="free-span-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="free-span-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="free-span-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="free-span-hist-isCurrentFlag"
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
                  <Label id="commentLabel" for="free-span-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="free-span-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="free-span-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="free-span-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="free-span-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="free-span-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="free-span-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="free-span-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="free-span-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="free-span-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-hist-id">Id</Label>
                  <AvInput id="free-span-hist-id" type="select" className="form-control" name="idId" required>
                    {freeSpans
                      ? freeSpans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="free-span-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="free-span-hist-idStatus">Id Status</Label>
                  <AvInput id="free-span-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/free-span-hist" replace color="info">
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
  freeSpans: storeState.freeSpan.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  freeSpanHistEntity: storeState.freeSpanHist.entity,
  loading: storeState.freeSpanHist.loading,
  updating: storeState.freeSpanHist.updating,
  updateSuccess: storeState.freeSpanHist.updateSuccess
});

const mapDispatchToProps = {
  getFreeSpans,
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
)(FreeSpanHistUpdate);
