package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ListSteelGrade.
 */
@Entity
@Table(name = "list_steel_grade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListSteelGrade implements Serializable {

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

    @Column(name = "steel_density", precision = 21, scale = 2)
    private BigDecimal steelDensity;

    @Column(name = "therm_exp_coef", precision = 21, scale = 2)
    private BigDecimal thermExpCoef;

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

    @OneToMany(mappedBy = "idSteelGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BendHist> bendHists = new HashSet<>();

    @OneToMany(mappedBy = "idSteelGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuckleArrestorHist> buckleArrestorHists = new HashSet<>();

    @OneToMany(mappedBy = "idSteelGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipeHist> pipeHists = new HashSet<>();

    @OneToMany(mappedBy = "idSteelGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeeHist> teeHists = new HashSet<>();

    @OneToMany(mappedBy = "idSteelGrade")
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

    public ListSteelGrade code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ListSteelGrade name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public ListSteelGrade fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSteelDensity() {
        return steelDensity;
    }

    public ListSteelGrade steelDensity(BigDecimal steelDensity) {
        this.steelDensity = steelDensity;
        return this;
    }

    public void setSteelDensity(BigDecimal steelDensity) {
        this.steelDensity = steelDensity;
    }

    public BigDecimal getThermExpCoef() {
        return thermExpCoef;
    }

    public ListSteelGrade thermExpCoef(BigDecimal thermExpCoef) {
        this.thermExpCoef = thermExpCoef;
        return this;
    }

    public void setThermExpCoef(BigDecimal thermExpCoef) {
        this.thermExpCoef = thermExpCoef;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public ListSteelGrade isCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
        return this;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public ListSteelGrade description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public ListSteelGrade dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public ListSteelGrade dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public ListSteelGrade creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public ListSteelGrade editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Set<BendHist> getBendHists() {
        return bendHists;
    }

    public ListSteelGrade bendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
        return this;
    }

    public ListSteelGrade addBendHist(BendHist bendHist) {
        this.bendHists.add(bendHist);
        bendHist.setIdSteelGrade(this);
        return this;
    }

    public ListSteelGrade removeBendHist(BendHist bendHist) {
        this.bendHists.remove(bendHist);
        bendHist.setIdSteelGrade(null);
        return this;
    }

    public void setBendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
    }

    public Set<BuckleArrestorHist> getBuckleArrestorHists() {
        return buckleArrestorHists;
    }

    public ListSteelGrade buckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
        return this;
    }

    public ListSteelGrade addBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.add(buckleArrestorHist);
        buckleArrestorHist.setIdSteelGrade(this);
        return this;
    }

    public ListSteelGrade removeBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.remove(buckleArrestorHist);
        buckleArrestorHist.setIdSteelGrade(null);
        return this;
    }

    public void setBuckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
    }

    public Set<PipeHist> getPipeHists() {
        return pipeHists;
    }

    public ListSteelGrade pipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
        return this;
    }

    public ListSteelGrade addPipeHist(PipeHist pipeHist) {
        this.pipeHists.add(pipeHist);
        pipeHist.setIdSteelGrade(this);
        return this;
    }

    public ListSteelGrade removePipeHist(PipeHist pipeHist) {
        this.pipeHists.remove(pipeHist);
        pipeHist.setIdSteelGrade(null);
        return this;
    }

    public void setPipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
    }

    public Set<TeeHist> getTeeHists() {
        return teeHists;
    }

    public ListSteelGrade teeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
        return this;
    }

    public ListSteelGrade addTeeHist(TeeHist teeHist) {
        this.teeHists.add(teeHist);
        teeHist.setIdSteelGrade(this);
        return this;
    }

    public ListSteelGrade removeTeeHist(TeeHist teeHist) {
        this.teeHists.remove(teeHist);
        teeHist.setIdSteelGrade(null);
        return this;
    }

    public void setTeeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
    }

    public Set<ValveHist> getValveHists() {
        return valveHists;
    }

    public ListSteelGrade valveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
        return this;
    }

    public ListSteelGrade addValveHist(ValveHist valveHist) {
        this.valveHists.add(valveHist);
        valveHist.setIdSteelGrade(this);
        return this;
    }

    public ListSteelGrade removeValveHist(ValveHist valveHist) {
        this.valveHists.remove(valveHist);
        valveHist.setIdSteelGrade(null);
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
        if (!(o instanceof ListSteelGrade)) {
            return false;
        }
        return id != null && id.equals(((ListSteelGrade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ListSteelGrade{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", steelDensity=" + getSteelDensity() +
            ", thermExpCoef=" + getThermExpCoef() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", description='" + getDescription() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
