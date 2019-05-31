import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListClcType from './list-clc-type';
import ListClcTypeDetail from './list-clc-type-detail';
import ListClcTypeUpdate from './list-clc-type-update';
import ListClcTypeDeleteDialog from './list-clc-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListClcTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListClcTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListClcTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListClcType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListClcTypeDeleteDialog} />
  </>
);

export default Routes;
