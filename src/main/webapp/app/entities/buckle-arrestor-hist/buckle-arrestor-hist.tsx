import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './buckle-arrestor-hist.reducer';
import { IBuckleArrestorHist } from 'app/shared/model/buckle-arrestor-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IBuckleArrestorHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IBuckleArrestorHistState = IPaginationBaseState;

export class BuckleArrestorHist extends React.Component<IBuckleArrestorHistProps, IBuckleArrestorHistState> {
  state: IBuckleArrestorHistState = {
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
    const { buckleArrestorHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="buckle-arrestor-hist-heading">
          Buckle Arrestor Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Buckle Arrestor Hist
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
                <th className="hand" onClick={this.sort('serialNum')}>
                  Serial Num <FontAwesomeIcon icon="sort" />
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
                <th className="hand" onClick={this.sort('measColWallThickness')}>
                  Meas Col Wall Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('measPipeWallThickness')}>
                  Meas Pipe Wall Thickness <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('colLength')}>
                  Col Length <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('pipeLength')}>
                  Pipe Length <FontAwesomeIcon icon="sort" />
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
                <th className="hand" onClick={this.sort('idStatus')}>
                  Id Status <FontAwesomeIcon icon="sort" />
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
                  Id Type <FontAwesomeIcon icon="sort" />
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
                <th />
              </tr>
            </thead>
            <tbody>
              {buckleArrestorHistList.map((buckleArrestorHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${buckleArrestorHist.id}`} color="link" size="sm">
                      {buckleArrestorHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{buckleArrestorHist.idWrk}</td>
                  <td>{buckleArrestorHist.serialNum}</td>
                  <td>{buckleArrestorHist.diameterOuterSteel}</td>
                  <td>{buckleArrestorHist.internalCoatThickness}</td>
                  <td>{buckleArrestorHist.externalCoatThickness}</td>
                  <td>{buckleArrestorHist.isConcrCoating}</td>
                  <td>{buckleArrestorHist.concrCoatThickness}</td>
                  <td>{buckleArrestorHist.concrCoatDensity}</td>
                  <td>{buckleArrestorHist.measColWallThickness}</td>
                  <td>{buckleArrestorHist.measPipeWallThickness}</td>
                  <td>{buckleArrestorHist.colLength}</td>
                  <td>{buckleArrestorHist.pipeLength}</td>
                  <td>{buckleArrestorHist.weight}</td>
                  <td>{buckleArrestorHist.smts}</td>
                  <td>{buckleArrestorHist.smys}</td>
                  <td>{buckleArrestorHist.sdbm}</td>
                  <td>{buckleArrestorHist.sdaf}</td>
                  <td>{buckleArrestorHist.sdcs}</td>
                  <td>{buckleArrestorHist.allowTensStrain}</td>
                  <td>{buckleArrestorHist.corrosionAllowance}</td>
                  <td>{buckleArrestorHist.fabricationAllowance}</td>
                  <td>{buckleArrestorHist.isBurial}</td>
                  <td>{buckleArrestorHist.burialDepth}</td>
                  <td>{buckleArrestorHist.factBurialDepth}</td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateManufactured} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{buckleArrestorHist.millTestPressure}</td>
                  <td>{buckleArrestorHist.designPressure}</td>
                  <td>{buckleArrestorHist.incidentalPressure}</td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateInstalled} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{buckleArrestorHist.seamOrient}</td>
                  <td>{buckleArrestorHist.seamAngle}</td>
                  <td>{buckleArrestorHist.azimuth}</td>
                  <td>{buckleArrestorHist.seabInstallTemp}</td>
                  <td>{buckleArrestorHist.verticalAngle}</td>
                  <td>{buckleArrestorHist.heatTreatDurat}</td>
                  <td>{buckleArrestorHist.maxDesignTemp}</td>
                  <td>{buckleArrestorHist.heatNumber}</td>
                  <td>{buckleArrestorHist.coord}</td>
                  <td>{buckleArrestorHist.designCoord}</td>
                  <td>{buckleArrestorHist.kpStart}</td>
                  <td>{buckleArrestorHist.kpEnd}</td>
                  <td>{buckleArrestorHist.isCurrentFlag}</td>
                  <td>{buckleArrestorHist.idStatus}</td>
                  <td>{buckleArrestorHist.description}</td>
                  <td>{buckleArrestorHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={buckleArrestorHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{buckleArrestorHist.creator}</td>
                  <td>{buckleArrestorHist.editor}</td>
                  <td>
                    {buckleArrestorHist.idId ? (
                      <Link to={`buckle-arrestor/${buckleArrestorHist.idId}`}>{buckleArrestorHist.idId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${buckleArrestorHist.idPipelineSectionId}`}>
                        {buckleArrestorHist.idPipelineSectionId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idTypeId ? (
                      <Link to={`list-bucklarr-type/${buckleArrestorHist.idTypeId}`}>{buckleArrestorHist.idTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idInternalCoatTypeId ? (
                      <Link to={`list-internal-coating/${buckleArrestorHist.idInternalCoatTypeId}`}>
                        {buckleArrestorHist.idInternalCoatTypeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${buckleArrestorHist.idExternalCoatTypeId}`}>
                        {buckleArrestorHist.idExternalCoatTypeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idNominalWallThicknessId ? (
                      <Link to={`list-nominal-wall-thickness/${buckleArrestorHist.idNominalWallThicknessId}`}>
                        {buckleArrestorHist.idNominalWallThicknessId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idPipeJointId ? (
                      <Link to={`pipejoint/${buckleArrestorHist.idPipeJointId}`}>{buckleArrestorHist.idPipeJointId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idManufacturerId ? (
                      <Link to={`list-bucklarr-manufacturer/${buckleArrestorHist.idManufacturerId}`}>
                        {buckleArrestorHist.idManufacturerId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idSpecificationId ? (
                      <Link to={`list-bucklarr-specification/${buckleArrestorHist.idSpecificationId}`}>
                        {buckleArrestorHist.idSpecificationId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idLongSeamWeldTypeId ? (
                      <Link to={`list-long-seam-weld-type/${buckleArrestorHist.idLongSeamWeldTypeId}`}>
                        {buckleArrestorHist.idLongSeamWeldTypeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idFabricationTypeId ? (
                      <Link to={`list-fabrication-type/${buckleArrestorHist.idFabricationTypeId}`}>
                        {buckleArrestorHist.idFabricationTypeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idMaterialId ? (
                      <Link to={`list-material/${buckleArrestorHist.idMaterialId}`}>{buckleArrestorHist.idMaterialId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idMillLocationId ? (
                      <Link to={`list-mill-location/${buckleArrestorHist.idMillLocationId}`}>{buckleArrestorHist.idMillLocationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {buckleArrestorHist.idSteelGradeId ? (
                      <Link to={`list-steel-grade/${buckleArrestorHist.idSteelGradeId}`}>{buckleArrestorHist.idSteelGradeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${buckleArrestorHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${buckleArrestorHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${buckleArrestorHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ buckleArrestorHist }: IRootState) => ({
  buckleArrestorHistList: buckleArrestorHist.entities,
  totalItems: buckleArrestorHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BuckleArrestorHist);
