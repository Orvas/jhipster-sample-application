package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ValveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Valve} and its DTO {@link ValveDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface ValveMapper extends EntityMapper<ValveDTO, Valve> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    ValveDTO toDto(Valve valve);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "valveHist", ignore = true)
    Valve toEntity(ValveDTO valveDTO);

    default Valve fromId(Long id) {
        if (id == null) {
            return null;
        }
        Valve valve = new Valve();
        valve.setId(id);
        return valve;
    }
}
