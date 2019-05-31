import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CpsHist from './cps-hist';
import CpsHistDetail from './cps-hist-detail';
import CpsHistUpdate from './cps-hist-update';
import CpsHistDeleteDialog from './cps-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CpsHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CpsHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CpsHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={CpsHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CpsHistDeleteDialog} />
  </>
);

export default Routes;
