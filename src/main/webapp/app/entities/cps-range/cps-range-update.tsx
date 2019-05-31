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
import { getEntity, updateEntity, createEntity, reset } from './cps-range.reducer';
import { ICpsRange } from 'app/shared/model/cps-range.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICpsRangeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICpsRangeUpdateState {
  isNew: boolean;
  idCpsId: string;
  idPipelineSectionId: string;
}

export class CpsRangeUpdate extends React.Component<ICpsRangeUpdateProps, ICpsRangeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idCpsId: '0',
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

    this.props.getCps();
    this.props.getPipelineSections();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { cpsRangeEntity } = this.props;
      const entity = {
        ...cpsRangeEntity,
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
    this.props.history.push('/entity/cps-range');
  };

  render() {
    const { cpsRangeEntity, cps, pipelineSections, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.cpsRange.home.createOrEditLabel">Create or edit a CpsRange</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : cpsRangeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="cps-range-id">ID</Label>
                    <AvInput id="cps-range-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="kpStartLabel" for="cps-range-kpStart">
                    Kp Start
                  </Label>
                  <AvField
                    id="cps-range-kpStart"
                    type="text"
                    name="kpStart"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="cps-range-kpEnd">
                    Kp End
                  </Label>
                  <AvField
                    id="cps-range-kpEnd"
                    type="text"
                    name="kpEnd"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateFromLabel" for="cps-range-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="cps-range-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="cps-range-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="cps-range-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="cps-range-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="cps-range-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.cpsRangeEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="cps-range-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="cps-range-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.cpsRangeEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="cps-range-creator">
                    Creator
                  </Label>
                  <AvField
                    id="cps-range-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="cps-range-editor">
                    Editor
                  </Label>
                  <AvField
                    id="cps-range-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="cps-range-idCps">Id Cps</Label>
                  <AvInput id="cps-range-idCps" type="select" className="form-control" name="idCpsId" required>
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
                  <Label for="cps-range-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="cps-range-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/cps-range" replace color="info">
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
  cpsRangeEntity: storeState.cpsRange.entity,
  loading: storeState.cpsRange.loading,
  updating: storeState.cpsRange.updating,
  updateSuccess: storeState.cpsRange.updateSuccess
});

const mapDispatchToProps = {
  getCps,
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
)(CpsRangeUpdate);
