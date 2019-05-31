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
 * Time-dependent attribute table. One table  rows corresponds to one bend and time period
 */
@Entity
@Table(name = "bend_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BendHist implements Serializable {

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
    @Column(name = "num", length = 255)
    private String num;

    @Column(name = "radius", precision = 21, scale = 2)
    private BigDecimal radius;

    @Column(name = "diameter_outer_steel")
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

    @Column(name = "kp_start", precision = 21, scale = 2)
    private BigDecimal kpStart;

    @Column(name = "kp_end", precision = 21, scale = 2)
    private BigDecimal kpEnd;

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
    @JsonIgnoreProperties("bendHists")
    private Bend id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("bendHists")
    private PipelineSection idPipelineSection;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListBendType idType;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListInternalCoating idInternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListExternalCoating idExternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListNominalWallThickness idNominalWallThickness;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private Pipejoint idPipeJoint;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListBendManufacturer idManufacturer;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListBendSpecification idSpecification;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListLongSeamWeldType idLongSeamWeldType;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListFabricationType idFabricationType;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListMaterial idMaterial;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListMillLocation idMillLocation;

    @ManyToOne
    @JsonIgnoreProperties("bendHists")
    private ListSteelGrade idSteelGrade;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("bendHists")
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

    public BendHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public BendHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public BendHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public String getNum() {
        return num;
    }

    public BendHist num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public BendHist radius(BigDecimal radius) {
        this.radius = radius;
        return this;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }

    public Integer getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public BendHist diameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
        return this;
    }

    public void setDiameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public BigDecimal getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public BendHist internalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
        return this;
    }

    public void setInternalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public BendHist externalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
        return this;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public Integer getIsConcrCoating() {
        return isConcrCoating;
    }

    public BendHist isConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
        return this;
    }

    public void setIsConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimal getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public BendHist concrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
        return this;
    }

    public void setConcrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimal getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public BendHist concrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
        return this;
    }

    public void setConcrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimal getMeasWallThickness() {
        return measWallThickness;
    }

    public BendHist measWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
        return this;
    }

    public void setMeasWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
    }

    public Integer getLength() {
        return length;
    }

    public BendHist length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BendHist weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmts() {
        return smts;
    }

    public BendHist smts(BigDecimal smts) {
        this.smts = smts;
        return this;
    }

    public void setSmts(BigDecimal smts) {
        this.smts = smts;
    }

    public BigDecimal getSmys() {
        return smys;
    }

    public BendHist smys(BigDecimal smys) {
        this.smys = smys;
        return this;
    }

    public void setSmys(BigDecimal smys) {
        this.smys = smys;
    }

    public BigDecimal getSdbm() {
        return sdbm;
    }

    public BendHist sdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
        return this;
    }

    public void setSdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimal getSdaf() {
        return sdaf;
    }

    public BendHist sdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
        return this;
    }

    public void setSdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimal getSdcs() {
        return sdcs;
    }

    public BendHist sdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
        return this;
    }

    public void setSdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimal getAllowTensStrain() {
        return allowTensStrain;
    }

    public BendHist allowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
        return this;
    }

    public void setAllowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public Integer getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public BendHist corrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
        return this;
    }

    public void setCorrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public Integer getFabricationAllowance() {
        return fabricationAllowance;
    }

    public BendHist fabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
        return this;
    }

    public void setFabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public BendHist isBurial(Integer isBurial) {
        this.isBurial = isBurial;
        return this;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public Integer getBurialDepth() {
        return burialDepth;
    }

    public BendHist burialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
        return this;
    }

    public void setBurialDepth(Integer burialDepth) {
        this.burialDepth = burialDepth;
    }

    public Integer getFactBurialDepth() {
        return factBurialDepth;
    }

    public BendHist factBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
        return this;
    }

    public void setFactBurialDepth(Integer factBurialDepth) {
        this.factBurialDepth = factBurialDepth;
    }

    public LocalDate getDateManufactured() {
        return dateManufactured;
    }

    public BendHist dateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
        return this;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimal getMillTestPressure() {
        return millTestPressure;
    }

    public BendHist millTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
        return this;
    }

    public void setMillTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimal getDesignPressure() {
        return designPressure;
    }

    public BendHist designPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
        return this;
    }

    public void setDesignPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimal getIncidentalPressure() {
        return incidentalPressure;
    }

    public BendHist incidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
        return this;
    }

    public void setIncidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDate getDateInstalled() {
        return dateInstalled;
    }

    public BendHist dateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
        return this;
    }

    public void setDateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimal getSeamOrient() {
        return seamOrient;
    }

    public BendHist seamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
        return this;
    }

    public void setSeamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimal getSeamAngle() {
        return seamAngle;
    }

    public BendHist seamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
        return this;
    }

    public void setSeamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public BendHist azimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
        return this;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimal getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public BendHist seabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
        return this;
    }

    public void setSeabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimal getVerticalAngle() {
        return verticalAngle;
    }

    public BendHist verticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
        return this;
    }

    public void setVerticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimal getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public BendHist heatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
        return this;
    }

    public void setHeatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimal getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public BendHist maxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
        return this;
    }

    public void setMaxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public String getHeatNumber() {
        return heatNumber;
    }

    public BendHist heatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
        return this;
    }

    public void setHeatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
    }

    public String getCoord() {
        return coord;
    }

    public BendHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getDesignCoord() {
        return designCoord;
    }

    public BendHist designCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
        return this;
    }

    public void setDesignCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public BendHist kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public BendHist kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public BendHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public BendHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public BendHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public BendHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public BendHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public BendHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public BendHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Bend getId() {
        return id;
    }

    public BendHist id(Bend bend) {
        this.id = bend;
        return this;
    }

    public void setId(Bend bend) {
        this.id = bend;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public BendHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListBendType getIdType() {
        return idType;
    }

    public BendHist idType(ListBendType listBendType) {
        this.idType = listBendType;
        return this;
    }

    public void setIdType(ListBendType listBendType) {
        this.idType = listBendType;
    }

    public ListInternalCoating getIdInternalCoatType() {
        return idInternalCoatType;
    }

    public BendHist idInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
        return this;
    }

    public void setIdInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
    }

    public ListExternalCoating getIdExternalCoatType() {
        return idExternalCoatType;
    }

    public BendHist idExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
        return this;
    }

    public void setIdExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
    }

    public ListNominalWallThickness getIdNominalWallThickness() {
        return idNominalWallThickness;
    }

    public BendHist idNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
        return this;
    }

    public void setIdNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
    }

    public Pipejoint getIdPipeJoint() {
        return idPipeJoint;
    }

    public BendHist idPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
        return this;
    }

    public void setIdPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
    }

    public ListBendManufacturer getIdManufacturer() {
        return idManufacturer;
    }

    public BendHist idManufacturer(ListBendManufacturer listBendManufacturer) {
        this.idManufacturer = listBendManufacturer;
        return this;
    }

    public void setIdManufacturer(ListBendManufacturer listBendManufacturer) {
        this.idManufacturer = listBendManufacturer;
    }

    public ListBendSpecification getIdSpecification() {
        return idSpecification;
    }

    public BendHist idSpecification(ListBendSpecification listBendSpecification) {
        this.idSpecification = listBendSpecification;
        return this;
    }

    public void setIdSpecification(ListBendSpecification listBendSpecification) {
        this.idSpecification = listBendSpecification;
    }

    public ListLongSeamWeldType getIdLongSeamWeldType() {
        return idLongSeamWeldType;
    }

    public BendHist idLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
        return this;
    }

    public void setIdLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
    }

    public ListFabricationType getIdFabricationType() {
        return idFabricationType;
    }

    public BendHist idFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
        return this;
    }

    public void setIdFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public BendHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListMillLocation getIdMillLocation() {
        return idMillLocation;
    }

    public BendHist idMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
        return this;
    }

    public void setIdMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
    }

    public ListSteelGrade getIdSteelGrade() {
        return idSteelGrade;
    }

    public BendHist idSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
        return this;
    }

    public void setIdSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public BendHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof BendHist)) {
            return false;
        }
        return id != null && id.equals(((BendHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BendHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", num='" + getNum() + "'" +
            ", radius=" + getRadius() +
            ", diameterOuterSteel=" + getDiameterOuterSteel() +
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
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
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
