package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBucklarrTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBucklarrType} and its DTO {@link ListBucklarrTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBucklarrTypeMapper extends EntityMapper<ListBucklarrTypeDTO, ListBucklarrType> {


    @Mapping(target = "buckleArrestorHists", ignore = true)
    ListBucklarrType toEntity(ListBucklarrTypeDTO listBucklarrTypeDTO);

    default ListBucklarrType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBucklarrType listBucklarrType = new ListBucklarrType();
        listBucklarrType.setId(id);
        return listBucklarrType;
    }
}
