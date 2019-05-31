import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListInternalPressUcase from './list-internal-press-ucase';
import ListInternalPressUcaseDetail from './list-internal-press-ucase-detail';
import ListInternalPressUcaseUpdate from './list-internal-press-ucase-update';
import ListInternalPressUcaseDeleteDialog from './list-internal-press-ucase-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListInternalPressUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListInternalPressUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListInternalPressUcaseDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListInternalPressUcase} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListInternalPressUcaseDeleteDialog} />
  </>
);

export default Routes;
