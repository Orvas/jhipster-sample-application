import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BendHist from './bend-hist';
import BendHistDetail from './bend-hist-detail';
import BendHistUpdate from './bend-hist-update';
import BendHistDeleteDialog from './bend-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BendHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BendHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BendHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={BendHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BendHistDeleteDialog} />
  </>
);

export default Routes;
