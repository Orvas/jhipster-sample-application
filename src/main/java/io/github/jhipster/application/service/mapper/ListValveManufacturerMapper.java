package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListValveManufacturerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListValveManufacturer} and its DTO {@link ListValveManufacturerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListValveManufacturerMapper extends EntityMapper<ListValveManufacturerDTO, ListValveManufacturer> {


    @Mapping(target = "valveHists", ignore = true)
    ListValveManufacturer toEntity(ListValveManufacturerDTO listValveManufacturerDTO);

    default ListValveManufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListValveManufacturer listValveManufacturer = new ListValveManufacturer();
        listValveManufacturer.setId(id);
        return listValveManufacturer;
    }
}
