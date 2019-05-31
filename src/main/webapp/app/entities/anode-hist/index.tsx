import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AnodeHist from './anode-hist';
import AnodeHistDetail from './anode-hist-detail';
import AnodeHistUpdate from './anode-hist-update';
import AnodeHistDeleteDialog from './anode-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AnodeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AnodeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AnodeHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={AnodeHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AnodeHistDeleteDialog} />
  </>
);

export default Routes;
