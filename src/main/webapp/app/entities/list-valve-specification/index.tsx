import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListValveSpecification from './list-valve-specification';
import ListValveSpecificationDetail from './list-valve-specification-detail';
import ListValveSpecificationUpdate from './list-valve-specification-update';
import ListValveSpecificationDeleteDialog from './list-valve-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListValveSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListValveSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListValveSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListValveSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListValveSpecificationDeleteDialog} />
  </>
);

export default Routes;
