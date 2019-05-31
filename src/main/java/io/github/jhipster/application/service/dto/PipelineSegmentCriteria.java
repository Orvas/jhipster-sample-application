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

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.PipelineSegment} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PipelineSegmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pipeline-segments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PipelineSegmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter num;

    private StringFilter name;

    private BigDecimalFilter kpStart1;

    private BigDecimalFilter kpEnd1;

    private BigDecimalFilter kpStart4;

    private BigDecimalFilter kpEnd4;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    public PipelineSegmentCriteria(){
    }

    public PipelineSegmentCriteria(PipelineSegmentCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.num = other.num == null ? null : other.num.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.kpStart1 = other.kpStart1 == null ? null : other.kpStart1.copy();
        this.kpEnd1 = other.kpEnd1 == null ? null : other.kpEnd1.copy();
        this.kpStart4 = other.kpStart4 == null ? null : other.kpStart4.copy();
        this.kpEnd4 = other.kpEnd4 == null ? null : other.kpEnd4.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
    }

    @Override
    public PipelineSegmentCriteria copy() {
        return new PipelineSegmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNum() {
        return num;
    }

    public void setNum(StringFilter num) {
        this.num = num;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BigDecimalFilter getKpStart1() {
        return kpStart1;
    }

    public void setKpStart1(BigDecimalFilter kpStart1) {
        this.kpStart1 = kpStart1;
    }

    public BigDecimalFilter getKpEnd1() {
        return kpEnd1;
    }

    public void setKpEnd1(BigDecimalFilter kpEnd1) {
        this.kpEnd1 = kpEnd1;
    }

    public BigDecimalFilter getKpStart4() {
        return kpStart4;
    }

    public void setKpStart4(BigDecimalFilter kpStart4) {
        this.kpStart4 = kpStart4;
    }

    public BigDecimalFilter getKpEnd4() {
        return kpEnd4;
    }

    public void setKpEnd4(BigDecimalFilter kpEnd4) {
        this.kpEnd4 = kpEnd4;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PipelineSegmentCriteria that = (PipelineSegmentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(num, that.num) &&
            Objects.equals(name, that.name) &&
            Objects.equals(kpStart1, that.kpStart1) &&
            Objects.equals(kpEnd1, that.kpEnd1) &&
            Objects.equals(kpStart4, that.kpStart4) &&
            Objects.equals(kpEnd4, that.kpEnd4) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        num,
        name,
        kpStart1,
        kpEnd1,
        kpStart4,
        kpEnd4,
        dateCreate,
        dateEdit,
        creator,
        editor
        );
    }

    @Override
    public String toString() {
        return "PipelineSegmentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (num != null ? "num=" + num + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (kpStart1 != null ? "kpStart1=" + kpStart1 + ", " : "") +
                (kpEnd1 != null ? "kpEnd1=" + kpEnd1 + ", " : "") +
                (kpStart4 != null ? "kpStart4=" + kpStart4 + ", " : "") +
                (kpEnd4 != null ? "kpEnd4=" + kpEnd4 + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
            "}";
    }

}
