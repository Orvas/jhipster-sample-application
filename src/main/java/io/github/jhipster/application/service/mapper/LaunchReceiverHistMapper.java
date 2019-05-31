package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.LaunchReceiverHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LaunchReceiverHist} and its DTO {@link LaunchReceiverHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {LaunchReceiverMapper.class, PipelineMapper.class, ListObjectStatusMapper.class})
public interface LaunchReceiverHistMapper extends EntityMapper<LaunchReceiverHistDTO, LaunchReceiverHist> {

    @Mapping(source = "launchReceiver.id", target = "launchReceiverId")
    @Mapping(source = "idPipeline.id", target = "idPipelineId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    LaunchReceiverHistDTO toDto(LaunchReceiverHist launchReceiverHist);

    @Mapping(source = "launchReceiverId", target = "launchReceiver")
    @Mapping(source = "idPipelineId", target = "idPipeline")
    @Mapping(source = "idStatusId", target = "idStatus")
    LaunchReceiverHist toEntity(LaunchReceiverHistDTO launchReceiverHistDTO);

    default LaunchReceiverHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        LaunchReceiverHist launchReceiverHist = new LaunchReceiverHist();
        launchReceiverHist.setId(id);
        return launchReceiverHist;
    }
}
