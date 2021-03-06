package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AnodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Anode} and its DTO {@link AnodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface AnodeMapper extends EntityMapper<AnodeDTO, Anode> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    AnodeDTO toDto(Anode anode);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "anodeHist", ignore = true)
    Anode toEntity(AnodeDTO anodeDTO);

    default Anode fromId(Long id) {
        if (id == null) {
            return null;
        }
        Anode anode = new Anode();
        anode.setId(id);
        return anode;
    }
}
