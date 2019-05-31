import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BuckleArrestor from './buckle-arrestor';
import BuckleArrestorDetail from './buckle-arrestor-detail';
import BuckleArrestorUpdate from './buckle-arrestor-update';
import BuckleArrestorDeleteDialog from './buckle-arrestor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BuckleArrestorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BuckleArrestorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BuckleArrestorDetail} />
      <ErrorBoundaryRoute path={match.url} component={BuckleArrestor} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BuckleArrestorDeleteDialog} />
  </>
);

export default Routes;
