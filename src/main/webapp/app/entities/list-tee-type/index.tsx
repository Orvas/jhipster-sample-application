import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListTeeType from './list-tee-type';
import ListTeeTypeDetail from './list-tee-type-detail';
import ListTeeTypeUpdate from './list-tee-type-update';
import ListTeeTypeDeleteDialog from './list-tee-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListTeeTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListTeeTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListTeeTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListTeeType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListTeeTypeDeleteDialog} />
  </>
);

export default Routes;
