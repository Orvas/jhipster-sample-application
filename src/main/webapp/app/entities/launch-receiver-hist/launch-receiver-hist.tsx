import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './launch-receiver-hist.reducer';
import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ILaunchReceiverHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ILaunchReceiverHistState = IPaginationBaseState;

export class LaunchReceiverHist extends React.Component<ILaunchReceiverHistProps, ILaunchReceiverHistState> {
  state: ILaunchReceiverHistState = {
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
    const { launchReceiverHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="launch-receiver-hist-heading">
          Launch Receiver Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Launch Receiver Hist
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
                <th className="hand" onClick={this.sort('name')}>
                  Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coord')}>
                  Coord <FontAwesomeIcon icon="sort" />
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
                  Launch Receiver <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipeline <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {launchReceiverHistList.map((launchReceiverHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${launchReceiverHist.id}`} color="link" size="sm">
                      {launchReceiverHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={launchReceiverHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={launchReceiverHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{launchReceiverHist.name}</td>
                  <td>{launchReceiverHist.coord}</td>
                  <td>{launchReceiverHist.kpInst}</td>
                  <td>{launchReceiverHist.isCurrentFlag}</td>
                  <td>{launchReceiverHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={launchReceiverHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={launchReceiverHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{launchReceiverHist.creator}</td>
                  <td>{launchReceiverHist.editor}</td>
                  <td>
                    {launchReceiverHist.launchReceiverId ? (
                      <Link to={`launch-receiver/${launchReceiverHist.launchReceiverId}`}>{launchReceiverHist.launchReceiverId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {launchReceiverHist.idPipelineId ? (
                      <Link to={`pipeline/${launchReceiverHist.idPipelineId}`}>{launchReceiverHist.idPipelineId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {launchReceiverHist.idStatusId ? (
                      <Link to={`list-object-status/${launchReceiverHist.idStatusId}`}>{launchReceiverHist.idStatusId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${launchReceiverHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${launchReceiverHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${launchReceiverHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ launchReceiverHist }: IRootState) => ({
  launchReceiverHistList: launchReceiverHist.entities,
  totalItems: launchReceiverHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LaunchReceiverHist);
