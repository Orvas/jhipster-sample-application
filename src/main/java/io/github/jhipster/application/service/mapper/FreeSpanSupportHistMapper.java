package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreeSpanSupportHist} and its DTO {@link FreeSpanSupportHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {FreeSpanSupportMapper.class, PipelineSectionMapper.class, ListObjectStatusMapper.class})
public interface FreeSpanSupportHistMapper extends EntityMapper<FreeSpanSupportHistDTO, FreeSpanSupportHist> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    FreeSpanSupportHistDTO toDto(FreeSpanSupportHist freeSpanSupportHist);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idStatusId", target = "idStatus")
    FreeSpanSupportHist toEntity(FreeSpanSupportHistDTO freeSpanSupportHistDTO);

    default FreeSpanSupportHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        FreeSpanSupportHist freeSpanSupportHist = new FreeSpanSupportHist();
        freeSpanSupportHist.setId(id);
        return freeSpanSupportHist;
    }
}
