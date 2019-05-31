import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListObjectType from './list-object-type';
import ListObjectTypeDetail from './list-object-type-detail';
import ListObjectTypeUpdate from './list-object-type-update';
import ListObjectTypeDeleteDialog from './list-object-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListObjectTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListObjectTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListObjectTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListObjectType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListObjectTypeDeleteDialog} />
  </>
);

export default Routes;
