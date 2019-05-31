import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListWrkcmmsStatus from './list-wrkcmms-status';
import ListWrkcmmsStatusDetail from './list-wrkcmms-status-detail';
import ListWrkcmmsStatusUpdate from './list-wrkcmms-status-update';
import ListWrkcmmsStatusDeleteDialog from './list-wrkcmms-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListWrkcmmsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListWrkcmmsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListWrkcmmsStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListWrkcmmsStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListWrkcmmsStatusDeleteDialog} />
  </>
);

export default Routes;
