package io.github.jhipster.application.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.PipelineHist} entity.
 */
public class PipelineHistDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    @Size(max = 255)
    private String name;

    private Integer designLife;

    @NotNull
    private Integer isCurrentFlag;

    @Size(max = 255)
    private String comment;

    private Instant dateCreate;

    private Instant dateEdit;

    @Size(max = 255)
    private String creator;

    @Size(max = 255)
    private String editor;


    private Long idId;

    private Long idLocationId;

    private Long idStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Instant getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Instant dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long pipelineId) {
        this.idId = pipelineId;
    }

    public Long getIdLocationId() {
        return idLocationId;
    }

    public void setIdLocationId(Long listPipelineLocationId) {
        this.idLocationId = listPipelineLocationId;
    }

    public Long getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(Long listObjectStatusId) {
        this.idStatusId = listObjectStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PipelineHistDTO pipelineHistDTO = (PipelineHistDTO) o;
        if (pipelineHistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pipelineHistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PipelineHistDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", name='" + getName() + "'" +
            ", designLife=" + getDesignLife() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            ", id=" + getIdId() +
            ", idLocation=" + getIdLocationId() +
            ", idStatus=" + getIdStatusId() +
            "}";
    }
}
