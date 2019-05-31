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
 * Criteria class for the {@link io.github.jhipster.application.domain.AnodeHist} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.AnodeHistResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /anode-hists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AnodeHistCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private LongFilter idWrk;

    private IntegerFilter designLife;

    private BigDecimalFilter dmcd;

    private BigDecimalFilter l1;

    private BigDecimalFilter l2;

    private IntegerFilter length;

    private BigDecimalFilter electrCapac;

    private BigDecimalFilter designWeight;

    private BigDecimalFilter weight;

    private IntegerFilter isBurial;

    private StringFilter chemicalComposition;

    private BigDecimalFilter density;

    private IntegerFilter spacing;

    private IntegerFilter coatCutbackLength;

    private BigDecimalFilter coatDmgArea;

    private BigDecimalFilter h2sSoil;

    private BigDecimalFilter remain;

    private BigDecimalFilter intFldTemp;

    private BigDecimalFilter minPrc;

    private BigDecimalFilter thickness;

    private StringFilter coord;

    private BigDecimalFilter kpStart;

    private BigDecimalFilter coatThickness;

    private BigDecimalFilter kpEnd;

    private IntegerFilter isCurrentFlag;

    private StringFilter description;

    private StringFilter comment;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter idId;

    private LongFilter idPipelineSectionId;

    private LongFilter idBraceleteTypeId;

    private LongFilter idMaterialId;

    private LongFilter idStatusId;

    public AnodeHistCriteria(){
    }

    public AnodeHistCriteria(AnodeHistCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.idWrk = other.idWrk == null ? null : other.idWrk.copy();
        this.designLife = other.designLife == null ? null : other.designLife.copy();
        this.dmcd = other.dmcd == null ? null : other.dmcd.copy();
        this.l1 = other.l1 == null ? null : other.l1.copy();
        this.l2 = other.l2 == null ? null : other.l2.copy();
        this.length = other.length == null ? null : other.length.copy();
        this.electrCapac = other.electrCapac == null ? null : other.electrCapac.copy();
        this.designWeight = other.designWeight == null ? null : other.designWeight.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.isBurial = other.isBurial == null ? null : other.isBurial.copy();
        this.chemicalComposition = other.chemicalComposition == null ? null : other.chemicalComposition.copy();
        this.density = other.density == null ? null : other.density.copy();
        this.spacing = other.spacing == null ? null : other.spacing.copy();
        this.coatCutbackLength = other.coatCutbackLength == null ? null : other.coatCutbackLength.copy();
        this.coatDmgArea = other.coatDmgArea == null ? null : other.coatDmgArea.copy();
        this.h2sSoil = other.h2sSoil == null ? null : other.h2sSoil.copy();
        this.remain = other.remain == null ? null : other.remain.copy();
        this.intFldTemp = other.intFldTemp == null ? null : other.intFldTemp.copy();
        this.minPrc = other.minPrc == null ? null : other.minPrc.copy();
        this.thickness = other.thickness == null ? null : other.thickness.copy();
        this.coord = other.coord == null ? null : other.coord.copy();
        this.kpStart = other.kpStart == null ? null : other.kpStart.copy();
        this.coatThickness = other.coatThickness == null ? null : other.coatThickness.copy();
        this.kpEnd = other.kpEnd == null ? null : other.kpEnd.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.idPipelineSectionId = other.idPipelineSectionId == null ? null : other.idPipelineSectionId.copy();
        this.idBraceleteTypeId = other.idBraceleteTypeId == null ? null : other.idBraceleteTypeId.copy();
        this.idMaterialId = other.idMaterialId == null ? null : other.idMaterialId.copy();
        this.idStatusId = other.idStatusId == null ? null : other.idStatusId.copy();
    }

    @Override
    public AnodeHistCriteria copy() {
        return new AnodeHistCriteria(this);
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

    public IntegerFilter getDesignLife() {
        return designLife;
    }

    public void setDesignLife(IntegerFilter designLife) {
        this.designLife = designLife;
    }

    public BigDecimalFilter getDmcd() {
        return dmcd;
    }

    public void setDmcd(BigDecimalFilter dmcd) {
        this.dmcd = dmcd;
    }

    public BigDecimalFilter getl1() {
        return l1;
    }

    public void setl1(BigDecimalFilter l1) {
        this.l1 = l1;
    }

    public BigDecimalFilter getl2() {
        return l2;
    }

    public void setl2(BigDecimalFilter l2) {
        this.l2 = l2;
    }

    public IntegerFilter getLength() {
        return length;
    }

    public void setLength(IntegerFilter length) {
        this.length = length;
    }

    public BigDecimalFilter getElectrCapac() {
        return electrCapac;
    }

    public void setElectrCapac(BigDecimalFilter electrCapac) {
        this.electrCapac = electrCapac;
    }

    public BigDecimalFilter getDesignWeight() {
        return designWeight;
    }

    public void setDesignWeight(BigDecimalFilter designWeight) {
        this.designWeight = designWeight;
    }

    public BigDecimalFilter getWeight() {
        return weight;
    }

    public void setWeight(BigDecimalFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getIsBurial() {
        return isBurial;
    }

    public void setIsBurial(IntegerFilter isBurial) {
        this.isBurial = isBurial;
    }

    public StringFilter getChemicalComposition() {
        return chemicalComposition;
    }

    public void setChemicalComposition(StringFilter chemicalComposition) {
        this.chemicalComposition = chemicalComposition;
    }

    public BigDecimalFilter getDensity() {
        return density;
    }

    public void setDensity(BigDecimalFilter density) {
        this.density = density;
    }

    public IntegerFilter getSpacing() {
        return spacing;
    }

    public void setSpacing(IntegerFilter spacing) {
        this.spacing = spacing;
    }

    public IntegerFilter getCoatCutbackLength() {
        return coatCutbackLength;
    }

    public void setCoatCutbackLength(IntegerFilter coatCutbackLength) {
        this.coatCutbackLength = coatCutbackLength;
    }

    public BigDecimalFilter getCoatDmgArea() {
        return coatDmgArea;
    }

    public void setCoatDmgArea(BigDecimalFilter coatDmgArea) {
        this.coatDmgArea = coatDmgArea;
    }

    public BigDecimalFilter geth2sSoil() {
        return h2sSoil;
    }

    public void seth2sSoil(BigDecimalFilter h2sSoil) {
        this.h2sSoil = h2sSoil;
    }

    public BigDecimalFilter getRemain() {
        return remain;
    }

    public void setRemain(BigDecimalFilter remain) {
        this.remain = remain;
    }

    public BigDecimalFilter getIntFldTemp() {
        return intFldTemp;
    }

    public void setIntFldTemp(BigDecimalFilter intFldTemp) {
        this.intFldTemp = intFldTemp;
    }

    public BigDecimalFilter getMinPrc() {
        return minPrc;
    }

    public void setMinPrc(BigDecimalFilter minPrc) {
        this.minPrc = minPrc;
    }

    public BigDecimalFilter getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimalFilter thickness) {
        this.thickness = thickness;
    }

    public StringFilter getCoord() {
        return coord;
    }

    public void setCoord(StringFilter coord) {
        this.coord = coord;
    }

    public BigDecimalFilter getKpStart() {
        return kpStart;
    }

    public void setKpStart(BigDecimalFilter kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimalFilter getCoatThickness() {
        return coatThickness;
    }

    public void setCoatThickness(BigDecimalFilter coatThickness) {
        this.coatThickness = coatThickness;
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

    public LongFilter getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(LongFilter idPipelineSectionId) {
        this.idPipelineSectionId = idPipelineSectionId;
    }

    public LongFilter getIdBraceleteTypeId() {
        return idBraceleteTypeId;
    }

    public void setIdBraceleteTypeId(LongFilter idBraceleteTypeId) {
        this.idBraceleteTypeId = idBraceleteTypeId;
    }

    public LongFilter getIdMaterialId() {
        return idMaterialId;
    }

    public void setIdMaterialId(LongFilter idMaterialId) {
        this.idMaterialId = idMaterialId;
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
        final AnodeHistCriteria that = (AnodeHistCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(idWrk, that.idWrk) &&
            Objects.equals(designLife, that.designLife) &&
            Objects.equals(dmcd, that.dmcd) &&
            Objects.equals(l1, that.l1) &&
            Objects.equals(l2, that.l2) &&
            Objects.equals(length, that.length) &&
            Objects.equals(electrCapac, that.electrCapac) &&
            Objects.equals(designWeight, that.designWeight) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(isBurial, that.isBurial) &&
            Objects.equals(chemicalComposition, that.chemicalComposition) &&
            Objects.equals(density, that.density) &&
            Objects.equals(spacing, that.spacing) &&
            Objects.equals(coatCutbackLength, that.coatCutbackLength) &&
            Objects.equals(coatDmgArea, that.coatDmgArea) &&
            Objects.equals(h2sSoil, that.h2sSoil) &&
            Objects.equals(remain, that.remain) &&
            Objects.equals(intFldTemp, that.intFldTemp) &&
            Objects.equals(minPrc, that.minPrc) &&
            Objects.equals(thickness, that.thickness) &&
            Objects.equals(coord, that.coord) &&
            Objects.equals(kpStart, that.kpStart) &&
            Objects.equals(coatThickness, that.coatThickness) &&
            Objects.equals(kpEnd, that.kpEnd) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(description, that.description) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(idPipelineSectionId, that.idPipelineSectionId) &&
            Objects.equals(idBraceleteTypeId, that.idBraceleteTypeId) &&
            Objects.equals(idMaterialId, that.idMaterialId) &&
            Objects.equals(idStatusId, that.idStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateFrom,
        dateTo,
        idWrk,
        designLife,
        dmcd,
        l1,
        l2,
        length,
        electrCapac,
        designWeight,
        weight,
        isBurial,
        chemicalComposition,
        density,
        spacing,
        coatCutbackLength,
        coatDmgArea,
        h2sSoil,
        remain,
        intFldTemp,
        minPrc,
        thickness,
        coord,
        kpStart,
        coatThickness,
        kpEnd,
        isCurrentFlag,
        description,
        comment,
        dateCreate,
        dateEdit,
        creator,
        editor,
        idId,
        idPipelineSectionId,
        idBraceleteTypeId,
        idMaterialId,
        idStatusId
        );
    }

    @Override
    public String toString() {
        return "AnodeHistCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (idWrk != null ? "idWrk=" + idWrk + ", " : "") +
                (designLife != null ? "designLife=" + designLife + ", " : "") +
                (dmcd != null ? "dmcd=" + dmcd + ", " : "") +
                (l1 != null ? "l1=" + l1 + ", " : "") +
                (l2 != null ? "l2=" + l2 + ", " : "") +
                (length != null ? "length=" + length + ", " : "") +
                (electrCapac != null ? "electrCapac=" + electrCapac + ", " : "") +
                (designWeight != null ? "designWeight=" + designWeight + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (isBurial != null ? "isBurial=" + isBurial + ", " : "") +
                (chemicalComposition != null ? "chemicalComposition=" + chemicalComposition + ", " : "") +
                (density != null ? "density=" + density + ", " : "") +
                (spacing != null ? "spacing=" + spacing + ", " : "") +
                (coatCutbackLength != null ? "coatCutbackLength=" + coatCutbackLength + ", " : "") +
                (coatDmgArea != null ? "coatDmgArea=" + coatDmgArea + ", " : "") +
                (h2sSoil != null ? "h2sSoil=" + h2sSoil + ", " : "") +
                (remain != null ? "remain=" + remain + ", " : "") +
                (intFldTemp != null ? "intFldTemp=" + intFldTemp + ", " : "") +
                (minPrc != null ? "minPrc=" + minPrc + ", " : "") +
                (thickness != null ? "thickness=" + thickness + ", " : "") +
                (coord != null ? "coord=" + coord + ", " : "") +
                (kpStart != null ? "kpStart=" + kpStart + ", " : "") +
                (coatThickness != null ? "coatThickness=" + coatThickness + ", " : "") +
                (kpEnd != null ? "kpEnd=" + kpEnd + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (comment != null ? "comment=" + comment + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (idPipelineSectionId != null ? "idPipelineSectionId=" + idPipelineSectionId + ", " : "") +
                (idBraceleteTypeId != null ? "idBraceleteTypeId=" + idBraceleteTypeId + ", " : "") +
                (idMaterialId != null ? "idMaterialId=" + idMaterialId + ", " : "") +
                (idStatusId != null ? "idStatusId=" + idStatusId + ", " : "") +
            "}";
    }

}
