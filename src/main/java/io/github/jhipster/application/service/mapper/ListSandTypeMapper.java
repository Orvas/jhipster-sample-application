package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListSandTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListSandType} and its DTO {@link ListSandTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListSandTypeMapper extends EntityMapper<ListSandTypeDTO, ListSandType> {



    default ListSandType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListSandType listSandType = new ListSandType();
        listSandType.setId(id);
        return listSandType;
    }
}
