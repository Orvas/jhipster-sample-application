import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tee-hist.reducer';
import { ITeeHist } from 'app/shared/model/tee-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ITeeHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ITeeHistState = IPaginationBaseState;

export class TeeHist extends React.Component<ITeeHistProps, ITeeHistState> {
  state: ITeeHistState = {
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
    const { teeHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="tee-hist-heading">
          Tee Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Tee Hist
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
                <th className="hand" onClick={this.sort('name')}>
                  Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('diameterOuterSteel')}>
                  Diameter Outer Steel <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('diameterOuterSteelBr')}>
                  Diameter Outer Steel Br <FontAwesomeIcon icon="sort" />
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
                <th className="hand" onClick={this.sort('kpInst')}>
                  Kp Inst <FontAwesomeIcon icon="sort" />
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
                <th>
                  Id Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {teeHistList.map((teeHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${teeHist.id}`} color="link" size="sm">
                      {teeHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{teeHist.idWrk}</td>
                  <td>{teeHist.name}</td>
                  <td>{teeHist.diameterOuterSteel}</td>
                  <td>{teeHist.diameterOuterSteelBr}</td>
                  <td>{teeHist.internalCoatThickness}</td>
                  <td>{teeHist.externalCoatThickness}</td>
                  <td>{teeHist.isConcrCoating}</td>
                  <td>{teeHist.concrCoatThickness}</td>
                  <td>{teeHist.concrCoatDensity}</td>
                  <td>{teeHist.measWallThickness}</td>
                  <td>{teeHist.length}</td>
                  <td>{teeHist.weight}</td>
                  <td>{teeHist.smts}</td>
                  <td>{teeHist.smys}</td>
                  <td>{teeHist.sdbm}</td>
                  <td>{teeHist.sdaf}</td>
                  <td>{teeHist.sdcs}</td>
                  <td>{teeHist.allowTensStrain}</td>
                  <td>{teeHist.corrosionAllowance}</td>
                  <td>{teeHist.fabricationAllowance}</td>
                  <td>{teeHist.isBurial}</td>
                  <td>{teeHist.burialDepth}</td>
                  <td>{teeHist.factBurialDepth}</td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateManufactured} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{teeHist.millTestPressure}</td>
                  <td>{teeHist.designPressure}</td>
                  <td>{teeHist.incidentalPressure}</td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateInstalled} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{teeHist.seamOrient}</td>
                  <td>{teeHist.seamAngle}</td>
                  <td>{teeHist.azimuth}</td>
                  <td>{teeHist.seabInstallTemp}</td>
                  <td>{teeHist.verticalAngle}</td>
                  <td>{teeHist.heatTreatDurat}</td>
                  <td>{teeHist.maxDesignTemp}</td>
                  <td>{teeHist.heatNumber}</td>
                  <td>{teeHist.coord}</td>
                  <td>{teeHist.designCoord}</td>
                  <td>{teeHist.kpInst}</td>
                  <td>{teeHist.isCurrentFlag}</td>
                  <td>{teeHist.description}</td>
                  <td>{teeHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={teeHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{teeHist.creator}</td>
                  <td>{teeHist.editor}</td>
                  <td>{teeHist.idId ? <Link to={`tee/${teeHist.idId}`}>{teeHist.idId}</Link> : ''}</td>
                  <td>
                    {teeHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${teeHist.idPipelineSectionId}`}>{teeHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{teeHist.idTypeId ? <Link to={`list-tee-type/${teeHist.idTypeId}`}>{teeHist.idTypeId}</Link> : ''}</td>
                  <td>
                    {teeHist.idInternalCoatTypeId ? (
                      <Link to={`list-internal-coating/${teeHist.idInternalCoatTypeId}`}>{teeHist.idInternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${teeHist.idExternalCoatTypeId}`}>{teeHist.idExternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idNominalWallThicknessId ? (
                      <Link to={`list-nominal-wall-thickness/${teeHist.idNominalWallThicknessId}`}>{teeHist.idNominalWallThicknessId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{teeHist.idPipeJointId ? <Link to={`pipejoint/${teeHist.idPipeJointId}`}>{teeHist.idPipeJointId}</Link> : ''}</td>
                  <td>
                    {teeHist.idManufacturerId ? (
                      <Link to={`list-tee-manufacturer/${teeHist.idManufacturerId}`}>{teeHist.idManufacturerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idSpecificationId ? (
                      <Link to={`list-tee-specification/${teeHist.idSpecificationId}`}>{teeHist.idSpecificationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idLongSeamWeldTypeId ? (
                      <Link to={`list-long-seam-weld-type/${teeHist.idLongSeamWeldTypeId}`}>{teeHist.idLongSeamWeldTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idFabricationTypeId ? (
                      <Link to={`list-fabrication-type/${teeHist.idFabricationTypeId}`}>{teeHist.idFabricationTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{teeHist.idMaterialId ? <Link to={`list-material/${teeHist.idMaterialId}`}>{teeHist.idMaterialId}</Link> : ''}</td>
                  <td>
                    {teeHist.idMillLocationId ? (
                      <Link to={`list-mill-location/${teeHist.idMillLocationId}`}>{teeHist.idMillLocationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {teeHist.idSteelGradeId ? <Link to={`list-steel-grade/${teeHist.idSteelGradeId}`}>{teeHist.idSteelGradeId}</Link> : ''}
                  </td>
                  <td>{teeHist.idStatusId ? <Link to={`list-object-status/${teeHist.idStatusId}`}>{teeHist.idStatusId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${teeHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${teeHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${teeHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ teeHist }: IRootState) => ({
  teeHistList: teeHist.entities,
  totalItems: teeHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeeHist);
