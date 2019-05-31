package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListClayTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListClayType} and its DTO {@link ListClayTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListClayTypeMapper extends EntityMapper<ListClayTypeDTO, ListClayType> {



    default ListClayType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListClayType listClayType = new ListClayType();
        listClayType.setId(id);
        return listClayType;
    }
}
