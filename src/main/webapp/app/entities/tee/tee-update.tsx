import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBaseClass } from 'app/shared/model/base-class.model';
import { getEntities as getBaseClasses } from 'app/entities/base-class/base-class.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tee.reducer';
import { ITee } from 'app/shared/model/tee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITeeUpdateState {
  isNew: boolean;
  idId: string;
}

export class TeeUpdate extends React.Component<ITeeUpdateProps, ITeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idId: '0',
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

    this.props.getBaseClasses();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { teeEntity } = this.props;
      const entity = {
        ...teeEntity,
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
    this.props.history.push('/entity/tee');
  };

  render() {
    const { teeEntity, baseClasses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.tee.home.createOrEditLabel">Create or edit a Tee</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : teeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="tee-id">ID</Label>
                    <AvInput id="tee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateCreateLabel" for="tee-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="tee-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.teeEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="tee-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="tee-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.teeEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="tee-creator">
                    Creator
                  </Label>
                  <AvField
                    id="tee-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="tee-editor">
                    Editor
                  </Label>
                  <AvField
                    id="tee-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="tee-id">Id</Label>
                  <AvInput id="tee-id" type="select" className="form-control" name="idId" required>
                    {baseClasses
                      ? baseClasses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvFeedback>This field is required.</AvFeedback>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/tee" replace color="info">
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
  baseClasses: storeState.baseClass.entities,
  teeEntity: storeState.tee.entity,
  loading: storeState.tee.loading,
  updating: storeState.tee.updating,
  updateSuccess: storeState.tee.updateSuccess
});

const mapDispatchToProps = {
  getBaseClasses,
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
)(TeeUpdate);
