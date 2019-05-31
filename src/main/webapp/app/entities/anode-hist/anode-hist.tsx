import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './anode-hist.reducer';
import { IAnodeHist } from 'app/shared/model/anode-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IAnodeHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IAnodeHistState = IPaginationBaseState;

export class AnodeHist extends React.Component<IAnodeHistProps, IAnodeHistState> {
  state: IAnodeHistState = {
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
    const { anodeHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="anode-hist-heading">
          Anode Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Anode Hist
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
                <th className="hand" onClick={this.sort('designLife')}>
                  Design Life <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dmcd')}>
                  Dmcd <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('l1')}>
                  L 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('l2')}>
                  L 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('length')}>
                  Length <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('electrCapac')}>
                  Electr Capac <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designWeight')}>
                  Design Weight <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('weight')}>
                  Weight <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isBurial')}>
                  Is Burial <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('chemicalComposition')}>
                  Chemical Composition <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('density')}>
                  Density <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('spacing')}>
                  Spacing <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coatCutbackLength')}>
                  Coat Cutback Length <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coatDmgArea')}>
                  Coat Dmg Area <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('h2sSoil')}>
                  H 2 S Soil <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('remain')}>
                  Remain <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('intFldTemp')}>
                  Int Fld Temp <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('minPrc')}>
                  Min Prc <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('thickness')}>
                  Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coord')}>
                  Coord <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpStart')}>
                  Kp Start <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coatThickness')}>
                  Coat Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kpEnd')}>
                  Kp End <FontAwesomeIcon icon="sort" />
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
                  Anode <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipeline Section <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Bracelete Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Material <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {anodeHistList.map((anodeHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${anodeHist.id}`} color="link" size="sm">
                      {anodeHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={anodeHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={anodeHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{anodeHist.idWrk}</td>
                  <td>{anodeHist.designLife}</td>
                  <td>{anodeHist.dmcd}</td>
                  <td>{anodeHist.l1}</td>
                  <td>{anodeHist.l2}</td>
                  <td>{anodeHist.length}</td>
                  <td>{anodeHist.electrCapac}</td>
                  <td>{anodeHist.designWeight}</td>
                  <td>{anodeHist.weight}</td>
                  <td>{anodeHist.isBurial}</td>
                  <td>{anodeHist.chemicalComposition}</td>
                  <td>{anodeHist.density}</td>
                  <td>{anodeHist.spacing}</td>
                  <td>{anodeHist.coatCutbackLength}</td>
                  <td>{anodeHist.coatDmgArea}</td>
                  <td>{anodeHist.h2sSoil}</td>
                  <td>{anodeHist.remain}</td>
                  <td>{anodeHist.intFldTemp}</td>
                  <td>{anodeHist.minPrc}</td>
                  <td>{anodeHist.thickness}</td>
                  <td>{anodeHist.coord}</td>
                  <td>{anodeHist.kpStart}</td>
                  <td>{anodeHist.coatThickness}</td>
                  <td>{anodeHist.kpEnd}</td>
                  <td>{anodeHist.isCurrentFlag}</td>
                  <td>{anodeHist.description}</td>
                  <td>{anodeHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={anodeHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={anodeHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{anodeHist.creator}</td>
                  <td>{anodeHist.editor}</td>
                  <td>{anodeHist.anodeId ? <Link to={`anode/${anodeHist.anodeId}`}>{anodeHist.anodeId}</Link> : ''}</td>
                  <td>
                    {anodeHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${anodeHist.idPipelineSectionId}`}>{anodeHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {anodeHist.idBraceleteTypeId ? (
                      <Link to={`list-anode-bracelete-type/${anodeHist.idBraceleteTypeId}`}>{anodeHist.idBraceleteTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {anodeHist.idMaterialId ? <Link to={`list-material/${anodeHist.idMaterialId}`}>{anodeHist.idMaterialId}</Link> : ''}
                  </td>
                  <td>{anodeHist.idStatusId ? <Link to={`list-wrk-status/${anodeHist.idStatusId}`}>{anodeHist.idStatusId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${anodeHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${anodeHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${anodeHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ anodeHist }: IRootState) => ({
  anodeHistList: anodeHist.entities,
  totalItems: anodeHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AnodeHist);
