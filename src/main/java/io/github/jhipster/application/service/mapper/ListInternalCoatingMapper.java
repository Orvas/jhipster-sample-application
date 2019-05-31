package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListInternalCoatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListInternalCoating} and its DTO {@link ListInternalCoatingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListInternalCoatingMapper extends EntityMapper<ListInternalCoatingDTO, ListInternalCoating> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListInternalCoating toEntity(ListInternalCoatingDTO listInternalCoatingDTO);

    default ListInternalCoating fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListInternalCoating listInternalCoating = new ListInternalCoating();
        listInternalCoating.setId(id);
        return listInternalCoating;
    }
}
