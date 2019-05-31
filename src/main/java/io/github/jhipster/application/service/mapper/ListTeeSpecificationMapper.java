package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListTeeSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListTeeSpecification} and its DTO {@link ListTeeSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListTeeSpecificationMapper extends EntityMapper<ListTeeSpecificationDTO, ListTeeSpecification> {


    @Mapping(target = "teeHists", ignore = true)
    ListTeeSpecification toEntity(ListTeeSpecificationDTO listTeeSpecificationDTO);

    default ListTeeSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListTeeSpecification listTeeSpecification = new ListTeeSpecification();
        listTeeSpecification.setId(id);
        return listTeeSpecification;
    }
}
