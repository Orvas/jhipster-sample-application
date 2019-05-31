import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './free-span-hist.reducer';
import { IFreeSpanHist } from 'app/shared/model/free-span-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IFreeSpanHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IFreeSpanHistState = IPaginationBaseState;

export class FreeSpanHist extends React.Component<IFreeSpanHistProps, IFreeSpanHistState> {
  state: IFreeSpanHistState = {
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
    const { freeSpanHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="free-span-hist-heading">
          Free Span Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Free Span Hist
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
                <th className="hand" onClick={this.sort('lenght')}>
                  Lenght <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lenghtAllow')}>
                  Lenght Allow <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('height')}>
                  Height <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isMultispan')}>
                  Is Multispan <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gap')}>
                  Gap <FontAwesomeIcon icon="sort" />
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
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {freeSpanHistList.map((freeSpanHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${freeSpanHist.id}`} color="link" size="sm">
                      {freeSpanHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanHist.idWrk}</td>
                  <td>{freeSpanHist.lenght}</td>
                  <td>{freeSpanHist.lenghtAllow}</td>
                  <td>{freeSpanHist.height}</td>
                  <td>{freeSpanHist.isMultispan}</td>
                  <td>{freeSpanHist.gap}</td>
                  <td>{freeSpanHist.kpStart}</td>
                  <td>{freeSpanHist.kpEnd}</td>
                  <td>{freeSpanHist.isCurrentFlag}</td>
                  <td>{freeSpanHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={freeSpanHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanHist.creator}</td>
                  <td>{freeSpanHist.editor}</td>
                  <td>{freeSpanHist.idId ? <Link to={`free-span/${freeSpanHist.idId}`}>{freeSpanHist.idId}</Link> : ''}</td>
                  <td>
                    {freeSpanHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${freeSpanHist.idPipelineSectionId}`}>{freeSpanHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {freeSpanHist.idStatusId ? (
                      <Link to={`list-object-status/${freeSpanHist.idStatusId}`}>{freeSpanHist.idStatusId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${freeSpanHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ freeSpanHist }: IRootState) => ({
  freeSpanHistList: freeSpanHist.entities,
  totalItems: freeSpanHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanHist);
