import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './list-long-seam-weld-type.reducer';
import { IListLongSeamWeldType } from 'app/shared/model/list-long-seam-weld-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IListLongSeamWeldTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IListLongSeamWeldTypeUpdateState {
  isNew: boolean;
}

export class ListLongSeamWeldTypeUpdate extends React.Component<IListLongSeamWeldTypeUpdateProps, IListLongSeamWeldTypeUpdateState> {
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
      const { listLongSeamWeldTypeEntity } = this.props;
      const entity = {
        ...listLongSeamWeldTypeEntity,
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
    this.props.history.push('/entity/list-long-seam-weld-type');
  };

  render() {
    const { listLongSeamWeldTypeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.listLongSeamWeldType.home.createOrEditLabel">Create or edit a ListLongSeamWeldType</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : listLongSeamWeldTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="list-long-seam-weld-type-id">ID</Label>
                    <AvInput id="list-long-seam-weld-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="list-long-seam-weld-type-code">
                    Code
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-code"
                    type="text"
                    name="code"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="list-long-seam-weld-type-name">
                    Name
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="list-long-seam-weld-type-fullName">
                    Full Name
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-fullName"
                    type="text"
                    name="fullName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isCurrentFlagLabel" for="list-long-seam-weld-type-isCurrentFlag">
                    Is Current Flag
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-isCurrentFlag"
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
                  <Label id="descriptionLabel" for="list-long-seam-weld-type-description">
                    Description
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateCreateLabel" for="list-long-seam-weld-type-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="list-long-seam-weld-type-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listLongSeamWeldTypeEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="list-long-seam-weld-type-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="list-long-seam-weld-type-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.listLongSeamWeldTypeEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="list-long-seam-weld-type-creator">
                    Creator
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="list-long-seam-weld-type-editor">
                    Editor
                  </Label>
                  <AvField
                    id="list-long-seam-weld-type-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/list-long-seam-weld-type" replace color="info">
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
  listLongSeamWeldTypeEntity: storeState.listLongSeamWeldType.entity,
  loading: storeState.listLongSeamWeldType.loading,
  updating: storeState.listLongSeamWeldType.updating,
  updateSuccess: storeState.listLongSeamWeldType.updateSuccess
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
)(ListLongSeamWeldTypeUpdate);
