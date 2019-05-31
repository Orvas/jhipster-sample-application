import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cps from './cps';
import CpsDetail from './cps-detail';
import CpsUpdate from './cps-update';
import CpsDeleteDialog from './cps-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CpsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CpsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CpsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Cps} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CpsDeleteDialog} />
  </>
);

export default Routes;
