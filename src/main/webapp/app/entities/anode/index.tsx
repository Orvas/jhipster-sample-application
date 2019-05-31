import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Anode from './anode';
import AnodeDetail from './anode-detail';
import AnodeUpdate from './anode-update';
import AnodeDeleteDialog from './anode-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AnodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AnodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AnodeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Anode} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AnodeDeleteDialog} />
  </>
);

export default Routes;
