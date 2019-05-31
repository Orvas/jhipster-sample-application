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
 * A ListObjectStatus.
 */
@Entity
@Table(name = "list_object_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListObjectStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "code", length = 255, nullable = false)
    private String code;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "is_current_flag", nullable = false)
    private Integer isCurrentFlag;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

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

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BendHist> bendHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CpsHist> cpsHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanHist> freeSpanHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanSupportHist> freeSpanSupportHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LaunchReceiverHist> launchReceiverHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipeHist> pipeHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipejointHist> pipejointHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipelineHist> pipelineHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeeHist> teeHists = new HashSet<>();

    @OneToMany(mappedBy = "idStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ValveHist> valveHists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ListObjectStatus code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ListObjectStatus name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public ListObjectStatus fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public ListObjectStatus isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public ListObjectStatus description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public ListObjectStatus dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public ListObjectStatus dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public ListObjectStatus creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public ListObjectStatus editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Set<BendHist> getBendHists() {
        return bendHists;
    }

    public ListObjectStatus bendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
        return this;
    }

    public ListObjectStatus addBendHist(BendHist bendHist) {
        this.bendHists.add(bendHist);
        bendHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeBendHist(BendHist bendHist) {
        this.bendHists.remove(bendHist);
        bendHist.setIdStatus(null);
        return this;
    }

    public void setBendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
    }

    public Set<CpsHist> getCpsHists() {
        return cpsHists;
    }

    public ListObjectStatus cpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
        return this;
    }

    public ListObjectStatus addCpsHist(CpsHist cpsHist) {
        this.cpsHists.add(cpsHist);
        cpsHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeCpsHist(CpsHist cpsHist) {
        this.cpsHists.remove(cpsHist);
        cpsHist.setIdStatus(null);
        return this;
    }

    public void setCpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
    }

    public Set<FreeSpanHist> getFreeSpanHists() {
        return freeSpanHists;
    }

    public ListObjectStatus freeSpanHists(Set<FreeSpanHist> freeSpanHists) {
        this.freeSpanHists = freeSpanHists;
        return this;
    }

    public ListObjectStatus addFreeSpanHist(FreeSpanHist freeSpanHist) {
        this.freeSpanHists.add(freeSpanHist);
        freeSpanHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeFreeSpanHist(FreeSpanHist freeSpanHist) {
        this.freeSpanHists.remove(freeSpanHist);
        freeSpanHist.setIdStatus(null);
        return this;
    }

    public void setFreeSpanHists(Set<FreeSpanHist> freeSpanHists) {
        this.freeSpanHists = freeSpanHists;
    }

    public Set<FreeSpanSupportHist> getFreeSpanSupportHists() {
        return freeSpanSupportHists;
    }

    public ListObjectStatus freeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
        return this;
    }

    public ListObjectStatus addFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.add(freeSpanSupportHist);
        freeSpanSupportHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.remove(freeSpanSupportHist);
        freeSpanSupportHist.setIdStatus(null);
        return this;
    }

    public void setFreeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
    }

    public Set<LaunchReceiverHist> getLaunchReceiverHists() {
        return launchReceiverHists;
    }

    public ListObjectStatus launchReceiverHists(Set<LaunchReceiverHist> launchReceiverHists) {
        this.launchReceiverHists = launchReceiverHists;
        return this;
    }

    public ListObjectStatus addLaunchReceiverHist(LaunchReceiverHist launchReceiverHist) {
        this.launchReceiverHists.add(launchReceiverHist);
        launchReceiverHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeLaunchReceiverHist(LaunchReceiverHist launchReceiverHist) {
        this.launchReceiverHists.remove(launchReceiverHist);
        launchReceiverHist.setIdStatus(null);
        return this;
    }

    public void setLaunchReceiverHists(Set<LaunchReceiverHist> launchReceiverHists) {
        this.launchReceiverHists = launchReceiverHists;
    }

    public Set<PipeHist> getPipeHists() {
        return pipeHists;
    }

    public ListObjectStatus pipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
        return this;
    }

    public ListObjectStatus addPipeHist(PipeHist pipeHist) {
        this.pipeHists.add(pipeHist);
        pipeHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removePipeHist(PipeHist pipeHist) {
        this.pipeHists.remove(pipeHist);
        pipeHist.setIdStatus(null);
        return this;
    }

    public void setPipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
    }

    public Set<PipejointHist> getPipejointHists() {
        return pipejointHists;
    }

    public ListObjectStatus pipejointHists(Set<PipejointHist> pipejointHists) {
        this.pipejointHists = pipejointHists;
        return this;
    }

    public ListObjectStatus addPipejointHist(PipejointHist pipejointHist) {
        this.pipejointHists.add(pipejointHist);
        pipejointHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removePipejointHist(PipejointHist pipejointHist) {
        this.pipejointHists.remove(pipejointHist);
        pipejointHist.setIdStatus(null);
        return this;
    }

    public void setPipejointHists(Set<PipejointHist> pipejointHists) {
        this.pipejointHists = pipejointHists;
    }

    public Set<PipelineHist> getPipelineHists() {
        return pipelineHists;
    }

    public ListObjectStatus pipelineHists(Set<PipelineHist> pipelineHists) {
        this.pipelineHists = pipelineHists;
        return this;
    }

    public ListObjectStatus addPipelineHist(PipelineHist pipelineHist) {
        this.pipelineHists.add(pipelineHist);
        pipelineHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removePipelineHist(PipelineHist pipelineHist) {
        this.pipelineHists.remove(pipelineHist);
        pipelineHist.setIdStatus(null);
        return this;
    }

    public void setPipelineHists(Set<PipelineHist> pipelineHists) {
        this.pipelineHists = pipelineHists;
    }

    public Set<TeeHist> getTeeHists() {
        return teeHists;
    }

    public ListObjectStatus teeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
        return this;
    }

    public ListObjectStatus addTeeHist(TeeHist teeHist) {
        this.teeHists.add(teeHist);
        teeHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeTeeHist(TeeHist teeHist) {
        this.teeHists.remove(teeHist);
        teeHist.setIdStatus(null);
        return this;
    }

    public void setTeeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
    }

    public Set<ValveHist> getValveHists() {
        return valveHists;
    }

    public ListObjectStatus valveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
        return this;
    }

    public ListObjectStatus addValveHist(ValveHist valveHist) {
        this.valveHists.add(valveHist);
        valveHist.setIdStatus(this);
        return this;
    }

    public ListObjectStatus removeValveHist(ValveHist valveHist) {
        this.valveHists.remove(valveHist);
        valveHist.setIdStatus(null);
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
        if (!(o instanceof ListObjectStatus)) {
            return false;
        }
        return id != null && id.equals(((ListObjectStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ListObjectStatus{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", description='" + getDescription() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
