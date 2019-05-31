package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListLongSeamWeldType} and its DTO {@link ListLongSeamWeldTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListLongSeamWeldTypeMapper extends EntityMapper<ListLongSeamWeldTypeDTO, ListLongSeamWeldType> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListLongSeamWeldType toEntity(ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO);

    default ListLongSeamWeldType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListLongSeamWeldType listLongSeamWeldType = new ListLongSeamWeldType();
        listLongSeamWeldType.setId(id);
        return listLongSeamWeldType;
    }
}
