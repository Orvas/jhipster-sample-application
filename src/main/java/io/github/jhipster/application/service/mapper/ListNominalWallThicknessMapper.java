package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListNominalWallThickness} and its DTO {@link ListNominalWallThicknessDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListNominalWallThicknessMapper extends EntityMapper<ListNominalWallThicknessDTO, ListNominalWallThickness> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListNominalWallThickness toEntity(ListNominalWallThicknessDTO listNominalWallThicknessDTO);

    default ListNominalWallThickness fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListNominalWallThickness listNominalWallThickness = new ListNominalWallThickness();
        listNominalWallThickness.setId(id);
        return listNominalWallThickness;
    }
}
