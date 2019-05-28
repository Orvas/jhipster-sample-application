import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PipelineSection from './pipeline-section';
import PipelineSectionDetail from './pipeline-section-detail';
import PipelineSectionUpdate from './pipeline-section-update';
import PipelineSectionDeleteDialog from './pipeline-section-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PipelineSectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PipelineSectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PipelineSectionDetail} />
      <ErrorBoundaryRoute path={match.url} component={PipelineSection} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PipelineSectionDeleteDialog} />
  </>
);

export default Routes;
