package io.github.jhipster.application.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.DisplacementHist} entity.
 */
public class DisplacementHistDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    private Long idWrk;

    private BigDecimal deltaX;

    private BigDecimal deltaY;

    private BigDecimal deltaZ;

    private BigDecimal deltaTotal;

    private BigDecimal kpStart;

    private BigDecimal kpEnd;

    @NotNull
    private Integer isCurrentFlag;

    @NotNull
    private Long idStatus;

    @Size(max = 255)
    private String comment;

    private Instant dateCreate;

    private Instant dateEdit;

    @Size(max = 255)
    private String creator;

    @Size(max = 255)
    private String editor;


    private Long idId;

    private Long idPipelineSectionId;

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

    public Long getIdWrk() {
        return idWrk;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimal getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(BigDecimal deltaX) {
        this.deltaX = deltaX;
    }

    public BigDecimal getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(BigDecimal deltaY) {
        this.deltaY = deltaY;
    }

    public BigDecimal getDeltaZ() {
        return deltaZ;
    }

    public void setDeltaZ(BigDecimal deltaZ) {
        this.deltaZ = deltaZ;
    }

    public BigDecimal getDeltaTotal() {
        return deltaTotal;
    }

    public void setDeltaTotal(BigDecimal deltaTotal) {
        this.deltaTotal = deltaTotal;
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

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
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

    public void setIdId(Long displacementId) {
        this.idId = displacementId;
    }

    public Long getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(Long pipelineSectionId) {
        this.idPipelineSectionId = pipelineSectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DisplacementHistDTO displacementHistDTO = (DisplacementHistDTO) o;
        if (displacementHistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), displacementHistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisplacementHistDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", deltaX=" + getDeltaX() +
            ", deltaY=" + getDeltaY() +
            ", deltaZ=" + getDeltaZ() +
            ", deltaTotal=" + getDeltaTotal() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", idStatus=" + getIdStatus() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            ", id=" + getIdId() +
            ", idPipelineSection=" + getIdPipelineSectionId() +
            "}";
    }
}
