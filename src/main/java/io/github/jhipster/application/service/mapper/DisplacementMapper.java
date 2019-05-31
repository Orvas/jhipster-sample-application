package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DisplacementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Displacement} and its DTO {@link DisplacementDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface DisplacementMapper extends EntityMapper<DisplacementDTO, Displacement> {

    @Mapping(source = "id.id", target = "idId")
    DisplacementDTO toDto(Displacement displacement);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "displacementHists", ignore = true)
    Displacement toEntity(DisplacementDTO displacementDTO);

    default Displacement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Displacement displacement = new Displacement();
        displacement.setId(id);
        return displacement;
    }
}
