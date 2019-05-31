import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListFabricationType from './list-fabrication-type';
import ListFabricationTypeDetail from './list-fabrication-type-detail';
import ListFabricationTypeUpdate from './list-fabrication-type-update';
import ListFabricationTypeDeleteDialog from './list-fabrication-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListFabricationTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListFabricationTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListFabricationTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListFabricationType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListFabricationTypeDeleteDialog} />
  </>
);

export default Routes;
