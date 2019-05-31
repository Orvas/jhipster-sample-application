package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FreeSpanHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreeSpanHist} and its DTO {@link FreeSpanHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {FreeSpanMapper.class, PipelineSectionMapper.class, ListObjectStatusMapper.class})
public interface FreeSpanHistMapper extends EntityMapper<FreeSpanHistDTO, FreeSpanHist> {

    @Mapping(source = "freeSpan.id", target = "freeSpanId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    FreeSpanHistDTO toDto(FreeSpanHist freeSpanHist);

    @Mapping(source = "freeSpanId", target = "freeSpan")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idStatusId", target = "idStatus")
    FreeSpanHist toEntity(FreeSpanHistDTO freeSpanHistDTO);

    default FreeSpanHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        FreeSpanHist freeSpanHist = new FreeSpanHist();
        freeSpanHist.setId(id);
        return freeSpanHist;
    }
}
