package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Collar. Time-dependent attribute table. One table  rows corresponds to one collar and time period
 */
@Entity
@Table(name = "buckle_arrestor_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BuckleArrestorHist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @NotNull
    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;

    @Column(name = "id_wrk")
    private Long idWrk;

    @Size(max = 255)
    @Column(name = "serial_num", length = 255)
    private String serialNum;

    @NotNull
    @Column(name = "diameter_outer_steel", nullable = false)
    private Integer diameterOuterSteel;

    @Column(name = "internal_coat_thickness", precision = 21, scale = 2)
    private BigDecimal internalCoatThickness;

    @Column(name = "external_coat_thickness", precision = 21, scale = 2)
    private BigDecimal externalCoatThickness;

    @Column(name = "is_concr_coating")
    private Integer isConcrCoating;

    @Column(name = "concr_coat_thickness", precision = 21, scale = 2)
    private BigDecimal concrCoatThickness;

    @Column(name = "concr_coat_density", precision = 21, scale = 2)
    private BigDecimal concrCoatDensity;

    @Column(name = "meas_col_wall_thickness", precision = 21, scale = 2)
    private BigDecimal measColWallThickness;

    @Column(name = "meas_pipe_wall_thickness", precision = 21, scale = 2)
    private BigDecimal measPipeWallThickness;

    @Column(name = "col_length")
    private Integer colLength;

    @Column(name = "pipe_length")
    private Integer pipeLength;

    @Column(name = "weight", precision = 21, scale = 2)
    private BigDecimal weight;

    @Column(name = "smts", precision = 21, scale = 2)
    private BigDecimal smts;

    @Column(name = "smys", precision = 21, scale = 2)
    private BigDecimal smys;

    @Column(name = "sdbm", precision = 21, scale = 2)
    private BigDecimal sdbm;

    @Column(name = "sdaf", precision = 21, scale = 2)
    private BigDecimal sdaf;

    @Column(name = "sdcs", precision = 21, scale = 2)
    private BigDecimal sdcs;

    @Column(name = "allow_tens_strain", precision = 21, scale = 2)
    private BigDecimal allowTensStrain;

    @Column(name = "corrosion_allowance")
    private Integer corrosionAllowance;

    @Column(name = "fabrication_allowance")
    private Integer fabricationAllowance;

    @Column(name = "is_burial")
    private Integer isBurial;

    @Column(name = "burial_depth")
    private Integer burialDepth;

    @Column(name = "fact_burial_depth")
    private Integer factBurialDepth;

    @Column(name = "date_manufactured")
    private LocalDate dateManufactured;

    @Column(name = "mill_test_pressure", precision = 21, scale = 2)
    private BigDecimal millTestPressure;

    @Column(name = "design_pressure", precision = 21, scale = 2)
    private BigDecimal designPressure;

    @Column(name = "incidental_pressure", precision = 21, scale = 2)
    private BigDecimal incidentalPressure;

    @Column(name = "date_installed")
    private LocalDate dateInstalled;

    @Column(name = "seam_orient", precision = 21, scale = 2)
    private BigDecimal seamOrient;

    @Column(name = "seam_angle", precision = 21, scale = 2)
    private BigDecimal seamAngle;

    @Column(name = "azimuth", precision = 21, scale = 2)
    private BigDecimal azimuth;

    @Column(name = "seab_install_temp", precision = 21, scale = 2)
    private BigDecimal seabInstallTemp;

    @Column(name = "vertical_angle", precision = 21, scale = 2)
    private BigDecimal verticalAngle;

    @Column(name = "heat_treat_durat", precision = 21, scale = 2)
    private BigDecimal heatTreatDurat;

    @Column(name = "max_design_temp", precision = 21, scale = 2)
    private BigDecimal maxDesignTemp;

    @Size(max = 255)
    @Column(name = "heat_number", length = 255)
    private String heatNumber;

    @Size(max = 255)
    @Column(name = "coord", length = 255)
    private String coord;

    @Column(name = "design_coord", precision = 21, scale = 2)
    private BigDecimal designCoord;

    @Column(name = "kp_start", precision = 21, scale = 2)
    private BigDecimal kpStart;

    @Column(name = "kp_end", precision = 21, scale = 2)
    private BigDecimal kpEnd;

    @NotNull
    @Column(name = "is_current_flag", nullable = false)
    private Integer isCurrentFlag;

    @NotNull
    @Column(name = "id_status", nullable = false)
    private Long idStatus;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Size(max = 255)
    @Column(name = "jhi_comment", length = 255)
    private String comment;

    @Column(name = "date_create")
    private Instant dateCreate;

    @Column(name = "date_edit")
    private Instant dateEdit;

    @Size(max = 255)
    @Column(name = "creator", length = 255)
    private String creator;

    @Size(max = 255)
    @Column(name = "editor", length = 255)
    private String editor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("buckleArrestorHists")
    private BuckleArrestor id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("buckleArrestorHists")
    private PipelineSection idPipelineSection;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListBucklarrType idType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListInternalCoating idInternalCoatType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListExternalCoating idExternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListNominalWallThickness idNominalWallThickness;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private Pipejoint idPipeJoint;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListBucklarrManufacturer idManufacturer;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListBucklarrSpecification idSpecification;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListLongSeamWeldType idLongSeamWeldType;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListFabricationType idFabricationType;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListMaterial idMaterial;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListMillLocation idMillLocation;

    @ManyToOne
    @JsonIgnoreProperties("buckleArrestorHists")
    private ListSteelGrade idSteelGrade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public BuckleArrestorHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public BuckleArrestorHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public BuckleArrestorHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public BuckleArrestorHist serialNum(String serialNum) {
        this.serialNum = serialNum;
        return this;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public BuckleArrestorHist diameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
        return this;
    }

    public void setDiameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public BigDecimal getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public BuckleArrestorHist internalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
        return this;
    }

    public void setInternalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public BuckleArrestorHist externalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
        return this;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public Integer getIsConcrCoating() {
        return isConcrCoating;
    }

    public BuckleArrestorHist isConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
        return this;
    }

    public void setIsConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimal getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public BuckleArrestorHist concrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
        return this;
    }

    public void setConcrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimal getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public BuckleArrestorHist concrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
        return this;
    }

    public void setConcrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimal getMeasColWallThickness() {
        return measColWallThickness;
    }

    public BuckleArrestorHist measColWallThickness(BigDecimal measColWallThickness) {
        this.measColWallThickness = measColWallThickness;
        return this;
    }

    public void setMeasColWallThickness(BigDecimal measColWallThickness) {
        this.measColWallThickness = measColWallThickness;
    }

    public BigDecimal getMeasPipeWallThickness() {
        return measPipeWallThickness;
    }

    public BuckleArrestorHist measPipeWallThickness(BigDecimal measPipeWallThickness) {
        this.measPipeWallThickness = measPipeWallThickness;
        return this;
    }

    public void setMeasPipeWallThickness(BigDecimal measPipeWallThickness) {
        this.measPipeWallThickness = measPipeWallThickness;
    }

    public Integer getColLength() {
        return colLength;
    }

    public BuckleArrestorHist colLength(Integer colLength) {
        this.colLength = colLength;
        return this;
    }

    public void setColLength(Integer colLength) {
        this.colLength = colLength;
    }

    public Integer getPipeLength() {
        return pipeLength;
    }

    public BuckleArrestorHist pipeLength(Integer pipeLength) {
        this.pipeLength = pipeLength;
        return this;
    }

    public void setPipeLength(Integer pipeLength) {
        this.pipeLength = pipeLength;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BuckleArrestorHist weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmts() {
        return smts;
    }

    public BuckleArrestorHist smts(BigDecimal smts) {
        this.smts = smts;
        return this;
    }

    public void setSmts(BigDecimal smts) {
        this.smts = smts;
    }

    public BigDecimal getSmys() {
        return smys;
    }

    public BuckleArrestorHist smys(BigDecimal smys) {
        this.smys = smys;
        return this;
    }

    public void setSmys(BigDecimal smys) {
        this.smys = smys;
    }

    public BigDecimal getSdbm() {
        return sdbm;
    }

    public BuckleArrestorHist sdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
        return this;
    }

    public void setSdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimal getSdaf() {
        return sdaf;
    }

    public BuckleArrestorHist sdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
        return this;
    }

    public void setSdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimal getSdcs() {
        return sdcs;
    }

    public BuckleArrestorHist sdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
        return this;
    }

    public void setSdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimal getAllowTensStrain() {
        return allowTensStrain;
    }

    public BuckleArrestorHist allowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
        return this;
    }

    public void setAllowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public Integer getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public BuckleArrestorHist corrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
        return this;
    }

    public void setCorrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public Integer getFabricationAllowance() {
        return fabricationAllowance;
    }

    public BuckleArrestorHist fabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
        return this;
    }

    public void setFabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public BuckleArrestorHist isBurial(Integer isBurial) {
        this.isBurial = isBurial;
        return this;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public Integer getBurialDepth() {
        return burialDepth;
    }

    public BuckleArrestorHist burialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
        return this;
    }

    public void setBurialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
    }

    public Integer getFactBurialDepth() {
        return factBurialDepth;
    }

    public BuckleArrestorHist factBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
        return this;
    }

    public void setFactBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
    }

    public LocalDate getDateManufactured() {
        return dateManufactured;
    }

    public BuckleArrestorHist dateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
        return this;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimal getMillTestPressure() {
        return millTestPressure;
    }

    public BuckleArrestorHist millTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
        return this;
    }

    public void setMillTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimal getDesignPressure() {
        return designPressure;
    }

    public BuckleArrestorHist designPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
        return this;
    }

    public void setDesignPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimal getIncidentalPressure() {
        return incidentalPressure;
    }

    public BuckleArrestorHist incidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
        return this;
    }

    public void setIncidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDate getDateInstalled() {
        return dateInstalled;
    }

    public BuckleArrestorHist dateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
        return this;
    }

    public void setDateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimal getSeamOrient() {
        return seamOrient;
    }

    public BuckleArrestorHist seamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
        return this;
    }

    public void setSeamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimal getSeamAngle() {
        return seamAngle;
    }

    public BuckleArrestorHist seamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
        return this;
    }

    public void setSeamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public BuckleArrestorHist azimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
        return this;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimal getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public BuckleArrestorHist seabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
        return this;
    }

    public void setSeabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimal getVerticalAngle() {
        return verticalAngle;
    }

    public BuckleArrestorHist verticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
        return this;
    }

    public void setVerticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimal getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public BuckleArrestorHist heatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
        return this;
    }

    public void setHeatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimal getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public BuckleArrestorHist maxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
        return this;
    }

    public void setMaxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public String getHeatNumber() {
        return heatNumber;
    }

    public BuckleArrestorHist heatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
        return this;
    }

    public void setHeatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
    }

    public String getCoord() {
        return coord;
    }

    public BuckleArrestorHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getDesignCoord() {
        return designCoord;
    }

    public BuckleArrestorHist designCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
        return this;
    }

    public void setDesignCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public BuckleArrestorHist kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public BuckleArrestorHist kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public BuckleArrestorHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public BuckleArrestorHist idStatus(Long idStatus) {
        this.idStatus = idStatus;
        return this;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescription() {
        return description;
    }

    public BuckleArrestorHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public BuckleArrestorHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public BuckleArrestorHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public BuckleArrestorHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public BuckleArrestorHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public BuckleArrestorHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BuckleArrestor getId() {
        return id;
    }

    public BuckleArrestorHist id(BuckleArrestor buckleArrestor) {
        this.id = buckleArrestor;
        return this;
    }

    public void setId(BuckleArrestor buckleArrestor) {
        this.id = buckleArrestor;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public BuckleArrestorHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListBucklarrType getIdType() {
        return idType;
    }

    public BuckleArrestorHist idType(ListBucklarrType listBucklarrType) {
        this.idType = listBucklarrType;
        return this;
    }

    public void setIdType(ListBucklarrType listBucklarrType) {
        this.idType = listBucklarrType;
    }

    public ListInternalCoating getIdInternalCoatType() {
        return idInternalCoatType;
    }

    public BuckleArrestorHist idInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
        return this;
    }

    public void setIdInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
    }

    public ListExternalCoating getIdExternalCoatType() {
        return idExternalCoatType;
    }

    public BuckleArrestorHist idExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
        return this;
    }

    public void setIdExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
    }

    public ListNominalWallThickness getIdNominalWallThickness() {
        return idNominalWallThickness;
    }

    public BuckleArrestorHist idNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
        return this;
    }

    public void setIdNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
    }

    public Pipejoint getIdPipeJoint() {
        return idPipeJoint;
    }

    public BuckleArrestorHist idPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
        return this;
    }

    public void setIdPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
    }

    public ListBucklarrManufacturer getIdManufacturer() {
        return idManufacturer;
    }

    public BuckleArrestorHist idManufacturer(ListBucklarrManufacturer listBucklarrManufacturer) {
        this.idManufacturer = listBucklarrManufacturer;
        return this;
    }

    public void setIdManufacturer(ListBucklarrManufacturer listBucklarrManufacturer) {
        this.idManufacturer = listBucklarrManufacturer;
    }

    public ListBucklarrSpecification getIdSpecification() {
        return idSpecification;
    }

    public BuckleArrestorHist idSpecification(ListBucklarrSpecification listBucklarrSpecification) {
        this.idSpecification = listBucklarrSpecification;
        return this;
    }

    public void setIdSpecification(ListBucklarrSpecification listBucklarrSpecification) {
        this.idSpecification = listBucklarrSpecification;
    }

    public ListLongSeamWeldType getIdLongSeamWeldType() {
        return idLongSeamWeldType;
    }

    public BuckleArrestorHist idLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
        return this;
    }

    public void setIdLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
    }

    public ListFabricationType getIdFabricationType() {
        return idFabricationType;
    }

    public BuckleArrestorHist idFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
        return this;
    }

    public void setIdFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public BuckleArrestorHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListMillLocation getIdMillLocation() {
        return idMillLocation;
    }

    public BuckleArrestorHist idMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
        return this;
    }

    public void setIdMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
    }

    public ListSteelGrade getIdSteelGrade() {
        return idSteelGrade;
    }

    public BuckleArrestorHist idSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
        return this;
    }

    public void setIdSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuckleArrestorHist)) {
            return false;
        }
        return id != null && id.equals(((BuckleArrestorHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BuckleArrestorHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", serialNum='" + getSerialNum() + "'" +
            ", diameterOuterSteel=" + getDiameterOuterSteel() +
            ", internalCoatThickness=" + getInternalCoatThickness() +
            ", externalCoatThickness=" + getExternalCoatThickness() +
            ", isConcrCoating=" + getIsConcrCoating() +
            ", concrCoatThickness=" + getConcrCoatThickness() +
            ", concrCoatDensity=" + getConcrCoatDensity() +
            ", measColWallThickness=" + getMeasColWallThickness() +
            ", measPipeWallThickness=" + getMeasPipeWallThickness() +
            ", colLength=" + getColLength() +
            ", pipeLength=" + getPipeLength() +
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
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", idStatus=" + getIdStatus() +
            ", description='" + getDescription() + "'" +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
