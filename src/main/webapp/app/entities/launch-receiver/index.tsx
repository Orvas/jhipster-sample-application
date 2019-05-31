import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LaunchReceiver from './launch-receiver';
import LaunchReceiverDetail from './launch-receiver-detail';
import LaunchReceiverUpdate from './launch-receiver-update';
import LaunchReceiverDeleteDialog from './launch-receiver-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LaunchReceiverUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LaunchReceiverUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LaunchReceiverDetail} />
      <ErrorBoundaryRoute path={match.url} component={LaunchReceiver} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LaunchReceiverDeleteDialog} />
  </>
);

export default Routes;
