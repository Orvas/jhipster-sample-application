import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBoundaryCondUcase from './list-boundary-cond-ucase';
import ListBoundaryCondUcaseDetail from './list-boundary-cond-ucase-detail';
import ListBoundaryCondUcaseUpdate from './list-boundary-cond-ucase-update';
import ListBoundaryCondUcaseDeleteDialog from './list-boundary-cond-ucase-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBoundaryCondUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBoundaryCondUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBoundaryCondUcaseDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBoundaryCondUcase} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBoundaryCondUcaseDeleteDialog} />
  </>
);

export default Routes;
