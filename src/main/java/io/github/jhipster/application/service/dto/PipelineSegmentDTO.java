package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.PipelineSegment} entity.
 */
@ApiModel(description = "One table rows corresponds to one pipeline segment List of Pipeline segments: Segment Number/Segment name/Pipeline 1 KP_start kp_end/Pipeline 4 kp_start kp_end 1Onshore Pipeline - Russia -2.2 0 -2.2 0 2 Micro Tunnel 0 1.4 0 1.4 3 Shelf section- Russia 1.4 30.1 1.4 31.1 4 Slope 30° - Russia 30.1 53 31.1 53 5 Abyssal Plain 1 (Russia territorial waters end) 53 228.3 53 228.3 6 Abyssal Plain 2 (original SS scope end) 228.3 660 228.3 660 7 Abyssal Plain 3 (New TurkStream Scope) 660 792 660 790 8 Slope 10° - Turkey 792 876.4 790 874.9 9 Shelf section - Turkey 876.4 923.2 874.9 921.8 10 Open Cut Shore Crossing 923.2 925.6 921.8 924.2 11 Onshore Pipeline - Turkey 1000 1001.8 1000 1001.8")
public class PipelineSegmentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String num;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private BigDecimal kpStart1;

    @NotNull
    private BigDecimal kpEnd1;

    @NotNull
    private BigDecimal kpStart4;

    @NotNull
    private BigDecimal kpEnd4;

    private Instant dateCreate;

    private Instant dateEdit;

    @Size(max = 255)
    private String creator;

    @Size(max = 255)
    private String editor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getKpStart1() {
        return kpStart1;
    }

    public void setKpStart1(BigDecimal kpStart1) {
        this.kpStart1 = kpStart1;
    }

    public BigDecimal getKpEnd1() {
        return kpEnd1;
    }

    public void setKpEnd1(BigDecimal kpEnd1) {
        this.kpEnd1 = kpEnd1;
    }

    public BigDecimal getKpStart4() {
        return kpStart4;
    }

    public void setKpStart4(BigDecimal kpStart4) {
        this.kpStart4 = kpStart4;
    }

    public BigDecimal getKpEnd4() {
        return kpEnd4;
    }

    public void setKpEnd4(BigDecimal kpEnd4) {
        this.kpEnd4 = kpEnd4;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PipelineSegmentDTO pipelineSegmentDTO = (PipelineSegmentDTO) o;
        if (pipelineSegmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pipelineSegmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PipelineSegmentDTO{" +
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
