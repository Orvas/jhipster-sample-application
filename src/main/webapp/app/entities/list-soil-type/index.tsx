import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListSoilType from './list-soil-type';
import ListSoilTypeDetail from './list-soil-type-detail';
import ListSoilTypeUpdate from './list-soil-type-update';
import ListSoilTypeDeleteDialog from './list-soil-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListSoilTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListSoilTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListSoilTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListSoilType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListSoilTypeDeleteDialog} />
  </>
);

export default Routes;
