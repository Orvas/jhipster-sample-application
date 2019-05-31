package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListEffAxforceUcase} and its DTO {@link ListEffAxforceUcaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListEffAxforceUcaseMapper extends EntityMapper<ListEffAxforceUcaseDTO, ListEffAxforceUcase> {



    default ListEffAxforceUcase fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListEffAxforceUcase listEffAxforceUcase = new ListEffAxforceUcase();
        listEffAxforceUcase.setId(id);
        return listEffAxforceUcase;
    }
}
