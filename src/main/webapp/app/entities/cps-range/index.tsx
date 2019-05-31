import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CpsRange from './cps-range';
import CpsRangeDetail from './cps-range-detail';
import CpsRangeUpdate from './cps-range-update';
import CpsRangeDeleteDialog from './cps-range-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CpsRangeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CpsRangeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CpsRangeDetail} />
      <ErrorBoundaryRoute path={match.url} component={CpsRange} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CpsRangeDeleteDialog} />
  </>
);

export default Routes;
