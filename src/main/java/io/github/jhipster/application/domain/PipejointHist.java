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
 * Time-dependent attribute table. One table  rows corresponds to one pipe joint and time period
 */
@Entity
@Table(name = "pipejoint_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PipejointHist implements Serializable {

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

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "external_coat_thickness", precision = 21, scale = 2)
    private BigDecimal externalCoatThickness;

    @Size(max = 255)
    @Column(name = "coord", length = 255)
    private String coord;

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
    private Pipejoint pipejoint;

    @ManyToOne
    @JsonIgnoreProperties("pipejointHists")
    private ListPipejointType idType;

    @ManyToOne
    @JsonIgnoreProperties("pipejointHists")
    private ListExternalCoating idExternalCoatType;

    @ManyToOne
    @JsonIgnoreProperties("pipejointHists")
    private ListMaterial idMaterial;

    @ManyToOne
    @JsonIgnoreProperties("pipejointHists")
    private ListPipejointSpecification idSpecification;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pipejointHists")
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

    public PipejointHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public PipejointHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getName() {
        return name;
    }

    public PipejointHist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExternalCoatThickness() {
        return externalCoatThickness;
    }

    public PipejointHist externalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
        return this;
    }

    public void setExternalCoatThickness(BigDecimal externalCoatThickness) {
        this.externalCoatThickness = externalCoatThickness;
    }

    public String getCoord() {
        return coord;
    }

    public PipejointHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public PipejointHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public PipejointHist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public PipejointHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public PipejointHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public PipejointHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public PipejointHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public PipejointHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Pipejoint getPipejoint() {
        return pipejoint;
    }

    public PipejointHist pipejoint(Pipejoint pipejoint) {
        this.pipejoint = pipejoint;
        return this;
    }

    public void setPipejoint(Pipejoint pipejoint) {
        this.pipejoint = pipejoint;
    }

    public ListPipejointType getIdType() {
        return idType;
    }

    public PipejointHist idType(ListPipejointType listPipejointType) {
        this.idType = listPipejointType;
        return this;
    }

    public void setIdType(ListPipejointType listPipejointType) {
        this.idType = listPipejointType;
    }

    public ListExternalCoating getIdExternalCoatType() {
        return idExternalCoatType;
    }

    public PipejointHist idExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
        return this;
    }

    public void setIdExternalCoatType(ListExternalCoating listExternalCoating) {
        this.idExternalCoatType = listExternalCoating;
    }

    public ListMaterial getIdMaterial() {
        return idMaterial;
    }

    public PipejointHist idMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
        return this;
    }

    public void setIdMaterial(ListMaterial listMaterial) {
        this.idMaterial = listMaterial;
    }

    public ListPipejointSpecification getIdSpecification() {
        return idSpecification;
    }

    public PipejointHist idSpecification(ListPipejointSpecification listPipejointSpecification) {
        this.idSpecification = listPipejointSpecification;
        return this;
    }

    public void setIdSpecification(ListPipejointSpecification listPipejointSpecification) {
        this.idSpecification = listPipejointSpecification;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public PipejointHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof PipejointHist)) {
            return false;
        }
        return id != null && id.equals(((PipejointHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PipejointHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", name='" + getName() + "'" +
            ", externalCoatThickness=" + getExternalCoatThickness() +
            ", coord='" + getCoord() + "'" +
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
