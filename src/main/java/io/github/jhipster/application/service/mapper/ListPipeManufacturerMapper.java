package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListPipeManufacturerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListPipeManufacturer} and its DTO {@link ListPipeManufacturerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListPipeManufacturerMapper extends EntityMapper<ListPipeManufacturerDTO, ListPipeManufacturer> {


    @Mapping(target = "pipeHists", ignore = true)
    ListPipeManufacturer toEntity(ListPipeManufacturerDTO listPipeManufacturerDTO);

    default ListPipeManufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListPipeManufacturer listPipeManufacturer = new ListPipeManufacturer();
        listPipeManufacturer.setId(id);
        return listPipeManufacturer;
    }
}
