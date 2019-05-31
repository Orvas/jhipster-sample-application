package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListMaterialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListMaterial} and its DTO {@link ListMaterialDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListMaterialMapper extends EntityMapper<ListMaterialDTO, ListMaterial> {


    @Mapping(target = "anodeHists", ignore = true)
    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "pipejointHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListMaterial toEntity(ListMaterialDTO listMaterialDTO);

    default ListMaterial fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListMaterial listMaterial = new ListMaterial();
        listMaterial.setId(id);
        return listMaterial;
    }
}
