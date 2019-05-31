import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Displacement from './displacement';
import DisplacementDetail from './displacement-detail';
import DisplacementUpdate from './displacement-update';
import DisplacementDeleteDialog from './displacement-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DisplacementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DisplacementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DisplacementDetail} />
      <ErrorBoundaryRoute path={match.url} component={Displacement} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DisplacementDeleteDialog} />
  </>
);

export default Routes;
