import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListDfctType from './list-dfct-type';
import ListDfctTypeDetail from './list-dfct-type-detail';
import ListDfctTypeUpdate from './list-dfct-type-update';
import ListDfctTypeDeleteDialog from './list-dfct-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListDfctTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListDfctTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListDfctTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListDfctType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListDfctTypeDeleteDialog} />
  </>
);

export default Routes;
