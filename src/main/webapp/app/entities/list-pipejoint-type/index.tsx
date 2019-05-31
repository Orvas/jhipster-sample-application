import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListPipejointType from './list-pipejoint-type';
import ListPipejointTypeDetail from './list-pipejoint-type-detail';
import ListPipejointTypeUpdate from './list-pipejoint-type-update';
import ListPipejointTypeDeleteDialog from './list-pipejoint-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListPipejointTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListPipejointTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListPipejointTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListPipejointType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListPipejointTypeDeleteDialog} />
  </>
);

export default Routes;
