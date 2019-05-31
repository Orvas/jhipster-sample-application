package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipelineHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PipelineHist} and its DTO {@link PipelineHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {PipelineMapper.class, ListPipelineLocationMapper.class, ListObjectStatusMapper.class})
public interface PipelineHistMapper extends EntityMapper<PipelineHistDTO, PipelineHist> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "idLocation.id", target = "idLocationId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    PipelineHistDTO toDto(PipelineHist pipelineHist);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idLocationId", target = "idLocation")
    @Mapping(source = "idStatusId", target = "idStatus")
    PipelineHist toEntity(PipelineHistDTO pipelineHistDTO);

    default PipelineHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        PipelineHist pipelineHist = new PipelineHist();
        pipelineHist.setId(id);
        return pipelineHist;
    }
}
