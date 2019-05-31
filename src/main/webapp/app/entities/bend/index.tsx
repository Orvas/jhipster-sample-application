import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Bend from './bend';
import BendDetail from './bend-detail';
import BendUpdate from './bend-update';
import BendDeleteDialog from './bend-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BendUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BendUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BendDetail} />
      <ErrorBoundaryRoute path={match.url} component={Bend} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BendDeleteDialog} />
  </>
);

export default Routes;
