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
 * Criteria class for the {@link io.github.jhipster.application.domain.PipejointHist} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PipejointHistResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pipejoint-hists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PipejointHistCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private StringFilter name;

    private BigDecimalFilter externalCoatThickness;

    private StringFilter coord;

    private IntegerFilter isCurrentFlag;

    private StringFilter description;

    private StringFilter comment;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idId;

    private LongFilter idTypeId;

    private LongFilter idExternalCoatTypeId;

    private LongFilter idMaterialId;

    private LongFilter idSpecificationId;

    private LongFilter idStatusId;

    public PipejointHistCriteria(){
    }

    public PipejointHistCriteria(PipejointHistCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.externalCoatThickness = other.externalCoatThickness == null ? null : other.externalCoatThickness.copy();
        this.coord = other.coord == null ? null : other.coord.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.idTypeId = other.idTypeId == null ? null : other.idTypeId.copy();
        this.idExternalCoatTypeId = other.idExternalCoatTypeId == null ? null : other.idExternalCoatTypeId.copy();
        this.idMaterialId = other.idMaterialId == null ? null : other.idMaterialId.copy();
        this.idSpecificationId = other.idSpecificationId == null ? null : other.idSpecificationId.copy();
        this.idStatusId = other.idStatusId == null ? null : other.idStatusId.copy();
    }

    @Override
    public PipejointHistCriteria copy() {
        return new PipejointHistCriteria(this);
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

    public BigDecimalFilter getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public void setExternalCoatThickness(BigDecimalFilter externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public StringFilter getCoord() {
        return coord;
    }

    public void setCoord(StringFilter coord) {
        this.coord = coord;
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

    public LongFilter getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(LongFilter idTypeId) {
        this.idTypeId = idTypeId;
    }

    public LongFilter getIdExternalCoatTypeId() {
        return idExternalCoatTypeId;
    }

    public void setIdExternalCoatTypeId(LongFilter idExternalCoatTypeId) {
        this.idExternalCoatTypeId = idExternalCoatTypeId;
    }

    public LongFilter getIdMaterialId() {
        return idMaterialId;
    }

    public void setIdMaterialId(LongFilter idMaterialId) {
        this.idMaterialId = idMaterialId;
    }

    public LongFilter getIdSpecificationId() {
        return idSpecificationId;
    }

    public void setIdSpecificationId(LongFilter idSpecificationId) {
        this.idSpecificationId = idSpecificationId;
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
        final PipejointHistCriteria that = (PipejointHistCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(name, that.name) &&
            Objects.equals(externalCoatThickness, that.externalCoatThickness) &&
            Objects.equals(coord, that.coord) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(description, that.description) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(idTypeId, that.idTypeId) &&
            Objects.equals(idExternalCoatTypeId, that.idExternalCoatTypeId) &&
            Objects.equals(idMaterialId, that.idMaterialId) &&
            Objects.equals(idSpecificationId, that.idSpecificationId) &&
            Objects.equals(idStatusId, that.idStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateFrom,
        dateTo,
        name,
        externalCoatThickness,
        coord,
        isCurrentFlag,
        description,
        comment,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idId,
        idTypeId,
        idExternalCoatTypeId,
        idMaterialId,
        idSpecificationId,
        idStatusId
        );
    }

    @Override
    public String toString() {
        return "PipejointHistCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (externalCoatThickness != null ? "externalCoatThickness=" + externalCoatThickness + ", " : "") +
                (coord != null ? "coord=" + coord + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (comment != null ? "comment=" + comment + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (idTypeId != null ? "idTypeId=" + idTypeId + ", " : "") +
                (idExternalCoatTypeId != null ? "idExternalCoatTypeId=" + idExternalCoatTypeId + ", " : "") +
                (idMaterialId != null ? "idMaterialId=" + idMaterialId + ", " : "") +
                (idSpecificationId != null ? "idSpecificationId=" + idSpecificationId + ", " : "") +
                (idStatusId != null ? "idStatusId=" + idStatusId + ", " : "") +
            "}";
    }

}
