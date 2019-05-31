package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListWrkcmmsStatus} and its DTO {@link ListWrkcmmsStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListWrkcmmsStatusMapper extends EntityMapper<ListWrkcmmsStatusDTO, ListWrkcmmsStatus> {



    default ListWrkcmmsStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListWrkcmmsStatus listWrkcmmsStatus = new ListWrkcmmsStatus();
        listWrkcmmsStatus.setId(id);
        return listWrkcmmsStatus;
    }
}
