import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-dfct-type.reducer';
import { IListDfctType } from 'app/shared/model/list-dfct-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListDfctTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListDfctTypeDetail extends React.Component<IListDfctTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listDfctTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListDfctType [<b>{listDfctTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listDfctTypeEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listDfctTypeEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listDfctTypeEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listDfctTypeEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listDfctTypeEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listDfctTypeEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listDfctTypeEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listDfctTypeEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listDfctTypeEntity.editor}</dd>
            <dt>Id Group</dt>
            <dd>{listDfctTypeEntity.idGroupId ? listDfctTypeEntity.idGroupId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-dfct-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-dfct-type/${listDfctTypeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listDfctType }: IRootState) => ({
  listDfctTypeEntity: listDfctType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListDfctTypeDetail);
