import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListClcKind from './list-clc-kind';
import ListClcKindDetail from './list-clc-kind-detail';
import ListClcKindUpdate from './list-clc-kind-update';
import ListClcKindDeleteDialog from './list-clc-kind-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListClcKindUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListClcKindUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListClcKindDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListClcKind} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListClcKindDeleteDialog} />
  </>
);

export default Routes;
