package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListPipeSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListPipeSpecification} and its DTO {@link ListPipeSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListPipeSpecificationMapper extends EntityMapper<ListPipeSpecificationDTO, ListPipeSpecification> {


    @Mapping(target = "pipeHists", ignore = true)
    ListPipeSpecification toEntity(ListPipeSpecificationDTO listPipeSpecificationDTO);

    default ListPipeSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListPipeSpecification listPipeSpecification = new ListPipeSpecification();
        listPipeSpecification.setId(id);
        return listPipeSpecification;
    }
}
