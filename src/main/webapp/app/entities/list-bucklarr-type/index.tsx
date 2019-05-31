import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBucklarrType from './list-bucklarr-type';
import ListBucklarrTypeDetail from './list-bucklarr-type-detail';
import ListBucklarrTypeUpdate from './list-bucklarr-type-update';
import ListBucklarrTypeDeleteDialog from './list-bucklarr-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBucklarrTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBucklarrTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBucklarrTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBucklarrType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBucklarrTypeDeleteDialog} />
  </>
);

export default Routes;
