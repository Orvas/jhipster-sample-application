package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBendManufacturerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBendManufacturer} and its DTO {@link ListBendManufacturerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBendManufacturerMapper extends EntityMapper<ListBendManufacturerDTO, ListBendManufacturer> {


    @Mapping(target = "bendHists", ignore = true)
    ListBendManufacturer toEntity(ListBendManufacturerDTO listBendManufacturerDTO);

    default ListBendManufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBendManufacturer listBendManufacturer = new ListBendManufacturer();
        listBendManufacturer.setId(id);
        return listBendManufacturer;
    }
}
