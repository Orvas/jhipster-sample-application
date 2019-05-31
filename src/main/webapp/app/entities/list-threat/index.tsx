import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListThreat from './list-threat';
import ListThreatDetail from './list-threat-detail';
import ListThreatUpdate from './list-threat-update';
import ListThreatDeleteDialog from './list-threat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListThreatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListThreatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListThreatDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListThreat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListThreatDeleteDialog} />
  </>
);

export default Routes;
