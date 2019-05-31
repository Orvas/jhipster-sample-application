import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPipejoint } from 'app/shared/model/pipejoint.model';
import { getEntities as getPipejoints } from 'app/entities/pipejoint/pipejoint.reducer';
import { IListPipejointType } from 'app/shared/model/list-pipejoint-type.model';
import { getEntities as getListPipejointTypes } from 'app/entities/list-pipejoint-type/list-pipejoint-type.reducer';
import { IListExternalCoating } from 'app/shared/model/list-external-coating.model';
import { getEntities as getListExternalCoatings } from 'app/entities/list-external-coating/list-external-coating.reducer';
import { IListMaterial } from 'app/shared/model/list-material.model';
import { getEntities as getListMaterials } from 'app/entities/list-material/list-material.reducer';
import { IListPipejointSpecification } from 'app/shared/model/list-pipejoint-specification.model';
import { getEntities as getListPipejointSpecifications } from 'app/entities/list-pipejoint-specification/list-pipejoint-specification.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
import { getEntities as getListObjectStatuses } from 'app/entities/list-object-status/list-object-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pipejoint-hist.reducer';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPipejointHistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPipejointHistUpdateState {
  isNew: boolean;
  pipejointId: string;
  idTypeId: string;
  idExternalCoatTypeId: string;
  idMaterialId: string;
  idSpecificationId: string;
  idStatusId: string;
}

export class PipejointHistUpdate extends React.Component<IPipejointHistUpdateProps, IPipejointHistUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      pipejointId: '0',
      idTypeId: '0',
      idExternalCoatTypeId: '0',
      idMaterialId: '0',
      idSpecificationId: '0',
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

    this.props.getPipejoints();
    this.props.getListPipejointTypes();
    this.props.getListExternalCoatings();
    this.props.getListMaterials();
    this.props.getListPipejointSpecifications();
    this.props.getListObjectStatuses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { pipejointHistEntity } = this.props;
      const entity = {
        ...pipejointHistEntity,
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
    this.props.history.push('/entity/pipejoint-hist');
  };

  render() {
    const {
      pipejointHistEntity,
      pipejoints,
      listPipejointTypes,
      listExternalCoatings,
      listMaterials,
      listPipejointSpecifications,
      listObjectStatuses,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.pipejointHist.home.createOrEditLabel">Create or edit a PipejointHist</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pipejointHistEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pipejoint-hist-id">ID</Label>
                    <AvInput id="pipejoint-hist-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateFromLabel" for="pipejoint-hist-dateFrom">
                    Date From
                  </Label>
                  <AvField
                    id="pipejoint-hist-dateFrom"
                    type="date"
                    className="form-control"
                    name="dateFrom"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateToLabel" for="pipejoint-hist-dateTo">
                    Date To
                  </Label>
                  <AvField
                    id="pipejoint-hist-dateTo"
                    type="date"
                    className="form-control"
                    name="dateTo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="pipejoint-hist-name">
                    Name
                  </Label>
                  <AvField
                    id="pipejoint-hist-name"
                    type="text"
                    name="name"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="externalCoatThicknessLabel" for="pipejoint-hist-externalCoatThickness">
                    External Coat Thickness
                  </Label>
                  <AvField id="pipejoint-hist-externalCoatThickness" type="text" name="externalCoatThickness" />
                </AvGroup>
                <AvGroup>
                  <Label id="coordLabel" for="pipejoint-hist-coord">
                    Coord
                  </Label>
                  <AvField
                    id="pipejoint-hist-coord"
                    type="text"
                    name="coord"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="pipejoint-hist-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="pipejoint-hist-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="pipejoint-hist-description">
                    Description
                  </Label>
                  <AvField
                    id="pipejoint-hist-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentLabel" for="pipejoint-hist-comment">
                    Comment
                  </Label>
                  <AvField
                    id="pipejoint-hist-comment"
                    type="text"
                    name="comment"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="pipejoint-hist-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="pipejoint-hist-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipejointHistEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="pipejoint-hist-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="pipejoint-hist-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.pipejointHistEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="pipejoint-hist-creator">
                    Creator
                  </Label>
                  <AvField
                    id="pipejoint-hist-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="pipejoint-hist-editor">
                    Editor
                  </Label>
                  <AvField
                    id="pipejoint-hist-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="pipejoint-hist-pipejoint">Pipejoint</Label>
                  <AvInput id="pipejoint-hist-pipejoint" type="select" className="form-control" name="pipejointId">
                    <option value="" key="0" />
                    {pipejoints
                      ? pipejoints.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipejoint-hist-idType">Id Type</Label>
                  <AvInput id="pipejoint-hist-idType" type="select" className="form-control" name="idTypeId">
                    <option value="" key="0" />
                    {listPipejointTypes
                      ? listPipejointTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipejoint-hist-idExternalCoatType">Id External Coat Type</Label>
                  <AvInput id="pipejoint-hist-idExternalCoatType" type="select" className="form-control" name="idExternalCoatTypeId">
                    <option value="" key="0" />
                    {listExternalCoatings
                      ? listExternalCoatings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipejoint-hist-idMaterial">Id Material</Label>
                  <AvInput id="pipejoint-hist-idMaterial" type="select" className="form-control" name="idMaterialId">
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
                  <Label for="pipejoint-hist-idSpecification">Id Specification</Label>
                  <AvInput id="pipejoint-hist-idSpecification" type="select" className="form-control" name="idSpecificationId">
                    <option value="" key="0" />
                    {listPipejointSpecifications
                      ? listPipejointSpecifications.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pipejoint-hist-idStatus">Id Status</Label>
                  <AvInput id="pipejoint-hist-idStatus" type="select" className="form-control" name="idStatusId" required>
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
                <Button tag={Link} id="cancel-save" to="/entity/pipejoint-hist" replace color="info">
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
  pipejoints: storeState.pipejoint.entities,
  listPipejointTypes: storeState.listPipejointType.entities,
  listExternalCoatings: storeState.listExternalCoating.entities,
  listMaterials: storeState.listMaterial.entities,
  listPipejointSpecifications: storeState.listPipejointSpecification.entities,
  listObjectStatuses: storeState.listObjectStatus.entities,
  pipejointHistEntity: storeState.pipejointHist.entity,
  loading: storeState.pipejointHist.loading,
  updating: storeState.pipejointHist.updating,
  updateSuccess: storeState.pipejointHist.updateSuccess
});

const mapDispatchToProps = {
  getPipejoints,
  getListPipejointTypes,
  getListExternalCoatings,
  getListMaterials,
  getListPipejointSpecifications,
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
)(PipejointHistUpdate);
