import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListObjectStatus from './list-object-status';
import ListObjectStatusDetail from './list-object-status-detail';
import ListObjectStatusUpdate from './list-object-status-update';
import ListObjectStatusDeleteDialog from './list-object-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListObjectStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListObjectStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListObjectStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListObjectStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListObjectStatusDeleteDialog} />
  </>
);

export default Routes;
