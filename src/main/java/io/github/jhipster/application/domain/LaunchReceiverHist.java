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
 * A LaunchReceiverHist.
 */
@Entity
@Table(name = "launch_receiver_hist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LaunchReceiverHist implements Serializable {

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

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "coord", length = 255)
    private String coord;

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
    @JsonIgnoreProperties("launchReceiverHists")
    private LaunchReceiver id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("launchReceiverHists")
    private Pipeline idPipeline;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("launchReceiverHists")
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

    public LaunchReceiverHist dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LaunchReceiverHist dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getName() {
        return name;
    }

    public LaunchReceiverHist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoord() {
        return coord;
    }

    public LaunchReceiverHist coord(String coord) {
        this.coord = coord;
        return this;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public BigDecimal getKpInst() {
        return kpInst;
    }

    public LaunchReceiverHist kpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
        return this;
    }

    public void setKpInst(BigDecimal kpInst) {
        this.kpInst = kpInst;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public LaunchReceiverHist isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getComment() {
        return comment;
    }

    public LaunchReceiverHist comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public LaunchReceiverHist dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public LaunchReceiverHist dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public LaunchReceiverHist creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public LaunchReceiverHist editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LaunchReceiver getId() {
        return id;
    }

    public LaunchReceiverHist id(LaunchReceiver launchReceiver) {
        this.id = launchReceiver;
        return this;
    }

    public void setId(LaunchReceiver launchReceiver) {
        this.id = launchReceiver;
    }

    public Pipeline getIdPipeline() {
        return idPipeline;
    }

    public LaunchReceiverHist idPipeline(Pipeline pipeline) {
        this.idPipeline = pipeline;
        return this;
    }

    public void setIdPipeline(Pipeline pipeline) {
        this.idPipeline = pipeline;
    }

    public ListObjectStatus getIdStatus() {
        return idStatus;
    }

    public LaunchReceiverHist idStatus(ListObjectStatus listObjectStatus) {
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
        if (!(o instanceof LaunchReceiverHist)) {
            return false;
        }
        return id != null && id.equals(((LaunchReceiverHist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LaunchReceiverHist{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", name='" + getName() + "'" +
            ", coord='" + getCoord() + "'" +
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
