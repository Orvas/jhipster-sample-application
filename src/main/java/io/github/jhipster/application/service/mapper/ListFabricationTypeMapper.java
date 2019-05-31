package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListFabricationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListFabricationType} and its DTO {@link ListFabricationTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListFabricationTypeMapper extends EntityMapper<ListFabricationTypeDTO, ListFabricationType> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListFabricationType toEntity(ListFabricationTypeDTO listFabricationTypeDTO);

    default ListFabricationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListFabricationType listFabricationType = new ListFabricationType();
        listFabricationType.setId(id);
        return listFabricationType;
    }
}
