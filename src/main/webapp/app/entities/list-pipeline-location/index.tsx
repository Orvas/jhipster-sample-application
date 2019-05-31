import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListPipelineLocation from './list-pipeline-location';
import ListPipelineLocationDetail from './list-pipeline-location-detail';
import ListPipelineLocationUpdate from './list-pipeline-location-update';
import ListPipelineLocationDeleteDialog from './list-pipeline-location-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListPipelineLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListPipelineLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListPipelineLocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListPipelineLocation} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListPipelineLocationDeleteDialog} />
  </>
);

export default Routes;
