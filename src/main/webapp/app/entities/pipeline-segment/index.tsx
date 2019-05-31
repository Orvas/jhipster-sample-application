import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PipelineSegment from './pipeline-segment';
import PipelineSegmentDetail from './pipeline-segment-detail';
import PipelineSegmentUpdate from './pipeline-segment-update';
import PipelineSegmentDeleteDialog from './pipeline-segment-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipelineSegmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipelineSegmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipelineSegmentDetail} />
      <ErrorBoundaryRoute path={match.url} component={PipelineSegment} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipelineSegmentDeleteDialog} />
  </>
);

export default Routes;
