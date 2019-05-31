import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListMillLocation from './list-mill-location';
import ListMillLocationDetail from './list-mill-location-detail';
import ListMillLocationUpdate from './list-mill-location-update';
import ListMillLocationDeleteDialog from './list-mill-location-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListMillLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListMillLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListMillLocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListMillLocation} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListMillLocationDeleteDialog} />
  </>
);

export default Routes;
