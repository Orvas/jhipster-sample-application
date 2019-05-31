package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListPipejointTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListPipejointType} and its DTO {@link ListPipejointTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListPipejointTypeMapper extends EntityMapper<ListPipejointTypeDTO, ListPipejointType> {


    @Mapping(target = "pipejointHists", ignore = true)
    ListPipejointType toEntity(ListPipejointTypeDTO listPipejointTypeDTO);

    default ListPipejointType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListPipejointType listPipejointType = new ListPipejointType();
        listPipejointType.setId(id);
        return listPipejointType;
    }
}
