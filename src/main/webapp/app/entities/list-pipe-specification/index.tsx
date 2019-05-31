import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ListPipeSpecification from './list-pipe-specification';
import ListPipeSpecificationDetail from './list-pipe-specification-detail';
import ListPipeSpecificationUpdate from './list-pipe-specification-update';
import ListPipeSpecificationDeleteDialog from './list-pipe-specification-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ListPipeSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ListPipeSpecificationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ListPipeSpecificationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ListPipeSpecification} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ListPipeSpecificationDeleteDialog} />
  </>
);

export default Routes;
