import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Tee from './tee';
import TeeDetail from './tee-detail';
import TeeUpdate from './tee-update';
import TeeDeleteDialog from './tee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Tee} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TeeDeleteDialog} />
  </>
);

export default Routes;
