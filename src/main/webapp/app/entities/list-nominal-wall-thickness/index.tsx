import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListNominalWallThickness from './list-nominal-wall-thickness';
import ListNominalWallThicknessDetail from './list-nominal-wall-thickness-detail';
import ListNominalWallThicknessUpdate from './list-nominal-wall-thickness-update';
import ListNominalWallThicknessDeleteDialog from './list-nominal-wall-thickness-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListNominalWallThicknessUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListNominalWallThicknessUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListNominalWallThicknessDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListNominalWallThickness} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListNominalWallThicknessDeleteDialog} />
  </>
);

export default Routes;
