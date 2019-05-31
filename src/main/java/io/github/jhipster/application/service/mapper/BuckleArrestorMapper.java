package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BuckleArrestorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BuckleArrestor} and its DTO {@link BuckleArrestorDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface BuckleArrestorMapper extends EntityMapper<BuckleArrestorDTO, BuckleArrestor> {

    @Mapping(source = "id.id", target = "idId")
    BuckleArrestorDTO toDto(BuckleArrestor buckleArrestor);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "buckleArrestorHists", ignore = true)
    BuckleArrestor toEntity(BuckleArrestorDTO buckleArrestorDTO);

    default BuckleArrestor fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuckleArrestor buckleArrestor = new BuckleArrestor();
        buckleArrestor.setId(id);
        return buckleArrestor;
    }
}
