import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListSafetyClass from './list-safety-class';
import ListSafetyClassDetail from './list-safety-class-detail';
import ListSafetyClassUpdate from './list-safety-class-update';
import ListSafetyClassDeleteDialog from './list-safety-class-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListSafetyClassUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListSafetyClassUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListSafetyClassDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListSafetyClass} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListSafetyClassDeleteDialog} />
  </>
);

export default Routes;
