package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * One table rows corresponds to one pipeline segment
 * 
 * List of Pipeline segments:
 * 
 * Segment Number/Segment name/Pipeline 1 KP_start kp_end/Pipeline 4 kp_start kp_end
 * 
 * 1Onshore Pipeline - Russia -2.2 0 -2.2 0
 * 2 Micro Tunnel 0 1.4 0 1.4
 * 3 Shelf section- Russia 1.4 30.1 1.4 31.1
 * 4 Slope 30° - Russia 30.1 53 31.1 53
 * 5 Abyssal Plain 1 (Russia territorial waters end) 53 228.3 53 228.3
 * 6 Abyssal Plain 2 (original SS scope end) 228.3 660 228.3 660
 * 7 Abyssal Plain 3 (New TurkStream Scope) 660 792 660 790
 * 8 Slope 10° - Turkey 792 876.4 790 874.9
 * 9 Shelf section - Turkey 876.4 923.2 874.9 921.8
 * 10 Open Cut Shore Crossing 923.2 925.6 921.8 924.2
 * 11 Onshore Pipeline - Turkey 1000 1001.8 1000 1001.8
 */
@Entity
@Table(name = "pipeline_segment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PipelineSegment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "num", length = 255, nullable = false)
    private String num;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Column(name = "kp_start_1", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpStart1;

    @NotNull
    @Column(name = "kp_end_1", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpEnd1;

    @NotNull
    @Column(name = "kp_start_4", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpStart4;

    @NotNull
    @Column(name = "kp_end_4", precision = 21, scale = 2, nullable = false)
    private BigDecimal kpEnd4;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public PipelineSegment num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public PipelineSegment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getKpStart1() {
        return kpStart1;
    }

    public PipelineSegment kpStart1(BigDecimal kpStart1) {
        this.kpStart1 = kpStart1;
        return this;
    }

    public void setKpStart1(BigDecimal kpStart1) {
        this.kpStart1 = kpStart1;
    }

    public BigDecimal getKpEnd1() {
        return kpEnd1;
    }

    public PipelineSegment kpEnd1(BigDecimal kpEnd1) {
        this.kpEnd1 = kpEnd1;
        return this;
    }

    public void setKpEnd1(BigDecimal kpEnd1) {
        this.kpEnd1 = kpEnd1;
    }

    public BigDecimal getKpStart4() {
        return kpStart4;
    }

    public PipelineSegment kpStart4(BigDecimal kpStart4) {
        this.kpStart4 = kpStart4;
        return this;
    }

    public void setKpStart4(BigDecimal kpStart4) {
        this.kpStart4 = kpStart4;
    }

    public BigDecimal getKpEnd4() {
        return kpEnd4;
    }

    public PipelineSegment kpEnd4(BigDecimal kpEnd4) {
        this.kpEnd4 = kpEnd4;
        return this;
    }

    public void setKpEnd4(BigDecimal kpEnd4) {
        this.kpEnd4 = kpEnd4;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public PipelineSegment dateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public PipelineSegment dateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
        return this;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public PipelineSegment creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public PipelineSegment editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PipelineSegment)) {
            return false;
        }
        return id != null && id.equals(((PipelineSegment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PipelineSegment{" +
            "id=" + getId() +
            ", num='" + getNum() + "'" +
            ", name='" + getName() + "'" +
            ", kpStart1=" + getKpStart1() +
            ", kpEnd1=" + getKpEnd1() +
            ", kpStart4=" + getKpStart4() +
            ", kpEnd4=" + getKpEnd4() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
