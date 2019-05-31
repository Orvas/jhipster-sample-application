import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cps-range.reducer';
import { ICpsRange } from 'app/shared/model/cps-range.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICpsRangeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CpsRangeDetail extends React.Component<ICpsRangeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { cpsRangeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CpsRange [<b>{cpsRangeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="kpStart">Kp Start</span>
            </dt>
            <dd>{cpsRangeEntity.kpStart}</dd>
            <dt>
              <span id="kpEnd">Kp End</span>
            </dt>
            <dd>{cpsRangeEntity.kpEnd}</dd>
            <dt>
              <span id="dateFrom">Date From</span>
            </dt>
            <dd>
              <TextFormat value={cpsRangeEntity.dateFrom} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateTo">Date To</span>
            </dt>
            <dd>
              <TextFormat value={cpsRangeEntity.dateTo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={cpsRangeEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={cpsRangeEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{cpsRangeEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{cpsRangeEntity.editor}</dd>
            <dt>Id Cps</dt>
            <dd>{cpsRangeEntity.idCpsId ? cpsRangeEntity.idCpsId : ''}</dd>
            <dt>Id Pipeline Section</dt>
            <dd>{cpsRangeEntity.idPipelineSectionId ? cpsRangeEntity.idPipelineSectionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/cps-range" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/cps-range/${cpsRangeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ cpsRange }: IRootState) => ({
  cpsRangeEntity: cpsRange.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CpsRangeDetail);
