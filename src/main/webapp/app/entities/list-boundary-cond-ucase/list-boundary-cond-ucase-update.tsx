import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './list-boundary-cond-ucase.reducer';
import { IListBoundaryCondUcase } from 'app/shared/model/list-boundary-cond-ucase.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IListBoundaryCondUcaseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IListBoundaryCondUcaseUpdateState {
  isNew: boolean;
}

export class ListBoundaryCondUcaseUpdate extends React.Component<IListBoundaryCondUcaseUpdateProps, IListBoundaryCondUcaseUpdateState> {
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
      const { listBoundaryCondUcaseEntity } = this.props;
      const entity = {
        ...listBoundaryCondUcaseEntity,
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
    this.props.history.push('/entity/list-boundary-cond-ucase');
  };

  render() {
    const { listBoundaryCondUcaseEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.listBoundaryCondUcase.home.createOrEditLabel">Create or edit a ListBoundaryCondUcase</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : listBoundaryCondUcaseEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="list-boundary-cond-ucase-id">ID</Label>
                    <AvInput id="list-boundary-cond-ucase-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="list-boundary-cond-ucase-code">
                    Code
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-code"
                    type="text"
                    name="code"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="list-boundary-cond-ucase-name">
                    Name
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="list-boundary-cond-ucase-fullName">
                    Full Name
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-fullName"
                    type="text"
                    name="fullName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="list-boundary-cond-ucase-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="list-boundary-cond-ucase-description">
                    Description
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="list-boundary-cond-ucase-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="list-boundary-cond-ucase-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listBoundaryCondUcaseEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="list-boundary-cond-ucase-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="list-boundary-cond-ucase-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listBoundaryCondUcaseEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="list-boundary-cond-ucase-creator">
                    Creator
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="list-boundary-cond-ucase-editor">
                    Editor
                  </Label>
                  <AvField
                    id="list-boundary-cond-ucase-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/list-boundary-cond-ucase" replace color="info">
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
  listBoundaryCondUcaseEntity: storeState.listBoundaryCondUcase.entity,
  loading: storeState.listBoundaryCondUcase.loading,
  updating: storeState.listBoundaryCondUcase.updating,
  updateSuccess: storeState.listBoundaryCondUcase.updateSuccess
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
)(ListBoundaryCondUcaseUpdate);
