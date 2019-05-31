package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CpsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cps} and its DTO {@link CpsDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface CpsMapper extends EntityMapper<CpsDTO, Cps> {

    @Mapping(source = "id.id", target = "idId")
    CpsDTO toDto(Cps cps);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "cpsHists", ignore = true)
    @Mapping(target = "cpsRanges", ignore = true)
    Cps toEntity(CpsDTO cpsDTO);

    default Cps fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cps cps = new Cps();
        cps.setId(id);
        return cps;
    }
}
