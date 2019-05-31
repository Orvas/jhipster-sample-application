import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListValveFunction from './list-valve-function';
import ListValveFunctionDetail from './list-valve-function-detail';
import ListValveFunctionUpdate from './list-valve-function-update';
import ListValveFunctionDeleteDialog from './list-valve-function-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListValveFunctionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListValveFunctionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListValveFunctionDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListValveFunction} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListValveFunctionDeleteDialog} />
  </>
);

export default Routes;
