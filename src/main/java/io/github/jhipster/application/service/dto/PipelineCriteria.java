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
 * Criteria class for the {@link io.github.jhipster.application.domain.Pipeline} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PipelineResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pipelines?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PipelineCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idId;

    private LongFilter launchReceiverHistId;

    private LongFilter pipelineHistId;

    private LongFilter pipelineSectionId;

    public PipelineCriteria(){
    }

    public PipelineCriteria(PipelineCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.launchReceiverHistId = other.launchReceiverHistId == null ? null : other.launchReceiverHistId.copy();
        this.pipelineHistId = other.pipelineHistId == null ? null : other.pipelineHistId.copy();
        this.pipelineSectionId = other.pipelineSectionId == null ? null : other.pipelineSectionId.copy();
    }

    @Override
    public PipelineCriteria copy() {
        return new PipelineCriteria(this);
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

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getLaunchReceiverHistId() {
        return launchReceiverHistId;
    }

    public void setLaunchReceiverHistId(LongFilter launchReceiverHistId) {
        this.launchReceiverHistId = launchReceiverHistId;
    }

    public LongFilter getPipelineHistId() {
        return pipelineHistId;
    }

    public void setPipelineHistId(LongFilter pipelineHistId) {
        this.pipelineHistId = pipelineHistId;
    }

    public LongFilter getPipelineSectionId() {
        return pipelineSectionId;
    }

    public void setPipelineSectionId(LongFilter pipelineSectionId) {
        this.pipelineSectionId = pipelineSectionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PipelineCriteria that = (PipelineCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(launchReceiverHistId, that.launchReceiverHistId) &&
            Objects.equals(pipelineHistId, that.pipelineHistId) &&
            Objects.equals(pipelineSectionId, that.pipelineSectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idId,
        launchReceiverHistId,
        pipelineHistId,
        pipelineSectionId
        );
    }

    @Override
    public String toString() {
        return "PipelineCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (launchReceiverHistId != null ? "launchReceiverHistId=" + launchReceiverHistId + ", " : "") +
                (pipelineHistId != null ? "pipelineHistId=" + pipelineHistId + ", " : "") +
                (pipelineSectionId != null ? "pipelineSectionId=" + pipelineSectionId + ", " : "") +
            "}";
    }

}
