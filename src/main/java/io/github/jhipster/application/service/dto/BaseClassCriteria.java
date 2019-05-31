package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.BaseClass} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.BaseClassResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /base-classes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BaseClassCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idTypeId;

    private LongFilter pipejointId;

    private LongFilter anodeId;

    private LongFilter bendId;

    private LongFilter buckleArrestorId;

    private LongFilter cpsId;

    private LongFilter displacementId;

    private LongFilter freeSpanId;

    private LongFilter freeSpanSupportId;

    private LongFilter launchReceiverId;

    private LongFilter pipeId;

    private LongFilter pipelineId;

    private LongFilter pipelineSectionId;

    private LongFilter teeId;

    private LongFilter valveId;

    public BaseClassCriteria(){
    }

    public BaseClassCriteria(BaseClassCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idTypeId = other.idTypeId == null ? null : other.idTypeId.copy();
        this.pipejointId = other.pipejointId == null ? null : other.pipejointId.copy();
        this.anodeId = other.anodeId == null ? null : other.anodeId.copy();
        this.bendId = other.bendId == null ? null : other.bendId.copy();
        this.buckleArrestorId = other.buckleArrestorId == null ? null : other.buckleArrestorId.copy();
        this.cpsId = other.cpsId == null ? null : other.cpsId.copy();
        this.displacementId = other.displacementId == null ? null : other.displacementId.copy();
        this.freeSpanId = other.freeSpanId == null ? null : other.freeSpanId.copy();
        this.freeSpanSupportId = other.freeSpanSupportId == null ? null : other.freeSpanSupportId.copy();
        this.launchReceiverId = other.launchReceiverId == null ? null : other.launchReceiverId.copy();
        this.pipeId = other.pipeId == null ? null : other.pipeId.copy();
        this.pipelineId = other.pipelineId == null ? null : other.pipelineId.copy();
        this.pipelineSectionId = other.pipelineSectionId == null ? null : other.pipelineSectionId.copy();
        this.teeId = other.teeId == null ? null : other.teeId.copy();
        this.valveId = other.valveId == null ? null : other.valveId.copy();
    }

    @Override
    public BaseClassCriteria copy() {
        return new BaseClassCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(InstantFilter dateCreate) {
        this.dateCreate = dateCreate;
    }

    public InstantFilter getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(InstantFilter dateEdit) {
        this.dateEdit = dateEdit;
    }

    public StringFilter getCreator() {
        return creator;
    }

    public void setCreator(StringFilter creator) {
        this.creator = creator;
    }

    public StringFilter getEditor() {
        return editor;
    }

    public void setEditor(StringFilter editor) {
        this.editor = editor;
    }

    public LongFilter getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(LongFilter idTypeId) {
        this.idTypeId = idTypeId;
    }

    public LongFilter getPipejointId() {
        return pipejointId;
    }

    public void setPipejointId(LongFilter pipejointId) {
        this.pipejointId = pipejointId;
    }

    public LongFilter getAnodeId() {
        return anodeId;
    }

    public void setAnodeId(LongFilter anodeId) {
        this.anodeId = anodeId;
    }

    public LongFilter getBendId() {
        return bendId;
    }

    public void setBendId(LongFilter bendId) {
        this.bendId = bendId;
    }

    public LongFilter getBuckleArrestorId() {
        return buckleArrestorId;
    }

    public void setBuckleArrestorId(LongFilter buckleArrestorId) {
        this.buckleArrestorId = buckleArrestorId;
    }

    public LongFilter getCpsId() {
        return cpsId;
    }

    public void setCpsId(LongFilter cpsId) {
        this.cpsId = cpsId;
    }

    public LongFilter getDisplacementId() {
        return displacementId;
    }

    public void setDisplacementId(LongFilter displacementId) {
        this.displacementId = displacementId;
    }

    public LongFilter getFreeSpanId() {
        return freeSpanId;
    }

    public void setFreeSpanId(LongFilter freeSpanId) {
        this.freeSpanId = freeSpanId;
    }

    public LongFilter getFreeSpanSupportId() {
        return freeSpanSupportId;
    }

    public void setFreeSpanSupportId(LongFilter freeSpanSupportId) {
        this.freeSpanSupportId = freeSpanSupportId;
    }

    public LongFilter getLaunchReceiverId() {
        return launchReceiverId;
    }

    public void setLaunchReceiverId(LongFilter launchReceiverId) {
        this.launchReceiverId = launchReceiverId;
    }

    public LongFilter getPipeId() {
        return pipeId;
    }

    public void setPipeId(LongFilter pipeId) {
        this.pipeId = pipeId;
    }

    public LongFilter getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(LongFilter pipelineId) {
        this.pipelineId = pipelineId;
    }

    public LongFilter getPipelineSectionId() {
        return pipelineSectionId;
    }

    public void setPipelineSectionId(LongFilter pipelineSectionId) {
        this.pipelineSectionId = pipelineSectionId;
    }

    public LongFilter getTeeId() {
        return teeId;
    }

    public void setTeeId(LongFilter teeId) {
        this.teeId = teeId;
    }

    public LongFilter getValveId() {
        return valveId;
    }

    public void setValveId(LongFilter valveId) {
        this.valveId = valveId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BaseClassCriteria that = (BaseClassCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idTypeId, that.idTypeId) &&
            Objects.equals(pipejointId, that.pipejointId) &&
            Objects.equals(anodeId, that.anodeId) &&
            Objects.equals(bendId, that.bendId) &&
            Objects.equals(buckleArrestorId, that.buckleArrestorId) &&
            Objects.equals(cpsId, that.cpsId) &&
            Objects.equals(displacementId, that.displacementId) &&
            Objects.equals(freeSpanId, that.freeSpanId) &&
            Objects.equals(freeSpanSupportId, that.freeSpanSupportId) &&
            Objects.equals(launchReceiverId, that.launchReceiverId) &&
            Objects.equals(pipeId, that.pipeId) &&
            Objects.equals(pipelineId, that.pipelineId) &&
            Objects.equals(pipelineSectionId, that.pipelineSectionId) &&
            Objects.equals(teeId, that.teeId) &&
            Objects.equals(valveId, that.valveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idTypeId,
        pipejointId,
        anodeId,
        bendId,
        buckleArrestorId,
        cpsId,
        displacementId,
        freeSpanId,
        freeSpanSupportId,
        launchReceiverId,
        pipeId,
        pipelineId,
        pipelineSectionId,
        teeId,
        valveId
        );
    }

    @Override
    public String toString() {
        return "BaseClassCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idTypeId != null ? "idTypeId=" + idTypeId + ", " : "") +
                (pipejointId != null ? "pipejointId=" + pipejointId + ", " : "") +
                (anodeId != null ? "anodeId=" + anodeId + ", " : "") +
                (bendId != null ? "bendId=" + bendId + ", " : "") +
                (buckleArrestorId != null ? "buckleArrestorId=" + buckleArrestorId + ", " : "") +
                (cpsId != null ? "cpsId=" + cpsId + ", " : "") +
                (displacementId != null ? "displacementId=" + displacementId + ", " : "") +
                (freeSpanId != null ? "freeSpanId=" + freeSpanId + ", " : "") +
                (freeSpanSupportId != null ? "freeSpanSupportId=" + freeSpanSupportId + ", " : "") +
                (launchReceiverId != null ? "launchReceiverId=" + launchReceiverId + ", " : "") +
                (pipeId != null ? "pipeId=" + pipeId + ", " : "") +
                (pipelineId != null ? "pipelineId=" + pipelineId + ", " : "") +
                (pipelineSectionId != null ? "pipelineSectionId=" + pipelineSectionId + ", " : "") +
                (teeId != null ? "teeId=" + teeId + ", " : "") +
                (valveId != null ? "valveId=" + valveId + ", " : "") +
            "}";
    }

}
