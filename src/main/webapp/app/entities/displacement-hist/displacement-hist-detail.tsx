import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './displacement-hist.reducer';
import { IDisplacementHist } from 'app/shared/model/displacement-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDisplacementHistDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DisplacementHistDetail extends React.Component<IDisplacementHistDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { displacementHistEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            DisplacementHist [<b>{displacementHistEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={displacementHistEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={displacementHistEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idWrk">Id Wrk</span>
            </dt>
            <dd>{displacementHistEntity.idWrk}</dd>
            <dt>
              <span id="deltaX">Delta X</span>
            </dt>
            <dd>{displacementHistEntity.deltaX}</dd>
            <dt>
              <span id="deltaY">Delta Y</span>
            </dt>
            <dd>{displacementHistEntity.deltaY}</dd>
            <dt>
              <span id="deltaZ">Delta Z</span>
            </dt>
            <dd>{displacementHistEntity.deltaZ}</dd>
            <dt>
              <span id="deltaTotal">Delta Total</span>
            </dt>
            <dd>{displacementHistEntity.deltaTotal}</dd>
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{displacementHistEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{displacementHistEntity.kpEnd}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{displacementHistEntity.isCurrentFlag}</dd>
            <dt>
              <span id="idStatus">Id Status</span>
            </dt>
            <dd>{displacementHistEntity.idStatus}</dd>
            <dt>
              <span id="comment">Comment</span>
            </dt>
            <dd>{displacementHistEntity.comment}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={displacementHistEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={displacementHistEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{displacementHistEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{displacementHistEntity.editor}</dd>
            <dt>Displacement</dt>
            <dd>{displacementHistEntity.displacementId ? displacementHistEntity.displacementId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{displacementHistEntity.idPipelineSectionId ? displacementHistEntity.idPipelineSectionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/displacement-hist" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/displacement-hist/${displacementHistEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ displacementHist }: IRootState) => ({
  displacementHistEntity: displacementHist.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisplacementHistDetail);
