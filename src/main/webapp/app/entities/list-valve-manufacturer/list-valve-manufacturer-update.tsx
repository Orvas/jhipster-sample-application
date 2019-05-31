import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './list-valve-manufacturer.reducer';
import { IListValveManufacturer } from 'app/shared/model/list-valve-manufacturer.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IListValveManufacturerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IListValveManufacturerUpdateState {
  isNew: boolean;
}

export class ListValveManufacturerUpdate extends React.Component<IListValveManufacturerUpdateProps, IListValveManufacturerUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { listValveManufacturerEntity } = this.props;
      const entity = {
        ...listValveManufacturerEntity,
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
    this.props.history.push('/entity/list-valve-manufacturer');
  };

  render() {
    const { listValveManufacturerEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.listValveManufacturer.home.createOrEditLabel">Create or edit a ListValveManufacturer</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : listValveManufacturerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="list-valve-manufacturer-id">ID</Label>
                    <AvInput id="list-valve-manufacturer-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="list-valve-manufacturer-code">
                    Code
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-code"
                    type="text"
                    name="code"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="list-valve-manufacturer-name">
                    Name
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="list-valve-manufacturer-fullName">
                    Full Name
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-fullName"
                    type="text"
                    name="fullName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="list-valve-manufacturer-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="list-valve-manufacturer-description">
                    Description
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="list-valve-manufacturer-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="list-valve-manufacturer-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listValveManufacturerEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="list-valve-manufacturer-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="list-valve-manufacturer-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listValveManufacturerEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="list-valve-manufacturer-creator">
                    Creator
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="list-valve-manufacturer-editor">
                    Editor
                  </Label>
                  <AvField
                    id="list-valve-manufacturer-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/list-valve-manufacturer" replace color="info">
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
  listValveManufacturerEntity: storeState.listValveManufacturer.entity,
  loading: storeState.listValveManufacturer.loading,
  updating: storeState.listValveManufacturer.updating,
  updateSuccess: storeState.listValveManufacturer.updateSuccess
});

const mapDispatchToProps = {
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
)(ListValveManufacturerUpdate);
