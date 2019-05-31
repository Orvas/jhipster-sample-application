import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IListObjectType } from 'app/shared/model/list-object-type.model';
import { getEntities as getListObjectTypes } from 'app/entities/list-object-type/list-object-type.reducer';
import { IAnode } from 'app/shared/model/anode.model';
import { getEntities as getAnodes } from 'app/entities/anode/anode.reducer';
import { IBend } from 'app/shared/model/bend.model';
import { getEntities as getBends } from 'app/entities/bend/bend.reducer';
import { IBuckleArrestor } from 'app/shared/model/buckle-arrestor.model';
import { getEntities as getBuckleArrestors } from 'app/entities/buckle-arrestor/buckle-arrestor.reducer';
import { ICps } from 'app/shared/model/cps.model';
import { getEntities as getCps } from 'app/entities/cps/cps.reducer';
import { IDisplacement } from 'app/shared/model/displacement.model';
import { getEntities as getDisplacements } from 'app/entities/displacement/displacement.reducer';
import { IFreeSpan } from 'app/shared/model/free-span.model';
import { getEntities as getFreeSpans } from 'app/entities/free-span/free-span.reducer';
import { IFreeSpanSupport } from 'app/shared/model/free-span-support.model';
import { getEntities as getFreeSpanSupports } from 'app/entities/free-span-support/free-span-support.reducer';
import { ILaunchReceiver } from 'app/shared/model/launch-receiver.model';
import { getEntities as getLaunchReceivers } from 'app/entities/launch-receiver/launch-receiver.reducer';
import { IPipe } from 'app/shared/model/pipe.model';
import { getEntities as getPipes } from 'app/entities/pipe/pipe.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IPipeline } from 'app/shared/model/pipeline.model';
import { getEntities as getPipelines } from 'app/entities/pipeline/pipeline.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { ITee } from 'app/shared/model/tee.model';
import { getEntities as getTees } from 'app/entities/tee/tee.reducer';
import { IValve } from 'app/shared/model/valve.model';
import { getEntities as getValves } from 'app/entities/valve/valve.reducer';
import { getEntity, updateEntity, createEntity, reset } from './base-class.reducer';
import { IBaseClass } from 'app/shared/model/base-class.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBaseClassUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBaseClassUpdateState {
  isNew: boolean;
  idTypeId: string;
  anodeId: string;
  bendId: string;
  buckleArrestorId: string;
  cpsId: string;
  displacementId: string;
  freeSpanId: string;
  freeSpanSupportId: string;
  launchReceiverId: string;
  pipeId: string;
  pipejointId: string;
  pipelineId: string;
  pipelineSectionId: string;
  teeId: string;
  valveId: string;
}

export class BaseClassUpdate extends React.Component<IBaseClassUpdateProps, IBaseClassUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idTypeId: '0',
      anodeId: '0',
      bendId: '0',
      buckleArrestorId: '0',
      cpsId: '0',
      displacementId: '0',
      freeSpanId: '0',
      freeSpanSupportId: '0',
      launchReceiverId: '0',
      pipeId: '0',
      pipejointId: '0',
      pipelineId: '0',
      pipelineSectionId: '0',
      teeId: '0',
      valveId: '0',
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

    this.props.getListObjectTypes();
    this.props.getAnodes();
    this.props.getBends();
    this.props.getBuckleArrestors();
    this.props.getCps();
    this.props.getDisplacements();
    this.props.getFreeSpans();
    this.props.getFreeSpanSupports();
    this.props.getLaunchReceivers();
    this.props.getPipes();
    this.props.getPipejoints();
    this.props.getPipelines();
    this.props.getPipelineSections();
    this.props.getTees();
    this.props.getValves();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { baseClassEntity } = this.props;
      const entity = {
        ...baseClassEntity,
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
    this.props.history.push('/entity/base-class');
  };

  render() {
    const {
      baseClassEntity,
      listObjectTypes,
      anodes,
      bends,
      buckleArrestors,
      cps,
      displacements,
      freeSpans,
      freeSpanSupports,
      launchReceivers,
      pipes,
      pipejoints,
      pipelines,
      pipelineSections,
      tees,
      valves,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.baseClass.home.createOrEditLabel">Create or edit a BaseClass</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : baseClassEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="base-class-id">ID</Label>
                    <AvInput id="base-class-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateCreateLabel" for="base-class-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="base-class-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.baseClassEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="base-class-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="base-class-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.baseClassEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="base-class-creator">
                    Creator
                  </Label>
                  <AvField
                    id="base-class-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="base-class-editor">
                    Editor
                  </Label>
                  <AvField
                    id="base-class-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="base-class-idType">Id Type</Label>
                  <AvInput id="base-class-idType" type="select" className="form-control" name="idTypeId" required>
                    {listObjectTypes
                      ? listObjectTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/base-class" replace color="info">
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
  listObjectTypes: storeState.listObjectType.entities,
  anodes: storeState.anode.entities,
  bends: storeState.bend.entities,
  buckleArrestors: storeState.buckleArrestor.entities,
  cps: storeState.cps.entities,
  displacements: storeState.displacement.entities,
  freeSpans: storeState.freeSpan.entities,
  freeSpanSupports: storeState.freeSpanSupport.entities,
  launchReceivers: storeState.launchReceiver.entities,
  pipes: storeState.pipe.entities,
  pipejoints: storeState.pipejoint.entities,
  pipelines: storeState.pipeline.entities,
  pipelineSections: storeState.pipelineSection.entities,
  tees: storeState.tee.entities,
  valves: storeState.valve.entities,
  baseClassEntity: storeState.baseClass.entity,
  loading: storeState.baseClass.loading,
  updating: storeState.baseClass.updating,
  updateSuccess: storeState.baseClass.updateSuccess
});

const mapDispatchToProps = {
  getListObjectTypes,
  getAnodes,
  getBends,
  getBuckleArrestors,
  getCps,
  getDisplacements,
  getFreeSpans,
  getFreeSpanSupports,
  getLaunchReceivers,
  getPipes,
  getPipejoints,
  getPipelines,
  getPipelineSections,
  getTees,
  getValves,
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
)(BaseClassUpdate);
