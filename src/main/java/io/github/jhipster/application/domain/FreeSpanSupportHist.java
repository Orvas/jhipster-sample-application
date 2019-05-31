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
 * A FreeSpanSupportHist.
 */
@Entity
@Table(name = "free_span_support_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreeSpanSupportHist implements Serializable {

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

    @Column(name = "height", precision = 21, scale = 2)
    private BigDecimal height;

    @Column(name = "kp_inst", precision = 21, scale = 2)
    private BigDecimal kpInst;

    @NotNull
    @Column(name = "is_current_flag", nullable = false)
    private Integer isCurrentFlag;

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
    @JsonIgnoreProperties("freeSpanSupportHists")
    private FreeSpanSupport id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("freeSpanSupportHists")
    private PipelineSection idPipelineSection;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("freeSpanSupportHists")
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

    public FreeSpanSupportHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public FreeSpanSupportHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public FreeSpanSupportHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public FreeSpanSupportHist height(BigDecimal height) {
        this.height = height;
        return this;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getKpInst() {
        return kpInst;
    }

    public FreeSpanSupportHist kpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
        return this;
    }

    public void setKpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public FreeSpanSupportHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getComment() {
        return comment;
    }

    public FreeSpanSupportHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public FreeSpanSupportHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public FreeSpanSupportHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public FreeSpanSupportHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public FreeSpanSupportHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public FreeSpanSupport getId() {
        return id;
    }

    public FreeSpanSupportHist id(FreeSpanSupport freeSpanSupport) {
        this.id = freeSpanSupport;
        return this;
    }

    public void setId(FreeSpanSupport freeSpanSupport) {
        this.id = freeSpanSupport;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public FreeSpanSupportHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public FreeSpanSupportHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof FreeSpanSupportHist)) {
            return false;
        }
        return id != null && id.equals(((FreeSpanSupportHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FreeSpanSupportHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", height=" + getHeight() +
            ", kpInst=" + getKpInst() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
