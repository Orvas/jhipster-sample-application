import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { getEntity, updateEntity, createEntity, reset } from './free-span-history.reducer';
import { IFreeSpanHistory } from 'app/shared/model/free-span-history.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFreeSpanHistoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFreeSpanHistoryUpdateState {
  isNew: boolean;
  statusId: string;
  pipelineSectionId: string;
}

export class FreeSpanHistoryUpdate extends React.Component<IFreeSpanHistoryUpdateProps, IFreeSpanHistoryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      statusId: '0',
      pipelineSectionId: '0',
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

    this.props.getListObjectStatuses();
    this.props.getPipelineSections();
  }

  saveEntity = (event, errors, values) => {
    values.dateForm = convertDateTimeToServer(values.dateForm);
    values.dateTo = convertDateTimeToServer(values.dateTo);
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { freeSpanHistoryEntity } = this.props;
      const entity = {
        ...freeSpanHistoryEntity,
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
    this.props.history.push('/entity/free-span-history');
  };

  render() {
    const { freeSpanHistoryEntity, listObjectStatuses, pipelineSections, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.freeSpanHistory.home.createOrEditLabel">Create or edit a FreeSpanHistory</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : freeSpanHistoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="free-span-history-id">ID</Label>
                    <AvInput id="free-span-history-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFormLabel" for="free-span-history-dateForm">
                    Date Form
                  </Label>
                  <AvInput
                    id="free-span-history-dateForm"
                    type="datetime-local"
                    className="form-control"
                    name="dateForm"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistoryEntity.dateForm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="free-span-history-dateTo">
                    Date To
                  </Label>
                  <AvInput
                    id="free-span-history-dateTo"
                    type="datetime-local"
                    className="form-control"
                    name="dateTo"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistoryEntity.dateTo)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="workIdLabel" for="free-span-history-workId">
                    Work Id
                  </Label>
                  <AvField id="free-span-history-workId" type="string" className="form-control" name="workId" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="free-span-history-length">
                    Length
                  </Label>
                  <AvField id="free-span-history-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="free-span-history-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="free-span-history-kpStart" type="string" className="form-control" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="free-span-history-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="free-span-history-kpEnd" type="string" className="form-control" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" check>
                    <AvInput id="free-span-history-isCurrentFlag" type="checkbox" className="form-control" name="isCurrentFlag" />
                    Is Current Flag
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="statusIdLabel" for="free-span-history-statusId">
                    Status Id
                  </Label>
                  <AvField id="free-span-history-statusId" type="string" className="form-control" name="statusId" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="free-span-history-comment">
                    Comment
                  </Label>
                  <AvField id="free-span-history-comment" type="text" name="comment" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="free-span-history-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="free-span-history-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistoryEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="free-span-history-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="free-span-history-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanHistoryEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="free-span-history-creator">
                    Creator
                  </Label>
                  <AvField id="free-span-history-creator" type="text" name="creator" />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="free-span-history-editor">
                    Editor
                  </Label>
                  <AvField id="free-span-history-editor" type="text" name="editor" />
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-history-status">Status</Label>
                  <AvInput id="free-span-history-status" type="select" className="form-control" name="status.id">
                    <option value="" key="0" />
                    {listObjectStatuses
                      ? listObjectStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-history-pipelineSection">Pipeline Section</Label>
                  <AvInput id="free-span-history-pipelineSection" type="select" className="form-control" name="pipelineSection.id">
                    <option value="" key="0" />
                    {pipelineSections
                      ? pipelineSections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/free-span-history" replace color="info">
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
  listObjectStatuses: storeState.listObjectStatus.entities,
  pipelineSections: storeState.pipelineSection.entities,
  freeSpanHistoryEntity: storeState.freeSpanHistory.entity,
  loading: storeState.freeSpanHistory.loading,
  updating: storeState.freeSpanHistory.updating,
  updateSuccess: storeState.freeSpanHistory.updateSuccess
});

const mapDispatchToProps = {
  getListObjectStatuses,
  getPipelineSections,
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
)(FreeSpanHistoryUpdate);
