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
 * Tee. Time-dependent attribute table. One table  rows corresponds to one tee and time period
 */
@Entity
@Table(name = "tee_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TeeHist implements Serializable {

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
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "diameter_outer_steel")
    private Integer diameterOuterSteel;

    @Column(name = "diameter_outer_steel_br")
    private Integer diameterOuterSteelBr;

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

    @Column(name = "meas_wall_thickness", precision = 21, scale = 2)
    private BigDecimal measWallThickness;

    @Column(name = "length")
    private Integer length;

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

    @Column(name = "kp_inst", precision = 21, scale = 2)
    private BigDecimal kpInst;

    @NotNull
    @Column(name = "is_current_flag", nullable = false)
    private Integer isCurrentFlag;

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
    @JsonIgnoreProperties("teeHists")
    private Tee id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("teeHists")
    private PipelineSection idPipelineSection;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListTeeType idType;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListInternalCoating idInternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListExternalCoating idExternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListNominalWallThickness idNominalWallThickness;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private Pipejoint idPipeJoint;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListTeeManufacturer idManufacturer;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListTeeSpecification idSpecification;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListLongSeamWeldType idLongSeamWeldType;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListFabricationType idFabricationType;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListMaterial idMaterial;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListMillLocation idMillLocation;

    @ManyToOne
    @JsonIgnoreProperties("teeHists")
    private ListSteelGrade idSteelGrade;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("teeHists")
    private ListObjectStatus idStatus;

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

    public TeeHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public TeeHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public TeeHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public String getName() {
        return name;
    }

    public TeeHist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public TeeHist diameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
        return this;
    }

    public void setDiameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public Integer getDiameterOuterSteelBr() {
        return diameterOuterSteelBr;
    }

    public TeeHist diameterOuterSteelBr(Integer diameterOuterSteelBr) {
        this.diameterOuterSteelBr = diameterOuterSteelBr;
        return this;
    }

    public void setDiameterOuterSteelBr(Integer diameterOuterSteelBr) {
        this.diameterOuterSteelBr = diameterOuterSteelBr;
    }

    public BigDecimal getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public TeeHist internalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
        return this;
    }

    public void setInternalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public TeeHist externalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
        return this;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public Integer getIsConcrCoating() {
        return isConcrCoating;
    }

    public TeeHist isConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
        return this;
    }

    public void setIsConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimal getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public TeeHist concrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
        return this;
    }

    public void setConcrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimal getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public TeeHist concrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
        return this;
    }

    public void setConcrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimal getMeasWallThickness() {
        return measWallThickness;
    }

    public TeeHist measWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
        return this;
    }

    public void setMeasWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
    }

    public Integer getLength() {
        return length;
    }

    public TeeHist length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public TeeHist weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmts() {
        return smts;
    }

    public TeeHist smts(BigDecimal smts) {
        this.smts = smts;
        return this;
    }

    public void setSmts(BigDecimal smts) {
        this.smts = smts;
    }

    public BigDecimal getSmys() {
        return smys;
    }

    public TeeHist smys(BigDecimal smys) {
        this.smys = smys;
        return this;
    }

    public void setSmys(BigDecimal smys) {
        this.smys = smys;
    }

    public BigDecimal getSdbm() {
        return sdbm;
    }

    public TeeHist sdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
        return this;
    }

    public void setSdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimal getSdaf() {
        return sdaf;
    }

    public TeeHist sdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
        return this;
    }

    public void setSdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimal getSdcs() {
        return sdcs;
    }

    public TeeHist sdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
        return this;
    }

    public void setSdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimal getAllowTensStrain() {
        return allowTensStrain;
    }

    public TeeHist allowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
        return this;
    }

    public void setAllowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public Integer getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public TeeHist corrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
        return this;
    }

    public void setCorrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public Integer getFabricationAllowance() {
        return fabricationAllowance;
    }

    public TeeHist fabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
        return this;
    }

    public void setFabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public TeeHist isBurial(Integer isBurial) {
        this.isBurial = isBurial;
        return this;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public Integer getBurialDepth() {
        return burialDepth;
    }

    public TeeHist burialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
        return this;
    }

    public void setBurialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
    }

    public Integer getFactBurialDepth() {
        return factBurialDepth;
    }

    public TeeHist factBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
        return this;
    }

    public void setFactBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
    }

    public LocalDate getDateManufactured() {
        return dateManufactured;
    }

    public TeeHist dateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
        return this;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimal getMillTestPressure() {
        return millTestPressure;
    }

    public TeeHist millTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
        return this;
    }

    public void setMillTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimal getDesignPressure() {
        return designPressure;
    }

    public TeeHist designPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
        return this;
    }

    public void setDesignPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimal getIncidentalPressure() {
        return incidentalPressure;
    }

    public TeeHist incidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
        return this;
    }

    public void setIncidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDate getDateInstalled() {
        return dateInstalled;
    }

    public TeeHist dateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
        return this;
    }

    public void setDateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimal getSeamOrient() {
        return seamOrient;
    }

    public TeeHist seamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
        return this;
    }

    public void setSeamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimal getSeamAngle() {
        return seamAngle;
    }

    public TeeHist seamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
        return this;
    }

    public void setSeamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public TeeHist azimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
        return this;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimal getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public TeeHist seabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
        return this;
    }

    public void setSeabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimal getVerticalAngle() {
        return verticalAngle;
    }

    public TeeHist verticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
        return this;
    }

    public void setVerticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimal getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public TeeHist heatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
        return this;
    }

    public void setHeatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimal getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public TeeHist maxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
        return this;
    }

    public void setMaxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public String getHeatNumber() {
        return heatNumber;
    }

    public TeeHist heatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
        return this;
    }

    public void setHeatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
    }

    public String getCoord() {
        return coord;
    }

    public TeeHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getDesignCoord() {
        return designCoord;
    }

    public TeeHist designCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
        return this;
    }

    public void setDesignCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
    }

    public BigDecimal getKpInst() {
        return kpInst;
    }

    public TeeHist kpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
        return this;
    }

    public void setKpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public TeeHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public TeeHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public TeeHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public TeeHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public TeeHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public TeeHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public TeeHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Tee getId() {
        return id;
    }

    public TeeHist id(Tee tee) {
        this.id = tee;
        return this;
    }

    public void setId(Tee tee) {
        this.id = tee;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public TeeHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListTeeType getIdType() {
        return idType;
    }

    public TeeHist idType(ListTeeType listTeeType) {
        this.idType = listTeeType;
        return this;
    }

    public void setIdType(ListTeeType listTeeType) {
        this.idType = listTeeType;
    }

    public ListInternalCoating getIdInternalCoatType() {
        return idInternalCoatType;
    }

    public TeeHist idInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
        return this;
    }

    public void setIdInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
    }

    public ListExternalCoating getIdExternalCoatType() {
        return idExternalCoatType;
    }

    public TeeHist idExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
        return this;
    }

    public void setIdExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
    }

    public ListNominalWallThickness getIdNominalWallThickness() {
        return idNominalWallThickness;
    }

    public TeeHist idNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
        return this;
    }

    public void setIdNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
    }

    public Pipejoint getIdPipeJoint() {
        return idPipeJoint;
    }

    public TeeHist idPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
        return this;
    }

    public void setIdPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
    }

    public ListTeeManufacturer getIdManufacturer() {
        return idManufacturer;
    }

    public TeeHist idManufacturer(ListTeeManufacturer listTeeManufacturer) {
        this.idManufacturer = listTeeManufacturer;
        return this;
    }

    public void setIdManufacturer(ListTeeManufacturer listTeeManufacturer) {
        this.idManufacturer = listTeeManufacturer;
    }

    public ListTeeSpecification getIdSpecification() {
        return idSpecification;
    }

    public TeeHist idSpecification(ListTeeSpecification listTeeSpecification) {
        this.idSpecification = listTeeSpecification;
        return this;
    }

    public void setIdSpecification(ListTeeSpecification listTeeSpecification) {
        this.idSpecification = listTeeSpecification;
    }

    public ListLongSeamWeldType getIdLongSeamWeldType() {
        return idLongSeamWeldType;
    }

    public TeeHist idLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
        return this;
    }

    public void setIdLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
    }

    public ListFabricationType getIdFabricationType() {
        return idFabricationType;
    }

    public TeeHist idFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
        return this;
    }

    public void setIdFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public TeeHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListMillLocation getIdMillLocation() {
        return idMillLocation;
    }

    public TeeHist idMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
        return this;
    }

    public void setIdMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
    }

    public ListSteelGrade getIdSteelGrade() {
        return idSteelGrade;
    }

    public TeeHist idSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
        return this;
    }

    public void setIdSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public TeeHist idStatus(ListObjectStatus listObjectStatus) {
        this.idStatus = listObjectStatus;
        return this;
    }

    public void setIdStatus(ListObjectStatus listObjectStatus) {
        this.idStatus = listObjectStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeeHist)) {
            return false;
        }
        return id != null && id.equals(((TeeHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TeeHist{" +
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
            "}";
    }
}
