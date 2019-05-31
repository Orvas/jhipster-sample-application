import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListDfctGroup from './list-dfct-group';
import ListDfctGroupDetail from './list-dfct-group-detail';
import ListDfctGroupUpdate from './list-dfct-group-update';
import ListDfctGroupDeleteDialog from './list-dfct-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListDfctGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListDfctGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListDfctGroupDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListDfctGroup} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListDfctGroupDeleteDialog} />
  </>
);

export default Routes;
