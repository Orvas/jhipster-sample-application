import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipejoint.reducer';
import { IPipejoint } from 'app/shared/model/pipejoint.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipejointDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipejointDetail extends React.Component<IPipejointDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipejointEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Pipejoint [<b>{pipejointEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipejointEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipejointEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipejointEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipejointEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{pipejointEntity.idId ? pipejointEntity.idId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipejoint" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipejoint/${pipejointEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipejoint }: IRootState) => ({
  pipejointEntity: pipejoint.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipejointDetail);
