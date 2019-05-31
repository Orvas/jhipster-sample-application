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
 * Valve. Time-dependent attribute table. One table  rows corresponds to one valve and time period
 */
@Entity
@Table(name = "valve_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValveHist implements Serializable {

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

    @Size(max = 255)
    @Column(name = "serial_num", length = 255)
    private String serialNum;

    @Size(max = 255)
    @Column(name = "model", length = 255)
    private String model;

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

    @Column(name = "date_manufactured")
    private LocalDate dateManufactured;

    @Column(name = "mill_test_pressure", precision = 21, scale = 2)
    private BigDecimal millTestPressure;

    @Column(name = "design_pressure", precision = 21, scale = 2)
    private BigDecimal designPressure;

    @Column(name = "design_pressure_in", precision = 21, scale = 2)
    private BigDecimal designPressureIn;

    @Column(name = "design_pressure_out", precision = 21, scale = 2)
    private BigDecimal designPressureOut;

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
    @JsonIgnoreProperties("valveHists")
    private Valve id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("valveHists")
    private PipelineSection idPipelineSection;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListValveType idType;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListInternalCoating idInternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListExternalCoating idExternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListNominalWallThickness idNominalWallThickness;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private Pipejoint idPipeJoint;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListValveManufacturer idManufacturer;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListValveSpecification idSpecification;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListValveFunction idFunction;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListLongSeamWeldType idLongSeamWeldType;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListFabricationType idFabricationType;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListMaterial idMaterial;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListMillLocation idMillLocation;

    @ManyToOne
    @JsonIgnoreProperties("valveHists")
    private ListSteelGrade idSteelGrade;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("valveHists")
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

    public ValveHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public ValveHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public ValveHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public String getName() {
        return name;
    }

    public ValveHist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public ValveHist serialNum(String serialNum) {
        this.serialNum = serialNum;
        return this;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getModel() {
        return model;
    }

    public ValveHist model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getDiameterOuterSteel() {
        return diameterOuterSteel;
    }

    public ValveHist diameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
        return this;
    }

    public void setDiameterOuterSteel(Integer diameterOuterSteel) {
        this.diameterOuterSteel = diameterOuterSteel;
    }

    public BigDecimal getInternalCoatThickness() {
        return internalCoatThickness;
    }

    public ValveHist internalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
        return this;
    }

    public void setInternalCoatThickness(BigDecimal internalCoatThickness) {
        this.internalCoatThickness = internalCoatThickness;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public ValveHist externalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
        return this;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public Integer getIsConcrCoating() {
        return isConcrCoating;
    }

    public ValveHist isConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
        return this;
    }

    public void setIsConcrCoating(Integer isConcrCoating) {
        this.isConcrCoating = isConcrCoating;
    }

    public BigDecimal getConcrCoatThickness() {
        return concrCoatThickness;
    }

    public ValveHist concrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
        return this;
    }

    public void setConcrCoatThickness(BigDecimal concrCoatThickness) {
        this.concrCoatThickness = concrCoatThickness;
    }

    public BigDecimal getConcrCoatDensity() {
        return concrCoatDensity;
    }

    public ValveHist concrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
        return this;
    }

    public void setConcrCoatDensity(BigDecimal concrCoatDensity) {
        this.concrCoatDensity = concrCoatDensity;
    }

    public BigDecimal getMeasWallThickness() {
        return measWallThickness;
    }

    public ValveHist measWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
        return this;
    }

    public void setMeasWallThickness(BigDecimal measWallThickness) {
        this.measWallThickness = measWallThickness;
    }

    public Integer getLength() {
        return length;
    }

    public ValveHist length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public ValveHist weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmts() {
        return smts;
    }

    public ValveHist smts(BigDecimal smts) {
        this.smts = smts;
        return this;
    }

    public void setSmts(BigDecimal smts) {
        this.smts = smts;
    }

    public BigDecimal getSmys() {
        return smys;
    }

    public ValveHist smys(BigDecimal smys) {
        this.smys = smys;
        return this;
    }

    public void setSmys(BigDecimal smys) {
        this.smys = smys;
    }

    public BigDecimal getSdbm() {
        return sdbm;
    }

    public ValveHist sdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
        return this;
    }

    public void setSdbm(BigDecimal sdbm) {
        this.sdbm = sdbm;
    }

    public BigDecimal getSdaf() {
        return sdaf;
    }

    public ValveHist sdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
        return this;
    }

    public void setSdaf(BigDecimal sdaf) {
        this.sdaf = sdaf;
    }

    public BigDecimal getSdcs() {
        return sdcs;
    }

    public ValveHist sdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
        return this;
    }

    public void setSdcs(BigDecimal sdcs) {
        this.sdcs = sdcs;
    }

    public BigDecimal getAllowTensStrain() {
        return allowTensStrain;
    }

    public ValveHist allowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
        return this;
    }

    public void setAllowTensStrain(BigDecimal allowTensStrain) {
        this.allowTensStrain = allowTensStrain;
    }

    public Integer getCorrosionAllowance() {
        return corrosionAllowance;
    }

    public ValveHist corrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
        return this;
    }

    public void setCorrosionAllowance(Integer corrosionAllowance) {
        this.corrosionAllowance = corrosionAllowance;
    }

    public Integer getFabricationAllowance() {
        return fabricationAllowance;
    }

    public ValveHist fabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
        return this;
    }

    public void setFabricationAllowance(Integer fabricationAllowance) {
        this.fabricationAllowance = fabricationAllowance;
    }

    public LocalDate getDateManufactured() {
        return dateManufactured;
    }

    public ValveHist dateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
        return this;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public BigDecimal getMillTestPressure() {
        return millTestPressure;
    }

    public ValveHist millTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
        return this;
    }

    public void setMillTestPressure(BigDecimal millTestPressure) {
        this.millTestPressure = millTestPressure;
    }

    public BigDecimal getDesignPressure() {
        return designPressure;
    }

    public ValveHist designPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
        return this;
    }

    public void setDesignPressure(BigDecimal designPressure) {
        this.designPressure = designPressure;
    }

    public BigDecimal getDesignPressureIn() {
        return designPressureIn;
    }

    public ValveHist designPressureIn(BigDecimal designPressureIn) {
        this.designPressureIn = designPressureIn;
        return this;
    }

    public void setDesignPressureIn(BigDecimal designPressureIn) {
        this.designPressureIn = designPressureIn;
    }

    public BigDecimal getDesignPressureOut() {
        return designPressureOut;
    }

    public ValveHist designPressureOut(BigDecimal designPressureOut) {
        this.designPressureOut = designPressureOut;
        return this;
    }

    public void setDesignPressureOut(BigDecimal designPressureOut) {
        this.designPressureOut = designPressureOut;
    }

    public BigDecimal getIncidentalPressure() {
        return incidentalPressure;
    }

    public ValveHist incidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
        return this;
    }

    public void setIncidentalPressure(BigDecimal incidentalPressure) {
        this.incidentalPressure = incidentalPressure;
    }

    public LocalDate getDateInstalled() {
        return dateInstalled;
    }

    public ValveHist dateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
        return this;
    }

    public void setDateInstalled(LocalDate dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public BigDecimal getSeamOrient() {
        return seamOrient;
    }

    public ValveHist seamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
        return this;
    }

    public void setSeamOrient(BigDecimal seamOrient) {
        this.seamOrient = seamOrient;
    }

    public BigDecimal getSeamAngle() {
        return seamAngle;
    }

    public ValveHist seamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
        return this;
    }

    public void setSeamAngle(BigDecimal seamAngle) {
        this.seamAngle = seamAngle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public ValveHist azimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
        return this;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }

    public BigDecimal getSeabInstallTemp() {
        return seabInstallTemp;
    }

    public ValveHist seabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
        return this;
    }

    public void setSeabInstallTemp(BigDecimal seabInstallTemp) {
        this.seabInstallTemp = seabInstallTemp;
    }

    public BigDecimal getVerticalAngle() {
        return verticalAngle;
    }

    public ValveHist verticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
        return this;
    }

    public void setVerticalAngle(BigDecimal verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public BigDecimal getHeatTreatDurat() {
        return heatTreatDurat;
    }

    public ValveHist heatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
        return this;
    }

    public void setHeatTreatDurat(BigDecimal heatTreatDurat) {
        this.heatTreatDurat = heatTreatDurat;
    }

    public BigDecimal getMaxDesignTemp() {
        return maxDesignTemp;
    }

    public ValveHist maxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
        return this;
    }

    public void setMaxDesignTemp(BigDecimal maxDesignTemp) {
        this.maxDesignTemp = maxDesignTemp;
    }

    public String getHeatNumber() {
        return heatNumber;
    }

    public ValveHist heatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
        return this;
    }

    public void setHeatNumber(String heatNumber) {
        this.heatNumber = heatNumber;
    }

    public String getCoord() {
        return coord;
    }

    public ValveHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getDesignCoord() {
        return designCoord;
    }

    public ValveHist designCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
        return this;
    }

    public void setDesignCoord(BigDecimal designCoord) {
        this.designCoord = designCoord;
    }

    public BigDecimal getKpInst() {
        return kpInst;
    }

    public ValveHist kpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
        return this;
    }

    public void setKpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public ValveHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public ValveHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public ValveHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public ValveHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public ValveHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public ValveHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public ValveHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Valve getId() {
        return id;
    }

    public ValveHist id(Valve valve) {
        this.id = valve;
        return this;
    }

    public void setId(Valve valve) {
        this.id = valve;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public ValveHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListValveType getIdType() {
        return idType;
    }

    public ValveHist idType(ListValveType listValveType) {
        this.idType = listValveType;
        return this;
    }

    public void setIdType(ListValveType listValveType) {
        this.idType = listValveType;
    }

    public ListInternalCoating getIdInternalCoatType() {
        return idInternalCoatType;
    }

    public ValveHist idInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
        return this;
    }

    public void setIdInternalCoatType(ListInternalCoating listInternalCoating) {
        this.idInternalCoatType = listInternalCoating;
    }

    public ListExternalCoating getIdExternalCoatType() {
        return idExternalCoatType;
    }

    public ValveHist idExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
        return this;
    }

    public void setIdExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
    }

    public ListNominalWallThickness getIdNominalWallThickness() {
        return idNominalWallThickness;
    }

    public ValveHist idNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
        return this;
    }

    public void setIdNominalWallThickness(ListNominalWallThickness listNominalWallThickness) {
        this.idNominalWallThickness = listNominalWallThickness;
    }

    public Pipejoint getIdPipeJoint() {
        return idPipeJoint;
    }

    public ValveHist idPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
        return this;
    }

    public void setIdPipeJoint(Pipejoint pipejoint) {
        this.idPipeJoint = pipejoint;
    }

    public ListValveManufacturer getIdManufacturer() {
        return idManufacturer;
    }

    public ValveHist idManufacturer(ListValveManufacturer listValveManufacturer) {
        this.idManufacturer = listValveManufacturer;
        return this;
    }

    public void setIdManufacturer(ListValveManufacturer listValveManufacturer) {
        this.idManufacturer = listValveManufacturer;
    }

    public ListValveSpecification getIdSpecification() {
        return idSpecification;
    }

    public ValveHist idSpecification(ListValveSpecification listValveSpecification) {
        this.idSpecification = listValveSpecification;
        return this;
    }

    public void setIdSpecification(ListValveSpecification listValveSpecification) {
        this.idSpecification = listValveSpecification;
    }

    public ListValveFunction getIdFunction() {
        return idFunction;
    }

    public ValveHist idFunction(ListValveFunction listValveFunction) {
        this.idFunction = listValveFunction;
        return this;
    }

    public void setIdFunction(ListValveFunction listValveFunction) {
        this.idFunction = listValveFunction;
    }

    public ListLongSeamWeldType getIdLongSeamWeldType() {
        return idLongSeamWeldType;
    }

    public ValveHist idLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
        return this;
    }

    public void setIdLongSeamWeldType(ListLongSeamWeldType listLongSeamWeldType) {
        this.idLongSeamWeldType = listLongSeamWeldType;
    }

    public ListFabricationType getIdFabricationType() {
        return idFabricationType;
    }

    public ValveHist idFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
        return this;
    }

    public void setIdFabricationType(ListFabricationType listFabricationType) {
        this.idFabricationType = listFabricationType;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public ValveHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListMillLocation getIdMillLocation() {
        return idMillLocation;
    }

    public ValveHist idMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
        return this;
    }

    public void setIdMillLocation(ListMillLocation listMillLocation) {
        this.idMillLocation = listMillLocation;
    }

    public ListSteelGrade getIdSteelGrade() {
        return idSteelGrade;
    }

    public ValveHist idSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
        return this;
    }

    public void setIdSteelGrade(ListSteelGrade listSteelGrade) {
        this.idSteelGrade = listSteelGrade;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public ValveHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof ValveHist)) {
            return false;
        }
        return id != null && id.equals(((ValveHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ValveHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", name='" + getName() + "'" +
            ", serialNum='" + getSerialNum() + "'" +
            ", model='" + getModel() + "'" +
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
            ", dateManufactured='" + getDateManufactured() + "'" +
            ", millTestPressure=" + getMillTestPressure() +
            ", designPressure=" + getDesignPressure() +
            ", designPressureIn=" + getDesignPressureIn() +
            ", designPressureOut=" + getDesignPressureOut() +
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
