package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BendDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bend} and its DTO {@link BendDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface BendMapper extends EntityMapper<BendDTO, Bend> {

    @Mapping(source = "id.id", target = "idId")
    BendDTO toDto(Bend bend);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "bendHists", ignore = true)
    Bend toEntity(BendDTO bendDTO);

    default Bend fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bend bend = new Bend();
        bend.setId(id);
        return bend;
    }
}
