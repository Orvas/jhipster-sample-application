import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListThreatGroup from './list-threat-group';
import ListThreatGroupDetail from './list-threat-group-detail';
import ListThreatGroupUpdate from './list-threat-group-update';
import ListThreatGroupDeleteDialog from './list-threat-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListThreatGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListThreatGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListThreatGroupDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListThreatGroup} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListThreatGroupDeleteDialog} />
  </>
);

export default Routes;
