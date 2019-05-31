package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListValveTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListValveType} and its DTO {@link ListValveTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListValveTypeMapper extends EntityMapper<ListValveTypeDTO, ListValveType> {


    @Mapping(target = "valveHists", ignore = true)
    ListValveType toEntity(ListValveTypeDTO listValveTypeDTO);

    default ListValveType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListValveType listValveType = new ListValveType();
        listValveType.setId(id);
        return listValveType;
    }
}
