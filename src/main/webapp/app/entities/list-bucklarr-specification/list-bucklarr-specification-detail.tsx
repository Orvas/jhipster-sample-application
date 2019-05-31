import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-bucklarr-specification.reducer';
import { IListBucklarrSpecification } from 'app/shared/model/list-bucklarr-specification.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListBucklarrSpecificationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListBucklarrSpecificationDetail extends React.Component<IListBucklarrSpecificationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listBucklarrSpecificationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListBucklarrSpecification [<b>{listBucklarrSpecificationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.fullName}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listBucklarrSpecificationEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listBucklarrSpecificationEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listBucklarrSpecificationEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-bucklarr-specification" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-bucklarr-specification/${listBucklarrSpecificationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listBucklarrSpecification }: IRootState) => ({
  listBucklarrSpecificationEntity: listBucklarrSpecification.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListBucklarrSpecificationDetail);
