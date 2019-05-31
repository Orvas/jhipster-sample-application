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
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.LaunchReceiverHist} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.LaunchReceiverHistResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /launch-receiver-hists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LaunchReceiverHistCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private StringFilter name;

    private StringFilter coord;

    private BigDecimalFilter kpInst;

    private IntegerFilter isCurrentFlag;

    private StringFilter comment;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idId;

    private LongFilter idPipelineId;

    private LongFilter idStatusId;

    public LaunchReceiverHistCriteria(){
    }

    public LaunchReceiverHistCriteria(LaunchReceiverHistCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.coord = other.coord == null ? null : other.coord.copy();
        this.kpInst = other.kpInst == null ? null : other.kpInst.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.idPipelineId = other.idPipelineId == null ? null : other.idPipelineId.copy();
        this.idStatusId = other.idStatusId == null ? null : other.idStatusId.copy();
    }

    @Override
    public LaunchReceiverHistCriteria copy() {
        return new LaunchReceiverHistCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateFilter dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateFilter getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateFilter dateTo) {
        this.dateTo = dateTo;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getCoord() {
        return coord;
    }

    public void setCoord(StringFilter coord) {
        this.coord = coord;
    }

    public BigDecimalFilter getKpInst() {
        return kpInst;
    }

    public void setKpInst(BigDecimalFilter kpInst) {
        this.kpInst = kpInst;
    }

    public IntegerFilter getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(IntegerFilter isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public StringFilter getComment() {
        return comment;
    }

    public void setComment(StringFilter comment) {
        this.comment = comment;
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

    public LongFilter getIdPipelineId() {
        return idPipelineId;
    }

    public void setIdPipelineId(LongFilter idPipelineId) {
        this.idPipelineId = idPipelineId;
    }

    public LongFilter getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(LongFilter idStatusId) {
        this.idStatusId = idStatusId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LaunchReceiverHistCriteria that = (LaunchReceiverHistCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(name, that.name) &&
            Objects.equals(coord, that.coord) &&
            Objects.equals(kpInst, that.kpInst) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(idPipelineId, that.idPipelineId) &&
            Objects.equals(idStatusId, that.idStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateFrom,
        dateTo,
        name,
        coord,
        kpInst,
        isCurrentFlag,
        comment,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idId,
        idPipelineId,
        idStatusId
        );
    }

    @Override
    public String toString() {
        return "LaunchReceiverHistCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (coord != null ? "coord=" + coord + ", " : "") +
                (kpInst != null ? "kpInst=" + kpInst + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (comment != null ? "comment=" + comment + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (idPipelineId != null ? "idPipelineId=" + idPipelineId + ", " : "") +
                (idStatusId != null ? "idStatusId=" + idStatusId + ", " : "") +
            "}";
    }

}
