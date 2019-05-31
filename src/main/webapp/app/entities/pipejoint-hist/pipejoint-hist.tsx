import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pipejoint-hist.reducer';
import { IPipejointHist } from 'app/shared/model/pipejoint-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPipejointHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPipejointHistState = IPaginationBaseState;

export class PipejointHist extends React.Component<IPipejointHistProps, IPipejointHistState> {
  state: IPipejointHistState = {
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
    const { pipejointHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="pipejoint-hist-heading">
          Pipejoint Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Pipejoint Hist
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
                <th className="hand" onClick={this.sort('externalCoatThickness')}>
                  External Coat Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coord')}>
                  Coord <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isCurrentFlag')}>
                  Is Current Flag <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('description')}>
                  Description <FontAwesomeIcon icon="sort" />
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
                  Id Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id External Coat Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Material <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Specification <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pipejointHistList.map((pipejointHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pipejointHist.id}`} color="link" size="sm">
                      {pipejointHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={pipejointHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={pipejointHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{pipejointHist.name}</td>
                  <td>{pipejointHist.externalCoatThickness}</td>
                  <td>{pipejointHist.coord}</td>
                  <td>{pipejointHist.isCurrentFlag}</td>
                  <td>{pipejointHist.description}</td>
                  <td>{pipejointHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={pipejointHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={pipejointHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pipejointHist.creator}</td>
                  <td>{pipejointHist.editor}</td>
                  <td>{pipejointHist.idId ? <Link to={`pipejoint/${pipejointHist.idId}`}>{pipejointHist.idId}</Link> : ''}</td>
                  <td>
                    {pipejointHist.idTypeId ? (
                      <Link to={`list-pipejoint-type/${pipejointHist.idTypeId}`}>{pipejointHist.idTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipejointHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${pipejointHist.idExternalCoatTypeId}`}>{pipejointHist.idExternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipejointHist.idMaterialId ? (
                      <Link to={`list-material/${pipejointHist.idMaterialId}`}>{pipejointHist.idMaterialId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipejointHist.idSpecificationId ? (
                      <Link to={`list-pipejoint-specification/${pipejointHist.idSpecificationId}`}>{pipejointHist.idSpecificationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipejointHist.idStatusId ? (
                      <Link to={`list-object-status/${pipejointHist.idStatusId}`}>{pipejointHist.idStatusId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pipejointHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipejointHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipejointHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pipejointHist }: IRootState) => ({
  pipejointHistList: pipejointHist.entities,
  totalItems: pipejointHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipejointHist);
