import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cps-range.reducer';
import { ICpsRange } from 'app/shared/model/cps-range.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICpsRangeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ICpsRangeState = IPaginationBaseState;

export class CpsRange extends React.Component<ICpsRangeProps, ICpsRangeState> {
  state: ICpsRangeState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { cpsRangeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="cps-range-heading">
          Cps Ranges
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Cps Range
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpStart')}>
                  Kp Start <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpEnd')}>
                  Kp End <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateFrom')}>
                  Date From <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateTo')}>
                  Date To <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateCreate')}>
                  Date Create <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateEdit')}>
                  Date Edit <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('creator')}>
                  Creator <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('editor')}>
                  Editor <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Cps <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipeline Section <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cpsRangeList.map((cpsRange, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cpsRange.id}`} color="link" size="sm">
                      {cpsRange.id}
                    </Button>
                  </td>
                  <td>{cpsRange.kpStart}</td>
                  <td>{cpsRange.kpEnd}</td>
                  <td>
                    <TextFormat type="date" value={cpsRange.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={cpsRange.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={cpsRange.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={cpsRange.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cpsRange.creator}</td>
                  <td>{cpsRange.editor}</td>
                  <td>{cpsRange.idCpsId ? <Link to={`cps/${cpsRange.idCpsId}`}>{cpsRange.idCpsId}</Link> : ''}</td>
                  <td>
                    {cpsRange.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${cpsRange.idPipelineSectionId}`}>{cpsRange.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cpsRange.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cpsRange.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cpsRange.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ cpsRange }: IRootState) => ({
  cpsRangeList: cpsRange.entities,
  totalItems: cpsRange.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CpsRange);
