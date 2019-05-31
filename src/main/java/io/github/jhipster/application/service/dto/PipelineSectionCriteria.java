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
 * Criteria class for the {@link io.github.jhipster.application.domain.PipelineSection} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PipelineSectionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pipeline-sections?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PipelineSectionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private IntegerFilter isOnshore;

    private BigDecimalFilter kpStart;

    private BigDecimalFilter kpEnd;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter baseClassId;

    private LongFilter idPipelineId;

    private LongFilter idSafetyClassId;

    private LongFilter anodeHistId;

    private LongFilter bendHistId;

    private LongFilter buckleArrestorHistId;

    private LongFilter cpsHistId;

    private LongFilter cpsRangeId;

    private LongFilter displacementHistId;

    private LongFilter freeSpanHistId;

    private LongFilter freeSpanSupportHistId;

    private LongFilter pipeHistId;

    private LongFilter teeHistId;

    private LongFilter valveHistId;

    public PipelineSectionCriteria(){
    }

    public PipelineSectionCriteria(PipelineSectionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.isOnshore = other.isOnshore == null ? null : other.isOnshore.copy();
        this.kpStart = other.kpStart == null ? null : other.kpStart.copy();
        this.kpEnd = other.kpEnd == null ? null : other.kpEnd.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.baseClassId = other.baseClassId == null ? null : other.baseClassId.copy();
        this.idPipelineId = other.idPipelineId == null ? null : other.idPipelineId.copy();
        this.idSafetyClassId = other.idSafetyClassId == null ? null : other.idSafetyClassId.copy();
        this.anodeHistId = other.anodeHistId == null ? null : other.anodeHistId.copy();
        this.bendHistId = other.bendHistId == null ? null : other.bendHistId.copy();
        this.buckleArrestorHistId = other.buckleArrestorHistId == null ? null : other.buckleArrestorHistId.copy();
        this.cpsHistId = other.cpsHistId == null ? null : other.cpsHistId.copy();
        this.cpsRangeId = other.cpsRangeId == null ? null : other.cpsRangeId.copy();
        this.displacementHistId = other.displacementHistId == null ? null : other.displacementHistId.copy();
        this.freeSpanHistId = other.freeSpanHistId == null ? null : other.freeSpanHistId.copy();
        this.freeSpanSupportHistId = other.freeSpanSupportHistId == null ? null : other.freeSpanSupportHistId.copy();
        this.pipeHistId = other.pipeHistId == null ? null : other.pipeHistId.copy();
        this.teeHistId = other.teeHistId == null ? null : other.teeHistId.copy();
        this.valveHistId = other.valveHistId == null ? null : other.valveHistId.copy();
    }

    @Override
    public PipelineSectionCriteria copy() {
        return new PipelineSectionCriteria(this);
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

    public IntegerFilter getIsOnshore() {
        return isOnshore;
    }

    public void setIsOnshore(IntegerFilter isOnshore) {
        this.isOnshore = isOnshore;
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

    public LongFilter getIdPipelineId() {
        return idPipelineId;
    }

    public void setIdPipelineId(LongFilter idPipelineId) {
        this.idPipelineId = idPipelineId;
    }

    public LongFilter getIdSafetyClassId() {
        return idSafetyClassId;
    }

    public void setIdSafetyClassId(LongFilter idSafetyClassId) {
        this.idSafetyClassId = idSafetyClassId;
    }

    public LongFilter getAnodeHistId() {
        return anodeHistId;
    }

    public void setAnodeHistId(LongFilter anodeHistId) {
        this.anodeHistId = anodeHistId;
    }

    public LongFilter getBendHistId() {
        return bendHistId;
    }

    public void setBendHistId(LongFilter bendHistId) {
        this.bendHistId = bendHistId;
    }

    public LongFilter getBuckleArrestorHistId() {
        return buckleArrestorHistId;
    }

    public void setBuckleArrestorHistId(LongFilter buckleArrestorHistId) {
        this.buckleArrestorHistId = buckleArrestorHistId;
    }

    public LongFilter getCpsHistId() {
        return cpsHistId;
    }

    public void setCpsHistId(LongFilter cpsHistId) {
        this.cpsHistId = cpsHistId;
    }

    public LongFilter getCpsRangeId() {
        return cpsRangeId;
    }

    public void setCpsRangeId(LongFilter cpsRangeId) {
        this.cpsRangeId = cpsRangeId;
    }

    public LongFilter getDisplacementHistId() {
        return displacementHistId;
    }

    public void setDisplacementHistId(LongFilter displacementHistId) {
        this.displacementHistId = displacementHistId;
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

    public LongFilter getPipeHistId() {
        return pipeHistId;
    }

    public void setPipeHistId(LongFilter pipeHistId) {
        this.pipeHistId = pipeHistId;
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
        final PipelineSectionCriteria that = (PipelineSectionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(isOnshore, that.isOnshore) &&
            Objects.equals(kpStart, that.kpStart) &&
            Objects.equals(kpEnd, that.kpEnd) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(baseClassId, that.baseClassId) &&
            Objects.equals(idPipelineId, that.idPipelineId) &&
            Objects.equals(idSafetyClassId, that.idSafetyClassId) &&
            Objects.equals(anodeHistId, that.anodeHistId) &&
            Objects.equals(bendHistId, that.bendHistId) &&
            Objects.equals(buckleArrestorHistId, that.buckleArrestorHistId) &&
            Objects.equals(cpsHistId, that.cpsHistId) &&
            Objects.equals(cpsRangeId, that.cpsRangeId) &&
            Objects.equals(displacementHistId, that.displacementHistId) &&
            Objects.equals(freeSpanHistId, that.freeSpanHistId) &&
            Objects.equals(freeSpanSupportHistId, that.freeSpanSupportHistId) &&
            Objects.equals(pipeHistId, that.pipeHistId) &&
            Objects.equals(teeHistId, that.teeHistId) &&
            Objects.equals(valveHistId, that.valveHistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        isOnshore,
        kpStart,
        kpEnd,
        dateCreate,
        dateEdit,
        creator,
        editor,
        baseClassId,
        idPipelineId,
        idSafetyClassId,
        anodeHistId,
        bendHistId,
        buckleArrestorHistId,
        cpsHistId,
        cpsRangeId,
        displacementHistId,
        freeSpanHistId,
        freeSpanSupportHistId,
        pipeHistId,
        teeHistId,
        valveHistId
        );
    }

    @Override
    public String toString() {
        return "PipelineSectionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (isOnshore != null ? "isOnshore=" + isOnshore + ", " : "") +
                (kpStart != null ? "kpStart=" + kpStart + ", " : "") +
                (kpEnd != null ? "kpEnd=" + kpEnd + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (baseClassId != null ? "baseClassId=" + baseClassId + ", " : "") +
                (idPipelineId != null ? "idPipelineId=" + idPipelineId + ", " : "") +
                (idSafetyClassId != null ? "idSafetyClassId=" + idSafetyClassId + ", " : "") +
                (anodeHistId != null ? "anodeHistId=" + anodeHistId + ", " : "") +
                (bendHistId != null ? "bendHistId=" + bendHistId + ", " : "") +
                (buckleArrestorHistId != null ? "buckleArrestorHistId=" + buckleArrestorHistId + ", " : "") +
                (cpsHistId != null ? "cpsHistId=" + cpsHistId + ", " : "") +
                (cpsRangeId != null ? "cpsRangeId=" + cpsRangeId + ", " : "") +
                (displacementHistId != null ? "displacementHistId=" + displacementHistId + ", " : "") +
                (freeSpanHistId != null ? "freeSpanHistId=" + freeSpanHistId + ", " : "") +
                (freeSpanSupportHistId != null ? "freeSpanSupportHistId=" + freeSpanSupportHistId + ", " : "") +
                (pipeHistId != null ? "pipeHistId=" + pipeHistId + ", " : "") +
                (teeHistId != null ? "teeHistId=" + teeHistId + ", " : "") +
                (valveHistId != null ? "valveHistId=" + valveHistId + ", " : "") +
            "}";
    }

}
