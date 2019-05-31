package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListClcResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListClcResult} and its DTO {@link ListClcResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListClcResultMapper extends EntityMapper<ListClcResultDTO, ListClcResult> {



    default ListClcResult fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListClcResult listClcResult = new ListClcResult();
        listClcResult.setId(id);
        return listClcResult;
    }
}
