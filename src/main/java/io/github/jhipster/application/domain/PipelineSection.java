package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PipelineSection.
 */
@Entity
@Table(name = "pipeline_section")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PipelineSection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pipeline_id")
    private Long pipelineId;

    @Column(name = "is_onshore")
    private Boolean isOnshore;

    @Column(name = "safety_class_id")
    private Long safetyClassId;

    @Column(name = "kp_start")
    private Double kpStart;

    @Column(name = "kp_end")
    private Double kpEnd;

    @Column(name = "date_create")
    private Instant dateCreate;

    @Column(name = "date_edit")
    private Instant dateEdit;

    @Column(name = "creator")
    private String creator;

    @Column(name = "editor")
    private String editor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PipelineSection name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public PipelineSection pipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
        return this;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Boolean isIsOnshore() {
        return isOnshore;
    }

    public PipelineSection isOnshore(Boolean isOnshore) {
        this.isOnshore = isOnshore;
        return this;
    }

    public void setIsOnshore(Boolean isOnshore) {
        this.isOnshore = isOnshore;
    }

    public Long getSafetyClassId() {
        return safetyClassId;
    }

    public PipelineSection safetyClassId(Long safetyClassId) {
        this.safetyClassId = safetyClassId;
        return this;
    }

    public void setSafetyClassId(Long safetyClassId) {
        this.safetyClassId = safetyClassId;
    }

    public Double getKpStart() {
        return kpStart;
    }

    public PipelineSection kpStart(Double kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(Double kpStart) {
        this.kpStart = kpStart;
    }

    public Double getKpEnd() {
        return kpEnd;
    }

    public PipelineSection kpEnd(Double kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(Double kpEnd) {
        this.kpEnd = kpEnd;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public PipelineSection dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public PipelineSection dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public PipelineSection creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public PipelineSection editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PipelineSection)) {
            return false;
        }
        return id != null && id.equals(((PipelineSection) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PipelineSection{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pipelineId=" + getPipelineId() +
            ", isOnshore='" + isIsOnshore() + "'" +
            ", safetyClassId=" + getSafetyClassId() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
