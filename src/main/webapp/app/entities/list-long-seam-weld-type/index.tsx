import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListLongSeamWeldType from './list-long-seam-weld-type';
import ListLongSeamWeldTypeDetail from './list-long-seam-weld-type-detail';
import ListLongSeamWeldTypeUpdate from './list-long-seam-weld-type-update';
import ListLongSeamWeldTypeDeleteDialog from './list-long-seam-weld-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListLongSeamWeldTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListLongSeamWeldTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListLongSeamWeldTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListLongSeamWeldType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListLongSeamWeldTypeDeleteDialog} />
  </>
);

export default Routes;
