import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBendType from './list-bend-type';
import ListBendTypeDetail from './list-bend-type-detail';
import ListBendTypeUpdate from './list-bend-type-update';
import ListBendTypeDeleteDialog from './list-bend-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBendTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBendTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBendTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBendType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBendTypeDeleteDialog} />
  </>
);

export default Routes;
