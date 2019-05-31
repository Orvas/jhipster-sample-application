import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListInternalCoating from './list-internal-coating';
import ListInternalCoatingDetail from './list-internal-coating-detail';
import ListInternalCoatingUpdate from './list-internal-coating-update';
import ListInternalCoatingDeleteDialog from './list-internal-coating-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListInternalCoatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListInternalCoatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListInternalCoatingDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListInternalCoating} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListInternalCoatingDeleteDialog} />
  </>
);

export default Routes;
