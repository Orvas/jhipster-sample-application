package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TeeHist} entity.
 */
@ApiModel(description = "Tee. Time-dependent attribute table. One table  rows corresponds to one tee and time period")
public class TeeHistDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    private Long idWrk;

    @Size(max = 255)
    private String name;

    private Integer diameterOuterSteel;

    private Integer diameterOuterSteelBr;

    private BigDecimal internalCoatThickness;

    private BigDecimal externalCoatThickness;

    private Integer isConcrCoating;

    private BigDecimal concrCoatThickness;

    private BigDecimal concrCoatDensity;

    private BigDecimal measWallThickness;

    private Integer length;

    private BigDecimal weight;

    private BigDecimal smts;

    private BigDecimal smys;

    private BigDecimal sdbm;

    private BigDecimal sdaf;

    private BigDecimal sdcs;

    private BigDecimal allowTensStrain;

    private Integer corrosionAllowance;

    private Integer fabricationAllowance;

    private Integer isBurial;

    private Integer burialDepth;

    private Integer factBurialDepth;

    private LocalDate dateManufactured;

    private BigDecimal millTestPressure;

    private BigDecimal designPressure;

    private BigDecimal incidentalPressure;

    private LocalDate dateInstalled;

    private BigDecimal seamOrient;

    private BigDecimal seamAngle;

    private BigDecimal azimuth;

    private BigDecimal seabInstallTemp;

    private BigDecimal verticalAngle;

    private BigDecimal heatTreatDurat;

    private BigDecimal maxDesignTemp;

    @Size(max = 255)
    private String heatNumber;

    @Size(max = 255)
    private String coord;

    private BigDecimal designCoord;

    private BigDecimal kpInst;

    @NotNull
    private Integer isCurrentFlag;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String comment;

    private Instant dateCreate;

    private Instant dateEdit;

    @Size(max = 255)
    private String creator;

    @Size(max = 255)
    private String editor;


    private Long teeId;

    private Long idPipelineSectionId;

    private Long idTypeId;

    private Long idInternalCoatTypeId;

    private Long idExternalCoatTypeId;

    private Long idNominalWallThicknessId;

    private Long idPipeJointId;

    private Long idManufacturerId;

    private Long idSpecificationId;

    private Long idLongSeamWeldTypeId;

    private Long idFabricationTypeId;

    private Long idMaterialId;

    private Long idMillLocationId;

    private Long idSteelGradeId;

    private Long idStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public void setDiameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public Integer getDiameterOuterSteelBr() {
        return diameterOuterSteelBr;
    }

    public void setDiameterOuterSteelBr(Integer diameterOuterSteelBr) {
        this.diameterOuterSteelBr = diameterOuterSteelBr;
    }

    public BigDecimal getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public void setInternalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public Integer getIsConcrCoating() {
        return isConcrCoating;
    }

    public void setIsConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimal getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public void setConcrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimal getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public void setConcrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimal getMeasWallThickness() {
        return measWallThickness;
    }

    public void setMeasWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmts() {
        return smts;
    }

    public void setSmts(BigDecimal smts) {
        this.smts = smts;
    }

    public BigDecimal getSmys() {
        return smys;
    }

    public void setSmys(BigDecimal smys) {
        this.smys = smys;
    }

    public BigDecimal getSdbm() {
        return sdbm;
    }

    public void setSdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimal getSdaf() {
        return sdaf;
    }

    public void setSdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimal getSdcs() {
        return sdcs;
    }

    public void setSdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimal getAllowTensStrain() {
        return allowTensStrain;
    }

    public void setAllowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public Integer getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public void setCorrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public Integer getFabricationAllowance() {
        return fabricationAllowance;
    }

    public void setFabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public Integer getBurialDepth() {
        return burialDepth;
    }

    public void setBurialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
    }

    public Integer getFactBurialDepth() {
        return factBurialDepth;
    }

    public void setFactBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
    }

    public LocalDate getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimal getMillTestPressure() {
        return millTestPressure;
    }

    public void setMillTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimal getDesignPressure() {
        return designPressure;
    }

    public void setDesignPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimal getIncidentalPressure() {
        return incidentalPressure;
    }

    public void setIncidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDate getDateInstalled() {
        return dateInstalled;
    }

    public void setDateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimal getSeamOrient() {
        return seamOrient;
    }

    public void setSeamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimal getSeamAngle() {
        return seamAngle;
    }

    public void setSeamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimal getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public void setSeabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimal getVerticalAngle() {
        return verticalAngle;
    }

    public void setVerticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimal getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public void setHeatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimal getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public void setMaxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public String getHeatNumber() {
        return heatNumber;
    }

    public void setHeatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getDesignCoord() {
        return designCoord;
    }

    public void setDesignCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
    }

    public BigDecimal getKpInst() {
        return kpInst;
    }

    public void setKpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Long getTeeId() {
        return teeId;
    }

    public void setTeeId(Long teeId) {
        this.teeId = teeId;
    }

    public Long getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(Long pipelineSectionId) {
        this.idPipelineSectionId = pipelineSectionId;
    }

    public Long getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(Long listTeeTypeId) {
        this.idTypeId = listTeeTypeId;
    }

    public Long getIdInternalCoatTypeId() {
        return idInternalCoatTypeId;
    }

    public void setIdInternalCoatTypeId(Long listInternalCoatingId) {
        this.idInternalCoatTypeId = listInternalCoatingId;
    }

    public Long getIdExternalCoatTypeId() {
        return idExternalCoatTypeId;
    }

    public void setIdExternalCoatTypeId(Long listExternalCoatingId) {
        this.idExternalCoatTypeId = listExternalCoatingId;
    }

    public Long getIdNominalWallThicknessId() {
        return idNominalWallThicknessId;
    }

    public void setIdNominalWallThicknessId(Long listNominalWallThicknessId) {
        this.idNominalWallThicknessId = listNominalWallThicknessId;
    }

    public Long getIdPipeJointId() {
        return idPipeJointId;
    }

    public void setIdPipeJointId(Long pipejointId) {
        this.idPipeJointId = pipejointId;
    }

    public Long getIdManufacturerId() {
        return idManufacturerId;
    }

    public void setIdManufacturerId(Long listTeeManufacturerId) {
        this.idManufacturerId = listTeeManufacturerId;
    }

    public Long getIdSpecificationId() {
        return idSpecificationId;
    }

    public void setIdSpecificationId(Long listTeeSpecificationId) {
        this.idSpecificationId = listTeeSpecificationId;
    }

    public Long getIdLongSeamWeldTypeId() {
        return idLongSeamWeldTypeId;
    }

    public void setIdLongSeamWeldTypeId(Long listLongSeamWeldTypeId) {
        this.idLongSeamWeldTypeId = listLongSeamWeldTypeId;
    }

    public Long getIdFabricationTypeId() {
        return idFabricationTypeId;
    }

    public void setIdFabricationTypeId(Long listFabricationTypeId) {
        this.idFabricationTypeId = listFabricationTypeId;
    }

    public Long getIdMaterialId() {
        return idMaterialId;
    }

    public void setIdMaterialId(Long listMaterialId) {
        this.idMaterialId = listMaterialId;
    }

    public Long getIdMillLocationId() {
        return idMillLocationId;
    }

    public void setIdMillLocationId(Long listMillLocationId) {
        this.idMillLocationId = listMillLocationId;
    }

    public Long getIdSteelGradeId() {
        return idSteelGradeId;
    }

    public void setIdSteelGradeId(Long listSteelGradeId) {
        this.idSteelGradeId = listSteelGradeId;
    }

    public Long getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(Long listObjectStatusId) {
        this.idStatusId = listObjectStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeeHistDTO teeHistDTO = (TeeHistDTO) o;
        if (teeHistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teeHistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeeHistDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", name='" + getName() + "'" +
            ", diameterOuterSteel=" + getDiameterOuterSteel() +
            ", diameterOuterSteelBr=" + getDiameterOuterSteelBr() +
            ", internalCoatThickness=" + getInternalCoatThickness() +
            ", externalCoatThickness=" + getExternalCoatThickness() +
            ", isConcrCoating=" + getIsConcrCoating() +
            ", concrCoatThickness=" + getConcrCoatThickness() +
            ", concrCoatDensity=" + getConcrCoatDensity() +
            ", measWallThickness=" + getMeasWallThickness() +
            ", length=" + getLength() +
            ", weight=" + getWeight() +
            ", smts=" + getSmts() +
            ", smys=" + getSmys() +
            ", sdbm=" + getSdbm() +
            ", sdaf=" + getSdaf() +
            ", sdcs=" + getSdcs() +
            ", allowTensStrain=" + getAllowTensStrain() +
            ", corrosionAllowance=" + getCorrosionAllowance() +
            ", fabricationAllowance=" + getFabricationAllowance() +
            ", isBurial=" + getIsBurial() +
            ", burialDepth=" + getBurialDepth() +
            ", factBurialDepth=" + getFactBurialDepth() +
            ", dateManufactured='" + getDateManufactured() + "'" +
            ", millTestPressure=" + getMillTestPressure() +
            ", designPressure=" + getDesignPressure() +
            ", incidentalPressure=" + getIncidentalPressure() +
            ", dateInstalled='" + getDateInstalled() + "'" +
            ", seamOrient=" + getSeamOrient() +
            ", seamAngle=" + getSeamAngle() +
            ", azimuth=" + getAzimuth() +
            ", seabInstallTemp=" + getSeabInstallTemp() +
            ", verticalAngle=" + getVerticalAngle() +
            ", heatTreatDurat=" + getHeatTreatDurat() +
            ", maxDesignTemp=" + getMaxDesignTemp() +
            ", heatNumber='" + getHeatNumber() + "'" +
            ", coord='" + getCoord() + "'" +
            ", designCoord=" + getDesignCoord() +
            ", kpInst=" + getKpInst() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", description='" + getDescription() + "'" +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            ", tee=" + getTeeId() +
            ", idPipelineSection=" + getIdPipelineSectionId() +
            ", idType=" + getIdTypeId() +
            ", idInternalCoatType=" + getIdInternalCoatTypeId() +
            ", idExternalCoatType=" + getIdExternalCoatTypeId() +
            ", idNominalWallThickness=" + getIdNominalWallThicknessId() +
            ", idPipeJoint=" + getIdPipeJointId() +
            ", idManufacturer=" + getIdManufacturerId() +
            ", idSpecification=" + getIdSpecificationId() +
            ", idLongSeamWeldType=" + getIdLongSeamWeldTypeId() +
            ", idFabricationType=" + getIdFabricationTypeId() +
            ", idMaterial=" + getIdMaterialId() +
            ", idMillLocation=" + getIdMillLocationId() +
            ", idSteelGrade=" + getIdSteelGradeId() +
            ", idStatus=" + getIdStatusId() +
            "}";
    }
}
