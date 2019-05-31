import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListRiskConsequence from './list-risk-consequence';
import ListRiskConsequenceDetail from './list-risk-consequence-detail';
import ListRiskConsequenceUpdate from './list-risk-consequence-update';
import ListRiskConsequenceDeleteDialog from './list-risk-consequence-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListRiskConsequenceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListRiskConsequenceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListRiskConsequenceDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListRiskConsequence} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListRiskConsequenceDeleteDialog} />
  </>
);

export default Routes;
