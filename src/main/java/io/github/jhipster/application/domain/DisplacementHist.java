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
 * A DisplacementHist.
 */
@Entity
@Table(name = "displacement_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DisplacementHist implements Serializable {

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

    @Column(name = "delta_x", precision = 21, scale = 2)
    private BigDecimal deltaX;

    @Column(name = "delta_y", precision = 21, scale = 2)
    private BigDecimal deltaY;

    @Column(name = "delta_z", precision = 21, scale = 2)
    private BigDecimal deltaZ;

    @Column(name = "delta_total", precision = 21, scale = 2)
    private BigDecimal deltaTotal;

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
    private Displacement displacement;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("displacementHists")
    private PipelineSection idPipelineSection;

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

    public DisplacementHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public DisplacementHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public DisplacementHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimal getDeltaX() {
        return deltaX;
    }

    public DisplacementHist deltaX(BigDecimal deltaX) {
        this.deltaX = deltaX;
        return this;
    }

    public void setDeltaX(BigDecimal deltaX) {
        this.deltaX = deltaX;
    }

    public BigDecimal getDeltaY() {
        return deltaY;
    }

    public DisplacementHist deltaY(BigDecimal deltaY) {
        this.deltaY = deltaY;
        return this;
    }

    public void setDeltaY(BigDecimal deltaY) {
        this.deltaY = deltaY;
    }

    public BigDecimal getDeltaZ() {
        return deltaZ;
    }

    public DisplacementHist deltaZ(BigDecimal deltaZ) {
        this.deltaZ = deltaZ;
        return this;
    }

    public void setDeltaZ(BigDecimal deltaZ) {
        this.deltaZ = deltaZ;
    }

    public BigDecimal getDeltaTotal() {
        return deltaTotal;
    }

    public DisplacementHist deltaTotal(BigDecimal deltaTotal) {
        this.deltaTotal = deltaTotal;
        return this;
    }

    public void setDeltaTotal(BigDecimal deltaTotal) {
        this.deltaTotal = deltaTotal;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public DisplacementHist kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public DisplacementHist kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public DisplacementHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public DisplacementHist idStatus(Long idStatus) {
        this.idStatus = idStatus;
        return this;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getComment() {
        return comment;
    }

    public DisplacementHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public DisplacementHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public DisplacementHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public DisplacementHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public DisplacementHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Displacement getDisplacement() {
        return displacement;
    }

    public DisplacementHist displacement(Displacement displacement) {
        this.displacement = displacement;
        return this;
    }

    public void setDisplacement(Displacement displacement) {
        this.displacement = displacement;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public DisplacementHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DisplacementHist)) {
            return false;
        }
        return id != null && id.equals(((DisplacementHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DisplacementHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", deltaX=" + getDeltaX() +
            ", deltaY=" + getDeltaY() +
            ", deltaZ=" + getDeltaZ() +
            ", deltaTotal=" + getDeltaTotal() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", idStatus=" + getIdStatus() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
