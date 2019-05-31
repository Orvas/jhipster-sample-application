import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './free-span-hist.reducer';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFreeSpanHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FreeSpanHistDetail extends React.Component<IFreeSpanHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { freeSpanHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FreeSpanHist [<b>{freeSpanHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{freeSpanHistEntity.idWrk}</dd>
            <dt>
              <span id="lenght">Lenght</span>
            </dt>
            <dd>{freeSpanHistEntity.lenght}</dd>
            <dt>
              <span id="lenghtAllow">Lenght Allow</span>
            </dt>
            <dd>{freeSpanHistEntity.lenghtAllow}</dd>
            <dt>
              <span id="height">Height</span>
            </dt>
            <dd>{freeSpanHistEntity.height}</dd>
            <dt>
              <span id="isMultispan">Is Multispan</span>
            </dt>
            <dd>{freeSpanHistEntity.isMultispan}</dd>
            <dt>
              <span id="gap">Gap</span>
            </dt>
            <dd>{freeSpanHistEntity.gap}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{freeSpanHistEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{freeSpanHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{freeSpanHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{freeSpanHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={freeSpanHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{freeSpanHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{freeSpanHistEntity.editor}</dd>
            <dt>Id</dt>
            <dd>{freeSpanHistEntity.idId ? freeSpanHistEntity.idId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{freeSpanHistEntity.idPipelineSectionId ? freeSpanHistEntity.idPipelineSectionId : ''}</dd>
            <dt>Id Status</dt>
            <dd>{freeSpanHistEntity.idStatusId ? freeSpanHistEntity.idStatusId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/free-span-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/free-span-hist/${freeSpanHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ freeSpanHist }: IRootState) => ({
  freeSpanHistEntity: freeSpanHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanHistDetail);
