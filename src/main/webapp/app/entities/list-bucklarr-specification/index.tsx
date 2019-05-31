import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBucklarrSpecification from './list-bucklarr-specification';
import ListBucklarrSpecificationDetail from './list-bucklarr-specification-detail';
import ListBucklarrSpecificationUpdate from './list-bucklarr-specification-update';
import ListBucklarrSpecificationDeleteDialog from './list-bucklarr-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBucklarrSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBucklarrSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBucklarrSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBucklarrSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBucklarrSpecificationDeleteDialog} />
  </>
);

export default Routes;
