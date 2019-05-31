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
 * Cathodic protecion station. Time-independent attribute table. One table  rows corresponds to one station
 */
@Entity
@Table(name = "cps")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cps implements Serializable {

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
    @JsonIgnoreProperties("cps")
    private BaseClass id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CpsHist> cpsHists = new HashSet<>();

    @OneToMany(mappedBy = "idCps")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CpsRange> cpsRanges = new HashSet<>();

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

    public Cps dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public Cps dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public Cps creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public Cps editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BaseClass getId() {
        return id;
    }

    public Cps id(BaseClass baseClass) {
        this.id = baseClass;
        return this;
    }

    public void setId(BaseClass baseClass) {
        this.id = baseClass;
    }

    public Set<CpsHist> getCpsHists() {
        return cpsHists;
    }

    public Cps cpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
        return this;
    }

    public Cps addCpsHist(CpsHist cpsHist) {
        this.cpsHists.add(cpsHist);
        cpsHist.setId(this);
        return this;
    }

    public Cps removeCpsHist(CpsHist cpsHist) {
        this.cpsHists.remove(cpsHist);
        cpsHist.setId(null);
        return this;
    }

    public void setCpsHists(Set<CpsHist> cpsHists) {
        this.cpsHists = cpsHists;
    }

    public Set<CpsRange> getCpsRanges() {
        return cpsRanges;
    }

    public Cps cpsRanges(Set<CpsRange> cpsRanges) {
        this.cpsRanges = cpsRanges;
        return this;
    }

    public Cps addCpsRange(CpsRange cpsRange) {
        this.cpsRanges.add(cpsRange);
        cpsRange.setIdCps(this);
        return this;
    }

    public Cps removeCpsRange(CpsRange cpsRange) {
        this.cpsRanges.remove(cpsRange);
        cpsRange.setIdCps(null);
        return this;
    }

    public void setCpsRanges(Set<CpsRange> cpsRanges) {
        this.cpsRanges = cpsRanges;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cps)) {
            return false;
        }
        return id != null && id.equals(((Cps) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cps{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
