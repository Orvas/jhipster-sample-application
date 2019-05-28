import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pipeline-section.reducer';
import { IPipelineSection } from 'app/shared/model/pipeline-section.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPipelineSectionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PipelineSection extends React.Component<IPipelineSectionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { pipelineSectionList, match } = this.props;
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
                <th>ID</th>
                <th>Name</th>
                <th>Pipeline Id</th>
                <th>Is Onshore</th>
                <th>Safety Class Id</th>
                <th>Kp Start</th>
                <th>Kp End</th>
                <th>Date Create</th>
                <th>Date Edit</th>
                <th>Creator</th>
                <th>Editor</th>
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
                  <td>{pipelineSection.pipelineId}</td>
                  <td>{pipelineSection.isOnshore ? 'true' : 'false'}</td>
                  <td>{pipelineSection.safetyClassId}</td>
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
      </div>
    );
  }
}

const mapStateToProps = ({ pipelineSection }: IRootState) => ({
  pipelineSectionList: pipelineSection.entities
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
