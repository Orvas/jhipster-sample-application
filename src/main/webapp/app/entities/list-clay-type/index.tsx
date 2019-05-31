import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListClayType from './list-clay-type';
import ListClayTypeDetail from './list-clay-type-detail';
import ListClayTypeUpdate from './list-clay-type-update';
import ListClayTypeDeleteDialog from './list-clay-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListClayTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListClayTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListClayTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListClayType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListClayTypeDeleteDialog} />
  </>
);

export default Routes;
