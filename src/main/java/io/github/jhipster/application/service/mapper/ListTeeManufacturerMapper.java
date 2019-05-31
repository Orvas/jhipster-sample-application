package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListTeeManufacturerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListTeeManufacturer} and its DTO {@link ListTeeManufacturerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListTeeManufacturerMapper extends EntityMapper<ListTeeManufacturerDTO, ListTeeManufacturer> {


    @Mapping(target = "teeHists", ignore = true)
    ListTeeManufacturer toEntity(ListTeeManufacturerDTO listTeeManufacturerDTO);

    default ListTeeManufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListTeeManufacturer listTeeManufacturer = new ListTeeManufacturer();
        listTeeManufacturer.setId(id);
        return listTeeManufacturer;
    }
}
