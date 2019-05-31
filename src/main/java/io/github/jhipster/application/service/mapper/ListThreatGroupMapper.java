package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListThreatGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListThreatGroup} and its DTO {@link ListThreatGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListThreatGroupMapper extends EntityMapper<ListThreatGroupDTO, ListThreatGroup> {


    @Mapping(target = "listThreats", ignore = true)
    ListThreatGroup toEntity(ListThreatGroupDTO listThreatGroupDTO);

    default ListThreatGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListThreatGroup listThreatGroup = new ListThreatGroup();
        listThreatGroup.setId(id);
        return listThreatGroup;
    }
}
