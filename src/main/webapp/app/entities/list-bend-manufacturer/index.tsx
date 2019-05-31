import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBendManufacturer from './list-bend-manufacturer';
import ListBendManufacturerDetail from './list-bend-manufacturer-detail';
import ListBendManufacturerUpdate from './list-bend-manufacturer-update';
import ListBendManufacturerDeleteDialog from './list-bend-manufacturer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBendManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBendManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBendManufacturerDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBendManufacturer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBendManufacturerDeleteDialog} />
  </>
);

export default Routes;
