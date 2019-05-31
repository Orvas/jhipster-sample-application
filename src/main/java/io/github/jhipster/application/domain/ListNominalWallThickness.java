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
 * A ListNominalWallThickness.
 */
@Entity
@Table(name = "list_nominal_wall_thickness")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListNominalWallThickness implements Serializable {

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

    @OneToMany(mappedBy = "idNominalWallThickness")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BendHist> bendHists = new HashSet<>();

    @OneToMany(mappedBy = "idNominalWallThickness")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuckleArrestorHist> buckleArrestorHists = new HashSet<>();

    @OneToMany(mappedBy = "idNominalWallThickness")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipeHist> pipeHists = new HashSet<>();

    @OneToMany(mappedBy = "idNominalWallThickness")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeeHist> teeHists = new HashSet<>();

    @OneToMany(mappedBy = "idNominalWallThickness")
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

    public ListNominalWallThickness code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ListNominalWallThickness name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public ListNominalWallThickness fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public ListNominalWallThickness isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public ListNominalWallThickness description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public ListNominalWallThickness dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public ListNominalWallThickness dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public ListNominalWallThickness creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public ListNominalWallThickness editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Set<BendHist> getBendHists() {
        return bendHists;
    }

    public ListNominalWallThickness bendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
        return this;
    }

    public ListNominalWallThickness addBendHist(BendHist bendHist) {
        this.bendHists.add(bendHist);
        bendHist.setIdNominalWallThickness(this);
        return this;
    }

    public ListNominalWallThickness removeBendHist(BendHist bendHist) {
        this.bendHists.remove(bendHist);
        bendHist.setIdNominalWallThickness(null);
        return this;
    }

    public void setBendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
    }

    public Set<BuckleArrestorHist> getBuckleArrestorHists() {
        return buckleArrestorHists;
    }

    public ListNominalWallThickness buckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
        return this;
    }

    public ListNominalWallThickness addBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.add(buckleArrestorHist);
        buckleArrestorHist.setIdNominalWallThickness(this);
        return this;
    }

    public ListNominalWallThickness removeBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.remove(buckleArrestorHist);
        buckleArrestorHist.setIdNominalWallThickness(null);
        return this;
    }

    public void setBuckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
    }

    public Set<PipeHist> getPipeHists() {
        return pipeHists;
    }

    public ListNominalWallThickness pipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
        return this;
    }

    public ListNominalWallThickness addPipeHist(PipeHist pipeHist) {
        this.pipeHists.add(pipeHist);
        pipeHist.setIdNominalWallThickness(this);
        return this;
    }

    public ListNominalWallThickness removePipeHist(PipeHist pipeHist) {
        this.pipeHists.remove(pipeHist);
        pipeHist.setIdNominalWallThickness(null);
        return this;
    }

    public void setPipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
    }

    public Set<TeeHist> getTeeHists() {
        return teeHists;
    }

    public ListNominalWallThickness teeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
        return this;
    }

    public ListNominalWallThickness addTeeHist(TeeHist teeHist) {
        this.teeHists.add(teeHist);
        teeHist.setIdNominalWallThickness(this);
        return this;
    }

    public ListNominalWallThickness removeTeeHist(TeeHist teeHist) {
        this.teeHists.remove(teeHist);
        teeHist.setIdNominalWallThickness(null);
        return this;
    }

    public void setTeeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
    }

    public Set<ValveHist> getValveHists() {
        return valveHists;
    }

    public ListNominalWallThickness valveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
        return this;
    }

    public ListNominalWallThickness addValveHist(ValveHist valveHist) {
        this.valveHists.add(valveHist);
        valveHist.setIdNominalWallThickness(this);
        return this;
    }

    public ListNominalWallThickness removeValveHist(ValveHist valveHist) {
        this.valveHists.remove(valveHist);
        valveHist.setIdNominalWallThickness(null);
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
        if (!(o instanceof ListNominalWallThickness)) {
            return false;
        }
        return id != null && id.equals(((ListNominalWallThickness) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ListNominalWallThickness{" +
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
