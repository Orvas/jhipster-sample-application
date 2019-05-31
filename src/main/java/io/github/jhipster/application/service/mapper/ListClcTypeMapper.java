package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListClcTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListClcType} and its DTO {@link ListClcTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListClcTypeMapper extends EntityMapper<ListClcTypeDTO, ListClcType> {



    default ListClcType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListClcType listClcType = new ListClcType();
        listClcType.setId(id);
        return listClcType;
    }
}
