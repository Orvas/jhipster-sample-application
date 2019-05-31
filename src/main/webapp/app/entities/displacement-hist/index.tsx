import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DisplacementHist from './displacement-hist';
import DisplacementHistDetail from './displacement-hist-detail';
import DisplacementHistUpdate from './displacement-hist-update';
import DisplacementHistDeleteDialog from './displacement-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DisplacementHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DisplacementHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DisplacementHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={DisplacementHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DisplacementHistDeleteDialog} />
  </>
);

export default Routes;
