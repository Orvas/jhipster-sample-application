import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListTeeSpecification from './list-tee-specification';
import ListTeeSpecificationDetail from './list-tee-specification-detail';
import ListTeeSpecificationUpdate from './list-tee-specification-update';
import ListTeeSpecificationDeleteDialog from './list-tee-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListTeeSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListTeeSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListTeeSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListTeeSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListTeeSpecificationDeleteDialog} />
  </>
);

export default Routes;
