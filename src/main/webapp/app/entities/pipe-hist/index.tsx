import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PipeHist from './pipe-hist';
import PipeHistDetail from './pipe-hist-detail';
import PipeHistUpdate from './pipe-hist-update';
import PipeHistDeleteDialog from './pipe-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipeHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipeHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={PipeHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipeHistDeleteDialog} />
  </>
);

export default Routes;
