import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListObjectStatus from './list-object-status';
import PipelineSection from './pipeline-section';
import FreeSpanHistory from './free-span-history';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/list-object-status`} component={ListObjectStatus} />
      <ErrorBoundaryRoute path={`${match.url}/pipeline-section`} component={PipelineSection} />
      <ErrorBoundaryRoute path={`${match.url}/free-span-history`} component={FreeSpanHistory} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
