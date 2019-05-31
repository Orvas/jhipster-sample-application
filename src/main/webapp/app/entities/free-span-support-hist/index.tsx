import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FreeSpanSupportHist from './free-span-support-hist';
import FreeSpanSupportHistDetail from './free-span-support-hist-detail';
import FreeSpanSupportHistUpdate from './free-span-support-hist-update';
import FreeSpanSupportHistDeleteDialog from './free-span-support-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FreeSpanSupportHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FreeSpanSupportHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FreeSpanSupportHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={FreeSpanSupportHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FreeSpanSupportHistDeleteDialog} />
  </>
);

export default Routes;
