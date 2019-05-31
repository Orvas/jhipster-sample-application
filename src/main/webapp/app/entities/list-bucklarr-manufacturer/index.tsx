import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBucklarrManufacturer from './list-bucklarr-manufacturer';
import ListBucklarrManufacturerDetail from './list-bucklarr-manufacturer-detail';
import ListBucklarrManufacturerUpdate from './list-bucklarr-manufacturer-update';
import ListBucklarrManufacturerDeleteDialog from './list-bucklarr-manufacturer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBucklarrManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBucklarrManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBucklarrManufacturerDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBucklarrManufacturer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBucklarrManufacturerDeleteDialog} />
  </>
);

export default Routes;
