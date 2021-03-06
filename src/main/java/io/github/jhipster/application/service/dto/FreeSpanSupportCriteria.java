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
 * Criteria class for the {@link io.github.jhipster.application.domain.FreeSpanSupport} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.FreeSpanSupportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /free-span-supports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FreeSpanSupportCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter baseClassId;

    private LongFilter freeSpanSupportHistId;

    public FreeSpanSupportCriteria(){
    }

    public FreeSpanSupportCriteria(FreeSpanSupportCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.baseClassId = other.baseClassId == null ? null : other.baseClassId.copy();
        this.freeSpanSupportHistId = other.freeSpanSupportHistId == null ? null : other.freeSpanSupportHistId.copy();
    }

    @Override
    public FreeSpanSupportCriteria copy() {
        return new FreeSpanSupportCriteria(this);
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

    public LongFilter getBaseClassId() {
        return baseClassId;
    }

    public void setBaseClassId(LongFilter baseClassId) {
        this.baseClassId = baseClassId;
    }

    public LongFilter getFreeSpanSupportHistId() {
        return freeSpanSupportHistId;
    }

    public void setFreeSpanSupportHistId(LongFilter freeSpanSupportHistId) {
        this.freeSpanSupportHistId = freeSpanSupportHistId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FreeSpanSupportCriteria that = (FreeSpanSupportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(baseClassId, that.baseClassId) &&
            Objects.equals(freeSpanSupportHistId, that.freeSpanSupportHistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateCreate,
        dateEdit,
        creator,
        editor,
        baseClassId,
        freeSpanSupportHistId
        );
    }

    @Override
    public String toString() {
        return "FreeSpanSupportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (baseClassId != null ? "baseClassId=" + baseClassId + ", " : "") +
                (freeSpanSupportHistId != null ? "freeSpanSupportHistId=" + freeSpanSupportHistId + ", " : "") +
            "}";
    }

}
