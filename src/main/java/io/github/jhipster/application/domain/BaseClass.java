package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
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
    private Anode anode;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Bend bend;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private BuckleArrestor buckleArrestor;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Cps cps;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Displacement displacement;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private FreeSpan freeSpan;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private FreeSpanSupport freeSpanSupport;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private LaunchReceiver launchReceiver;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Pipe pipe;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Pipejoint pipejoint;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Pipeline pipeline;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private PipelineSection pipelineSection;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Tee tee;

    @OneToOne(mappedBy = "baseClass")
    @JsonIgnore
    private Valve valve;

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

    public Anode getAnode() {
        return anode;
    }

    public BaseClass anode(Anode anode) {
        this.anode = anode;
        return this;
    }

    public void setAnode(Anode anode) {
        this.anode = anode;
    }

    public Bend getBend() {
        return bend;
    }

    public BaseClass bend(Bend bend) {
        this.bend = bend;
        return this;
    }

    public void setBend(Bend bend) {
        this.bend = bend;
    }

    public BuckleArrestor getBuckleArrestor() {
        return buckleArrestor;
    }

    public BaseClass buckleArrestor(BuckleArrestor buckleArrestor) {
        this.buckleArrestor = buckleArrestor;
        return this;
    }

    public void setBuckleArrestor(BuckleArrestor buckleArrestor) {
        this.buckleArrestor = buckleArrestor;
    }

    public Cps getCps() {
        return cps;
    }

    public BaseClass cps(Cps cps) {
        this.cps = cps;
        return this;
    }

    public void setCps(Cps cps) {
        this.cps = cps;
    }

    public Displacement getDisplacement() {
        return displacement;
    }

    public BaseClass displacement(Displacement displacement) {
        this.displacement = displacement;
        return this;
    }

    public void setDisplacement(Displacement displacement) {
        this.displacement = displacement;
    }

    public FreeSpan getFreeSpan() {
        return freeSpan;
    }

    public BaseClass freeSpan(FreeSpan freeSpan) {
        this.freeSpan = freeSpan;
        return this;
    }

    public void setFreeSpan(FreeSpan freeSpan) {
        this.freeSpan = freeSpan;
    }

    public FreeSpanSupport getFreeSpanSupport() {
        return freeSpanSupport;
    }

    public BaseClass freeSpanSupport(FreeSpanSupport freeSpanSupport) {
        this.freeSpanSupport = freeSpanSupport;
        return this;
    }

    public void setFreeSpanSupport(FreeSpanSupport freeSpanSupport) {
        this.freeSpanSupport = freeSpanSupport;
    }

    public LaunchReceiver getLaunchReceiver() {
        return launchReceiver;
    }

    public BaseClass launchReceiver(LaunchReceiver launchReceiver) {
        this.launchReceiver = launchReceiver;
        return this;
    }

    public void setLaunchReceiver(LaunchReceiver launchReceiver) {
        this.launchReceiver = launchReceiver;
    }

    public Pipe getPipe() {
        return pipe;
    }

    public BaseClass pipe(Pipe pipe) {
        this.pipe = pipe;
        return this;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
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

    public Pipeline getPipeline() {
        return pipeline;
    }

    public BaseClass pipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public PipelineSection getPipelineSection() {
        return pipelineSection;
    }

    public BaseClass pipelineSection(PipelineSection pipelineSection) {
        this.pipelineSection = pipelineSection;
        return this;
    }

    public void setPipelineSection(PipelineSection pipelineSection) {
        this.pipelineSection = pipelineSection;
    }

    public Tee getTee() {
        return tee;
    }

    public BaseClass tee(Tee tee) {
        this.tee = tee;
        return this;
    }

    public void setTee(Tee tee) {
        this.tee = tee;
    }

    public Valve getValve() {
        return valve;
    }

    public BaseClass valve(Valve valve) {
        this.valve = valve;
        return this;
    }

    public void setValve(Valve valve) {
        this.valve = valve;
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
