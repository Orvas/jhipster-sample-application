import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pipe-hist.reducer';
import { IPipeHist } from 'app/shared/model/pipe-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPipeHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPipeHistState = IPaginationBaseState;

export class PipeHist extends React.Component<IPipeHistProps, IPipeHistState> {
  state: IPipeHistState = {
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
    const { pipeHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="pipe-hist-heading">
          Pipe Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Pipe Hist
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
                <th className="hand" onClick={this.sort('num')}>
                  Num <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('diameterOuterSteel')}>
                  Diameter Outer Steel <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('internalCoatThickness')}>
                  Internal Coat Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('externalCoatThickness')}>
                  External Coat Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isConcrCoating')}>
                  Is Concr Coating <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('concrCoatThickness')}>
                  Concr Coat Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('concrCoatDensity')}>
                  Concr Coat Density <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('measWallThickness')}>
                  Meas Wall Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('length')}>
                  Length <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('weight')}>
                  Weight <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('smts')}>
                  Smts <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('smys')}>
                  Smys <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sdbm')}>
                  Sdbm <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sdaf')}>
                  Sdaf <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sdcs')}>
                  Sdcs <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('allowTensStrain')}>
                  Allow Tens Strain <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('corrosionAllowance')}>
                  Corrosion Allowance <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fabricationAllowance')}>
                  Fabrication Allowance <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isBurial')}>
                  Is Burial <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('burialDepth')}>
                  Burial Depth <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('factBurialDepth')}>
                  Fact Burial Depth <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateManufactured')}>
                  Date Manufactured <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('millTestPressure')}>
                  Mill Test Pressure <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designPressure')}>
                  Design Pressure <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('incidentalPressure')}>
                  Incidental Pressure <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dateInstalled')}>
                  Date Installed <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('seamOrient')}>
                  Seam Orient <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('seamAngle')}>
                  Seam Angle <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('azimuth')}>
                  Azimuth <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('seabInstallTemp')}>
                  Seab Install Temp <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('verticalAngle')}>
                  Vertical Angle <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('heatTreatDurat')}>
                  Heat Treat Durat <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('maxDesignTemp')}>
                  Max Design Temp <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('heatNumber')}>
                  Heat Number <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('coord')}>
                  Coord <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designCoord')}>
                  Design Coord <FontAwesomeIcon icon="sort" />
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
                  Id Pipeline Section <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Internal Coat Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id External Coat Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Nominal Wall Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Pipe Joint <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Manufacturer <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Specification <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Long Seam Weld Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Fabrication Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Material <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Mill Location <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Steel Grade <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pipeHistList.map((pipeHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pipeHist.id}`} color="link" size="sm">
                      {pipeHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{pipeHist.idWrk}</td>
                  <td>{pipeHist.num}</td>
                  <td>{pipeHist.diameterOuterSteel}</td>
                  <td>{pipeHist.internalCoatThickness}</td>
                  <td>{pipeHist.externalCoatThickness}</td>
                  <td>{pipeHist.isConcrCoating}</td>
                  <td>{pipeHist.concrCoatThickness}</td>
                  <td>{pipeHist.concrCoatDensity}</td>
                  <td>{pipeHist.measWallThickness}</td>
                  <td>{pipeHist.length}</td>
                  <td>{pipeHist.weight}</td>
                  <td>{pipeHist.smts}</td>
                  <td>{pipeHist.smys}</td>
                  <td>{pipeHist.sdbm}</td>
                  <td>{pipeHist.sdaf}</td>
                  <td>{pipeHist.sdcs}</td>
                  <td>{pipeHist.allowTensStrain}</td>
                  <td>{pipeHist.corrosionAllowance}</td>
                  <td>{pipeHist.fabricationAllowance}</td>
                  <td>{pipeHist.isBurial}</td>
                  <td>{pipeHist.burialDepth}</td>
                  <td>{pipeHist.factBurialDepth}</td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateManufactured} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{pipeHist.millTestPressure}</td>
                  <td>{pipeHist.designPressure}</td>
                  <td>{pipeHist.incidentalPressure}</td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateInstalled} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{pipeHist.seamOrient}</td>
                  <td>{pipeHist.seamAngle}</td>
                  <td>{pipeHist.azimuth}</td>
                  <td>{pipeHist.seabInstallTemp}</td>
                  <td>{pipeHist.verticalAngle}</td>
                  <td>{pipeHist.heatTreatDurat}</td>
                  <td>{pipeHist.maxDesignTemp}</td>
                  <td>{pipeHist.heatNumber}</td>
                  <td>{pipeHist.coord}</td>
                  <td>{pipeHist.designCoord}</td>
                  <td>{pipeHist.kpStart}</td>
                  <td>{pipeHist.kpEnd}</td>
                  <td>{pipeHist.isCurrentFlag}</td>
                  <td>{pipeHist.description}</td>
                  <td>{pipeHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={pipeHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pipeHist.creator}</td>
                  <td>{pipeHist.editor}</td>
                  <td>{pipeHist.idId ? <Link to={`pipe/${pipeHist.idId}`}>{pipeHist.idId}</Link> : ''}</td>
                  <td>
                    {pipeHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${pipeHist.idPipelineSectionId}`}>{pipeHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idInternalCoatTypeId ? (
                      <Link to={`list-internal-coating/${pipeHist.idInternalCoatTypeId}`}>{pipeHist.idInternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${pipeHist.idExternalCoatTypeId}`}>{pipeHist.idExternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idNominalWallThicknessId ? (
                      <Link to={`list-nominal-wall-thickness/${pipeHist.idNominalWallThicknessId}`}>
                        {pipeHist.idNominalWallThicknessId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{pipeHist.idPipeJointId ? <Link to={`pipejoint/${pipeHist.idPipeJointId}`}>{pipeHist.idPipeJointId}</Link> : ''}</td>
                  <td>
                    {pipeHist.idManufacturerId ? (
                      <Link to={`list-pipe-manufacturer/${pipeHist.idManufacturerId}`}>{pipeHist.idManufacturerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idSpecificationId ? (
                      <Link to={`list-pipe-specification/${pipeHist.idSpecificationId}`}>{pipeHist.idSpecificationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idLongSeamWeldTypeId ? (
                      <Link to={`list-long-seam-weld-type/${pipeHist.idLongSeamWeldTypeId}`}>{pipeHist.idLongSeamWeldTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idFabricationTypeId ? (
                      <Link to={`list-fabrication-type/${pipeHist.idFabricationTypeId}`}>{pipeHist.idFabricationTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{pipeHist.idMaterialId ? <Link to={`list-material/${pipeHist.idMaterialId}`}>{pipeHist.idMaterialId}</Link> : ''}</td>
                  <td>
                    {pipeHist.idMillLocationId ? (
                      <Link to={`list-mill-location/${pipeHist.idMillLocationId}`}>{pipeHist.idMillLocationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {pipeHist.idSteelGradeId ? (
                      <Link to={`list-steel-grade/${pipeHist.idSteelGradeId}`}>{pipeHist.idSteelGradeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{pipeHist.idStatusId ? <Link to={`list-object-status/${pipeHist.idStatusId}`}>{pipeHist.idStatusId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pipeHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipeHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pipeHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pipeHist }: IRootState) => ({
  pipeHistList: pipeHist.entities,
  totalItems: pipeHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PipeHist);
