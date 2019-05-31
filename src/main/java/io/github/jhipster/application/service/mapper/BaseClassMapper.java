package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BaseClassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BaseClass} and its DTO {@link BaseClassDTO}.
 */
@Mapper(componentModel = "spring", uses = {ListObjectTypeMapper.class})
public interface BaseClassMapper extends EntityMapper<BaseClassDTO, BaseClass> {

    @Mapping(source = "idType.id", target = "idTypeId")
    BaseClassDTO toDto(BaseClass baseClass);

    @Mapping(source = "idTypeId", target = "idType")
    @Mapping(target = "pipejoint", ignore = true)
    @Mapping(target = "anodes", ignore = true)
    @Mapping(target = "bends", ignore = true)
    @Mapping(target = "buckleArrestors", ignore = true)
    @Mapping(target = "cps", ignore = true)
    @Mapping(target = "displacements", ignore = true)
    @Mapping(target = "freeSpans", ignore = true)
    @Mapping(target = "freeSpanSupports", ignore = true)
    @Mapping(target = "launchReceivers", ignore = true)
    @Mapping(target = "pipes", ignore = true)
    @Mapping(target = "pipelines", ignore = true)
    @Mapping(target = "pipelineSections", ignore = true)
    @Mapping(target = "tees", ignore = true)
    @Mapping(target = "valves", ignore = true)
    BaseClass toEntity(BaseClassDTO baseClassDTO);

    default BaseClass fromId(Long id) {
        if (id == null) {
            return null;
        }
        BaseClass baseClass = new BaseClass();
        baseClass.setId(id);
        return baseClass;
    }
}
