package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListRiskConsequenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListRiskConsequence} and its DTO {@link ListRiskConsequenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListRiskConsequenceMapper extends EntityMapper<ListRiskConsequenceDTO, ListRiskConsequence> {



    default ListRiskConsequence fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListRiskConsequence listRiskConsequence = new ListRiskConsequence();
        listRiskConsequence.setId(id);
        return listRiskConsequence;
    }
}
