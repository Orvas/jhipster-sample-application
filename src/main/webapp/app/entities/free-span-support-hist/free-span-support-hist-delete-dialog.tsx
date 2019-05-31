import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFreeSpanSupportHist } from 'app/shared/model/free-span-support-hist.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './free-span-support-hist.reducer';

export interface IFreeSpanSupportHistDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FreeSpanSupportHistDeleteDialog extends React.Component<IFreeSpanSupportHistDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.freeSpanSupportHistEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { freeSpanSupportHistEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="jhipsterSampleApplicationApp.freeSpanSupportHist.delete.question">
          Are you sure you want to delete this FreeSpanSupportHist?
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-freeSpanSupportHist" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ freeSpanSupportHist }: IRootState) => ({
  freeSpanSupportHistEntity: freeSpanSupportHist.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FreeSpanSupportHistDeleteDialog);
