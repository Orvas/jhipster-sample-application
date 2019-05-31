package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tee} and its DTO {@link TeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface TeeMapper extends EntityMapper<TeeDTO, Tee> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    TeeDTO toDto(Tee tee);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "teeHist", ignore = true)
    Tee toEntity(TeeDTO teeDTO);

    default Tee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tee tee = new Tee();
        tee.setId(id);
        return tee;
    }
}
