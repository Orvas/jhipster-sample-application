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
 * Criteria class for the {@link io.github.jhipster.application.domain.FreeSpanHist} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.FreeSpanHistResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /free-span-hists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FreeSpanHistCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private LongFilter idWrk;

    private BigDecimalFilter lenght;

    private BigDecimalFilter lenghtAllow;

    private BigDecimalFilter height;

    private IntegerFilter isMultispan;

    private BigDecimalFilter gap;

    private BigDecimalFilter kpStart;

    private BigDecimalFilter kpEnd;

    private IntegerFilter isCurrentFlag;

    private StringFilter comment;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idId;

    private LongFilter idPipelineSectionId;

    private LongFilter idStatusId;

    public FreeSpanHistCriteria(){
    }

    public FreeSpanHistCriteria(FreeSpanHistCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.idWrk = other.idWrk == null ? null : other.idWrk.copy();
        this.lenght = other.lenght == null ? null : other.lenght.copy();
        this.lenghtAllow = other.lenghtAllow == null ? null : other.lenghtAllow.copy();
        this.height = other.height == null ? null : other.height.copy();
        this.isMultispan = other.isMultispan == null ? null : other.isMultispan.copy();
        this.gap = other.gap == null ? null : other.gap.copy();
        this.kpStart = other.kpStart == null ? null : other.kpStart.copy();
        this.kpEnd = other.kpEnd == null ? null : other.kpEnd.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.idPipelineSectionId = other.idPipelineSectionId == null ? null : other.idPipelineSectionId.copy();
        this.idStatusId = other.idStatusId == null ? null : other.idStatusId.copy();
    }

    @Override
    public FreeSpanHistCriteria copy() {
        return new FreeSpanHistCriteria(this);
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

    public LongFilter getIdWrk() {
        return idWrk;
    }

    public void setIdWrk(LongFilter idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimalFilter getLenght() {
        return lenght;
    }

    public void setLenght(BigDecimalFilter lenght) {
        this.lenght = lenght;
    }

    public BigDecimalFilter getLenghtAllow() {
        return lenghtAllow;
    }

    public void setLenghtAllow(BigDecimalFilter lenghtAllow) {
        this.lenghtAllow = lenghtAllow;
    }

    public BigDecimalFilter getHeight() {
        return height;
    }

    public void setHeight(BigDecimalFilter height) {
        this.height = height;
    }

    public IntegerFilter getIsMultispan() {
        return isMultispan;
    }

    public void setIsMultispan(IntegerFilter isMultispan) {
        this.isMultispan = isMultispan;
    }

    public BigDecimalFilter getGap() {
        return gap;
    }

    public void setGap(BigDecimalFilter gap) {
        this.gap = gap;
    }

    public BigDecimalFilter getKpStart() {
        return kpStart;
    }

    public void setKpStart(BigDecimalFilter kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimalFilter getKpEnd() {
        return kpEnd;
    }

    public void setKpEnd(BigDecimalFilter kpEnd) {
        this.kpEnd = kpEnd;
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

    public LongFilter getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(LongFilter idPipelineSectionId) {
        this.idPipelineSectionId = idPipelineSectionId;
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
        final FreeSpanHistCriteria that = (FreeSpanHistCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(idWrk, that.idWrk) &&
            Objects.equals(lenght, that.lenght) &&
            Objects.equals(lenghtAllow, that.lenghtAllow) &&
            Objects.equals(height, that.height) &&
            Objects.equals(isMultispan, that.isMultispan) &&
            Objects.equals(gap, that.gap) &&
            Objects.equals(kpStart, that.kpStart) &&
            Objects.equals(kpEnd, that.kpEnd) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(idPipelineSectionId, that.idPipelineSectionId) &&
            Objects.equals(idStatusId, that.idStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateFrom,
        dateTo,
        idWrk,
        lenght,
        lenghtAllow,
        height,
        isMultispan,
        gap,
        kpStart,
        kpEnd,
        isCurrentFlag,
        comment,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idId,
        idPipelineSectionId,
        idStatusId
        );
    }

    @Override
    public String toString() {
        return "FreeSpanHistCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (idWrk != null ? "idWrk=" + idWrk + ", " : "") +
                (lenght != null ? "lenght=" + lenght + ", " : "") +
                (lenghtAllow != null ? "lenghtAllow=" + lenghtAllow + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (isMultispan != null ? "isMultispan=" + isMultispan + ", " : "") +
                (gap != null ? "gap=" + gap + ", " : "") +
                (kpStart != null ? "kpStart=" + kpStart + ", " : "") +
                (kpEnd != null ? "kpEnd=" + kpEnd + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (comment != null ? "comment=" + comment + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (idPipelineSectionId != null ? "idPipelineSectionId=" + idPipelineSectionId + ", " : "") +
                (idStatusId != null ? "idStatusId=" + idStatusId + ", " : "") +
            "}";
    }

}
