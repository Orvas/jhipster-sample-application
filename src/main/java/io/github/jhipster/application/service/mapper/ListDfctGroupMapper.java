package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListDfctGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListDfctGroup} and its DTO {@link ListDfctGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListDfctGroupMapper extends EntityMapper<ListDfctGroupDTO, ListDfctGroup> {


    @Mapping(target = "listDfctTypes", ignore = true)
    ListDfctGroup toEntity(ListDfctGroupDTO listDfctGroupDTO);

    default ListDfctGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListDfctGroup listDfctGroup = new ListDfctGroup();
        listDfctGroup.setId(id);
        return listDfctGroup;
    }
}
