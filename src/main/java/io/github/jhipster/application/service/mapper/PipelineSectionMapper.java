package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipelineSectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PipelineSection} and its DTO {@link PipelineSectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class, PipelineMapper.class, ListSafetyClassMapper.class})
public interface PipelineSectionMapper extends EntityMapper<PipelineSectionDTO, PipelineSection> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    @Mapping(source = "idPipeline.id", target = "idPipelineId")
    @Mapping(source = "idSafetyClass.id", target = "idSafetyClassId")
    PipelineSectionDTO toDto(PipelineSection pipelineSection);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(source = "idPipelineId", target = "idPipeline")
    @Mapping(source = "idSafetyClassId", target = "idSafetyClass")
    @Mapping(target = "anodeHists", ignore = true)
    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "cpsHists", ignore = true)
    @Mapping(target = "cpsRanges", ignore = true)
    @Mapping(target = "displacementHists", ignore = true)
    @Mapping(target = "freeSpanHists", ignore = true)
    @Mapping(target = "freeSpanSupportHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    PipelineSection toEntity(PipelineSectionDTO pipelineSectionDTO);

    default PipelineSection fromId(Long id) {
        if (id == null) {
            return null;
        }
        PipelineSection pipelineSection = new PipelineSection();
        pipelineSection.setId(id);
        return pipelineSection;
    }
}
