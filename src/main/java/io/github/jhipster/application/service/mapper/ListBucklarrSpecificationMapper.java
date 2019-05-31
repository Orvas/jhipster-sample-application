package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBucklarrSpecification} and its DTO {@link ListBucklarrSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBucklarrSpecificationMapper extends EntityMapper<ListBucklarrSpecificationDTO, ListBucklarrSpecification> {


    @Mapping(target = "buckleArrestorHists", ignore = true)
    ListBucklarrSpecification toEntity(ListBucklarrSpecificationDTO listBucklarrSpecificationDTO);

    default ListBucklarrSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBucklarrSpecification listBucklarrSpecification = new ListBucklarrSpecification();
        listBucklarrSpecification.setId(id);
        return listBucklarrSpecification;
    }
}
