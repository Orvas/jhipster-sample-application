import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListEffAxforceUcase from './list-eff-axforce-ucase';
import ListEffAxforceUcaseDetail from './list-eff-axforce-ucase-detail';
import ListEffAxforceUcaseUpdate from './list-eff-axforce-ucase-update';
import ListEffAxforceUcaseDeleteDialog from './list-eff-axforce-ucase-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListEffAxforceUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListEffAxforceUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListEffAxforceUcaseDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListEffAxforceUcase} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListEffAxforceUcaseDeleteDialog} />
  </>
);

export default Routes;
