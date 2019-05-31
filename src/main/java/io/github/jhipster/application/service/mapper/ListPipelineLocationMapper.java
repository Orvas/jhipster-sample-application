package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListPipelineLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListPipelineLocation} and its DTO {@link ListPipelineLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListPipelineLocationMapper extends EntityMapper<ListPipelineLocationDTO, ListPipelineLocation> {


    @Mapping(target = "pipelineHists", ignore = true)
    ListPipelineLocation toEntity(ListPipelineLocationDTO listPipelineLocationDTO);

    default ListPipelineLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListPipelineLocation listPipelineLocation = new ListPipelineLocation();
        listPipelineLocation.setId(id);
        return listPipelineLocation;
    }
}
