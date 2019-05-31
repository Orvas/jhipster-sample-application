package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListClcLvlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListClcLvl} and its DTO {@link ListClcLvlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListClcLvlMapper extends EntityMapper<ListClcLvlDTO, ListClcLvl> {



    default ListClcLvl fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListClcLvl listClcLvl = new ListClcLvl();
        listClcLvl.setId(id);
        return listClcLvl;
    }
}
