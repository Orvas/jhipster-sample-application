import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FreeSpanSupport from './free-span-support';
import FreeSpanSupportDetail from './free-span-support-detail';
import FreeSpanSupportUpdate from './free-span-support-update';
import FreeSpanSupportDeleteDialog from './free-span-support-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FreeSpanSupportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FreeSpanSupportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FreeSpanSupportDetail} />
      <ErrorBoundaryRoute path={match.url} component={FreeSpanSupport} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FreeSpanSupportDeleteDialog} />
  </>
);

export default Routes;
