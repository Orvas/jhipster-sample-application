import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListTeeManufacturer from './list-tee-manufacturer';
import ListTeeManufacturerDetail from './list-tee-manufacturer-detail';
import ListTeeManufacturerUpdate from './list-tee-manufacturer-update';
import ListTeeManufacturerDeleteDialog from './list-tee-manufacturer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListTeeManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListTeeManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListTeeManufacturerDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListTeeManufacturer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListTeeManufacturerDeleteDialog} />
  </>
);

export default Routes;
