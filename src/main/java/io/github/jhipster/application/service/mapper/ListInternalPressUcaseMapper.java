package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListInternalPressUcase} and its DTO {@link ListInternalPressUcaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListInternalPressUcaseMapper extends EntityMapper<ListInternalPressUcaseDTO, ListInternalPressUcase> {



    default ListInternalPressUcase fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListInternalPressUcase listInternalPressUcase = new ListInternalPressUcase();
        listInternalPressUcase.setId(id);
        return listInternalPressUcase;
    }
}
