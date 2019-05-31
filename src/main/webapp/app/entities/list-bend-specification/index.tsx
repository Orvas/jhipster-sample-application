import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListBendSpecification from './list-bend-specification';
import ListBendSpecificationDetail from './list-bend-specification-detail';
import ListBendSpecificationUpdate from './list-bend-specification-update';
import ListBendSpecificationDeleteDialog from './list-bend-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListBendSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListBendSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListBendSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListBendSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListBendSpecificationDeleteDialog} />
  </>
);

export default Routes;
