import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAnode } from 'app/shared/model/anode.model';
import { getEntities as getAnodes } from 'app/entities/anode/anode.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
import { getEntities as getPipelineSections } from 'app/entities/pipeline-section/pipeline-section.reducer';
import { IListAnodeBraceleteType } from 'app/shared/model/list-anode-bracelete-type.model';
import { getEntities as getListAnodeBraceleteTypes } from 'app/entities/list-anode-bracelete-type/list-anode-bracelete-type.reducer';
import { IListMaterial } from 'app/shared/model/list-material.model';
import { getEntities as getListMaterials } from 'app/entities/list-material/list-material.reducer';
import { IListWrkStatus } from 'app/shared/model/list-wrk-status.model';
import { getEntities as getListWrkStatuses } from 'app/entities/list-wrk-status/list-wrk-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './anode-hist.reducer';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAnodeHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAnodeHistUpdateState {
  isNew: boolean;
  idId: string;
  idPipelineSectionId: string;
  idBraceleteTypeId: string;
  idMaterialId: string;
  idStatusId: string;
}

export class AnodeHistUpdate extends React.Component<IAnodeHistUpdateProps, IAnodeHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
      idPipelineSectionId: '0',
      idBraceleteTypeId: '0',
      idMaterialId: '0',
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

    this.props.getAnodes();
    this.props.getPipelineSections();
    this.props.getListAnodeBraceleteTypes();
    this.props.getListMaterials();
    this.props.getListWrkStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { anodeHistEntity } = this.props;
      const entity = {
        ...anodeHistEntity,
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
    this.props.history.push('/entity/anode-hist');
  };

  render() {
    const {
      anodeHistEntity,
      anodes,
      pipelineSections,
      listAnodeBraceleteTypes,
      listMaterials,
      listWrkStatuses,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.anodeHist.home.createOrEditLabel">Create or edit a AnodeHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : anodeHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="anode-hist-id">ID</Label>
                    <AvInput id="anode-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="anode-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="anode-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="anode-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="anode-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idWrkLabel" for="anode-hist-idWrk">
                    Id Wrk
                  </Label>
                  <AvField id="anode-hist-idWrk" type="string" className="form-control" name="idWrk" />
                </AvGroup>
                <AvGroup>
                  <Label id="designLifeLabel" for="anode-hist-designLife">
                    Design Life
                  </Label>
                  <AvField id="anode-hist-designLife" type="string" className="form-control" name="designLife" />
                </AvGroup>
                <AvGroup>
                  <Label id="dmcdLabel" for="anode-hist-dmcd">
                    Dmcd
                  </Label>
                  <AvField id="anode-hist-dmcd" type="text" name="dmcd" />
                </AvGroup>
                <AvGroup>
                  <Label id="l1Label" for="anode-hist-l1">
                    L 1
                  </Label>
                  <AvField id="anode-hist-l1" type="text" name="l1" />
                </AvGroup>
                <AvGroup>
                  <Label id="l2Label" for="anode-hist-l2">
                    L 2
                  </Label>
                  <AvField id="anode-hist-l2" type="text" name="l2" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="anode-hist-length">
                    Length
                  </Label>
                  <AvField id="anode-hist-length" type="string" className="form-control" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="electrCapacLabel" for="anode-hist-electrCapac">
                    Electr Capac
                  </Label>
                  <AvField id="anode-hist-electrCapac" type="text" name="electrCapac" />
                </AvGroup>
                <AvGroup>
                  <Label id="designWeightLabel" for="anode-hist-designWeight">
                    Design Weight
                  </Label>
                  <AvField id="anode-hist-designWeight" type="text" name="designWeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="anode-hist-weight">
                    Weight
                  </Label>
                  <AvField id="anode-hist-weight" type="text" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label id="isBurialLabel" for="anode-hist-isBurial">
                    Is Burial
                  </Label>
                  <AvField id="anode-hist-isBurial" type="string" className="form-control" name="isBurial" />
                </AvGroup>
                <AvGroup>
                  <Label id="chemicalCompositionLabel" for="anode-hist-chemicalComposition">
                    Chemical Composition
                  </Label>
                  <AvField
                    id="anode-hist-chemicalComposition"
                    type="text"
                    name="chemicalComposition"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="densityLabel" for="anode-hist-density">
                    Density
                  </Label>
                  <AvField id="anode-hist-density" type="text" name="density" />
                </AvGroup>
                <AvGroup>
                  <Label id="spacingLabel" for="anode-hist-spacing">
                    Spacing
                  </Label>
                  <AvField id="anode-hist-spacing" type="string" className="form-control" name="spacing" />
                </AvGroup>
                <AvGroup>
                  <Label id="coatCutbackLengthLabel" for="anode-hist-coatCutbackLength">
                    Coat Cutback Length
                  </Label>
                  <AvField id="anode-hist-coatCutbackLength" type="string" className="form-control" name="coatCutbackLength" />
                </AvGroup>
                <AvGroup>
                  <Label id="coatDmgAreaLabel" for="anode-hist-coatDmgArea">
                    Coat Dmg Area
                  </Label>
                  <AvField id="anode-hist-coatDmgArea" type="text" name="coatDmgArea" />
                </AvGroup>
                <AvGroup>
                  <Label id="h2sSoilLabel" for="anode-hist-h2sSoil">
                    H 2 S Soil
                  </Label>
                  <AvField id="anode-hist-h2sSoil" type="text" name="h2sSoil" />
                </AvGroup>
                <AvGroup>
                  <Label id="remainLabel" for="anode-hist-remain">
                    Remain
                  </Label>
                  <AvField id="anode-hist-remain" type="text" name="remain" />
                </AvGroup>
                <AvGroup>
                  <Label id="intFldTempLabel" for="anode-hist-intFldTemp">
                    Int Fld Temp
                  </Label>
                  <AvField id="anode-hist-intFldTemp" type="text" name="intFldTemp" />
                </AvGroup>
                <AvGroup>
                  <Label id="minPrcLabel" for="anode-hist-minPrc">
                    Min Prc
                  </Label>
                  <AvField id="anode-hist-minPrc" type="text" name="minPrc" />
                </AvGroup>
                <AvGroup>
                  <Label id="thicknessLabel" for="anode-hist-thickness">
                    Thickness
                  </Label>
                  <AvField id="anode-hist-thickness" type="text" name="thickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="anode-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="anode-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kpStartLabel" for="anode-hist-kpStart">
                    Kp Start
                  </Label>
                  <AvField id="anode-hist-kpStart" type="text" name="kpStart" />
                </AvGroup>
                <AvGroup>
                  <Label id="coatThicknessLabel" for="anode-hist-coatThickness">
                    Coat Thickness
                  </Label>
                  <AvField id="anode-hist-coatThickness" type="text" name="coatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="kpEndLabel" for="anode-hist-kpEnd">
                    Kp End
                  </Label>
                  <AvField id="anode-hist-kpEnd" type="text" name="kpEnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="anode-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="anode-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="anode-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="anode-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="anode-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="anode-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="anode-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="anode-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.anodeHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="anode-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="anode-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.anodeHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="anode-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="anode-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="anode-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="anode-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="anode-hist-id">Id</Label>
                  <AvInput id="anode-hist-id" type="select" className="form-control" name="idId" required>
                    {anodes
                      ? anodes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <AvGroup>
                  <Label for="anode-hist-idPipelineSection">Id Pipeline Section</Label>
                  <AvInput id="anode-hist-idPipelineSection" type="select" className="form-control" name="idPipelineSectionId" required>
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
                  <Label for="anode-hist-idBraceleteType">Id Bracelete Type</Label>
                  <AvInput id="anode-hist-idBraceleteType" type="select" className="form-control" name="idBraceleteTypeId">
                    <option value="" key="0" />
                    {listAnodeBraceleteTypes
                      ? listAnodeBraceleteTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="anode-hist-idMaterial">Id Material</Label>
                  <AvInput id="anode-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
                    <option value="" key="0" />
                    {listMaterials
                      ? listMaterials.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="anode-hist-idStatus">Id Status</Label>
                  <AvInput id="anode-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
                    {listWrkStatuses
                      ? listWrkStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/anode-hist" replace color="info">
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
  anodes: storeState.anode.entities,
  pipelineSections: storeState.pipelineSection.entities,
  listAnodeBraceleteTypes: storeState.listAnodeBraceleteType.entities,
  listMaterials: storeState.listMaterial.entities,
  listWrkStatuses: storeState.listWrkStatus.entities,
  anodeHistEntity: storeState.anodeHist.entity,
  loading: storeState.anodeHist.loading,
  updating: storeState.anodeHist.updating,
  updateSuccess: storeState.anodeHist.updateSuccess
});

const mapDispatchToProps = {
  getAnodes,
  getPipelineSections,
  getListAnodeBraceleteTypes,
  getListMaterials,
  getListWrkStatuses,
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
)(AnodeHistUpdate);
