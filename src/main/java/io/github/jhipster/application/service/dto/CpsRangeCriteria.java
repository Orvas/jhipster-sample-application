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
 * Criteria class for the {@link io.github.jhipster.application.domain.CpsRange} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.CpsRangeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cps-ranges?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CpsRangeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter kpStart;

    private BigDecimalFilter kpEnd;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idCpsId;

    private LongFilter idPipelineSectionId;

    public CpsRangeCriteria(){
    }

    public CpsRangeCriteria(CpsRangeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.kpStart = other.kpStart == null ? null : other.kpStart.copy();
        this.kpEnd = other.kpEnd == null ? null : other.kpEnd.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idCpsId = other.idCpsId == null ? null : other.idCpsId.copy();
        this.idPipelineSectionId = other.idPipelineSectionId == null ? null : other.idPipelineSectionId.copy();
    }

    @Override
    public CpsRangeCriteria copy() {
        return new CpsRangeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public LongFilter getIdCpsId() {
        return idCpsId;
    }

    public void setIdCpsId(LongFilter idCpsId) {
        this.idCpsId = idCpsId;
    }

    public LongFilter getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(LongFilter idPipelineSectionId) {
        this.idPipelineSectionId = idPipelineSectionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CpsRangeCriteria that = (CpsRangeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(kpStart, that.kpStart) &&
            Objects.equals(kpEnd, that.kpEnd) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idCpsId, that.idCpsId) &&
            Objects.equals(idPipelineSectionId, that.idPipelineSectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        kpStart,
        kpEnd,
        dateFrom,
        dateTo,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idCpsId,
        idPipelineSectionId
        );
    }

    @Override
    public String toString() {
        return "CpsRangeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (kpStart != null ? "kpStart=" + kpStart + ", " : "") +
                (kpEnd != null ? "kpEnd=" + kpEnd + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idCpsId != null ? "idCpsId=" + idCpsId + ", " : "") +
                (idPipelineSectionId != null ? "idPipelineSectionId=" + idPipelineSectionId + ", " : "") +
            "}";
    }

}
