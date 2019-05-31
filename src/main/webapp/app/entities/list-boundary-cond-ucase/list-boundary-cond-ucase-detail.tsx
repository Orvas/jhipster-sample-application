import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-boundary-cond-ucase.reducer';
import { IListBoundaryCondUcase } from 'app/shared/model/list-boundary-cond-ucase.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListBoundaryCondUcaseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListBoundaryCondUcaseDetail extends React.Component<IListBoundaryCondUcaseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listBoundaryCondUcaseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListBoundaryCondUcase [<b>{listBoundaryCondUcaseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listBoundaryCondUcaseEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listBoundaryCondUcaseEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listBoundaryCondUcaseEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-boundary-cond-ucase" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-boundary-cond-ucase/${listBoundaryCondUcaseEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listBoundaryCondUcase }: IRootState) => ({
  listBoundaryCondUcaseEntity: listBoundaryCondUcase.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListBoundaryCondUcaseDetail);
