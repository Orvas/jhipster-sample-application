import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListValveManufacturer from './list-valve-manufacturer';
import ListValveManufacturerDetail from './list-valve-manufacturer-detail';
import ListValveManufacturerUpdate from './list-valve-manufacturer-update';
import ListValveManufacturerDeleteDialog from './list-valve-manufacturer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListValveManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListValveManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListValveManufacturerDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListValveManufacturer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListValveManufacturerDeleteDialog} />
  </>
);

export default Routes;
