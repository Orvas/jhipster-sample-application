package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListDfctTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListDfctType} and its DTO {@link ListDfctTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ListDfctGroupMapper.class})
public interface ListDfctTypeMapper extends EntityMapper<ListDfctTypeDTO, ListDfctType> {

    @Mapping(source = "idGroup.id", target = "idGroupId")
    ListDfctTypeDTO toDto(ListDfctType listDfctType);

    @Mapping(source = "idGroupId", target = "idGroup")
    ListDfctType toEntity(ListDfctTypeDTO listDfctTypeDTO);

    default ListDfctType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListDfctType listDfctType = new ListDfctType();
        listDfctType.setId(id);
        return listDfctType;
    }
}
