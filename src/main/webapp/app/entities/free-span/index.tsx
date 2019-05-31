import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FreeSpan from './free-span';
import FreeSpanDetail from './free-span-detail';
import FreeSpanUpdate from './free-span-update';
import FreeSpanDeleteDialog from './free-span-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FreeSpanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FreeSpanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FreeSpanDetail} />
      <ErrorBoundaryRoute path={match.url} component={FreeSpan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FreeSpanDeleteDialog} />
  </>
);

export default Routes;
