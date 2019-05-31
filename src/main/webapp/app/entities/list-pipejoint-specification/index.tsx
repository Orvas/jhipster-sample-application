import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListPipejointSpecification from './list-pipejoint-specification';
import ListPipejointSpecificationDetail from './list-pipejoint-specification-detail';
import ListPipejointSpecificationUpdate from './list-pipejoint-specification-update';
import ListPipejointSpecificationDeleteDialog from './list-pipejoint-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListPipejointSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListPipejointSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListPipejointSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListPipejointSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListPipejointSpecificationDeleteDialog} />
  </>
);

export default Routes;
