import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListEnvPoint from './list-env-point';
import ListEnvPointDetail from './list-env-point-detail';
import ListEnvPointUpdate from './list-env-point-update';
import ListEnvPointDeleteDialog from './list-env-point-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListEnvPointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListEnvPointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListEnvPointDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListEnvPoint} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListEnvPointDeleteDialog} />
  </>
);

export default Routes;
