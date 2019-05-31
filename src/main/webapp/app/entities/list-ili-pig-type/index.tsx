import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListIliPigType from './list-ili-pig-type';
import ListIliPigTypeDetail from './list-ili-pig-type-detail';
import ListIliPigTypeUpdate from './list-ili-pig-type-update';
import ListIliPigTypeDeleteDialog from './list-ili-pig-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListIliPigTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListIliPigTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListIliPigTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListIliPigType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListIliPigTypeDeleteDialog} />
  </>
);

export default Routes;
