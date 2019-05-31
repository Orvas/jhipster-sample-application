package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListMinpressUcaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListMinpressUcase} and its DTO {@link ListMinpressUcaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListMinpressUcaseMapper extends EntityMapper<ListMinpressUcaseDTO, ListMinpressUcase> {



    default ListMinpressUcase fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListMinpressUcase listMinpressUcase = new ListMinpressUcase();
        listMinpressUcase.setId(id);
        return listMinpressUcase;
    }
}
