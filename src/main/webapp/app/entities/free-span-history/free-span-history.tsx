import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './free-span-history.reducer';
import { IFreeSpanHistory } from 'app/shared/model/free-span-history.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFreeSpanHistoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FreeSpanHistory extends React.Component<IFreeSpanHistoryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { freeSpanHistoryList, match } = this.props;
    return (
      <div>
        <h2 id="free-span-history-heading">
          Free Span Histories
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Free Span History
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date Form</th>
                <th>Date To</th>
                <th>Work Id</th>
                <th>Pipeline Section</th>
                <th>Length</th>
                <th>Kp Start</th>
                <th>Kp End</th>
                <th>Is Current Flag</th>
                <th>Status Id</th>
                <th>Comment</th>
                <th>Date Create</th>
                <th>Date Edit</th>
                <th>Creator</th>
                <th>Editor</th>
                <th>Status</th>
                <th>Pipeline Section</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {freeSpanHistoryList.map((freeSpanHistory, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${freeSpanHistory.id}`} color="link" size="sm">
                      {freeSpanHistory.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHistory.dateForm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHistory.dateTo} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanHistory.workId}</td>
                  <td>{freeSpanHistory.pipelineSection}</td>
                  <td>{freeSpanHistory.length}</td>
                  <td>{freeSpanHistory.kpStart}</td>
                  <td>{freeSpanHistory.kpEnd}</td>
                  <td>{freeSpanHistory.isCurrentFlag ? 'true' : 'false'}</td>
                  <td>{freeSpanHistory.statusId}</td>
                  <td>{freeSpanHistory.comment}</td>
                  <td>
                    <TextFormat type="date" value={freeSpanHistory.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={freeSpanHistory.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{freeSpanHistory.creator}</td>
                  <td>{freeSpanHistory.editor}</td>
                  <td>
                    {freeSpanHistory.status ? (
                      <Link to={`list-object-status/${freeSpanHistory.status.id}`}>{freeSpanHistory.status.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {freeSpanHistory.pipelineSection ? (
                      <Link to={`pipeline-section/${freeSpanHistory.pipelineSection.id}`}>{freeSpanHistory.pipelineSection.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${freeSpanHistory.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanHistory.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${freeSpanHistory.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ freeSpanHistory }: IRootState) => ({
  freeSpanHistoryList: freeSpanHistory.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanHistory);
