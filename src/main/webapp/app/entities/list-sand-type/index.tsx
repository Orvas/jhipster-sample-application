import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListSandType from './list-sand-type';
import ListSandTypeDetail from './list-sand-type-detail';
import ListSandTypeUpdate from './list-sand-type-update';
import ListSandTypeDeleteDialog from './list-sand-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListSandTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListSandTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListSandTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListSandType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListSandTypeDeleteDialog} />
  </>
);

export default Routes;
