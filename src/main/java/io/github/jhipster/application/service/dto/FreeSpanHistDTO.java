package io.github.jhipster.application.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.FreeSpanHist} entity.
 */
public class FreeSpanHistDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    private Long idWrk;

    private BigDecimal lenght;

    private BigDecimal lenghtAllow;

    private BigDecimal height;

    private Integer isMultispan;

    private BigDecimal gap;

    private BigDecimal kpStart;

    private BigDecimal kpEnd;

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


    private Long freeSpanId;

    private Long idPipelineSectionId;

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

    public Long getIdWrk() {
        return idWrk;
    }

    public void setIdWrk(Long idWrk) {
        this.idWrk = idWrk;
    }

    public BigDecimal getLenght() {
        return lenght;
    }

    public void setLenght(BigDecimal lenght) {
        this.lenght = lenght;
    }

    public BigDecimal getLenghtAllow() {
        return lenghtAllow;
    }

    public void setLenghtAllow(BigDecimal lenghtAllow) {
        this.lenghtAllow = lenghtAllow;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getIsMultispan() {
        return isMultispan;
    }

    public void setIsMultispan(Integer isMultispan) {
        this.isMultispan = isMultispan;
    }

    public BigDecimal getGap() {
        return gap;
    }

    public void setGap(BigDecimal gap) {
        this.gap = gap;
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

    public Long getFreeSpanId() {
        return freeSpanId;
    }

    public void setFreeSpanId(Long freeSpanId) {
        this.freeSpanId = freeSpanId;
    }

    public Long getIdPipelineSectionId() {
        return idPipelineSectionId;
    }

    public void setIdPipelineSectionId(Long pipelineSectionId) {
        this.idPipelineSectionId = pipelineSectionId;
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

        FreeSpanHistDTO freeSpanHistDTO = (FreeSpanHistDTO) o;
        if (freeSpanHistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), freeSpanHistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FreeSpanHistDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", idWrk=" + getIdWrk() +
            ", lenght=" + getLenght() +
            ", lenghtAllow=" + getLenghtAllow() +
            ", height=" + getHeight() +
            ", isMultispan=" + getIsMultispan() +
            ", gap=" + getGap() +
            ", kpStart=" + getKpStart() +
            ", kpEnd=" + getKpEnd() +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", comment='" + getComment() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            ", freeSpan=" + getFreeSpanId() +
            ", idPipelineSection=" + getIdPipelineSectionId() +
            ", idStatus=" + getIdStatusId() +
            "}";
    }
}
