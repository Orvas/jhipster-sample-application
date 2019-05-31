package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListTeeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListTeeType} and its DTO {@link ListTeeTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListTeeTypeMapper extends EntityMapper<ListTeeTypeDTO, ListTeeType> {


    @Mapping(target = "teeHists", ignore = true)
    ListTeeType toEntity(ListTeeTypeDTO listTeeTypeDTO);

    default ListTeeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListTeeType listTeeType = new ListTeeType();
        listTeeType.setId(id);
        return listTeeType;
    }
}
