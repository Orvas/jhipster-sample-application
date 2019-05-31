package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipejointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pipejoint} and its DTO {@link PipejointDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface PipejointMapper extends EntityMapper<PipejointDTO, Pipejoint> {

    @Mapping(source = "id.id", target = "idId")
    PipejointDTO toDto(Pipejoint pipejoint);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "pipejointHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    Pipejoint toEntity(PipejointDTO pipejointDTO);

    default Pipejoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pipejoint pipejoint = new Pipejoint();
        pipejoint.setId(id);
        return pipejoint;
    }
}
