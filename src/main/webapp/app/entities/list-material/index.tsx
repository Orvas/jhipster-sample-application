import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListMaterial from './list-material';
import ListMaterialDetail from './list-material-detail';
import ListMaterialUpdate from './list-material-update';
import ListMaterialDeleteDialog from './list-material-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListMaterialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListMaterialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListMaterialDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListMaterial} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListMaterialDeleteDialog} />
  </>
);

export default Routes;
