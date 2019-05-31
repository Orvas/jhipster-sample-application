import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListClcLvl from './list-clc-lvl';
import ListClcLvlDetail from './list-clc-lvl-detail';
import ListClcLvlUpdate from './list-clc-lvl-update';
import ListClcLvlDeleteDialog from './list-clc-lvl-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListClcLvlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListClcLvlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListClcLvlDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListClcLvl} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListClcLvlDeleteDialog} />
  </>
);

export default Routes;
