package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DisplacementHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DisplacementHist} and its DTO {@link DisplacementHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {DisplacementMapper.class, PipelineSectionMapper.class})
public interface DisplacementHistMapper extends EntityMapper<DisplacementHistDTO, DisplacementHist> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    DisplacementHistDTO toDto(DisplacementHist displacementHist);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    DisplacementHist toEntity(DisplacementHistDTO displacementHistDTO);

    default DisplacementHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        DisplacementHist displacementHist = new DisplacementHist();
        displacementHist.setId(id);
        return displacementHist;
    }
}
