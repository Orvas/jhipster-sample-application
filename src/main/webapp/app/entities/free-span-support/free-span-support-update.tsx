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
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
import { getEntities as getFreeSpanSupportHists } from 'app/entities/free-span-support-hist/free-span-support-hist.reducer';
import { getEntity, updateEntity, createEntity, reset } from './free-span-support.reducer';
import { IFreeSpanSupport } from 'app/shared/model/free-span-support.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFreeSpanSupportUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFreeSpanSupportUpdateState {
  isNew: boolean;
  baseClassId: string;
  freeSpanSupportHistId: string;
}

export class FreeSpanSupportUpdate extends React.Component<IFreeSpanSupportUpdateProps, IFreeSpanSupportUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      baseClassId: '0',
      freeSpanSupportHistId: '0',
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
    this.props.getFreeSpanSupportHists();
  }

  saveEntity = (event, errors, values) => {
    values.dateCreate = convertDateTimeToServer(values.dateCreate);
    values.dateEdit = convertDateTimeToServer(values.dateEdit);

    if (errors.length === 0) {
      const { freeSpanSupportEntity } = this.props;
      const entity = {
        ...freeSpanSupportEntity,
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
    this.props.history.push('/entity/free-span-support');
  };

  render() {
    const { freeSpanSupportEntity, baseClasses, freeSpanSupportHists, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.freeSpanSupport.home.createOrEditLabel">Create or edit a FreeSpanSupport</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : freeSpanSupportEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="free-span-support-id">ID</Label>
                    <AvInput id="free-span-support-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dateCreateLabel" for="free-span-support-dateCreate">
                    Date Create
                  </Label>
                  <AvInput
                    id="free-span-support-dateCreate"
                    type="datetime-local"
                    className="form-control"
                    name="dateCreate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanSupportEntity.dateCreate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateEditLabel" for="free-span-support-dateEdit">
                    Date Edit
                  </Label>
                  <AvInput
                    id="free-span-support-dateEdit"
                    type="datetime-local"
                    className="form-control"
                    name="dateEdit"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.freeSpanSupportEntity.dateEdit)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="creatorLabel" for="free-span-support-creator">
                    Creator
                  </Label>
                  <AvField
                    id="free-span-support-creator"
                    type="text"
                    name="creator"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="editorLabel" for="free-span-support-editor">
                    Editor
                  </Label>
                  <AvField
                    id="free-span-support-editor"
                    type="text"
                    name="editor"
                    validate={{
                      maxLength: { value: 255, errorMessage: 'This field cannot be longer than 255 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="free-span-support-baseClass">Base Class</Label>
                  <AvInput id="free-span-support-baseClass" type="select" className="form-control" name="baseClassId">
                    <option value="" key="0" />
                    {baseClasses
                      ? baseClasses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/free-span-support" replace color="info">
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
  freeSpanSupportHists: storeState.freeSpanSupportHist.entities,
  freeSpanSupportEntity: storeState.freeSpanSupport.entity,
  loading: storeState.freeSpanSupport.loading,
  updating: storeState.freeSpanSupport.updating,
  updateSuccess: storeState.freeSpanSupport.updateSuccess
});

const mapDispatchToProps = {
  getBaseClasses,
  getFreeSpanSupportHists,
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
)(FreeSpanSupportUpdate);
