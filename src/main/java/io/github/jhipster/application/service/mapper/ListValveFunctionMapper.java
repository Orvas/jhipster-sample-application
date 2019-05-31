package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListValveFunctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListValveFunction} and its DTO {@link ListValveFunctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListValveFunctionMapper extends EntityMapper<ListValveFunctionDTO, ListValveFunction> {


    @Mapping(target = "valveHists", ignore = true)
    ListValveFunction toEntity(ListValveFunctionDTO listValveFunctionDTO);

    default ListValveFunction fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListValveFunction listValveFunction = new ListValveFunction();
        listValveFunction.setId(id);
        return listValveFunction;
    }
}
