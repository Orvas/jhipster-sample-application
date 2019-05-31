import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pipejoint from './pipejoint';
import PipejointDetail from './pipejoint-detail';
import PipejointUpdate from './pipejoint-update';
import PipejointDeleteDialog from './pipejoint-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipejointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipejointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipejointDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pipejoint} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipejointDeleteDialog} />
  </>
);

export default Routes;
