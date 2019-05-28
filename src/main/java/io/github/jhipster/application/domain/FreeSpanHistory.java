package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FreeSpanHistory.
 */
@Entity
@Table(name = "free_span_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreeSpanHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_form")
    private Instant dateForm;

    @Column(name = "date_to")
    private Instant dateTo;

    @Column(name = "work_id")
    private Long workId;

    @Column(name = "length")
    private Double length;

    @Column(name = "kp_start")
    private Double kpStart;

    @Column(name = "kp_end")
    private Double kpEnd;

    @Column(name = "is_current_flag")
    private Boolean isCurrentFlag;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "date_create")
    private Instant dateCreate;

    @Column(name = "date_edit")
    private Instant dateEdit;

    @Column(name = "creator")
    private String creator;

    @Column(name = "editor")
    private String editor;

    @ManyToOne
    @JsonIgnoreProperties("freeSpanHistories")
    private ListObjectStatus status;

    @ManyToOne
    @JsonIgnoreProperties("freeSpanHistories")
    private PipelineSection pipelineSection;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateForm() {
        return dateForm;
    }

    public FreeSpanHistory dateForm(Instant dateForm) {
        this.dateForm = dateForm;
        return this;
    }

    public void setDateForm(Instant dateForm) {
        this.dateForm = dateForm;
    }

    public Instant getDateTo() {
        return dateTo;
    }

    public FreeSpanHistory dateTo(Instant dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(Instant dateTo) {
        this.dateTo = dateTo;
    }

    public Long getWorkId() {
        return workId;
    }

    public FreeSpanHistory workId(Long workId) {
        this.workId = workId;
        return this;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Double getLength() {
        return length;
    }

    public FreeSpanHistory length(Double length) {
        this.length = length;
        return this;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getKpStart() {
        return kpStart;
    }

    public FreeSpanHistory kpStart(Double kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(Double kpStart) {
        this.kpStart = kpStart;
    }

    public Double getKpEnd() {
        return kpEnd;
    }

    public FreeSpanHistory kpEnd(Double kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(Double kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Boolean isIsCurrentFlag() {
        return isCurrentFlag;
    }

    public FreeSpanHistory isCurrentFlag(Boolean isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Boolean isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getComment() {
        return comment;
    }

    public FreeSpanHistory comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public FreeSpanHistory dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public FreeSpanHistory dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public FreeSpanHistory creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public FreeSpanHistory editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public ListObjectStatus getStatus() {
        return status;
    }

    public FreeSpanHistory status(ListObjectStatus listObjectStatus) {
        this.status = listObjectStatus;
        return this;
    }

    public void setStatus(ListObjectStatus listObjectStatus) {
        this.status = listObjectStatus;
    }

    public PipelineSection getPipelineSection() {
        return pipelineSection;
    }

    public FreeSpanHistory pipelineSection(PipelineSection pipelineSection) {
        this.pipelineSection = pipelineSection;
        return this;
    }

    public void setPipelineSection(PipelineSection pipelineSection) {
        this.pipelineSection = pipelineSection;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FreeSpanHistory)) {
            return false;
        }
        return id != null && id.equals(((FreeSpanHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FreeSpanHistory{" +
            "id=" + getId() +
            ", dateForm='" + getDateForm() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", workId=" + getWorkId() +
            ", length=" + getLength() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag='" + isIsCurrentFlag() + "'" +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
