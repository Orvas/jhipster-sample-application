import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListDfctPosType from './list-dfct-pos-type';
import ListDfctPosTypeDetail from './list-dfct-pos-type-detail';
import ListDfctPosTypeUpdate from './list-dfct-pos-type-update';
import ListDfctPosTypeDeleteDialog from './list-dfct-pos-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListDfctPosTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListDfctPosTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListDfctPosTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListDfctPosType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListDfctPosTypeDeleteDialog} />
  </>
);

export default Routes;
