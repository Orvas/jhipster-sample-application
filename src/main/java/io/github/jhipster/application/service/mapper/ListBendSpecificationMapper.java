package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBendSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBendSpecification} and its DTO {@link ListBendSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBendSpecificationMapper extends EntityMapper<ListBendSpecificationDTO, ListBendSpecification> {


    @Mapping(target = "bendHists", ignore = true)
    ListBendSpecification toEntity(ListBendSpecificationDTO listBendSpecificationDTO);

    default ListBendSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBendSpecification listBendSpecification = new ListBendSpecification();
        listBendSpecification.setId(id);
        return listBendSpecification;
    }
}
