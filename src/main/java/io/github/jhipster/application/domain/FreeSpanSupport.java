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
 * Free span support  main table. One table  rows corresponds to one free span support
 */
@Entity
@Table(name = "free_span_support")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreeSpanSupport implements Serializable {

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
    @JsonIgnoreProperties("freeSpanSupports")
    private BaseClass id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FreeSpanSupportHist> freeSpanSupportHists = new HashSet<>();

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

    public FreeSpanSupport dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public FreeSpanSupport dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public FreeSpanSupport creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public FreeSpanSupport editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BaseClass getId() {
        return id;
    }

    public FreeSpanSupport id(BaseClass baseClass) {
        this.id = baseClass;
        return this;
    }

    public void setId(BaseClass baseClass) {
        this.id = baseClass;
    }

    public Set<FreeSpanSupportHist> getFreeSpanSupportHists() {
        return freeSpanSupportHists;
    }

    public FreeSpanSupport freeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
        return this;
    }

    public FreeSpanSupport addFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.add(freeSpanSupportHist);
        freeSpanSupportHist.setId(this);
        return this;
    }

    public FreeSpanSupport removeFreeSpanSupportHist(FreeSpanSupportHist freeSpanSupportHist) {
        this.freeSpanSupportHists.remove(freeSpanSupportHist);
        freeSpanSupportHist.setId(null);
        return this;
    }

    public void setFreeSpanSupportHists(Set<FreeSpanSupportHist> freeSpanSupportHists) {
        this.freeSpanSupportHists = freeSpanSupportHists;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FreeSpanSupport)) {
            return false;
        }
        return id != null && id.equals(((FreeSpanSupport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FreeSpanSupport{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
