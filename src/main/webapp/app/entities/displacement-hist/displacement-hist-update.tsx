import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDisplacement } from 'app/shared/model/displacement.model';
import { getEntities as getDisplacements } from 'app/entities/displacement/displacement.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { getEntity, updateEntity, createEntity, reset } from './displacement-hist.reducer';
import { IDisplacementHist } from 'app/shared/model/displacement-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDisplacementHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDisplacementHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
}

export class DisplacementHistUpdate extends React.Component<IDisplacementHistUpdateProps, IDisplacementHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineSectionId: '0',
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

    this.props.getDisplacements();
    this.props.getPipelineSections();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { displacementHistEntity } = this.props;
      const entity = {
        ...displacementHistEntity,
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
    this.props.history.push('/entity/displacement-hist');
  };

  render() {
    const { displacementHistEntity, displacements, pipelineSections, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.displacementHist.home.createOrEditLabel">Create or edit a DisplacementHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : displacementHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="displacement-hist-id">ID</Label>
                    <AvInput id="displacement-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="displacement-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="displacement-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="displacement-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="displacement-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="displacement-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="displacement-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="deltaXLabel" for="displacement-hist-deltaX">
                    Delta X
                  </Label>
                  <AvField id="displacement-hist-deltaX" type="text" name="deltaX" />
                </AvGroup>
                <AvGroup>
                  <Label id="deltaYLabel" for="displacement-hist-deltaY">
                    Delta Y
                  </Label>
                  <AvField id="displacement-hist-deltaY" type="text" name="deltaY" />
                </AvGroup>
                <AvGroup>
                  <Label id="deltaZLabel" for="displacement-hist-deltaZ">
                    Delta Z
                  </Label>
                  <AvField id="displacement-hist-deltaZ" type="text" name="deltaZ" />
                </AvGroup>
                <AvGroup>
                  <Label id="deltaTotalLabel" for="displacement-hist-deltaTotal">
                    Delta Total
                  </Label>
                  <AvField id="displacement-hist-deltaTotal" type="text" name="deltaTotal" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="displacement-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="displacement-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="displacement-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="displacement-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="displacement-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="displacement-hist-isCurrentFlag"
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
                  <Label id="idStatusLabel" for="displacement-hist-idStatus">
                    Id Status
                  </Label>
                  <AvField
                    id="displacement-hist-idStatus"
                    type="string"
                    className="form-control"
                    name="idStatus"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="displacement-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="displacement-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="displacement-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="displacement-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.displacementHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="displacement-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="displacement-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.displacementHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="displacement-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="displacement-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="displacement-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="displacement-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="displacement-hist-id">Id</Label>
                  <AvInput id="displacement-hist-id" type="select" className="form-control" name="idId" required>
                    {displacements
                      ? displacements.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="displacement-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput
                    id="displacement-hist-idPipelineSection"
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
                <Button tag={Link} id="cancel-save" to="/entity/displacement-hist" replace color="info">
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
  displacements: storeState.displacement.entities,
  pipelineSections: storeState.pipelineSection.entities,
  displacementHistEntity: storeState.displacementHist.entity,
  loading: storeState.displacementHist.loading,
  updating: storeState.displacementHist.updating,
  updateSuccess: storeState.displacementHist.updateSuccess
});

const mapDispatchToProps = {
  getDisplacements,
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
)(DisplacementHistUpdate);
