import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './valve-hist.reducer';
import { IValveHist } from 'app/shared/model/valve-hist.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IValveHistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IValveHistState = IPaginationBaseState;

export class ValveHist extends React.Component<IValveHistProps, IValveHistState> {
  state: IValveHistState = {
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
    const { valveHistList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="valve-hist-heading">
          Valve Hists
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Valve Hist
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
                <th className="hand" onClick={this.sort('serialNum')}>
                  Serial Num <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('model')}>
                  Model <FontAwesomeIcon icon="sort" />
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
                <th className="hand" onClick={this.sort('dateManufactured')}>
                  Date Manufactured <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('millTestPressure')}>
                  Mill Test Pressure <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designPressure')}>
                  Design Pressure <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designPressureIn')}>
                  Design Pressure In <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('designPressureOut')}>
                  Design Pressure Out <FontAwesomeIcon icon="sort" />
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
                  Valve <FontAwesomeIcon icon="sort" />
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
                  Id Function <FontAwesomeIcon icon="sort" />
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
              {valveHistList.map((valveHist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${valveHist.id}`} color="link" size="sm">
                      {valveHist.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateFrom} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateTo} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{valveHist.idWrk}</td>
                  <td>{valveHist.name}</td>
                  <td>{valveHist.serialNum}</td>
                  <td>{valveHist.model}</td>
                  <td>{valveHist.diameterOuterSteel}</td>
                  <td>{valveHist.internalCoatThickness}</td>
                  <td>{valveHist.externalCoatThickness}</td>
                  <td>{valveHist.isConcrCoating}</td>
                  <td>{valveHist.concrCoatThickness}</td>
                  <td>{valveHist.concrCoatDensity}</td>
                  <td>{valveHist.measWallThickness}</td>
                  <td>{valveHist.length}</td>
                  <td>{valveHist.weight}</td>
                  <td>{valveHist.smts}</td>
                  <td>{valveHist.smys}</td>
                  <td>{valveHist.sdbm}</td>
                  <td>{valveHist.sdaf}</td>
                  <td>{valveHist.sdcs}</td>
                  <td>{valveHist.allowTensStrain}</td>
                  <td>{valveHist.corrosionAllowance}</td>
                  <td>{valveHist.fabricationAllowance}</td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateManufactured} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{valveHist.millTestPressure}</td>
                  <td>{valveHist.designPressure}</td>
                  <td>{valveHist.designPressureIn}</td>
                  <td>{valveHist.designPressureOut}</td>
                  <td>{valveHist.incidentalPressure}</td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateInstalled} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{valveHist.seamOrient}</td>
                  <td>{valveHist.seamAngle}</td>
                  <td>{valveHist.azimuth}</td>
                  <td>{valveHist.seabInstallTemp}</td>
                  <td>{valveHist.verticalAngle}</td>
                  <td>{valveHist.heatTreatDurat}</td>
                  <td>{valveHist.maxDesignTemp}</td>
                  <td>{valveHist.heatNumber}</td>
                  <td>{valveHist.coord}</td>
                  <td>{valveHist.designCoord}</td>
                  <td>{valveHist.kpInst}</td>
                  <td>{valveHist.isCurrentFlag}</td>
                  <td>{valveHist.description}</td>
                  <td>{valveHist.comment}</td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateCreate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={valveHist.dateEdit} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{valveHist.creator}</td>
                  <td>{valveHist.editor}</td>
                  <td>{valveHist.valveId ? <Link to={`valve/${valveHist.valveId}`}>{valveHist.valveId}</Link> : ''}</td>
                  <td>
                    {valveHist.idPipelineSectionId ? (
                      <Link to={`pipeline-section/${valveHist.idPipelineSectionId}`}>{valveHist.idPipelineSectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{valveHist.idTypeId ? <Link to={`list-valve-type/${valveHist.idTypeId}`}>{valveHist.idTypeId}</Link> : ''}</td>
                  <td>
                    {valveHist.idInternalCoatTypeId ? (
                      <Link to={`list-internal-coating/${valveHist.idInternalCoatTypeId}`}>{valveHist.idInternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idExternalCoatTypeId ? (
                      <Link to={`list-external-coating/${valveHist.idExternalCoatTypeId}`}>{valveHist.idExternalCoatTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idNominalWallThicknessId ? (
                      <Link to={`list-nominal-wall-thickness/${valveHist.idNominalWallThicknessId}`}>
                        {valveHist.idNominalWallThicknessId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idPipeJointId ? <Link to={`pipejoint/${valveHist.idPipeJointId}`}>{valveHist.idPipeJointId}</Link> : ''}
                  </td>
                  <td>
                    {valveHist.idManufacturerId ? (
                      <Link to={`list-valve-manufacturer/${valveHist.idManufacturerId}`}>{valveHist.idManufacturerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idSpecificationId ? (
                      <Link to={`list-valve-specification/${valveHist.idSpecificationId}`}>{valveHist.idSpecificationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idFunctionId ? (
                      <Link to={`list-valve-function/${valveHist.idFunctionId}`}>{valveHist.idFunctionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idLongSeamWeldTypeId ? (
                      <Link to={`list-long-seam-weld-type/${valveHist.idLongSeamWeldTypeId}`}>{valveHist.idLongSeamWeldTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idFabricationTypeId ? (
                      <Link to={`list-fabrication-type/${valveHist.idFabricationTypeId}`}>{valveHist.idFabricationTypeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idMaterialId ? <Link to={`list-material/${valveHist.idMaterialId}`}>{valveHist.idMaterialId}</Link> : ''}
                  </td>
                  <td>
                    {valveHist.idMillLocationId ? (
                      <Link to={`list-mill-location/${valveHist.idMillLocationId}`}>{valveHist.idMillLocationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idSteelGradeId ? (
                      <Link to={`list-steel-grade/${valveHist.idSteelGradeId}`}>{valveHist.idSteelGradeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {valveHist.idStatusId ? <Link to={`list-object-status/${valveHist.idStatusId}`}>{valveHist.idStatusId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${valveHist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${valveHist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${valveHist.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ valveHist }: IRootState) => ({
  valveHistList: valveHist.entities,
  totalItems: valveHist.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ValveHist);
