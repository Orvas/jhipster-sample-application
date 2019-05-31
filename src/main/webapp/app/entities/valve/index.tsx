import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Valve from './valve';
import ValveDetail from './valve-detail';
import ValveUpdate from './valve-update';
import ValveDeleteDialog from './valve-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ValveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ValveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ValveDetail} />
      <ErrorBoundaryRoute path={match.url} component={Valve} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ValveDeleteDialog} />
  </>
);

export default Routes;
