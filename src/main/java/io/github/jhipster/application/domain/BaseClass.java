package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * Object header. One row corresponds to one object
 */
@Entity
@Table(name = "base_class")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BaseClass implements Serializable {

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("baseClasses")
    private ListObjectType idType;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Pipejoint pipejoint;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Anode> anodes = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bend> bends = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuckleArrestor> buckleArrestors = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cps> cps = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Displacement> displacements = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpan> freeSpans = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanSupport> freeSpanSupports = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LaunchReceiver> launchReceivers = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pipe> pipes = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pipeline> pipelines = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipelineSection> pipelineSections = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tee> tees = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Valve> valves = new HashSet<>();

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

    public BaseClass dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public BaseClass dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public BaseClass creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public BaseClass editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public ListObjectType getIdType() {
        return idType;
    }

    public BaseClass idType(ListObjectType listObjectType) {
        this.idType = listObjectType;
        return this;
    }

    public void setIdType(ListObjectType listObjectType) {
        this.idType = listObjectType;
    }

    public Pipejoint getPipejoint() {
        return pipejoint;
    }

    public BaseClass pipejoint(Pipejoint pipejoint) {
        this.pipejoint = pipejoint;
        return this;
    }

    public void setPipejoint(Pipejoint pipejoint) {
        this.pipejoint = pipejoint;
    }

    public Set<Anode> getAnodes() {
        return anodes;
    }

    public BaseClass anodes(Set<Anode> anodes) {
        this.anodes = anodes;
        return this;
    }

    public BaseClass addAnode(Anode anode) {
        this.anodes.add(anode);
        anode.setId(this);
        return this;
    }

    public BaseClass removeAnode(Anode anode) {
        this.anodes.remove(anode);
        anode.setId(null);
        return this;
    }

    public void setAnodes(Set<Anode> anodes) {
        this.anodes = anodes;
    }

    public Set<Bend> getBends() {
        return bends;
    }

    public BaseClass bends(Set<Bend> bends) {
        this.bends = bends;
        return this;
    }

    public BaseClass addBend(Bend bend) {
        this.bends.add(bend);
        bend.setId(this);
        return this;
    }

    public BaseClass removeBend(Bend bend) {
        this.bends.remove(bend);
        bend.setId(null);
        return this;
    }

    public void setBends(Set<Bend> bends) {
        this.bends = bends;
    }

    public Set<BuckleArrestor> getBuckleArrestors() {
        return buckleArrestors;
    }

    public BaseClass buckleArrestors(Set<BuckleArrestor> buckleArrestors) {
        this.buckleArrestors = buckleArrestors;
        return this;
    }

    public BaseClass addBuckleArrestor(BuckleArrestor buckleArrestor) {
        this.buckleArrestors.add(buckleArrestor);
        buckleArrestor.setId(this);
        return this;
    }

    public BaseClass removeBuckleArrestor(BuckleArrestor buckleArrestor) {
        this.buckleArrestors.remove(buckleArrestor);
        buckleArrestor.setId(null);
        return this;
    }

    public void setBuckleArrestors(Set<BuckleArrestor> buckleArrestors) {
        this.buckleArrestors = buckleArrestors;
    }

    public Set<Cps> getCps() {
        return cps;
    }

    public BaseClass cps(Set<Cps> cps) {
        this.cps = cps;
        return this;
    }

    public BaseClass addCps(Cps cps) {
        this.cps.add(cps);
        cps.setId(this);
        return this;
    }

    public BaseClass removeCps(Cps cps) {
        this.cps.remove(cps);
        cps.setId(null);
        return this;
    }

    public void setCps(Set<Cps> cps) {
        this.cps = cps;
    }

    public Set<Displacement> getDisplacements() {
        return displacements;
    }

    public BaseClass displacements(Set<Displacement> displacements) {
        this.displacements = displacements;
        return this;
    }

    public BaseClass addDisplacement(Displacement displacement) {
        this.displacements.add(displacement);
        displacement.setId(this);
        return this;
    }

    public BaseClass removeDisplacement(Displacement displacement) {
        this.displacements.remove(displacement);
        displacement.setId(null);
        return this;
    }

    public void setDisplacements(Set<Displacement> displacements) {
        this.displacements = displacements;
    }

    public Set<FreeSpan> getFreeSpans() {
        return freeSpans;
    }

    public BaseClass freeSpans(Set<FreeSpan> freeSpans) {
        this.freeSpans = freeSpans;
        return this;
    }

    public BaseClass addFreeSpan(FreeSpan freeSpan) {
        this.freeSpans.add(freeSpan);
        freeSpan.setId(this);
        return this;
    }

    public BaseClass removeFreeSpan(FreeSpan freeSpan) {
        this.freeSpans.remove(freeSpan);
        freeSpan.setId(null);
        return this;
    }

    public void setFreeSpans(Set<FreeSpan> freeSpans) {
        this.freeSpans = freeSpans;
    }

    public Set<FreeSpanSupport> getFreeSpanSupports() {
        return freeSpanSupports;
    }

    public BaseClass freeSpanSupports(Set<FreeSpanSupport> freeSpanSupports) {
        this.freeSpanSupports = freeSpanSupports;
        return this;
    }

    public BaseClass addFreeSpanSupport(FreeSpanSupport freeSpanSupport) {
        this.freeSpanSupports.add(freeSpanSupport);
        freeSpanSupport.setId(this);
        return this;
    }

    public BaseClass removeFreeSpanSupport(FreeSpanSupport freeSpanSupport) {
        this.freeSpanSupports.remove(freeSpanSupport);
        freeSpanSupport.setId(null);
        return this;
    }

    public void setFreeSpanSupports(Set<FreeSpanSupport> freeSpanSupports) {
        this.freeSpanSupports = freeSpanSupports;
    }

    public Set<LaunchReceiver> getLaunchReceivers() {
        return launchReceivers;
    }

    public BaseClass launchReceivers(Set<LaunchReceiver> launchReceivers) {
        this.launchReceivers = launchReceivers;
        return this;
    }

    public BaseClass addLaunchReceiver(LaunchReceiver launchReceiver) {
        this.launchReceivers.add(launchReceiver);
        launchReceiver.setId(this);
        return this;
    }

    public BaseClass removeLaunchReceiver(LaunchReceiver launchReceiver) {
        this.launchReceivers.remove(launchReceiver);
        launchReceiver.setId(null);
        return this;
    }

    public void setLaunchReceivers(Set<LaunchReceiver> launchReceivers) {
        this.launchReceivers = launchReceivers;
    }

    public Set<Pipe> getPipes() {
        return pipes;
    }

    public BaseClass pipes(Set<Pipe> pipes) {
        this.pipes = pipes;
        return this;
    }

    public BaseClass addPipe(Pipe pipe) {
        this.pipes.add(pipe);
        pipe.setId(this);
        return this;
    }

    public BaseClass removePipe(Pipe pipe) {
        this.pipes.remove(pipe);
        pipe.setId(null);
        return this;
    }

    public void setPipes(Set<Pipe> pipes) {
        this.pipes = pipes;
    }

    public Set<Pipeline> getPipelines() {
        return pipelines;
    }

    public BaseClass pipelines(Set<Pipeline> pipelines) {
        this.pipelines = pipelines;
        return this;
    }

    public BaseClass addPipeline(Pipeline pipeline) {
        this.pipelines.add(pipeline);
        pipeline.setId(this);
        return this;
    }

    public BaseClass removePipeline(Pipeline pipeline) {
        this.pipelines.remove(pipeline);
        pipeline.setId(null);
        return this;
    }

    public void setPipelines(Set<Pipeline> pipelines) {
        this.pipelines = pipelines;
    }

    public Set<PipelineSection> getPipelineSections() {
        return pipelineSections;
    }

    public BaseClass pipelineSections(Set<PipelineSection> pipelineSections) {
        this.pipelineSections = pipelineSections;
        return this;
    }

    public BaseClass addPipelineSection(PipelineSection pipelineSection) {
        this.pipelineSections.add(pipelineSection);
        pipelineSection.setId(this);
        return this;
    }

    public BaseClass removePipelineSection(PipelineSection pipelineSection) {
        this.pipelineSections.remove(pipelineSection);
        pipelineSection.setId(null);
        return this;
    }

    public void setPipelineSections(Set<PipelineSection> pipelineSections) {
        this.pipelineSections = pipelineSections;
    }

    public Set<Tee> getTees() {
        return tees;
    }

    public BaseClass tees(Set<Tee> tees) {
        this.tees = tees;
        return this;
    }

    public BaseClass addTee(Tee tee) {
        this.tees.add(tee);
        tee.setId(this);
        return this;
    }

    public BaseClass removeTee(Tee tee) {
        this.tees.remove(tee);
        tee.setId(null);
        return this;
    }

    public void setTees(Set<Tee> tees) {
        this.tees = tees;
    }

    public Set<Valve> getValves() {
        return valves;
    }

    public BaseClass valves(Set<Valve> valves) {
        this.valves = valves;
        return this;
    }

    public BaseClass addValve(Valve valve) {
        this.valves.add(valve);
        valve.setId(this);
        return this;
    }

    public BaseClass removeValve(Valve valve) {
        this.valves.remove(valve);
        valve.setId(null);
        return this;
    }

    public void setValves(Set<Valve> valves) {
        this.valves = valves;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseClass)) {
            return false;
        }
        return id != null && id.equals(((BaseClass) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BaseClass{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
