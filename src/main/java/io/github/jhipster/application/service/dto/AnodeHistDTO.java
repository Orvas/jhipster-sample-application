package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.AnodeHist} entity.
 */
@ApiModel(description = "Time-dependent attribute table. One table  rows corresponds to one anode and time period")
public class AnodeHistDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    private Long idWrk;

    private Integer designLife;

    private BigDecimal dmcd;

    private BigDecimal l1;

    private BigDecimal l2;

    private Integer length;

    private BigDecimal electrCapac;

    private BigDecimal designWeight;

    private BigDecimal weight;

    private Integer isBurial;

    @Size(max = 255)
    private String chemicalComposition;

    private BigDecimal density;

    private Integer spacing;

    private Integer coatCutbackLength;

    private BigDecimal coatDmgArea;

    private BigDecimal h2sSoil;

    private BigDecimal remain;

    private BigDecimal intFldTemp;

    private BigDecimal minPrc;

    private BigDecimal thickness;

    @Size(max = 255)
    private String coord;

    private BigDecimal kpStart;

    private BigDecimal coatThickness;

    private BigDecimal kpEnd;

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


    private Long anodeId;

    private Long idPipelineSectionId;

    private Long idBraceleteTypeId;

    private Long idMaterialId;

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

    public Integer getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
    }

    public BigDecimal getDmcd() {
        return dmcd;
    }

    public void setDmcd(BigDecimal dmcd) {
        this.dmcd = dmcd;
    }

    public BigDecimal getl1() {
        return l1;
    }

    public void setl1(BigDecimal l1) {
        this.l1 = l1;
    }

    public BigDecimal getl2() {
        return l2;
    }

    public void setl2(BigDecimal l2) {
        this.l2 = l2;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getElectrCapac() {
        return electrCapac;
    }

    public void setElectrCapac(BigDecimal electrCapac) {
        this.electrCapac = electrCapac;
    }

    public BigDecimal getDesignWeight() {
        return designWeight;
    }

    public void setDesignWeight(BigDecimal designWeight) {
        this.designWeight = designWeight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getIsBurial() {
        return isBurial;
    }

    public void setIsBurial(Integer isBurial) {
        this.isBurial = isBurial;
    }

    public String getChemicalComposition() {
        return chemicalComposition;
    }

    public void setChemicalComposition(String chemicalComposition) {
        this.chemicalComposition = chemicalComposition;
    }

    public BigDecimal getDensity() {
        return density;
    }

    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public Integer getCoatCutbackLength() {
        return coatCutbackLength;
    }

    public void setCoatCutbackLength(Integer coatCutbackLength) {
        this.coatCutbackLength = coatCutbackLength;
    }

    public BigDecimal getCoatDmgArea() {
        return coatDmgArea;
    }

    public void setCoatDmgArea(BigDecimal coatDmgArea) {
        this.coatDmgArea = coatDmgArea;
    }

    public BigDecimal geth2sSoil() {
        return h2sSoil;
    }

    public void seth2sSoil(BigDecimal h2sSoil) {
        this.h2sSoil = h2sSoil;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public BigDecimal getIntFldTemp() {
        return intFldTemp;
    }

    public void setIntFldTemp(BigDecimal intFldTemp) {
        this.intFldTemp = intFldTemp;
    }

    public BigDecimal getMinPrc() {
        return minPrc;
    }

    public void setMinPrc(BigDecimal minPrc) {
        this.minPrc = minPrc;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getCoatThickness() {
        return coatThickness;
    }

    public void setCoatThickness(BigDecimal coatThickness) {
        this.coatThickness = coatThickness;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
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

    public Long getAnodeId() {
        return anodeId;
    }

    public void setAnodeId(Long anodeId) {
        this.anodeId = anodeId;
    }

    public Long getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(Long pipelineSectionId) {
        this.idPipelineSectionId = pipelineSectionId;
    }

    public Long getIdBraceleteTypeId() {
        return idBraceleteTypeId;
    }

    public void setIdBraceleteTypeId(Long listAnodeBraceleteTypeId) {
        this.idBraceleteTypeId = listAnodeBraceleteTypeId;
    }

    public Long getIdMaterialId() {
        return idMaterialId;
    }

    public void setIdMaterialId(Long listMaterialId) {
        this.idMaterialId = listMaterialId;
    }

    public Long getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(Long listWrkStatusId) {
        this.idStatusId = listWrkStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnodeHistDTO anodeHistDTO = (AnodeHistDTO) o;
        if (anodeHistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anodeHistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnodeHistDTO{" +
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
            ", anode=" + getAnodeId() +
            ", idPipelineSection=" + getIdPipelineSectionId() +
            ", idBraceleteType=" + getIdBraceleteTypeId() +
            ", idMaterial=" + getIdMaterialId() +
            ", idStatus=" + getIdStatusId() +
            "}";
    }
}
