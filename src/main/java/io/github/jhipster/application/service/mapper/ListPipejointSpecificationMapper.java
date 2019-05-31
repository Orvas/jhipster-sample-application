package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListPipejointSpecification} and its DTO {@link ListPipejointSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListPipejointSpecificationMapper extends EntityMapper<ListPipejointSpecificationDTO, ListPipejointSpecification> {


    @Mapping(target = "pipejointHists", ignore = true)
    ListPipejointSpecification toEntity(ListPipejointSpecificationDTO listPipejointSpecificationDTO);

    default ListPipejointSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListPipejointSpecification listPipejointSpecification = new ListPipejointSpecification();
        listPipejointSpecification.setId(id);
        return listPipejointSpecification;
    }
}
