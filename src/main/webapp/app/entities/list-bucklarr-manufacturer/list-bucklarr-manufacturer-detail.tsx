import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-bucklarr-manufacturer.reducer';
import { IListBucklarrManufacturer } from 'app/shared/model/list-bucklarr-manufacturer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListBucklarrManufacturerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListBucklarrManufacturerDetail extends React.Component<IListBucklarrManufacturerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listBucklarrManufacturerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListBucklarrManufacturer [<b>{listBucklarrManufacturerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listBucklarrManufacturerEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listBucklarrManufacturerEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listBucklarrManufacturerEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-bucklarr-manufacturer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-bucklarr-manufacturer/${listBucklarrManufacturerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listBucklarrManufacturer }: IRootState) => ({
  listBucklarrManufacturerEntity: listBucklarrManufacturer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListBucklarrManufacturerDetail);
