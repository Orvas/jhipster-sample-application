import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListExternalCoating from './list-external-coating';
import ListExternalCoatingDetail from './list-external-coating-detail';
import ListExternalCoatingUpdate from './list-external-coating-update';
import ListExternalCoatingDeleteDialog from './list-external-coating-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListExternalCoatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListExternalCoatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListExternalCoatingDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListExternalCoating} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListExternalCoatingDeleteDialog} />
  </>
);

export default Routes;
