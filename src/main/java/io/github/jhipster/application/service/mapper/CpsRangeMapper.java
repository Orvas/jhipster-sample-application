package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CpsRangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CpsRange} and its DTO {@link CpsRangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {CpsMapper.class, PipelineSectionMapper.class})
public interface CpsRangeMapper extends EntityMapper<CpsRangeDTO, CpsRange> {

    @Mapping(source = "idCps.id", target = "idCpsId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    CpsRangeDTO toDto(CpsRange cpsRange);

    @Mapping(source = "idCpsId", target = "idCps")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    CpsRange toEntity(CpsRangeDTO cpsRangeDTO);

    default CpsRange fromId(Long id) {
        if (id == null) {
            return null;
        }
        CpsRange cpsRange = new CpsRange();
        cpsRange.setId(id);
        return cpsRange;
    }
}
