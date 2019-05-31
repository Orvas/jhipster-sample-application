package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListAnodeBraceleteType} and its DTO {@link ListAnodeBraceleteTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListAnodeBraceleteTypeMapper extends EntityMapper<ListAnodeBraceleteTypeDTO, ListAnodeBraceleteType> {


    @Mapping(target = "anodeHists", ignore = true)
    ListAnodeBraceleteType toEntity(ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO);

    default ListAnodeBraceleteType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListAnodeBraceleteType listAnodeBraceleteType = new ListAnodeBraceleteType();
        listAnodeBraceleteType.setId(id);
        return listAnodeBraceleteType;
    }
}
