import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FreeSpanHistory from './free-span-history';
import FreeSpanHistoryDetail from './free-span-history-detail';
import FreeSpanHistoryUpdate from './free-span-history-update';
import FreeSpanHistoryDeleteDialog from './free-span-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FreeSpanHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FreeSpanHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FreeSpanHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={FreeSpanHistory} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FreeSpanHistoryDeleteDialog} />
  </>
);

export default Routes;
