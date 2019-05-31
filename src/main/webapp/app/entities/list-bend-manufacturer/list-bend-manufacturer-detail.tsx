import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-bend-manufacturer.reducer';
import { IListBendManufacturer } from 'app/shared/model/list-bend-manufacturer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListBendManufacturerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListBendManufacturerDetail extends React.Component<IListBendManufacturerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listBendManufacturerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListBendManufacturer [<b>{listBendManufacturerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listBendManufacturerEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listBendManufacturerEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listBendManufacturerEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listBendManufacturerEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listBendManufacturerEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listBendManufacturerEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listBendManufacturerEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listBendManufacturerEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listBendManufacturerEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-bend-manufacturer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-bend-manufacturer/${listBendManufacturerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listBendManufacturer }: IRootState) => ({
  listBendManufacturerEntity: listBendManufacturer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListBendManufacturerDetail);
