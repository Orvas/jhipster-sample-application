import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ValveHist from './valve-hist';
import ValveHistDetail from './valve-hist-detail';
import ValveHistUpdate from './valve-hist-update';
import ValveHistDeleteDialog from './valve-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ValveHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ValveHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ValveHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={ValveHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ValveHistDeleteDialog} />
  </>
);

export default Routes;
