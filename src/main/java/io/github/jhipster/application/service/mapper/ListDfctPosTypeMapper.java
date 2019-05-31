package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListDfctPosTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListDfctPosType} and its DTO {@link ListDfctPosTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListDfctPosTypeMapper extends EntityMapper<ListDfctPosTypeDTO, ListDfctPosType> {



    default ListDfctPosType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListDfctPosType listDfctPosType = new ListDfctPosType();
        listDfctPosType.setId(id);
        return listDfctPosType;
    }
}
