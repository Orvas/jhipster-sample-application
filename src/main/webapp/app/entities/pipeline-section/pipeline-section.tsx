import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pipeline-section.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPipelineSectionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPipelineSectionState = IPaginationBaseState;

export class PipelineSection extends React.Component<IPipelineSectionProps, IPipelineSectionState> {
  state: IPipelineSectionState = {
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
    const { pipelineSectionList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="pipeline-section-heading">
          Pipeline Sections
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Pipeline Section
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('name')}>
                  Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isOnshore')}>
                  Is Onshore <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpStart')}>
                  Kp Start <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpEnd')}>
                  Kp End <FontAwesomeIcon icon="sort" />
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
                  Id Pipeline <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Safety Class <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pipelineSectionList.map((pipelineSection, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pipelineSection.id}`} color="link" size="sm">
                      {pipelineSection.id}
                    </Button>
                  </td>
                  <td>{pipelineSection.name}</td>
                  <td>{pipelineSection.isOnshore}</td>
                  <td>{pipelineSection.kpStart}</td>
                  <td>{pipelineSection.kpEnd}</td>
                  <td>
                    <TextFormat type="date" value={pipelineSection.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={pipelineSection.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pipelineSection.creator}</td>
                  <td>{pipelineSection.editor}</td>
                  <td>{pipelineSection.idId ? <Link to={`base-class/${pipelineSection.idId}`}>{pipelineSection.idId}</Link> : ''}</td>
                  <td>
                    {pipelineSection.idPipelineId ? (
                      <Link to={`pipeline/${pipelineSection.idPipelineId}`}>{pipelineSection.idPipelineId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipelineSection.idSafetyClassId ? (
                      <Link to={`list-safety-class/${pipelineSection.idSafetyClassId}`}>{pipelineSection.idSafetyClassId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pipelineSection.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipelineSection.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipelineSection.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pipelineSection }: IRootState) => ({
  pipelineSectionList: pipelineSection.entities,
  totalItems: pipelineSection.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipelineSection);
