package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListIliPigTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListIliPigType} and its DTO {@link ListIliPigTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListIliPigTypeMapper extends EntityMapper<ListIliPigTypeDTO, ListIliPigType> {



    default ListIliPigType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListIliPigType listIliPigType = new ListIliPigType();
        listIliPigType.setId(id);
        return listIliPigType;
    }
}
