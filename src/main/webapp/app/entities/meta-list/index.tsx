import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MetaList from './meta-list';
import MetaListDetail from './meta-list-detail';
import MetaListUpdate from './meta-list-update';
import MetaListDeleteDialog from './meta-list-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MetaListUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MetaListUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MetaListDetail} />
      <ErrorBoundaryRoute path={match.url} component={MetaList} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MetaListDeleteDialog} />
  </>
);

export default Routes;
