import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PipelineHist from './pipeline-hist';
import PipelineHistDetail from './pipeline-hist-detail';
import PipelineHistUpdate from './pipeline-hist-update';
import PipelineHistDeleteDialog from './pipeline-hist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipelineHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipelineHistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipelineHistDetail} />
      <ErrorBoundaryRoute path={match.url} component={PipelineHist} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipelineHistDeleteDialog} />
  </>
);

export default Routes;
