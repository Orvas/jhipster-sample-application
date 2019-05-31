package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBucklarrManufacturer} and its DTO {@link ListBucklarrManufacturerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBucklarrManufacturerMapper extends EntityMapper<ListBucklarrManufacturerDTO, ListBucklarrManufacturer> {


    @Mapping(target = "buckleArrestorHists", ignore = true)
    ListBucklarrManufacturer toEntity(ListBucklarrManufacturerDTO listBucklarrManufacturerDTO);

    default ListBucklarrManufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBucklarrManufacturer listBucklarrManufacturer = new ListBucklarrManufacturer();
        listBucklarrManufacturer.setId(id);
        return listBucklarrManufacturer;
    }
}
