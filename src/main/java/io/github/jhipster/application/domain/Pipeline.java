package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Pipeline.
 */
@Entity
@Table(name = "pipeline")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pipeline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

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
    private BaseClass baseClass;

    @OneToOne(mappedBy = "pipeline")
    @JsonIgnore
    private PipelineHist pipelineHist;

    @OneToMany(mappedBy = "idPipeline")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LaunchReceiverHist> launchReceiverHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipeline")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipelineSection> pipelineSections = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public Pipeline dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public Pipeline dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public Pipeline creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public Pipeline editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BaseClass getBaseClass() {
        return baseClass;
    }

    public Pipeline baseClass(BaseClass baseClass) {
        this.baseClass = baseClass;
        return this;
    }

    public void setBaseClass(BaseClass baseClass) {
        this.baseClass = baseClass;
    }

    public PipelineHist getPipelineHist() {
        return pipelineHist;
    }

    public Pipeline pipelineHist(PipelineHist pipelineHist) {
        this.pipelineHist = pipelineHist;
        return this;
    }

    public void setPipelineHist(PipelineHist pipelineHist) {
        this.pipelineHist = pipelineHist;
    }

    public Set<LaunchReceiverHist> getLaunchReceiverHists() {
        return launchReceiverHists;
    }

    public Pipeline launchReceiverHists(Set<LaunchReceiverHist> launchReceiverHists) {
        this.launchReceiverHists = launchReceiverHists;
        return this;
    }

    public Pipeline addLaunchReceiverHist(LaunchReceiverHist launchReceiverHist) {
        this.launchReceiverHists.add(launchReceiverHist);
        launchReceiverHist.setIdPipeline(this);
        return this;
    }

    public Pipeline removeLaunchReceiverHist(LaunchReceiverHist launchReceiverHist) {
        this.launchReceiverHists.remove(launchReceiverHist);
        launchReceiverHist.setIdPipeline(null);
        return this;
    }

    public void setLaunchReceiverHists(Set<LaunchReceiverHist> launchReceiverHists) {
        this.launchReceiverHists = launchReceiverHists;
    }

    public Set<PipelineSection> getPipelineSections() {
        return pipelineSections;
    }

    public Pipeline pipelineSections(Set<PipelineSection> pipelineSections) {
        this.pipelineSections = pipelineSections;
        return this;
    }

    public Pipeline addPipelineSection(PipelineSection pipelineSection) {
        this.pipelineSections.add(pipelineSection);
        pipelineSection.setIdPipeline(this);
        return this;
    }

    public Pipeline removePipelineSection(PipelineSection pipelineSection) {
        this.pipelineSections.remove(pipelineSection);
        pipelineSection.setIdPipeline(null);
        return this;
    }

    public void setPipelineSections(Set<PipelineSection> pipelineSections) {
        this.pipelineSections = pipelineSections;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pipeline)) {
            return false;
        }
        return id != null && id.equals(((Pipeline) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pipeline{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
