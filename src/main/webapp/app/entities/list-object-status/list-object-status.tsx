import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './list-object-status.reducer';
import { IListObjectStatus } from 'app/shared/model/list-object-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IListObjectStatusProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ListObjectStatus extends React.Component<IListObjectStatusProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { listObjectStatusList, match } = this.props;
    return (
      <div>
        <h2 id="list-object-status-heading">
          List Object Statuses
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new List Object Status
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Name</th>
                <th>Full Name</th>
                <th>Is Current Flag</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {listObjectStatusList.map((listObjectStatus, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${listObjectStatus.id}`} color="link" size="sm">
                      {listObjectStatus.id}
                    </Button>
                  </td>
                  <td>{listObjectStatus.code}</td>
                  <td>{listObjectStatus.name}</td>
                  <td>{listObjectStatus.fullName}</td>
                  <td>{listObjectStatus.isCurrentFlag ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${listObjectStatus.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${listObjectStatus.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${listObjectStatus.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ listObjectStatus }: IRootState) => ({
  listObjectStatusList: listObjectStatus.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListObjectStatus);
