package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.PipelineSection} entity.
 */
@ApiModel(description = "Homogeneous sections,  areas of unchanged values ??of the following characteristics: - is_onshore - safety class One table rows corresponds to one section")
public class PipelineSectionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Integer isOnshore;

    @NotNull
    private BigDecimal kpStart;

    @NotNull
    private BigDecimal kpEnd;

    private Instant dateCreate;

    private Instant dateEdit;

    @Size(max = 255)
    private String creator;

    @Size(max = 255)
    private String editor;


    private Long idId;

    private Long idPipelineId;

    private Long idSafetyClassId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsOnshore() {
        return isOnshore;
    }

    public void setIsOnshore(Integer isOnshore) {
        this.isOnshore = isOnshore;
    }

    public BigDecimal getKpStart() {
        return kpStart;
    }

    public void setKpStart(BigDecimal kpStart) {
        this.kpStart = kpStart;
    }

    public BigDecimal getKpEnd() {
        return kpEnd;
    }

    public void setKpEnd(BigDecimal kpEnd) {
        this.kpEnd = kpEnd;
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

    public void setIdId(Long baseClassId) {
        this.idId = baseClassId;
    }

    public Long getIdPipelineId() {
        return idPipelineId;
    }

    public void setIdPipelineId(Long pipelineId) {
        this.idPipelineId = pipelineId;
    }

    public Long getIdSafetyClassId() {
        return idSafetyClassId;
    }

    public void setIdSafetyClassId(Long listSafetyClassId) {
        this.idSafetyClassId = listSafetyClassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PipelineSectionDTO pipelineSectionDTO = (PipelineSectionDTO) o;
        if (pipelineSectionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pipelineSectionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PipelineSectionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isOnshore=" + getIsOnshore() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            ", id=" + getIdId() +
            ", idPipeline=" + getIdPipelineId() +
            ", idSafetyClass=" + getIdSafetyClassId() +
            "}";
    }
}
