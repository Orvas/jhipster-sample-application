package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBoundaryCondUcase} and its DTO {@link ListBoundaryCondUcaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBoundaryCondUcaseMapper extends EntityMapper<ListBoundaryCondUcaseDTO, ListBoundaryCondUcase> {



    default ListBoundaryCondUcase fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBoundaryCondUcase listBoundaryCondUcase = new ListBoundaryCondUcase();
        listBoundaryCondUcase.setId(id);
        return listBoundaryCondUcase;
    }
}
