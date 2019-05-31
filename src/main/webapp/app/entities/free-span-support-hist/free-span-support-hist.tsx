import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './free-span-support-hist.reducer';
import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IFreeSpanSupportHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IFreeSpanSupportHistState = IPaginationBaseState;

export class FreeSpanSupportHist extends React.Component<IFreeSpanSupportHistProps, IFreeSpanSupportHistState> {
  state: IFreeSpanSupportHistState = {
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
    const { freeSpanSupportHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="free-span-support-hist-heading">
          Free Span Support Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Free Span Support Hist
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
                <th className="hand" onClick={this.sort('height')}>
                  Height <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpInst')}>
                  Kp Inst <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isCurrentFlag')}>
                  Is Current Flag <FontAwesomeIcon icon="sort" />
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
                  Free Span Support <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipeline Section <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {freeSpanSupportHistList.map((freeSpanSupportHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${freeSpanSupportHist.id}`} color="link" size="sm">
                      {freeSpanSupportHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanSupportHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanSupportHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanSupportHist.idWrk}</td>
                  <td>{freeSpanSupportHist.height}</td>
                  <td>{freeSpanSupportHist.kpInst}</td>
                  <td>{freeSpanSupportHist.isCurrentFlag}</td>
                  <td>{freeSpanSupportHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={freeSpanSupportHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanSupportHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanSupportHist.creator}</td>
                  <td>{freeSpanSupportHist.editor}</td>
                  <td>
                    {freeSpanSupportHist.freeSpanSupportId ? (
                      <Link to={`free-span-support/${freeSpanSupportHist.freeSpanSupportId}`}>{freeSpanSupportHist.freeSpanSupportId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {freeSpanSupportHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${freeSpanSupportHist.idPipelineSectionId}`}>
                        {freeSpanSupportHist.idPipelineSectionId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {freeSpanSupportHist.idStatusId ? (
                      <Link to={`list-object-status/${freeSpanSupportHist.idStatusId}`}>{freeSpanSupportHist.idStatusId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${freeSpanSupportHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanSupportHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanSupportHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ freeSpanSupportHist }: IRootState) => ({
  freeSpanSupportHistList: freeSpanSupportHist.entities,
  totalItems: freeSpanSupportHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanSupportHist);
