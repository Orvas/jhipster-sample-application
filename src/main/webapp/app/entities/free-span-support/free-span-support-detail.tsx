import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './free-span-support.reducer';
import { IFreeSpanSupport } from 'app/shared/model/free-span-support.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFreeSpanSupportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FreeSpanSupportDetail extends React.Component<IFreeSpanSupportDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { freeSpanSupportEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FreeSpanSupport [<b>{freeSpanSupportEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanSupportEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{freeSpanSupportEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{freeSpanSupportEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{freeSpanSupportEntity.idId ? freeSpanSupportEntity.idId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/free-span-support" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/free-span-support/${freeSpanSupportEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ freeSpanSupport }: IRootState) => ({
  freeSpanSupportEntity: freeSpanSupport.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanSupportDetail);
