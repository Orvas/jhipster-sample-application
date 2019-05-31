import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListSteelGrade from './list-steel-grade';
import ListSteelGradeDetail from './list-steel-grade-detail';
import ListSteelGradeUpdate from './list-steel-grade-update';
import ListSteelGradeDeleteDialog from './list-steel-grade-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListSteelGradeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListSteelGradeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListSteelGradeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListSteelGrade} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListSteelGradeDeleteDialog} />
  </>
);

export default Routes;
