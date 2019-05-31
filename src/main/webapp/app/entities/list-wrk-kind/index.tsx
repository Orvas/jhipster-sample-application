import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListWrkKind from './list-wrk-kind';
import ListWrkKindDetail from './list-wrk-kind-detail';
import ListWrkKindUpdate from './list-wrk-kind-update';
import ListWrkKindDeleteDialog from './list-wrk-kind-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListWrkKindUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListWrkKindUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListWrkKindDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListWrkKind} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListWrkKindDeleteDialog} />
  </>
);

export default Routes;
