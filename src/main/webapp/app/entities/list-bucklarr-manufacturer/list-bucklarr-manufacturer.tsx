import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './list-bucklarr-manufacturer.reducer';
import { IListBucklarrManufacturer } from 'app/shared/model/list-bucklarr-manufacturer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IListBucklarrManufacturerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IListBucklarrManufacturerState = IPaginationBaseState;

export class ListBucklarrManufacturer extends React.Component<IListBucklarrManufacturerProps, IListBucklarrManufacturerState> {
  state: IListBucklarrManufacturerState = {
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
    const { listBucklarrManufacturerList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="list-bucklarr-manufacturer-heading">
          List Bucklarr Manufacturers
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new List Bucklarr Manufacturer
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('code')}>
                  Code <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('name')}>
                  Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fullName')}>
                  Full Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isCurrentFlag')}>
                  Is Current Flag <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('description')}>
                  Description <FontAwesomeIcon icon="sort" />
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
                <th />
              </tr>
            </thead>
            <tbody>
              {listBucklarrManufacturerList.map((listBucklarrManufacturer, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${listBucklarrManufacturer.id}`} color="link" size="sm">
                      {listBucklarrManufacturer.id}
                    </Button>
                  </td>
                  <td>{listBucklarrManufacturer.code}</td>
                  <td>{listBucklarrManufacturer.name}</td>
                  <td>{listBucklarrManufacturer.fullName}</td>
                  <td>{listBucklarrManufacturer.isCurrentFlag}</td>
                  <td>{listBucklarrManufacturer.description}</td>
                  <td>
                    <TextFormat type="date" value={listBucklarrManufacturer.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={listBucklarrManufacturer.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{listBucklarrManufacturer.creator}</td>
                  <td>{listBucklarrManufacturer.editor}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${listBucklarrManufacturer.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${listBucklarrManufacturer.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${listBucklarrManufacturer.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ listBucklarrManufacturer }: IRootState) => ({
  listBucklarrManufacturerList: listBucklarrManufacturer.entities,
  totalItems: listBucklarrManufacturer.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListBucklarrManufacturer);
