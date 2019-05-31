package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListSafetyClassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListSafetyClass} and its DTO {@link ListSafetyClassDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListSafetyClassMapper extends EntityMapper<ListSafetyClassDTO, ListSafetyClass> {


    @Mapping(target = "pipelineSections", ignore = true)
    ListSafetyClass toEntity(ListSafetyClassDTO listSafetyClassDTO);

    default ListSafetyClass fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListSafetyClass listSafetyClass = new ListSafetyClass();
        listSafetyClass.setId(id);
        return listSafetyClass;
    }
}
