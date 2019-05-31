import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PipejointHist from './pipejoint-hist';
import PipejointHistDetail from './pipejoint-hist-detail';
import PipejointHistUpdate from './pipejoint-hist-update';
import PipejointHistDeleteDialog from './pipejoint-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipejointHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipejointHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipejointHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={PipejointHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipejointHistDeleteDialog} />
  </>
);

export default Routes;
