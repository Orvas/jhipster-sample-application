import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListEnvDirection from './list-env-direction';
import ListEnvDirectionDetail from './list-env-direction-detail';
import ListEnvDirectionUpdate from './list-env-direction-update';
import ListEnvDirectionDeleteDialog from './list-env-direction-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListEnvDirectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListEnvDirectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListEnvDirectionDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListEnvDirection} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListEnvDirectionDeleteDialog} />
  </>
);

export default Routes;
