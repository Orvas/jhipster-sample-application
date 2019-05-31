import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './displacement-hist.reducer';
import { IDisplacementHist } from 'app/shared/model/displacement-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDisplacementHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IDisplacementHistState = IPaginationBaseState;

export class DisplacementHist extends React.Component<IDisplacementHistProps, IDisplacementHistState> {
  state: IDisplacementHistState = {
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
    const { displacementHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="displacement-hist-heading">
          Displacement Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Displacement Hist
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateFrom')}>
                  Date From <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateTo')}>
                  Date To <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idWrk')}>
                  Id Wrk <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('deltaX')}>
                  Delta X <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('deltaY')}>
                  Delta Y <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('deltaZ')}>
                  Delta Z <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('deltaTotal')}>
                  Delta Total <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpStart')}>
                  Kp Start <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpEnd')}>
                  Kp End <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isCurrentFlag')}>
                  Is Current Flag <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idStatus')}>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('comment')}>
                  Comment <FontAwesomeIcon icon="sort" />
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
                  Id <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipeline Section <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {displacementHistList.map((displacementHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${displacementHist.id}`} color="link" size="sm">
                      {displacementHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={displacementHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={displacementHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{displacementHist.idWrk}</td>
                  <td>{displacementHist.deltaX}</td>
                  <td>{displacementHist.deltaY}</td>
                  <td>{displacementHist.deltaZ}</td>
                  <td>{displacementHist.deltaTotal}</td>
                  <td>{displacementHist.kpStart}</td>
                  <td>{displacementHist.kpEnd}</td>
                  <td>{displacementHist.isCurrentFlag}</td>
                  <td>{displacementHist.idStatus}</td>
                  <td>{displacementHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={displacementHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={displacementHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{displacementHist.creator}</td>
                  <td>{displacementHist.editor}</td>
                  <td>{displacementHist.idId ? <Link to={`displacement/${displacementHist.idId}`}>{displacementHist.idId}</Link> : ''}</td>
                  <td>
                    {displacementHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${displacementHist.idPipelineSectionId}`}>{displacementHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${displacementHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${displacementHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${displacementHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ displacementHist }: IRootState) => ({
  displacementHistList: displacementHist.entities,
  totalItems: displacementHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisplacementHist);
