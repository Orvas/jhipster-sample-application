import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICps } from 'app/shared/model/cps.model';
import { getEntities as getCps } from 'app/entities/cps/cps.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './cps-hist.reducer';
import { ICpsHist } from 'app/shared/model/cps-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICpsHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICpsHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idStatusId: string;
}

export class CpsHistUpdate extends React.Component<ICpsHistUpdateProps, ICpsHistUpdateState> {
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

    this.props.getCps();
    this.props.getPipelineSections();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { cpsHistEntity } = this.props;
      const entity = {
        ...cpsHistEntity,
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
    this.props.history.push('/entity/cps-hist');
  };

  render() {
    const { cpsHistEntity, cps, pipelineSections, listObjectStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.cpsHist.home.createOrEditLabel">Create or edit a CpsHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : cpsHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="cps-hist-id">ID</Label>
                    <AvInput id="cps-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="cps-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="cps-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="cps-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="cps-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="cps-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="cps-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="currentLabel" for="cps-hist-current">
                    Current
                  </Label>
                  <AvField id="cps-hist-current" type="text" name="current" />
                </AvGroup>
                <AvGroup>
                  <Label id="potentialLabel" for="cps-hist-potential">
                    Potential
                  </Label>
                  <AvField id="cps-hist-potential" type="text" name="potential" />
                </AvGroup>
                <AvGroup>
                  <Label id="downtimeLabel" for="cps-hist-downtime">
                    Downtime
                  </Label>
                  <AvField id="cps-hist-downtime" type="text" name="downtime" />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="cps-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="cps-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpInstLabel" for="cps-hist-kpInst">
                    Kp Inst
                  </Label>
                  <AvField id="cps-hist-kpInst" type="text" name="kpInst" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="cps-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="cps-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="cps-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="cps-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="cps-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="cps-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="cps-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="cps-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.cpsHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="cps-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="cps-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.cpsHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="cps-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="cps-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="cps-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="cps-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="cps-hist-id">Id</Label>
                  <AvInput id="cps-hist-id" type="select" className="form-control" name="idId" required>
                    {cps
                      ? cps.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="cps-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="cps-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="cps-hist-idStatus">Id Status</Label>
                  <AvInput id="cps-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/cps-hist" replace color="info">
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
  cps: storeState.cps.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  cpsHistEntity: storeState.cpsHist.entity,
  loading: storeState.cpsHist.loading,
  updating: storeState.cpsHist.updating,
  updateSuccess: storeState.cpsHist.updateSuccess
});

const mapDispatchToProps = {
  getCps,
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
)(CpsHistUpdate);
