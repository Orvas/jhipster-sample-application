package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * Homogeneous sections,  areas of unchanged values ??of the following characteristics:- is_onshore- safety classOne table rows corresponds to one section
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

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Column(name = "is_onshore", nullable = false)
    private Integer isOnshore;

    @NotNull
    @Column(name = "kp_start", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpStart;

    @NotNull
    @Column(name = "kp_end", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpEnd;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pipelineSections")
    private Pipeline idPipeline;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pipelineSections")
    private ListSafetyClass idSafetyClass;

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AnodeHist> anodeHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BendHist> bendHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuckleArrestorHist> buckleArrestorHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CpsHist> cpsHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CpsRange> cpsRanges = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DisplacementHist> displacementHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanHist> freeSpanHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanSupportHist> freeSpanSupportHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PipeHist> pipeHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeeHist> teeHists = new HashSet<>();

    @OneToMany(mappedBy = "idPipelineSection")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ValveHist> valveHists = new HashSet<>();

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

    public Integer getIsOnshore() {
        return isOnshore;
    }

    public PipelineSection isOnshore(Integer isOnshore) {
        this.isOnshore = isOnshore;
        return this;
    }

    public void setIsOnshore(Integer isOnshore) {
        this.isOnshore = isOnshore;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public PipelineSection kpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
        return this;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public PipelineSection kpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
        return this;
    }

    public void setKpEnd(BigDecimal kpEnd) {
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

    public BaseClass getBaseClass() {
        return baseClass;
    }

    public PipelineSection baseClass(BaseClass baseClass) {
        this.baseClass = baseClass;
        return this;
    }

    public void setBaseClass(BaseClass baseClass) {
        this.baseClass = baseClass;
    }

    public Pipeline getIdPipeline() {
        return idPipeline;
    }

    public PipelineSection idPipeline(Pipeline pipeline) {
        this.idPipeline = pipeline;
        return this;
    }

    public void setIdPipeline(Pipeline pipeline) {
        this.idPipeline = pipeline;
    }

    public ListSafetyClass getIdSafetyClass() {
        return idSafetyClass;
    }

    public PipelineSection idSafetyClass(ListSafetyClass listSafetyClass) {
        this.idSafetyClass = listSafetyClass;
        return this;
    }

    public void setIdSafetyClass(ListSafetyClass listSafetyClass) {
        this.idSafetyClass = listSafetyClass;
    }

    public Set<AnodeHist> getAnodeHists() {
        return anodeHists;
    }

    public PipelineSection anodeHists(Set<AnodeHist> anodeHists) {
        this.anodeHists = anodeHists;
        return this;
    }

    public PipelineSection addAnodeHist(AnodeHist anodeHist) {
        this.anodeHists.add(anodeHist);
        anodeHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeAnodeHist(AnodeHist anodeHist) {
        this.anodeHists.remove(anodeHist);
        anodeHist.setIdPipelineSection(null);
        return this;
    }

    public void setAnodeHists(Set<AnodeHist> anodeHists) {
        this.anodeHists = anodeHists;
    }

    public Set<BendHist> getBendHists() {
        return bendHists;
    }

    public PipelineSection bendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
        return this;
    }

    public PipelineSection addBendHist(BendHist bendHist) {
        this.bendHists.add(bendHist);
        bendHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeBendHist(BendHist bendHist) {
        this.bendHists.remove(bendHist);
        bendHist.setIdPipelineSection(null);
        return this;
    }

    public void setBendHists(Set<BendHist> bendHists) {
        this.bendHists = bendHists;
    }

    public Set<BuckleArrestorHist> getBuckleArrestorHists() {
        return buckleArrestorHists;
    }

    public PipelineSection buckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
        return this;
    }

    public PipelineSection addBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.add(buckleArrestorHist);
        buckleArrestorHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeBuckleArrestorHist(BuckleArrestorHist buckleArrestorHist) {
        this.buckleArrestorHists.remove(buckleArrestorHist);
        buckleArrestorHist.setIdPipelineSection(null);
        return this;
    }

    public void setBuckleArrestorHists(Set<BuckleArrestorHist> buckleArrestorHists) {
        this.buckleArrestorHists = buckleArrestorHists;
    }

    public Set<CpsHist> getCpsHists() {
        return cpsHists;
    }

    public PipelineSection cpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
        return this;
    }

    public PipelineSection addCpsHist(CpsHist cpsHist) {
        this.cpsHists.add(cpsHist);
        cpsHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeCpsHist(CpsHist cpsHist) {
        this.cpsHists.remove(cpsHist);
        cpsHist.setIdPipelineSection(null);
        return this;
    }

    public void setCpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
    }

    public Set<CpsRange> getCpsRanges() {
        return cpsRanges;
    }

    public PipelineSection cpsRanges(Set<CpsRange> cpsRanges) {
        this.cpsRanges = cpsRanges;
        return this;
    }

    public PipelineSection addCpsRange(CpsRange cpsRange) {
        this.cpsRanges.add(cpsRange);
        cpsRange.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeCpsRange(CpsRange cpsRange) {
        this.cpsRanges.remove(cpsRange);
        cpsRange.setIdPipelineSection(null);
        return this;
    }

    public void setCpsRanges(Set<CpsRange> cpsRanges) {
        this.cpsRanges = cpsRanges;
    }

    public Set<DisplacementHist> getDisplacementHists() {
        return displacementHists;
    }

    public PipelineSection displacementHists(Set<DisplacementHist> displacementHists) {
        this.displacementHists = displacementHists;
        return this;
    }

    public PipelineSection addDisplacementHist(DisplacementHist displacementHist) {
        this.displacementHists.add(displacementHist);
        displacementHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeDisplacementHist(DisplacementHist displacementHist) {
        this.displacementHists.remove(displacementHist);
        displacementHist.setIdPipelineSection(null);
        return this;
    }

    public void setDisplacementHists(Set<DisplacementHist> displacementHists) {
        this.displacementHists = displacementHists;
    }

    public Set<FreeSpanHist> getFreeSpanHists() {
        return freeSpanHists;
    }

    public PipelineSection freeSpanHists(Set<FreeSpanHist> freeSpanHists) {
        this.freeSpanHists = freeSpanHists;
        return this;
    }

    public PipelineSection addFreeSpanHist(FreeSpanHist freeSpanHist) {
        this.freeSpanHists.add(freeSpanHist);
        freeSpanHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeFreeSpanHist(FreeSpanHist freeSpanHist) {
        this.freeSpanHists.remove(freeSpanHist);
        freeSpanHist.setIdPipelineSection(null);
        return this;
    }

    public void setFreeSpanHists(Set<FreeSpanHist> freeSpanHists) {
        this.freeSpanHists = freeSpanHists;
    }

    public Set<FreeSpanSupportHist> getFreeSpanSupportHists() {
        return freeSpanSupportHists;
    }

    public PipelineSection freeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
        return this;
    }

    public PipelineSection addFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.add(freeSpanSupportHist);
        freeSpanSupportHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.remove(freeSpanSupportHist);
        freeSpanSupportHist.setIdPipelineSection(null);
        return this;
    }

    public void setFreeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
    }

    public Set<PipeHist> getPipeHists() {
        return pipeHists;
    }

    public PipelineSection pipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
        return this;
    }

    public PipelineSection addPipeHist(PipeHist pipeHist) {
        this.pipeHists.add(pipeHist);
        pipeHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removePipeHist(PipeHist pipeHist) {
        this.pipeHists.remove(pipeHist);
        pipeHist.setIdPipelineSection(null);
        return this;
    }

    public void setPipeHists(Set<PipeHist> pipeHists) {
        this.pipeHists = pipeHists;
    }

    public Set<TeeHist> getTeeHists() {
        return teeHists;
    }

    public PipelineSection teeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
        return this;
    }

    public PipelineSection addTeeHist(TeeHist teeHist) {
        this.teeHists.add(teeHist);
        teeHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeTeeHist(TeeHist teeHist) {
        this.teeHists.remove(teeHist);
        teeHist.setIdPipelineSection(null);
        return this;
    }

    public void setTeeHists(Set<TeeHist> teeHists) {
        this.teeHists = teeHists;
    }

    public Set<ValveHist> getValveHists() {
        return valveHists;
    }

    public PipelineSection valveHists(Set<ValveHist> valveHists) {
        this.valveHists = valveHists;
        return this;
    }

    public PipelineSection addValveHist(ValveHist valveHist) {
        this.valveHists.add(valveHist);
        valveHist.setIdPipelineSection(this);
        return this;
    }

    public PipelineSection removeValveHist(ValveHist valveHist) {
        this.valveHists.remove(valveHist);
        valveHist.setIdPipelineSection(null);
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
            ", isOnshore=" + getIsOnshore() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
