package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListThreatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListThreat} and its DTO {@link ListThreatDTO}.
 */
@Mapper(componentModel = "spring", uses = {ListThreatGroupMapper.class})
public interface ListThreatMapper extends EntityMapper<ListThreatDTO, ListThreat> {

    @Mapping(source = "idGroup.id", target = "idGroupId")
    ListThreatDTO toDto(ListThreat listThreat);

    @Mapping(source = "idGroupId", target = "idGroup")
    ListThreat toEntity(ListThreatDTO listThreatDTO);

    default ListThreat fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListThreat listThreat = new ListThreat();
        listThreat.setId(id);
        return listThreat;
    }
}
