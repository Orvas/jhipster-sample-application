import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListRiskProbability from './list-risk-probability';
import ListRiskProbabilityDetail from './list-risk-probability-detail';
import ListRiskProbabilityUpdate from './list-risk-probability-update';
import ListRiskProbabilityDeleteDialog from './list-risk-probability-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListRiskProbabilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListRiskProbabilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListRiskProbabilityDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListRiskProbability} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListRiskProbabilityDeleteDialog} />
  </>
);

export default Routes;
