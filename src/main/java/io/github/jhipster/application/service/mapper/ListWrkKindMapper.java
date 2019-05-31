package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListWrkKindDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListWrkKind} and its DTO {@link ListWrkKindDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListWrkKindMapper extends EntityMapper<ListWrkKindDTO, ListWrkKind> {


    @Mapping(target = "listWrkStatuses", ignore = true)
    ListWrkKind toEntity(ListWrkKindDTO listWrkKindDTO);

    default ListWrkKind fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListWrkKind listWrkKind = new ListWrkKind();
        listWrkKind.setId(id);
        return listWrkKind;
    }
}
