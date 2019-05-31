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
 * Pipe joints main table. One table  rows corresponds to one pipe joint
 */
@Entity
@Table(name = "pipejoint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pipejoint implements Serializable {

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
    @JsonIgnoreProperties("pipejoints")
    private BaseClass id;

    @OneToMany(mappedBy = "idPipeJoint")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BendHist> bendHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipeJoint")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuckleArrestorHist> buckleArrestorHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipeJoint")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipeHist> pipeHists = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipejointHist> pipejointHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipeJoint")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeeHist> teeHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipeJoint")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ValveHist> valveHists = new HashSet<>();

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

    public Pipejoint dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public Pipejoint dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public Pipejoint creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public Pipejoint editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BaseClass getId() {
        return id;
    }

    public Pipejoint id(BaseClass baseClass) {
        this.id = baseClass;
        return this;
    }

    public void setId(BaseClass baseClass) {
        this.id = baseClass;
    }

    public Set<BendHist> getBendHists() {
        return bendHists;
    }

    public Pipejoint bendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
        return this;
    }

    public Pipejoint addBendHist(BendHist bendHist) {
        this.bendHists.add(bendHist);
        bendHist.setIdPipeJoint(this);
        return this;
    }

    public Pipejoint removeBendHist(BendHist bendHist) {
        this.bendHists.remove(bendHist);
        bendHist.setIdPipeJoint(null);
        return this;
    }

    public void setBendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
    }

    public Set<BuckleArrestorHist> getBuckleArrestorHists() {
        return buckleArrestorHists;
    }

    public Pipejoint buckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
        return this;
    }

    public Pipejoint addBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.add(buckleArrestorHist);
        buckleArrestorHist.setIdPipeJoint(this);
        return this;
    }

    public Pipejoint removeBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.remove(buckleArrestorHist);
        buckleArrestorHist.setIdPipeJoint(null);
        return this;
    }

    public void setBuckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
    }

    public Set<PipeHist> getPipeHists() {
        return pipeHists;
    }

    public Pipejoint pipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
        return this;
    }

    public Pipejoint addPipeHist(PipeHist pipeHist) {
        this.pipeHists.add(pipeHist);
        pipeHist.setIdPipeJoint(this);
        return this;
    }

    public Pipejoint removePipeHist(PipeHist pipeHist) {
        this.pipeHists.remove(pipeHist);
        pipeHist.setIdPipeJoint(null);
        return this;
    }

    public void setPipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
    }

    public Set<PipejointHist> getPipejointHists() {
        return pipejointHists;
    }

    public Pipejoint pipejointHists(Set<PipejointHist> pipejointHists) {
        this.pipejointHists = pipejointHists;
        return this;
    }

    public Pipejoint addPipejointHist(PipejointHist pipejointHist) {
        this.pipejointHists.add(pipejointHist);
        pipejointHist.setId(this);
        return this;
    }

    public Pipejoint removePipejointHist(PipejointHist pipejointHist) {
        this.pipejointHists.remove(pipejointHist);
        pipejointHist.setId(null);
        return this;
    }

    public void setPipejointHists(Set<PipejointHist> pipejointHists) {
        this.pipejointHists = pipejointHists;
    }

    public Set<TeeHist> getTeeHists() {
        return teeHists;
    }

    public Pipejoint teeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
        return this;
    }

    public Pipejoint addTeeHist(TeeHist teeHist) {
        this.teeHists.add(teeHist);
        teeHist.setIdPipeJoint(this);
        return this;
    }

    public Pipejoint removeTeeHist(TeeHist teeHist) {
        this.teeHists.remove(teeHist);
        teeHist.setIdPipeJoint(null);
        return this;
    }

    public void setTeeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
    }

    public Set<ValveHist> getValveHists() {
        return valveHists;
    }

    public Pipejoint valveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
        return this;
    }

    public Pipejoint addValveHist(ValveHist valveHist) {
        this.valveHists.add(valveHist);
        valveHist.setIdPipeJoint(this);
        return this;
    }

    public Pipejoint removeValveHist(ValveHist valveHist) {
        this.valveHists.remove(valveHist);
        valveHist.setIdPipeJoint(null);
        return this;
    }

    public void setValveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pipejoint)) {
            return false;
        }
        return id != null && id.equals(((Pipejoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pipejoint{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
