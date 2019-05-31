import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pipeline from './pipeline';
import PipelineDetail from './pipeline-detail';
import PipelineUpdate from './pipeline-update';
import PipelineDeleteDialog from './pipeline-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipelineUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipelineUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipelineDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pipeline} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipelineDeleteDialog} />
  </>
);

export default Routes;
