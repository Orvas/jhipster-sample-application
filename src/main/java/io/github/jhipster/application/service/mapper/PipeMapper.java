package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pipe} and its DTO {@link PipeDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface PipeMapper extends EntityMapper<PipeDTO, Pipe> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    PipeDTO toDto(Pipe pipe);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "pipeHist", ignore = true)
    Pipe toEntity(PipeDTO pipeDTO);

    default Pipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pipe pipe = new Pipe();
        pipe.setId(id);
        return pipe;
    }
}
