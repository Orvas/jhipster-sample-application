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
 * Anode main table. One table  rows corresponds to one anode
 */
@Entity
@Table(name = "anode")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Anode implements Serializable {

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
    @JsonIgnoreProperties("anodes")
    private BaseClass id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AnodeHist> anodeHists = new HashSet<>();

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

    public Anode dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public Anode dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public Anode creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public Anode editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BaseClass getId() {
        return id;
    }

    public Anode id(BaseClass baseClass) {
        this.id = baseClass;
        return this;
    }

    public void setId(BaseClass baseClass) {
        this.id = baseClass;
    }

    public Set<AnodeHist> getAnodeHists() {
        return anodeHists;
    }

    public Anode anodeHists(Set<AnodeHist> anodeHists) {
        this.anodeHists = anodeHists;
        return this;
    }

    public Anode addAnodeHist(AnodeHist anodeHist) {
        this.anodeHists.add(anodeHist);
        anodeHist.setId(this);
        return this;
    }

    public Anode removeAnodeHist(AnodeHist anodeHist) {
        this.anodeHists.remove(anodeHist);
        anodeHist.setId(null);
        return this;
    }

    public void setAnodeHists(Set<AnodeHist> anodeHists) {
        this.anodeHists = anodeHists;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Anode)) {
            return false;
        }
        return id != null && id.equals(((Anode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Anode{" +
            "id=" + getId() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
