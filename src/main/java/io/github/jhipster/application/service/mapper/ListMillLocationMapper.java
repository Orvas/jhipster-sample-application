package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListMillLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListMillLocation} and its DTO {@link ListMillLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListMillLocationMapper extends EntityMapper<ListMillLocationDTO, ListMillLocation> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListMillLocation toEntity(ListMillLocationDTO listMillLocationDTO);

    default ListMillLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListMillLocation listMillLocation = new ListMillLocation();
        listMillLocation.setId(id);
        return listMillLocation;
    }
}
