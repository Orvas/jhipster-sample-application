import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bend-hist.reducer';
import { IBendHist } from 'app/shared/model/bend-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IBendHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IBendHistState = IPaginationBaseState;

export class BendHist extends React.Component<IBendHistProps, IBendHistState> {
  state: IBendHistState = {
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
    const { bendHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="bend-hist-heading">
          Bend Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Bend Hist
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
                <th className="hand" onClick={this.sort('radius')}>
                  Radius <FontAwesomeIcon icon="sort" />
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
                  Bend <FontAwesomeIcon icon="sort" />
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
              {bendHistList.map((bendHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${bendHist.id}`} color="link" size="sm">
                      {bendHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{bendHist.idWrk}</td>
                  <td>{bendHist.num}</td>
                  <td>{bendHist.radius}</td>
                  <td>{bendHist.diameterOuterSteel}</td>
                  <td>{bendHist.internalCoatThickness}</td>
                  <td>{bendHist.externalCoatThickness}</td>
                  <td>{bendHist.isConcrCoating}</td>
                  <td>{bendHist.concrCoatThickness}</td>
                  <td>{bendHist.concrCoatDensity}</td>
                  <td>{bendHist.measWallThickness}</td>
                  <td>{bendHist.length}</td>
                  <td>{bendHist.weight}</td>
                  <td>{bendHist.smts}</td>
                  <td>{bendHist.smys}</td>
                  <td>{bendHist.sdbm}</td>
                  <td>{bendHist.sdaf}</td>
                  <td>{bendHist.sdcs}</td>
                  <td>{bendHist.allowTensStrain}</td>
                  <td>{bendHist.corrosionAllowance}</td>
                  <td>{bendHist.fabricationAllowance}</td>
                  <td>{bendHist.isBurial}</td>
                  <td>{bendHist.burialDepth}</td>
                  <td>{bendHist.factBurialDepth}</td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateManufactured} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{bendHist.millTestPressure}</td>
                  <td>{bendHist.designPressure}</td>
                  <td>{bendHist.incidentalPressure}</td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateInstalled} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{bendHist.seamOrient}</td>
                  <td>{bendHist.seamAngle}</td>
                  <td>{bendHist.azimuth}</td>
                  <td>{bendHist.seabInstallTemp}</td>
                  <td>{bendHist.verticalAngle}</td>
                  <td>{bendHist.heatTreatDurat}</td>
                  <td>{bendHist.maxDesignTemp}</td>
                  <td>{bendHist.heatNumber}</td>
                  <td>{bendHist.coord}</td>
                  <td>{bendHist.designCoord}</td>
                  <td>{bendHist.kpStart}</td>
                  <td>{bendHist.kpEnd}</td>
                  <td>{bendHist.isCurrentFlag}</td>
                  <td>{bendHist.description}</td>
                  <td>{bendHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={bendHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{bendHist.creator}</td>
                  <td>{bendHist.editor}</td>
                  <td>{bendHist.bendId ? <Link to={`bend/${bendHist.bendId}`}>{bendHist.bendId}</Link> : ''}</td>
                  <td>
                    {bendHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${bendHist.idPipelineSectionId}`}>{bendHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{bendHist.idTypeId ? <Link to={`list-bend-type/${bendHist.idTypeId}`}>{bendHist.idTypeId}</Link> : ''}</td>
                  <td>
                    {bendHist.idInternalCoatTypeId ? (
                      <Link to={`list-internal-coating/${bendHist.idInternalCoatTypeId}`}>{bendHist.idInternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${bendHist.idExternalCoatTypeId}`}>{bendHist.idExternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idNominalWallThicknessId ? (
                      <Link to={`list-nominal-wall-thickness/${bendHist.idNominalWallThicknessId}`}>
                        {bendHist.idNominalWallThicknessId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{bendHist.idPipeJointId ? <Link to={`pipejoint/${bendHist.idPipeJointId}`}>{bendHist.idPipeJointId}</Link> : ''}</td>
                  <td>
                    {bendHist.idManufacturerId ? (
                      <Link to={`list-bend-manufacturer/${bendHist.idManufacturerId}`}>{bendHist.idManufacturerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idSpecificationId ? (
                      <Link to={`list-bend-specification/${bendHist.idSpecificationId}`}>{bendHist.idSpecificationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idLongSeamWeldTypeId ? (
                      <Link to={`list-long-seam-weld-type/${bendHist.idLongSeamWeldTypeId}`}>{bendHist.idLongSeamWeldTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idFabricationTypeId ? (
                      <Link to={`list-fabrication-type/${bendHist.idFabricationTypeId}`}>{bendHist.idFabricationTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{bendHist.idMaterialId ? <Link to={`list-material/${bendHist.idMaterialId}`}>{bendHist.idMaterialId}</Link> : ''}</td>
                  <td>
                    {bendHist.idMillLocationId ? (
                      <Link to={`list-mill-location/${bendHist.idMillLocationId}`}>{bendHist.idMillLocationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {bendHist.idSteelGradeId ? (
                      <Link to={`list-steel-grade/${bendHist.idSteelGradeId}`}>{bendHist.idSteelGradeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{bendHist.idStatusId ? <Link to={`list-object-status/${bendHist.idStatusId}`}>{bendHist.idStatusId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bendHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bendHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bendHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ bendHist }: IRootState) => ({
  bendHistList: bendHist.entities,
  totalItems: bendHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BendHist);
