import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TeeHist from './tee-hist';
import TeeHistDetail from './tee-hist-detail';
import TeeHistUpdate from './tee-hist-update';
import TeeHistDeleteDialog from './tee-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TeeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TeeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TeeHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={TeeHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TeeHistDeleteDialog} />
  </>
);

export default Routes;
