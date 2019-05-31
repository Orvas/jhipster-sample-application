package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListClcKindDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListClcKind} and its DTO {@link ListClcKindDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListClcKindMapper extends EntityMapper<ListClcKindDTO, ListClcKind> {



    default ListClcKind fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListClcKind listClcKind = new ListClcKind();
        listClcKind.setId(id);
        return listClcKind;
    }
}
