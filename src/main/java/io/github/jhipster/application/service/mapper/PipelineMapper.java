package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipelineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pipeline} and its DTO {@link PipelineDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface PipelineMapper extends EntityMapper<PipelineDTO, Pipeline> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    PipelineDTO toDto(Pipeline pipeline);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "pipelineHist", ignore = true)
    @Mapping(target = "launchReceiverHists", ignore = true)
    @Mapping(target = "pipelineSections", ignore = true)
    Pipeline toEntity(PipelineDTO pipelineDTO);

    default Pipeline fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pipeline pipeline = new Pipeline();
        pipeline.setId(id);
        return pipeline;
    }
}
