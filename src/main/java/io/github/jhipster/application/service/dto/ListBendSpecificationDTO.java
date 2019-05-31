package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ListBendSpecification} entity.
 */
public class ListBendSpecificationDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String code;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String fullName;

    @NotNull
    private Integer isCurrentFlag;

    @Size(max = 255)
    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getIsCurrentFlag() {
        return isCurrentFlag;
    }

    public void setIsCurrentFlag(Integer isCurrentFlag) {
        this.isCurrentFlag = isCurrentFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        ListBendSpecificationDTO listBendSpecificationDTO = (ListBendSpecificationDTO) o;
        if (listBendSpecificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listBendSpecificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ListBendSpecificationDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", isCurrentFlag=" + getIsCurrentFlag() +
            ", description='" + getDescription() + "'" +
            ", dateCreate='" + getDateCreate() + "'" +
            ", dateEdit='" + getDateEdit() + "'" +
            ", creator='" + getCreator() + "'" +
            ", editor='" + getEditor() + "'" +
            "}";
    }
}
