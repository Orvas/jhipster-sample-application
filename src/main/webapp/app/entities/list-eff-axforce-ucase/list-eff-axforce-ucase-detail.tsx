import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-eff-axforce-ucase.reducer';
import { IListEffAxforceUcase } from 'app/shared/model/list-eff-axforce-ucase.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListEffAxforceUcaseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListEffAxforceUcaseDetail extends React.Component<IListEffAxforceUcaseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listEffAxforceUcaseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListEffAxforceUcase [<b>{listEffAxforceUcaseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listEffAxforceUcaseEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listEffAxforceUcaseEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listEffAxforceUcaseEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-eff-axforce-ucase" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-eff-axforce-ucase/${listEffAxforceUcaseEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listEffAxforceUcase }: IRootState) => ({
  listEffAxforceUcaseEntity: listEffAxforceUcase.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListEffAxforceUcaseDetail);
