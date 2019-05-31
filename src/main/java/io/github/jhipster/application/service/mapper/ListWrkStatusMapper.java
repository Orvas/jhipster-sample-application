package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListWrkStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListWrkStatus} and its DTO {@link ListWrkStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {ListWrkKindMapper.class})
public interface ListWrkStatusMapper extends EntityMapper<ListWrkStatusDTO, ListWrkStatus> {

    @Mapping(source = "idWrkKind.id", target = "idWrkKindId")
    ListWrkStatusDTO toDto(ListWrkStatus listWrkStatus);

    @Mapping(source = "idWrkKindId", target = "idWrkKind")
    @Mapping(target = "anodeHists", ignore = true)
    ListWrkStatus toEntity(ListWrkStatusDTO listWrkStatusDTO);

    default ListWrkStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListWrkStatus listWrkStatus = new ListWrkStatus();
        listWrkStatus.setId(id);
        return listWrkStatus;
    }
}
