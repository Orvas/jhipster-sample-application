package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListEnvPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListEnvPoint} and its DTO {@link ListEnvPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListEnvPointMapper extends EntityMapper<ListEnvPointDTO, ListEnvPoint> {



    default ListEnvPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListEnvPoint listEnvPoint = new ListEnvPoint();
        listEnvPoint.setId(id);
        return listEnvPoint;
    }
}
