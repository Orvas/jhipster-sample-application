import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LaunchReceiverHist from './launch-receiver-hist';
import LaunchReceiverHistDetail from './launch-receiver-hist-detail';
import LaunchReceiverHistUpdate from './launch-receiver-hist-update';
import LaunchReceiverHistDeleteDialog from './launch-receiver-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LaunchReceiverHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LaunchReceiverHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LaunchReceiverHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={LaunchReceiverHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LaunchReceiverHistDeleteDialog} />
  </>
);

export default Routes;
