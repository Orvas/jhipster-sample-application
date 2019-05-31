import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pipejoint-hist.reducer';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipejointHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PipejointHistDetail extends React.Component<IPipejointHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pipejointHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PipejointHist [<b>{pipejointHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={pipejointHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={pipejointHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{pipejointHistEntity.name}</dd>
            <dt>
              <span id="externalCoatThickness">External Coat Thickness</span>
            </dt>
            <dd>{pipejointHistEntity.externalCoatThickness}</dd>
            <dt>
              <span id="coord">Coord</span>
            </dt>
            <dd>{pipejointHistEntity.coord}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{pipejointHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{pipejointHistEntity.description}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{pipejointHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={pipejointHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={pipejointHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{pipejointHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{pipejointHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{pipejointHistEntity.idId ? pipejointHistEntity.idId : ''}</dd>
            <dt>Id Type</dt>
            <dd>{pipejointHistEntity.idTypeId ? pipejointHistEntity.idTypeId : ''}</dd>
            <dt>Id External Coat Type</dt>
            <dd>{pipejointHistEntity.idExternalCoatTypeId ? pipejointHistEntity.idExternalCoatTypeId : ''}</dd>
            <dt>Id Material</dt>
            <dd>{pipejointHistEntity.idMaterialId ? pipejointHistEntity.idMaterialId : ''}</dd>
            <dt>Id Specification</dt>
            <dd>{pipejointHistEntity.idSpecificationId ? pipejointHistEntity.idSpecificationId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{pipejointHistEntity.idStatusId ? pipejointHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pipejoint-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pipejoint-hist/${pipejointHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pipejointHist }: IRootState) => ({
  pipejointHistEntity: pipejointHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipejointHistDetail);
