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
 * Criteria class for the {@link io.github.jhipster.application.domain.TeeHist} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.TeeHistResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tee-hists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TeeHistCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter dateFrom;

    private LocalDateFilter dateTo;

    private LongFilter idWrk;

    private StringFilter name;

    private IntegerFilter diameterOuterSteel;

    private IntegerFilter diameterOuterSteelBr;

    private BigDecimalFilter internalCoatThickness;

    private BigDecimalFilter externalCoatThickness;

    private IntegerFilter isConcrCoating;

    private BigDecimalFilter concrCoatThickness;

    private BigDecimalFilter concrCoatDensity;

    private BigDecimalFilter measWallThickness;

    private IntegerFilter length;

    private BigDecimalFilter weight;

    private BigDecimalFilter smts;

    private BigDecimalFilter smys;

    private BigDecimalFilter sdbm;

    private BigDecimalFilter sdaf;

    private BigDecimalFilter sdcs;

    private BigDecimalFilter allowTensStrain;

    private IntegerFilter corrosionAllowance;

    private IntegerFilter fabricationAllowance;

    private IntegerFilter isBurial;

    private IntegerFilter burialDepth;

    private IntegerFilter factBurialDepth;

    private LocalDateFilter dateManufactured;

    private BigDecimalFilter millTestPressure;

    private BigDecimalFilter designPressure;

    private BigDecimalFilter incidentalPressure;

    private LocalDateFilter dateInstalled;

    private BigDecimalFilter seamOrient;

    private BigDecimalFilter seamAngle;

    private BigDecimalFilter azimuth;

    private BigDecimalFilter seabInstallTemp;

    private BigDecimalFilter verticalAngle;

    private BigDecimalFilter heatTreatDurat;

    private BigDecimalFilter maxDesignTemp;

    private StringFilter heatNumber;

    private StringFilter coord;

    private BigDecimalFilter designCoord;

    private BigDecimalFilter kpInst;

    private IntegerFilter isCurrentFlag;

    private StringFilter description;

    private StringFilter comment;

    private InstantFilter dateCreate;

    private InstantFilter dateEdit;

    private StringFilter creator;

    private StringFilter editor;

    private LongFilter teeId;

    private LongFilter idPipelineSectionId;

    private LongFilter idTypeId;

    private LongFilter idInternalCoatTypeId;

    private LongFilter idExternalCoatTypeId;

    private LongFilter idNominalWallThicknessId;

    private LongFilter idPipeJointId;

    private LongFilter idManufacturerId;

    private LongFilter idSpecificationId;

    private LongFilter idLongSeamWeldTypeId;

    private LongFilter idFabricationTypeId;

    private LongFilter idMaterialId;

    private LongFilter idMillLocationId;

    private LongFilter idSteelGradeId;

    private LongFilter idStatusId;

    public TeeHistCriteria(){
    }

    public TeeHistCriteria(TeeHistCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.dateFrom = other.dateFrom == null ? null : other.dateFrom.copy();
        this.dateTo = other.dateTo == null ? null : other.dateTo.copy();
        this.idWrk = other.idWrk == null ? null : other.idWrk.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.diameterOuterSteel = other.diameterOuterSteel == null ? null : other.diameterOuterSteel.copy();
        this.diameterOuterSteelBr = other.diameterOuterSteelBr == null ? null : other.diameterOuterSteelBr.copy();
        this.internalCoatThickness = other.internalCoatThickness == null ? null : other.internalCoatThickness.copy();
        this.externalCoatThickness = other.externalCoatThickness == null ? null : other.externalCoatThickness.copy();
        this.isConcrCoating = other.isConcrCoating == null ? null : other.isConcrCoating.copy();
        this.concrCoatThickness = other.concrCoatThickness == null ? null : other.concrCoatThickness.copy();
        this.concrCoatDensity = other.concrCoatDensity == null ? null : other.concrCoatDensity.copy();
        this.measWallThickness = other.measWallThickness == null ? null : other.measWallThickness.copy();
        this.length = other.length == null ? null : other.length.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.smts = other.smts == null ? null : other.smts.copy();
        this.smys = other.smys == null ? null : other.smys.copy();
        this.sdbm = other.sdbm == null ? null : other.sdbm.copy();
        this.sdaf = other.sdaf == null ? null : other.sdaf.copy();
        this.sdcs = other.sdcs == null ? null : other.sdcs.copy();
        this.allowTensStrain = other.allowTensStrain == null ? null : other.allowTensStrain.copy();
        this.corrosionAllowance = other.corrosionAllowance == null ? null : other.corrosionAllowance.copy();
        this.fabricationAllowance = other.fabricationAllowance == null ? null : other.fabricationAllowance.copy();
        this.isBurial = other.isBurial == null ? null : other.isBurial.copy();
        this.burialDepth = other.burialDepth == null ? null : other.burialDepth.copy();
        this.factBurialDepth = other.factBurialDepth == null ? null : other.factBurialDepth.copy();
        this.dateManufactured = other.dateManufactured == null ? null : other.dateManufactured.copy();
        this.millTestPressure = other.millTestPressure == null ? null : other.millTestPressure.copy();
        this.designPressure = other.designPressure == null ? null : other.designPressure.copy();
        this.incidentalPressure = other.incidentalPressure == null ? null : other.incidentalPressure.copy();
        this.dateInstalled = other.dateInstalled == null ? null : other.dateInstalled.copy();
        this.seamOrient = other.seamOrient == null ? null : other.seamOrient.copy();
        this.seamAngle = other.seamAngle == null ? null : other.seamAngle.copy();
        this.azimuth = other.azimuth == null ? null : other.azimuth.copy();
        this.seabInstallTemp = other.seabInstallTemp == null ? null : other.seabInstallTemp.copy();
        this.verticalAngle = other.verticalAngle == null ? null : other.verticalAngle.copy();
        this.heatTreatDurat = other.heatTreatDurat == null ? null : other.heatTreatDurat.copy();
        this.maxDesignTemp = other.maxDesignTemp == null ? null : other.maxDesignTemp.copy();
        this.heatNumber = other.heatNumber == null ? null : other.heatNumber.copy();
        this.coord = other.coord == null ? null : other.coord.copy();
        this.designCoord = other.designCoord == null ? null : other.designCoord.copy();
        this.kpInst = other.kpInst == null ? null : other.kpInst.copy();
        this.isCurrentFlag = other.isCurrentFlag == null ? null : other.isCurrentFlag.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.dateEdit = other.dateEdit == null ? null : other.dateEdit.copy();
        this.creator = other.creator == null ? null : other.creator.copy();
        this.editor = other.editor == null ? null : other.editor.copy();
        this.teeId = other.teeId == null ? null : other.teeId.copy();
        this.idPipelineSectionId = other.idPipelineSectionId == null ? null : other.idPipelineSectionId.copy();
        this.idTypeId = other.idTypeId == null ? null : other.idTypeId.copy();
        this.idInternalCoatTypeId = other.idInternalCoatTypeId == null ? null : other.idInternalCoatTypeId.copy();
        this.idExternalCoatTypeId = other.idExternalCoatTypeId == null ? null : other.idExternalCoatTypeId.copy();
        this.idNominalWallThicknessId = other.idNominalWallThicknessId == null ? null : other.idNominalWallThicknessId.copy();
        this.idPipeJointId = other.idPipeJointId == null ? null : other.idPipeJointId.copy();
        this.idManufacturerId = other.idManufacturerId == null ? null : other.idManufacturerId.copy();
        this.idSpecificationId = other.idSpecificationId == null ? null : other.idSpecificationId.copy();
        this.idLongSeamWeldTypeId = other.idLongSeamWeldTypeId == null ? null : other.idLongSeamWeldTypeId.copy();
        this.idFabricationTypeId = other.idFabricationTypeId == null ? null : other.idFabricationTypeId.copy();
        this.idMaterialId = other.idMaterialId == null ? null : other.idMaterialId.copy();
        this.idMillLocationId = other.idMillLocationId == null ? null : other.idMillLocationId.copy();
        this.idSteelGradeId = other.idSteelGradeId == null ? null : other.idSteelGradeId.copy();
        this.idStatusId = other.idStatusId == null ? null : other.idStatusId.copy();
    }

    @Override
    public TeeHistCriteria copy() {
        return new TeeHistCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public IntegerFilter getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public void setDiameterOuterSteel(IntegerFilter diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public IntegerFilter getDiameterOuterSteelBr() {
        return diameterOuterSteelBr;
    }

    public void setDiameterOuterSteelBr(IntegerFilter diameterOuterSteelBr) {
        this.diameterOuterSteelBr = diameterOuterSteelBr;
    }

    public BigDecimalFilter getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public void setInternalCoatThickness(BigDecimalFilter internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimalFilter getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public void setExternalCoatThickness(BigDecimalFilter externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public IntegerFilter getIsConcrCoating() {
        return isConcrCoating;
    }

    public void setIsConcrCoating(IntegerFilter isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimalFilter getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public void setConcrCoatThickness(BigDecimalFilter concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimalFilter getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public void setConcrCoatDensity(BigDecimalFilter concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimalFilter getMeasWallThickness() {
        return measWallThickness;
    }

    public void setMeasWallThickness(BigDecimalFilter measWallThickness) {
        this.measWallThickness = measWallThickness;
    }

    public IntegerFilter getLength() {
        return length;
    }

    public void setLength(IntegerFilter length) {
        this.length = length;
    }

    public BigDecimalFilter getWeight() {
        return weight;
    }

    public void setWeight(BigDecimalFilter weight) {
        this.weight = weight;
    }

    public BigDecimalFilter getSmts() {
        return smts;
    }

    public void setSmts(BigDecimalFilter smts) {
        this.smts = smts;
    }

    public BigDecimalFilter getSmys() {
        return smys;
    }

    public void setSmys(BigDecimalFilter smys) {
        this.smys = smys;
    }

    public BigDecimalFilter getSdbm() {
        return sdbm;
    }

    public void setSdbm(BigDecimalFilter sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimalFilter getSdaf() {
        return sdaf;
    }

    public void setSdaf(BigDecimalFilter sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimalFilter getSdcs() {
        return sdcs;
    }

    public void setSdcs(BigDecimalFilter sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimalFilter getAllowTensStrain() {
        return allowTensStrain;
    }

    public void setAllowTensStrain(BigDecimalFilter allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public IntegerFilter getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public void setCorrosionAllowance(IntegerFilter corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public IntegerFilter getFabricationAllowance() {
        return fabricationAllowance;
    }

    public void setFabricationAllowance(IntegerFilter fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public IntegerFilter getIsBurial() {
        return isBurial;
    }

    public void setIsBurial(IntegerFilter isBurial) {
        this.isBurial = isBurial;
    }

    public IntegerFilter getBurialDepth() {
        return burialDepth;
    }

    public void setBurialDepth(IntegerFilter burialDepth) {
        this.burialDepth = burialDepth;
    }

    public IntegerFilter getFactBurialDepth() {
        return factBurialDepth;
    }

    public void setFactBurialDepth(IntegerFilter factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
    }

    public LocalDateFilter getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(LocalDateFilter dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimalFilter getMillTestPressure() {
        return millTestPressure;
    }

    public void setMillTestPressure(BigDecimalFilter millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimalFilter getDesignPressure() {
        return designPressure;
    }

    public void setDesignPressure(BigDecimalFilter designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimalFilter getIncidentalPressure() {
        return incidentalPressure;
    }

    public void setIncidentalPressure(BigDecimalFilter incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDateFilter getDateInstalled() {
        return dateInstalled;
    }

    public void setDateInstalled(LocalDateFilter dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimalFilter getSeamOrient() {
        return seamOrient;
    }

    public void setSeamOrient(BigDecimalFilter seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimalFilter getSeamAngle() {
        return seamAngle;
    }

    public void setSeamAngle(BigDecimalFilter seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimalFilter getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(BigDecimalFilter azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimalFilter getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public void setSeabInstallTemp(BigDecimalFilter seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimalFilter getVerticalAngle() {
        return verticalAngle;
    }

    public void setVerticalAngle(BigDecimalFilter verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimalFilter getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public void setHeatTreatDurat(BigDecimalFilter heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimalFilter getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public void setMaxDesignTemp(BigDecimalFilter maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public StringFilter getHeatNumber() {
        return heatNumber;
    }

    public void setHeatNumber(StringFilter heatNumber) {
        this.heatNumber = heatNumber;
    }

    public StringFilter getCoord() {
        return coord;
    }

    public void setCoord(StringFilter coord) {
        this.coord = coord;
    }

    public BigDecimalFilter getDesignCoord() {
        return designCoord;
    }

    public void setDesignCoord(BigDecimalFilter designCoord) {
        this.designCoord = designCoord;
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

    public LongFilter getTeeId() {
        return teeId;
    }

    public void setTeeId(LongFilter teeId) {
        this.teeId = teeId;
    }

    public LongFilter getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(LongFilter idPipelineSectionId) {
        this.idPipelineSectionId = idPipelineSectionId;
    }

    public LongFilter getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(LongFilter idTypeId) {
        this.idTypeId = idTypeId;
    }

    public LongFilter getIdInternalCoatTypeId() {
        return idInternalCoatTypeId;
    }

    public void setIdInternalCoatTypeId(LongFilter idInternalCoatTypeId) {
        this.idInternalCoatTypeId = idInternalCoatTypeId;
    }

    public LongFilter getIdExternalCoatTypeId() {
        return idExternalCoatTypeId;
    }

    public void setIdExternalCoatTypeId(LongFilter idExternalCoatTypeId) {
        this.idExternalCoatTypeId = idExternalCoatTypeId;
    }

    public LongFilter getIdNominalWallThicknessId() {
        return idNominalWallThicknessId;
    }

    public void setIdNominalWallThicknessId(LongFilter idNominalWallThicknessId) {
        this.idNominalWallThicknessId = idNominalWallThicknessId;
    }

    public LongFilter getIdPipeJointId() {
        return idPipeJointId;
    }

    public void setIdPipeJointId(LongFilter idPipeJointId) {
        this.idPipeJointId = idPipeJointId;
    }

    public LongFilter getIdManufacturerId() {
        return idManufacturerId;
    }

    public void setIdManufacturerId(LongFilter idManufacturerId) {
        this.idManufacturerId = idManufacturerId;
    }

    public LongFilter getIdSpecificationId() {
        return idSpecificationId;
    }

    public void setIdSpecificationId(LongFilter idSpecificationId) {
        this.idSpecificationId = idSpecificationId;
    }

    public LongFilter getIdLongSeamWeldTypeId() {
        return idLongSeamWeldTypeId;
    }

    public void setIdLongSeamWeldTypeId(LongFilter idLongSeamWeldTypeId) {
        this.idLongSeamWeldTypeId = idLongSeamWeldTypeId;
    }

    public LongFilter getIdFabricationTypeId() {
        return idFabricationTypeId;
    }

    public void setIdFabricationTypeId(LongFilter idFabricationTypeId) {
        this.idFabricationTypeId = idFabricationTypeId;
    }

    public LongFilter getIdMaterialId() {
        return idMaterialId;
    }

    public void setIdMaterialId(LongFilter idMaterialId) {
        this.idMaterialId = idMaterialId;
    }

    public LongFilter getIdMillLocationId() {
        return idMillLocationId;
    }

    public void setIdMillLocationId(LongFilter idMillLocationId) {
        this.idMillLocationId = idMillLocationId;
    }

    public LongFilter getIdSteelGradeId() {
        return idSteelGradeId;
    }

    public void setIdSteelGradeId(LongFilter idSteelGradeId) {
        this.idSteelGradeId = idSteelGradeId;
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
        final TeeHistCriteria that = (TeeHistCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(idWrk, that.idWrk) &&
            Objects.equals(name, that.name) &&
            Objects.equals(diameterOuterSteel, that.diameterOuterSteel) &&
            Objects.equals(diameterOuterSteelBr, that.diameterOuterSteelBr) &&
            Objects.equals(internalCoatThickness, that.internalCoatThickness) &&
            Objects.equals(externalCoatThickness, that.externalCoatThickness) &&
            Objects.equals(isConcrCoating, that.isConcrCoating) &&
            Objects.equals(concrCoatThickness, that.concrCoatThickness) &&
            Objects.equals(concrCoatDensity, that.concrCoatDensity) &&
            Objects.equals(measWallThickness, that.measWallThickness) &&
            Objects.equals(length, that.length) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(smts, that.smts) &&
            Objects.equals(smys, that.smys) &&
            Objects.equals(sdbm, that.sdbm) &&
            Objects.equals(sdaf, that.sdaf) &&
            Objects.equals(sdcs, that.sdcs) &&
            Objects.equals(allowTensStrain, that.allowTensStrain) &&
            Objects.equals(corrosionAllowance, that.corrosionAllowance) &&
            Objects.equals(fabricationAllowance, that.fabricationAllowance) &&
            Objects.equals(isBurial, that.isBurial) &&
            Objects.equals(burialDepth, that.burialDepth) &&
            Objects.equals(factBurialDepth, that.factBurialDepth) &&
            Objects.equals(dateManufactured, that.dateManufactured) &&
            Objects.equals(millTestPressure, that.millTestPressure) &&
            Objects.equals(designPressure, that.designPressure) &&
            Objects.equals(incidentalPressure, that.incidentalPressure) &&
            Objects.equals(dateInstalled, that.dateInstalled) &&
            Objects.equals(seamOrient, that.seamOrient) &&
            Objects.equals(seamAngle, that.seamAngle) &&
            Objects.equals(azimuth, that.azimuth) &&
            Objects.equals(seabInstallTemp, that.seabInstallTemp) &&
            Objects.equals(verticalAngle, that.verticalAngle) &&
            Objects.equals(heatTreatDurat, that.heatTreatDurat) &&
            Objects.equals(maxDesignTemp, that.maxDesignTemp) &&
            Objects.equals(heatNumber, that.heatNumber) &&
            Objects.equals(coord, that.coord) &&
            Objects.equals(designCoord, that.designCoord) &&
            Objects.equals(kpInst, that.kpInst) &&
            Objects.equals(isCurrentFlag, that.isCurrentFlag) &&
            Objects.equals(description, that.description) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(dateEdit, that.dateEdit) &&
            Objects.equals(creator, that.creator) &&
            Objects.equals(editor, that.editor) &&
            Objects.equals(teeId, that.teeId) &&
            Objects.equals(idPipelineSectionId, that.idPipelineSectionId) &&
            Objects.equals(idTypeId, that.idTypeId) &&
            Objects.equals(idInternalCoatTypeId, that.idInternalCoatTypeId) &&
            Objects.equals(idExternalCoatTypeId, that.idExternalCoatTypeId) &&
            Objects.equals(idNominalWallThicknessId, that.idNominalWallThicknessId) &&
            Objects.equals(idPipeJointId, that.idPipeJointId) &&
            Objects.equals(idManufacturerId, that.idManufacturerId) &&
            Objects.equals(idSpecificationId, that.idSpecificationId) &&
            Objects.equals(idLongSeamWeldTypeId, that.idLongSeamWeldTypeId) &&
            Objects.equals(idFabricationTypeId, that.idFabricationTypeId) &&
            Objects.equals(idMaterialId, that.idMaterialId) &&
            Objects.equals(idMillLocationId, that.idMillLocationId) &&
            Objects.equals(idSteelGradeId, that.idSteelGradeId) &&
            Objects.equals(idStatusId, that.idStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateFrom,
        dateTo,
        idWrk,
        name,
        diameterOuterSteel,
        diameterOuterSteelBr,
        internalCoatThickness,
        externalCoatThickness,
        isConcrCoating,
        concrCoatThickness,
        concrCoatDensity,
        measWallThickness,
        length,
        weight,
        smts,
        smys,
        sdbm,
        sdaf,
        sdcs,
        allowTensStrain,
        corrosionAllowance,
        fabricationAllowance,
        isBurial,
        burialDepth,
        factBurialDepth,
        dateManufactured,
        millTestPressure,
        designPressure,
        incidentalPressure,
        dateInstalled,
        seamOrient,
        seamAngle,
        azimuth,
        seabInstallTemp,
        verticalAngle,
        heatTreatDurat,
        maxDesignTemp,
        heatNumber,
        coord,
        designCoord,
        kpInst,
        isCurrentFlag,
        description,
        comment,
        dateCreate,
        dateEdit,
        creator,
        editor,
        teeId,
        idPipelineSectionId,
        idTypeId,
        idInternalCoatTypeId,
        idExternalCoatTypeId,
        idNominalWallThicknessId,
        idPipeJointId,
        idManufacturerId,
        idSpecificationId,
        idLongSeamWeldTypeId,
        idFabricationTypeId,
        idMaterialId,
        idMillLocationId,
        idSteelGradeId,
        idStatusId
        );
    }

    @Override
    public String toString() {
        return "TeeHistCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateFrom != null ? "dateFrom=" + dateFrom + ", " : "") +
                (dateTo != null ? "dateTo=" + dateTo + ", " : "") +
                (idWrk != null ? "idWrk=" + idWrk + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (diameterOuterSteel != null ? "diameterOuterSteel=" + diameterOuterSteel + ", " : "") +
                (diameterOuterSteelBr != null ? "diameterOuterSteelBr=" + diameterOuterSteelBr + ", " : "") +
                (internalCoatThickness != null ? "internalCoatThickness=" + internalCoatThickness + ", " : "") +
                (externalCoatThickness != null ? "externalCoatThickness=" + externalCoatThickness + ", " : "") +
                (isConcrCoating != null ? "isConcrCoating=" + isConcrCoating + ", " : "") +
                (concrCoatThickness != null ? "concrCoatThickness=" + concrCoatThickness + ", " : "") +
                (concrCoatDensity != null ? "concrCoatDensity=" + concrCoatDensity + ", " : "") +
                (measWallThickness != null ? "measWallThickness=" + measWallThickness + ", " : "") +
                (length != null ? "length=" + length + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (smts != null ? "smts=" + smts + ", " : "") +
                (smys != null ? "smys=" + smys + ", " : "") +
                (sdbm != null ? "sdbm=" + sdbm + ", " : "") +
                (sdaf != null ? "sdaf=" + sdaf + ", " : "") +
                (sdcs != null ? "sdcs=" + sdcs + ", " : "") +
                (allowTensStrain != null ? "allowTensStrain=" + allowTensStrain + ", " : "") +
                (corrosionAllowance != null ? "corrosionAllowance=" + corrosionAllowance + ", " : "") +
                (fabricationAllowance != null ? "fabricationAllowance=" + fabricationAllowance + ", " : "") +
                (isBurial != null ? "isBurial=" + isBurial + ", " : "") +
                (burialDepth != null ? "burialDepth=" + burialDepth + ", " : "") +
                (factBurialDepth != null ? "factBurialDepth=" + factBurialDepth + ", " : "") +
                (dateManufactured != null ? "dateManufactured=" + dateManufactured + ", " : "") +
                (millTestPressure != null ? "millTestPressure=" + millTestPressure + ", " : "") +
                (designPressure != null ? "designPressure=" + designPressure + ", " : "") +
                (incidentalPressure != null ? "incidentalPressure=" + incidentalPressure + ", " : "") +
                (dateInstalled != null ? "dateInstalled=" + dateInstalled + ", " : "") +
                (seamOrient != null ? "seamOrient=" + seamOrient + ", " : "") +
                (seamAngle != null ? "seamAngle=" + seamAngle + ", " : "") +
                (azimuth != null ? "azimuth=" + azimuth + ", " : "") +
                (seabInstallTemp != null ? "seabInstallTemp=" + seabInstallTemp + ", " : "") +
                (verticalAngle != null ? "verticalAngle=" + verticalAngle + ", " : "") +
                (heatTreatDurat != null ? "heatTreatDurat=" + heatTreatDurat + ", " : "") +
                (maxDesignTemp != null ? "maxDesignTemp=" + maxDesignTemp + ", " : "") +
                (heatNumber != null ? "heatNumber=" + heatNumber + ", " : "") +
                (coord != null ? "coord=" + coord + ", " : "") +
                (designCoord != null ? "designCoord=" + designCoord + ", " : "") +
                (kpInst != null ? "kpInst=" + kpInst + ", " : "") +
                (isCurrentFlag != null ? "isCurrentFlag=" + isCurrentFlag + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (comment != null ? "comment=" + comment + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
                (dateEdit != null ? "dateEdit=" + dateEdit + ", " : "") +
                (creator != null ? "creator=" + creator + ", " : "") +
                (editor != null ? "editor=" + editor + ", " : "") +
                (teeId != null ? "teeId=" + teeId + ", " : "") +
                (idPipelineSectionId != null ? "idPipelineSectionId=" + idPipelineSectionId + ", " : "") +
                (idTypeId != null ? "idTypeId=" + idTypeId + ", " : "") +
                (idInternalCoatTypeId != null ? "idInternalCoatTypeId=" + idInternalCoatTypeId + ", " : "") +
                (idExternalCoatTypeId != null ? "idExternalCoatTypeId=" + idExternalCoatTypeId + ", " : "") +
                (idNominalWallThicknessId != null ? "idNominalWallThicknessId=" + idNominalWallThicknessId + ", " : "") +
                (idPipeJointId != null ? "idPipeJointId=" + idPipeJointId + ", " : "") +
                (idManufacturerId != null ? "idManufacturerId=" + idManufacturerId + ", " : "") +
                (idSpecificationId != null ? "idSpecificationId=" + idSpecificationId + ", " : "") +
                (idLongSeamWeldTypeId != null ? "idLongSeamWeldTypeId=" + idLongSeamWeldTypeId + ", " : "") +
                (idFabricationTypeId != null ? "idFabricationTypeId=" + idFabricationTypeId + ", " : "") +
                (idMaterialId != null ? "idMaterialId=" + idMaterialId + ", " : "") +
                (idMillLocationId != null ? "idMillLocationId=" + idMillLocationId + ", " : "") +
                (idSteelGradeId != null ? "idSteelGradeId=" + idSteelGradeId + ", " : "") +
                (idStatusId != null ? "idStatusId=" + idStatusId + ", " : "") +
            "}";
    }

}
