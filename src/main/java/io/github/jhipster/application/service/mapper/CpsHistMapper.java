package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CpsHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CpsHist} and its DTO {@link CpsHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {CpsMapper.class, PipelineSectionMapper.class, ListObjectStatusMapper.class})
public interface CpsHistMapper extends EntityMapper<CpsHistDTO, CpsHist> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    CpsHistDTO toDto(CpsHist cpsHist);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idStatusId", target = "idStatus")
    CpsHist toEntity(CpsHistDTO cpsHistDTO);

    default CpsHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        CpsHist cpsHist = new CpsHist();
        cpsHist.setId(id);
        return cpsHist;
    }
}
