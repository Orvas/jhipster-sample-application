import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BuckleArrestorHist from './buckle-arrestor-hist';
import BuckleArrestorHistDetail from './buckle-arrestor-hist-detail';
import BuckleArrestorHistUpdate from './buckle-arrestor-hist-update';
import BuckleArrestorHistDeleteDialog from './buckle-arrestor-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BuckleArrestorHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BuckleArrestorHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BuckleArrestorHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={BuckleArrestorHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BuckleArrestorHistDeleteDialog} />
  </>
);

export default Routes;
