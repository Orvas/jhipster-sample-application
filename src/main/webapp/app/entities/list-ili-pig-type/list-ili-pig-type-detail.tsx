import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-ili-pig-type.reducer';
import { IListIliPigType } from 'app/shared/model/list-ili-pig-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListIliPigTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListIliPigTypeDetail extends React.Component<IListIliPigTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listIliPigTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListIliPigType [<b>{listIliPigTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listIliPigTypeEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listIliPigTypeEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listIliPigTypeEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listIliPigTypeEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listIliPigTypeEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listIliPigTypeEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listIliPigTypeEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listIliPigTypeEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listIliPigTypeEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-ili-pig-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-ili-pig-type/${listIliPigTypeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listIliPigType }: IRootState) => ({
  listIliPigTypeEntity: listIliPigType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListIliPigTypeDetail);
