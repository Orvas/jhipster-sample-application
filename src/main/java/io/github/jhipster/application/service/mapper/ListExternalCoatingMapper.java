package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListExternalCoatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListExternalCoating} and its DTO {@link ListExternalCoatingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListExternalCoatingMapper extends EntityMapper<ListExternalCoatingDTO, ListExternalCoating> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "pipejointHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListExternalCoating toEntity(ListExternalCoatingDTO listExternalCoatingDTO);

    default ListExternalCoating fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListExternalCoating listExternalCoating = new ListExternalCoating();
        listExternalCoating.setId(id);
        return listExternalCoating;
    }
}
