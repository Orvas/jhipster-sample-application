package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListEnvDirectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListEnvDirection} and its DTO {@link ListEnvDirectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListEnvDirectionMapper extends EntityMapper<ListEnvDirectionDTO, ListEnvDirection> {



    default ListEnvDirection fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListEnvDirection listEnvDirection = new ListEnvDirection();
        listEnvDirection.setId(id);
        return listEnvDirection;
    }
}
