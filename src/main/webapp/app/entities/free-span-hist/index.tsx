import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FreeSpanHist from './free-span-hist';
import FreeSpanHistDetail from './free-span-hist-detail';
import FreeSpanHistUpdate from './free-span-hist-update';
import FreeSpanHistDeleteDialog from './free-span-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FreeSpanHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FreeSpanHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FreeSpanHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={FreeSpanHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FreeSpanHistDeleteDialog} />
  </>
);

export default Routes;
