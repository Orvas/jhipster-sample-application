package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListObjectTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListObjectType} and its DTO {@link ListObjectTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListObjectTypeMapper extends EntityMapper<ListObjectTypeDTO, ListObjectType> {


    @Mapping(target = "baseClasses", ignore = true)
    ListObjectType toEntity(ListObjectTypeDTO listObjectTypeDTO);

    default ListObjectType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListObjectType listObjectType = new ListObjectType();
        listObjectType.setId(id);
        return listObjectType;
    }
}
