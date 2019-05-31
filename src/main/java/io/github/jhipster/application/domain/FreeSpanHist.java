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
 * A FreeSpanHist.
 */
@Entity
@Table(name = "free_span_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreeSpanHist implements Serializable {

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

    @Column(name = "lenght", precision = 21, scale = 2)
    private BigDecimal lenght;

    @Column(name = "lenght_allow", precision = 21, scale = 2)
    private BigDecimal lenghtAllow;

    @Column(name = "height", precision = 21, scale = 2)
    private BigDecimal height;

    @Column(name = "is_multispan")
    private Integer isMultispan;

    @Column(name = "gap", precision = 21, scale = 2)
    private BigDecimal gap;

    @Column(name = "kp_start", precision = 21, scale = 2)
    private BigDecimal kpStart;

    @Column(name = "kp_end", precision = 21, scale = 2)
    private BigDecimal kpEnd;

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

    @OneToOne
    @JoinColumn(unique = true)
    private FreeSpan freeSpan;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("freeSpanHists")
    private PipelineSection idPipelineSection;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("freeSpanHists")
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

    public FreeSpanHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public FreeSpanHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getIdWrk() {
        return idWrk;
    }

    public FreeSpanHist idWrk(Long idWrk) {
        this.idWrk = idWrk;
        return this;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimal getLenght() {
        return lenght;
    }

    public FreeSpanHist lenght(BigDecimal lenght) {
        this.lenght = lenght;
        return this;
    }

    public void setLenght(BigDecimal lenght) {
        this.lenght = lenght;
    }

    public BigDecimal getLenghtAllow() {
        return lenghtAllow;
    }

    public FreeSpanHist lenghtAllow(BigDecimal lenghtAllow) {
        this.lenghtAllow = lenghtAllow;
        return this;
    }

    public void setLenghtAllow(BigDecimal lenghtAllow) {
        this.lenghtAllow = lenghtAllow;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public FreeSpanHist height(BigDecimal height) {
        this.height = height;
        return this;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getIsMultispan() {
        return isMultispan;
    }

    public FreeSpanHist isMultispan(Integer isMultispan) {
        this.isMultispan = isMultispan;
        return this;
    }

    public void setIsMultispan(Integer isMultispan) {
        this.isMultispan = isMultispan;
    }

    public BigDecimal getGap() {
        return gap;
    }

    public FreeSpanHist gap(BigDecimal gap) {
        this.gap = gap;
        return this;
    }

    public void setGap(BigDecimal gap) {
        this.gap = gap;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public FreeSpanHist kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public FreeSpanHist kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public FreeSpanHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getComment() {
        return comment;
    }

    public FreeSpanHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public FreeSpanHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public FreeSpanHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public FreeSpanHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public FreeSpanHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public FreeSpan getFreeSpan() {
        return freeSpan;
    }

    public FreeSpanHist freeSpan(FreeSpan freeSpan) {
        this.freeSpan = freeSpan;
        return this;
    }

    public void setFreeSpan(FreeSpan freeSpan) {
        this.freeSpan = freeSpan;
    }

    public PipelineSection getIdPipelineSection() {
        return idPipelineSection;
    }

    public FreeSpanHist idPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
        return this;
    }

    public void setIdPipelineSection(PipelineSection pipelineSection) {
        this.idPipelineSection = pipelineSection;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public FreeSpanHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof FreeSpanHist)) {
            return false;
        }
        return id != null && id.equals(((FreeSpanHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FreeSpanHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", lenght=" + getLenght() +
            ", lenghtAllow=" + getLenghtAllow() +
            ", height=" + getHeight() +
            ", isMultispan=" + getIsMultispan() +
            ", gap=" + getGap() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
