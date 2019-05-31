import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListClcResult from './list-clc-result';
import ListClcResultDetail from './list-clc-result-detail';
import ListClcResultUpdate from './list-clc-result-update';
import ListClcResultDeleteDialog from './list-clc-result-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListClcResultUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListClcResultUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListClcResultDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListClcResult} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListClcResultDeleteDialog} />
  </>
);

export default Routes;
