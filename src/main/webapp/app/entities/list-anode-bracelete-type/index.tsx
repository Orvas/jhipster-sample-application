import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListAnodeBraceleteType from './list-anode-bracelete-type';
import ListAnodeBraceleteTypeDetail from './list-anode-bracelete-type-detail';
import ListAnodeBraceleteTypeUpdate from './list-anode-bracelete-type-update';
import ListAnodeBraceleteTypeDeleteDialog from './list-anode-bracelete-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListAnodeBraceleteTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListAnodeBraceleteTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListAnodeBraceleteTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListAnodeBraceleteType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListAnodeBraceleteTypeDeleteDialog} />
  </>
);

export default Routes;
