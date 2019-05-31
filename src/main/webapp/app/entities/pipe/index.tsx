import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pipe from './pipe';
import PipeDetail from './pipe-detail';
import PipeUpdate from './pipe-update';
import PipeDeleteDialog from './pipe-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pipe} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipeDeleteDialog} />
  </>
);

export default Routes;
