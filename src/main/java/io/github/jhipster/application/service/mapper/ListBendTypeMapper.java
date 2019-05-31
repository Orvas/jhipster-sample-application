package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListBendTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListBendType} and its DTO {@link ListBendTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListBendTypeMapper extends EntityMapper<ListBendTypeDTO, ListBendType> {


    @Mapping(target = "bendHists", ignore = true)
    ListBendType toEntity(ListBendTypeDTO listBendTypeDTO);

    default ListBendType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListBendType listBendType = new ListBendType();
        listBendType.setId(id);
        return listBendType;
    }
}
