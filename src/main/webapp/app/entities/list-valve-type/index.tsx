import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListValveType from './list-valve-type';
import ListValveTypeDetail from './list-valve-type-detail';
import ListValveTypeUpdate from './list-valve-type-update';
import ListValveTypeDeleteDialog from './list-valve-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListValveTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListValveTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListValveTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListValveType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListValveTypeDeleteDialog} />
  </>
);

export default Routes;
