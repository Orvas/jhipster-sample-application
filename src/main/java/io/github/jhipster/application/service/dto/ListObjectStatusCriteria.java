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
 * Criteria class for the {@link io.github.jhipster.application.domain.ListObjectStatus} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ListObjectStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /list-object-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ListObjectStatusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter fullName;

    private IntegerFilter isCurrentFlag;

    private StringFilter description;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter bendHistId;

    private LongFilter cpsHistId;

    private LongFilter freeSpanHistId;

    private LongFilter freeSpanSupportHistId;

    private LongFilter launchReceiverHistId;

    private LongFilter pipeHistId;

    private LongFilter pipejointHistId;

    private LongFilter pipelineHistId;

    private LongFilter teeHistId;

    private LongFilter valveHistId;

    public ListObjectStatusCriteria(){
    }

    public ListObjectStatusCriteria(ListObjectStatusCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.bendHistId = other.bendHistId == null ? null : other.bendHistId.copy();
        this.cpsHistId = other.cpsHistId == null ? null : other.cpsHistId.copy();
        this.freeSpanHistId = other.freeSpanHistId == null ? null : other.freeSpanHistId.copy();
        this.freeSpanSupportHistId = other.freeSpanSupportHistId == null ? null : other.freeSpanSupportHistId.copy();
        this.launchReceiverHistId = other.launchReceiverHistId == null ? null : other.launchReceiverHistId.copy();
        this.pipeHistId = other.pipeHistId == null ? null : other.pipeHistId.copy();
        this.pipejointHistId = other.pipejointHistId == null ? null : other.pipejointHistId.copy();
        this.pipelineHistId = other.pipelineHistId == null ? null : other.pipelineHistId.copy();
        this.teeHistId = other.teeHistId == null ? null : other.teeHistId.copy();
        this.valveHistId = other.valveHistId == null ? null : other.valveHistId.copy();
    }

    @Override
    public ListObjectStatusCriteria copy() {
        return new ListObjectStatusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public IntegerFilter getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(IntegerFilter isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public LongFilter getBendHistId() {
        return bendHistId;
    }

    public void setBendHistId(LongFilter bendHistId) {
        this.bendHistId = bendHistId;
    }

    public LongFilter getCpsHistId() {
        return cpsHistId;
    }

    public void setCpsHistId(LongFilter cpsHistId) {
        this.cpsHistId = cpsHistId;
    }

    public LongFilter getFreeSpanHistId() {
        return freeSpanHistId;
    }

    public void setFreeSpanHistId(LongFilter freeSpanHistId) {
        this.freeSpanHistId = freeSpanHistId;
    }

    public LongFilter getFreeSpanSupportHistId() {
        return freeSpanSupportHistId;
    }

    public void setFreeSpanSupportHistId(LongFilter freeSpanSupportHistId) {
        this.freeSpanSupportHistId = freeSpanSupportHistId;
    }

    public LongFilter getLaunchReceiverHistId() {
        return launchReceiverHistId;
    }

    public void setLaunchReceiverHistId(LongFilter launchReceiverHistId) {
        this.launchReceiverHistId = launchReceiverHistId;
    }

    public LongFilter getPipeHistId() {
        return pipeHistId;
    }

    public void setPipeHistId(LongFilter pipeHistId) {
        this.pipeHistId = pipeHistId;
    }

    public LongFilter getPipejointHistId() {
        return pipejointHistId;
    }

    public void setPipejointHistId(LongFilter pipejointHistId) {
        this.pipejointHistId = pipejointHistId;
    }

    public LongFilter getPipelineHistId() {
        return pipelineHistId;
    }

    public void setPipelineHistId(LongFilter pipelineHistId) {
        this.pipelineHistId = pipelineHistId;
    }

    public LongFilter getTeeHistId() {
        return teeHistId;
    }

    public void setTeeHistId(LongFilter teeHistId) {
        this.teeHistId = teeHistId;
    }

    public LongFilter getValveHistId() {
        return valveHistId;
    }

    public void setValveHistId(LongFilter valveHistId) {
        this.valveHistId = valveHistId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ListObjectStatusCriteria that = (ListObjectStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(bendHistId, that.bendHistId) &&
            Objects.equals(cpsHistId, that.cpsHistId) &&
            Objects.equals(freeSpanHistId, that.freeSpanHistId) &&
            Objects.equals(freeSpanSupportHistId, that.freeSpanSupportHistId) &&
            Objects.equals(launchReceiverHistId, that.launchReceiverHistId) &&
            Objects.equals(pipeHistId, that.pipeHistId) &&
            Objects.equals(pipejointHistId, that.pipejointHistId) &&
            Objects.equals(pipelineHistId, that.pipelineHistId) &&
            Objects.equals(teeHistId, that.teeHistId) &&
            Objects.equals(valveHistId, that.valveHistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        name,
        fullName,
        isCurrentFlag,
        description,
        dateCreate,
        dateEdit,
        creator,
        editor,
        bendHistId,
        cpsHistId,
        freeSpanHistId,
        freeSpanSupportHistId,
        launchReceiverHistId,
        pipeHistId,
        pipejointHistId,
        pipelineHistId,
        teeHistId,
        valveHistId
        );
    }

    @Override
    public String toString() {
        return "ListObjectStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (bendHistId != null ? "bendHistId=" + bendHistId + ", " : "") +
                (cpsHistId != null ? "cpsHistId=" + cpsHistId + ", " : "") +
                (freeSpanHistId != null ? "freeSpanHistId=" + freeSpanHistId + ", " : "") +
                (freeSpanSupportHistId != null ? "freeSpanSupportHistId=" + freeSpanSupportHistId + ", " : "") +
                (launchReceiverHistId != null ? "launchReceiverHistId=" + launchReceiverHistId + ", " : "") +
                (pipeHistId != null ? "pipeHistId=" + pipeHistId + ", " : "") +
                (pipejointHistId != null ? "pipejointHistId=" + pipejointHistId + ", " : "") +
                (pipelineHistId != null ? "pipelineHistId=" + pipelineHistId + ", " : "") +
                (teeHistId != null ? "teeHistId=" + teeHistId + ", " : "") +
                (valveHistId != null ? "valveHistId=" + valveHistId + ", " : "") +
            "}";
    }

}
