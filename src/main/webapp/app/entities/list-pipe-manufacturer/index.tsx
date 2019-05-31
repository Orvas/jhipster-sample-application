import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListPipeManufacturer from './list-pipe-manufacturer';
import ListPipeManufacturerDetail from './list-pipe-manufacturer-detail';
import ListPipeManufacturerUpdate from './list-pipe-manufacturer-update';
import ListPipeManufacturerDeleteDialog from './list-pipe-manufacturer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListPipeManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListPipeManufacturerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListPipeManufacturerDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListPipeManufacturer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListPipeManufacturerDeleteDialog} />
  </>
);

export default Routes;
