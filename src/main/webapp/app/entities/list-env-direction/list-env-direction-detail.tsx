import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-env-direction.reducer';
import { IListEnvDirection } from 'app/shared/model/list-env-direction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListEnvDirectionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListEnvDirectionDetail extends React.Component<IListEnvDirectionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listEnvDirectionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListEnvDirection [<b>{listEnvDirectionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listEnvDirectionEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listEnvDirectionEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listEnvDirectionEntity.fullName}</dd>
            <dt>
              <span id="degreeStart">Degree Start</span>
            </dt>
            <dd>{listEnvDirectionEntity.degreeStart}</dd>
            <dt>
              <span id="degreeEnd">Degree End</span>
            </dt>
            <dd>{listEnvDirectionEntity.degreeEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listEnvDirectionEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listEnvDirectionEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listEnvDirectionEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listEnvDirectionEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listEnvDirectionEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listEnvDirectionEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-env-direction" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-env-direction/${listEnvDirectionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listEnvDirection }: IRootState) => ({
  listEnvDirectionEntity: listEnvDirection.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListEnvDirectionDetail);
