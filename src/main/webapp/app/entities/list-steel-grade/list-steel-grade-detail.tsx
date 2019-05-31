import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './list-steel-grade.reducer';
import { IListSteelGrade } from 'app/shared/model/list-steel-grade.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListSteelGradeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ListSteelGradeDetail extends React.Component<IListSteelGradeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { listSteelGradeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ListSteelGrade [<b>{listSteelGradeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">Code</span>
            </dt>
            <dd>{listSteelGradeEntity.code}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{listSteelGradeEntity.name}</dd>
            <dt>
              <span id="fullName">Full Name</span>
            </dt>
            <dd>{listSteelGradeEntity.fullName}</dd>
            <dt>
              <span id="steelDensity">Steel Density</span>
            </dt>
            <dd>{listSteelGradeEntity.steelDensity}</dd>
            <dt>
              <span id="thermExpCoef">Therm Exp Coef</span>
            </dt>
            <dd>{listSteelGradeEntity.thermExpCoef}</dd>
            <dt>
              <span id="isCurrentFlag">Is Current Flag</span>
            </dt>
            <dd>{listSteelGradeEntity.isCurrentFlag}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{listSteelGradeEntity.description}</dd>
            <dt>
              <span id="dateCreate">Date Create</span>
            </dt>
            <dd>
              <TextFormat value={listSteelGradeEntity.dateCreate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dateEdit">Date Edit</span>
            </dt>
            <dd>
              <TextFormat value={listSteelGradeEntity.dateEdit} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="creator">Creator</span>
            </dt>
            <dd>{listSteelGradeEntity.creator}</dd>
            <dt>
              <span id="editor">Editor</span>
            </dt>
            <dd>{listSteelGradeEntity.editor}</dd>
          </dl>
          <Button tag={Link} to="/entity/list-steel-grade" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/list-steel-grade/${listSteelGradeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ listSteelGrade }: IRootState) => ({
  listSteelGradeEntity: listSteelGrade.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListSteelGradeDetail);
