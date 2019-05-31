import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListWrkStatus from './list-wrk-status';
import ListWrkStatusDetail from './list-wrk-status-detail';
import ListWrkStatusUpdate from './list-wrk-status-update';
import ListWrkStatusDeleteDialog from './list-wrk-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListWrkStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListWrkStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListWrkStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListWrkStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListWrkStatusDeleteDialog} />
  </>
);

export default Routes;
