package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MetaListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MetaList} and its DTO {@link MetaListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MetaListMapper extends EntityMapper<MetaListDTO, MetaList> {



    default MetaList fromId(Long id) {
        if (id == null) {
            return null;
        }
        MetaList metaList = new MetaList();
        metaList.setId(id);
        return metaList;
    }
}
