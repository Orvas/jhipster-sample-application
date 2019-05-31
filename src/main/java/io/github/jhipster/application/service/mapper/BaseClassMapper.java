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
    @Mapping(target = "anode", ignore = true)
    @Mapping(target = "bend", ignore = true)
    @Mapping(target = "buckleArrestor", ignore = true)
    @Mapping(target = "cps", ignore = true)
    @Mapping(target = "displacement", ignore = true)
    @Mapping(target = "freeSpan", ignore = true)
    @Mapping(target = "freeSpanSupport", ignore = true)
    @Mapping(target = "launchReceiver", ignore = true)
    @Mapping(target = "pipe", ignore = true)
    @Mapping(target = "pipejoint", ignore = true)
    @Mapping(target = "pipeline", ignore = true)
    @Mapping(target = "pipelineSection", ignore = true)
    @Mapping(target = "tee", ignore = true)
    @Mapping(target = "valve", ignore = true)
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
