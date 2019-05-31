package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListSoilTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListSoilType} and its DTO {@link ListSoilTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListSoilTypeMapper extends EntityMapper<ListSoilTypeDTO, ListSoilType> {



    default ListSoilType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListSoilType listSoilType = new ListSoilType();
        listSoilType.setId(id);
        return listSoilType;
    }
}
