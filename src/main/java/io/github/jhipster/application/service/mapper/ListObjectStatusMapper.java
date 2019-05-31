package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListObjectStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListObjectStatus} and its DTO {@link ListObjectStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListObjectStatusMapper extends EntityMapper<ListObjectStatusDTO, ListObjectStatus> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "cpsHists", ignore = true)
    @Mapping(target = "freeSpanHists", ignore = true)
    @Mapping(target = "freeSpanSupportHists", ignore = true)
    @Mapping(target = "launchReceiverHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "pipejointHists", ignore = true)
    @Mapping(target = "pipelineHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListObjectStatus toEntity(ListObjectStatusDTO listObjectStatusDTO);

    default ListObjectStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListObjectStatus listObjectStatus = new ListObjectStatus();
        listObjectStatus.setId(id);
        return listObjectStatus;
    }
}
