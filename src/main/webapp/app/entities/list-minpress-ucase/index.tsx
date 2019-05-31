import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListMinpressUcase from './list-minpress-ucase';
import ListMinpressUcaseDetail from './list-minpress-ucase-detail';
import ListMinpressUcaseUpdate from './list-minpress-ucase-update';
import ListMinpressUcaseDeleteDialog from './list-minpress-ucase-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListMinpressUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListMinpressUcaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListMinpressUcaseDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListMinpressUcase} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListMinpressUcaseDeleteDialog} />
  </>
);

export default Routes;
