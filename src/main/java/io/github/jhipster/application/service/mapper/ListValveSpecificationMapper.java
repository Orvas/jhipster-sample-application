package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListValveSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListValveSpecification} and its DTO {@link ListValveSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListValveSpecificationMapper extends EntityMapper<ListValveSpecificationDTO, ListValveSpecification> {


    @Mapping(target = "valveHists", ignore = true)
    ListValveSpecification toEntity(ListValveSpecificationDTO listValveSpecificationDTO);

    default ListValveSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListValveSpecification listValveSpecification = new ListValveSpecification();
        listValveSpecification.setId(id);
        return listValveSpecification;
    }
}
