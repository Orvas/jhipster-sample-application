package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListWrkPurposeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListWrkPurpose} and its DTO {@link ListWrkPurposeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListWrkPurposeMapper extends EntityMapper<ListWrkPurposeDTO, ListWrkPurpose> {



    default ListWrkPurpose fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListWrkPurpose listWrkPurpose = new ListWrkPurpose();
        listWrkPurpose.setId(id);
        return listWrkPurpose;
    }
}
