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
 * Criteria class for the {@link io.github.jhipster.application.domain.MetaList} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.MetaListResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /meta-lists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MetaListCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter schemaName;

    private StringFilter tableName;

    private IntegerFilter isCurrentFlag;

    private StringFilter description;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    public MetaListCriteria(){
    }

    public MetaListCriteria(MetaListCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.schemaName = other.schemaName == null ? null : other.schemaName.copy();
        this.tableName = other.tableName == null ? null : other.tableName.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
    }

    @Override
    public MetaListCriteria copy() {
        return new MetaListCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(StringFilter schemaName) {
        this.schemaName = schemaName;
    }

    public StringFilter getTableName() {
        return tableName;
    }

    public void setTableName(StringFilter tableName) {
        this.tableName = tableName;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MetaListCriteria that = (MetaListCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(schemaName, that.schemaName) &&
            Objects.equals(tableName, that.tableName) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        schemaName,
        tableName,
        isCurrentFlag,
        description,
        dateCreate,
        dateEdit,
        creator,
        editor
        );
    }

    @Override
    public String toString() {
        return "MetaListCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (schemaName != null ? "schemaName=" + schemaName + ", " : "") +
                (tableName != null ? "tableName=" + tableName + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
            "}";
    }

}
