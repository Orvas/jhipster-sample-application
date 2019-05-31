import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILaunchReceiverHist } from 'app/shared/model/launch-receiver-hist.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './launch-receiver-hist.reducer';

export interface ILaunchReceiverHistDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LaunchReceiverHistDeleteDialog extends React.Component<ILaunchReceiverHistDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.launchReceiverHistEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { launchReceiverHistEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="jhipsterSampleApplicationApp.launchReceiverHist.delete.question">
          Are you sure you want to delete this LaunchReceiverHist?
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-launchReceiverHist" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ launchReceiverHist }: IRootState) => ({
  launchReceiverHistEntity: launchReceiverHist.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LaunchReceiverHistDeleteDialog);
