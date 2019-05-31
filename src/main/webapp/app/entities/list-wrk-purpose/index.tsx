import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListWrkPurpose from './list-wrk-purpose';
import ListWrkPurposeDetail from './list-wrk-purpose-detail';
import ListWrkPurposeUpdate from './list-wrk-purpose-update';
import ListWrkPurposeDeleteDialog from './list-wrk-purpose-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListWrkPurposeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListWrkPurposeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListWrkPurposeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListWrkPurpose} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListWrkPurposeDeleteDialog} />
  </>
);

export default Routes;
