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
 * Time-dependent attribute table. One table  rows corresponds to one anode and time period
 */
@Entity
@Table(name = "anode_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AnodeHist implements Serializable {

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

    @Column(name = "design_life")
    private Integer designLife;

    @Column(name = "dmcd", precision = 21, scale = 2)
    private BigDecimal dmcd;

    @Column(name = "l_1", precision = 21, scale = 2)
    private BigDecimal l1;

    @Column(name = "l_2", precision = 21, scale = 2)
    private BigDecimal l2;

    @Column(name = "length")
    private Integer length;

    @Column(name = "electr_capac", precision = 21, scale = 2)
    private BigDecimal electrCapac;

    @Column(name = "design_weight", precision = 21, scale = 2)
    private BigDecimal designWeight;

    @Column(name = "weight", precision = 21, scale = 2)
    private BigDecimal weight;

    @Column(name = "is_burial")
    private Integer isBurial;

    @Size(max = 255)
    @Column(name = "chemical_composition", length = 255)
    private String chemicalComposition;

    @Column(name = "density", precision = 21, scale = 2)
    private BigDecimal density;

    @Column(name = "spacing")
    private Integer spacing;

    @Column(name = "coat_cutback_length")
    private Integer coatCutbackLength;

    @Column(name = "coat_dmg_area", precision = 21, scale = 2)
    private BigDecimal coatDmgArea;

    @Column(name = "h_2_s_soil", precision = 21, scale = 2)
    private BigDecimal h2sSoil;

    @Column(name = "remain", precision = 21, scale = 2)
    private BigDecimal remain;

    @Column(name = "int_fld_temp", precision = 21, scale = 2)
    private BigDecimal intFldTemp;

    @Column(name = "min_prc", precision = 21, scale = 2)
    private BigDecimal minPrc;

    @Column(name = "thickness", precision = 21, scale = 2)
    private BigDecimal thickness;

    @Size(max = 255)
    @Column(name = "coord", length = 255)
    private String coord;

    @Column(name = "kp_start", precision = 21, scale = 2)
    private BigDecimal kpStart;

    @Column(name = "coat_thickness", precision = 21, scale = 2)
    private BigDecimal coatThickness;

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

    @OneToOne
    @JoinColumn(unique = true)
    private Anode anode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("anodeHists")
    private PipelineSection idPipelineSection;

    @ManyToOne
    @JsonIgnoreProperties("anodeHists")
    private ListAnodeBraceleteType idBraceleteType;

    @ManyToOne
    @JsonIgnoreProperties("anodeHists")
    private ListMaterial idMaterial;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("anodeHists")
    private ListWrkStatus idStatus;

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

    public AnodeHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public AnodeHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public AnodeHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public Integer getDesignLife() {
        return designLife;
    }

    public AnodeHist designLife(Integer designLife) {
        this.designLife = designLife;
        return this;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
    }

    public BigDecimal getDmcd() {
        return dmcd;
    }

    public AnodeHist dmcd(BigDecimal dmcd) {
        this.dmcd = dmcd;
        return this;
    }

    public void setDmcd(BigDecimal dmcd) {
        this.dmcd = dmcd;
    }

    public BigDecimal getl1() {
        return l1;
    }

    public AnodeHist l1(BigDecimal l1) {
        this.l1 = l1;
        return this;
    }

    public void setl1(BigDecimal l1) {
        this.l1 = l1;
    }

    public BigDecimal getl2() {
        return l2;
    }

    public AnodeHist l2(BigDecimal l2) {
        this.l2 = l2;
        return this;
    }

    public void setl2(BigDecimal l2) {
        this.l2 = l2;
    }

    public Integer getLength() {
        return length;
    }

    public AnodeHist length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getElectrCapac() {
        return electrCapac;
    }

    public AnodeHist electrCapac(BigDecimal electrCapac) {
        this.electrCapac = electrCapac;
        return this;
    }

    public void setElectrCapac(BigDecimal electrCapac) {
        this.electrCapac = electrCapac;
    }

    public BigDecimal getDesignWeight() {
        return designWeight;
    }

    public AnodeHist designWeight(BigDecimal designWeight) {
        this.designWeight = designWeight;
        return this;
    }

    public void setDesignWeight(BigDecimal designWeight) {
        this.designWeight = designWeight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public AnodeHist weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public AnodeHist isBurial(Integer isBurial) {
        this.isBurial = isBurial;
        return this;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public String getChemicalComposition() {
        return chemicalComposition;
    }

    public AnodeHist chemicalComposition(String chemicalComposition) {
        this.chemicalComposition = chemicalComposition;
        return this;
    }

    public void setChemicalComposition(String chemicalComposition) {
        this.chemicalComposition = chemicalComposition;
    }

    public BigDecimal getDensity() {
        return density;
    }

    public AnodeHist density(BigDecimal density) {
        this.density = density;
        return this;
    }

    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public AnodeHist spacing(Integer spacing) {
        this.spacing = spacing;
        return this;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public Integer getCoatCutbackLength() {
        return coatCutbackLength;
    }

    public AnodeHist coatCutbackLength(Integer coatCutbackLength) {
        this.coatCutbackLength = coatCutbackLength;
        return this;
    }

    public void setCoatCutbackLength(Integer coatCutbackLength) {
        this.coatCutbackLength = coatCutbackLength;
    }

    public BigDecimal getCoatDmgArea() {
        return coatDmgArea;
    }

    public AnodeHist coatDmgArea(BigDecimal coatDmgArea) {
        this.coatDmgArea = coatDmgArea;
        return this;
    }

    public void setCoatDmgArea(BigDecimal coatDmgArea) {
        this.coatDmgArea = coatDmgArea;
    }

    public BigDecimal geth2sSoil() {
        return h2sSoil;
    }

    public AnodeHist h2sSoil(BigDecimal h2sSoil) {
        this.h2sSoil = h2sSoil;
        return this;
    }

    public void seth2sSoil(BigDecimal h2sSoil) {
        this.h2sSoil = h2sSoil;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public AnodeHist remain(BigDecimal remain) {
        this.remain = remain;
        return this;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public BigDecimal getIntFldTemp() {
        return intFldTemp;
    }

    public AnodeHist intFldTemp(BigDecimal intFldTemp) {
        this.intFldTemp = intFldTemp;
        return this;
    }

    public void setIntFldTemp(BigDecimal intFldTemp) {
        this.intFldTemp = intFldTemp;
    }

    public BigDecimal getMinPrc() {
        return minPrc;
    }

    public AnodeHist minPrc(BigDecimal minPrc) {
        this.minPrc = minPrc;
        return this;
    }

    public void setMinPrc(BigDecimal minPrc) {
        this.minPrc = minPrc;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public AnodeHist thickness(BigDecimal thickness) {
        this.thickness = thickness;
        return this;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public String getCoord() {
        return coord;
    }

    public AnodeHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public AnodeHist kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getCoatThickness() {
        return coatThickness;
    }

    public AnodeHist coatThickness(BigDecimal coatThickness) {
        this.coatThickness = coatThickness;
        return this;
    }

    public void setCoatThickness(BigDecimal coatThickness) {
        this.coatThickness = coatThickness;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public AnodeHist kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public AnodeHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public AnodeHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public AnodeHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public AnodeHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public AnodeHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public AnodeHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public AnodeHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Anode getAnode() {
        return anode;
    }

    public AnodeHist anode(Anode anode) {
        this.anode = anode;
        return this;
    }

    public void setAnode(Anode anode) {
        this.anode = anode;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public AnodeHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListAnodeBraceleteType getIdBraceleteType() {
        return idBraceleteType;
    }

    public AnodeHist idBraceleteType(ListAnodeBraceleteType listAnodeBraceleteType) {
        this.idBraceleteType = listAnodeBraceleteType;
        return this;
    }

    public void setIdBraceleteType(ListAnodeBraceleteType listAnodeBraceleteType) {
        this.idBraceleteType = listAnodeBraceleteType;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public AnodeHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListWrkStatus getIdStatus() {
        return idStatus;
    }

    public AnodeHist idStatus(ListWrkStatus listWrkStatus) {
        this.idStatus = listWrkStatus;
        return this;
    }

    public void setIdStatus(ListWrkStatus listWrkStatus) {
        this.idStatus = listWrkStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnodeHist)) {
            return false;
        }
        return id != null && id.equals(((AnodeHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AnodeHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", designLife=" + getDesignLife() +
            ", dmcd=" + getDmcd() +
            ", l1=" + getl1() +
            ", l2=" + getl2() +
            ", length=" + getLength() +
            ", electrCapac=" + getElectrCapac() +
            ", designWeight=" + getDesignWeight() +
            ", weight=" + getWeight() +
            ", isBurial=" + getIsBurial() +
            ", chemicalComposition='" + getChemicalComposition() + "'" +
            ", density=" + getDensity() +
            ", spacing=" + getSpacing() +
            ", coatCutbackLength=" + getCoatCutbackLength() +
            ", coatDmgArea=" + getCoatDmgArea() +
            ", h2sSoil=" + geth2sSoil() +
            ", remain=" + getRemain() +
            ", intFldTemp=" + getIntFldTemp() +
            ", minPrc=" + getMinPrc() +
            ", thickness=" + getThickness() +
            ", coord='" + getCoord() + "'" +
            ", kpStart=" + getKpStart() +
            ", coatThickness=" + getCoatThickness() +
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
