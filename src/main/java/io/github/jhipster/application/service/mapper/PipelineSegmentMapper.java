package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipelineSegmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PipelineSegment} and its DTO {@link PipelineSegmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PipelineSegmentMapper extends EntityMapper<PipelineSegmentDTO, PipelineSegment> {



    default PipelineSegment fromId(Long id) {
        if (id == null) {
            return null;
        }
        PipelineSegment pipelineSegment = new PipelineSegment();
        pipelineSegment.setId(id);
        return pipelineSegment;
    }
}
