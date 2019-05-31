package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListRiskProbabilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListRiskProbability} and its DTO {@link ListRiskProbabilityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListRiskProbabilityMapper extends EntityMapper<ListRiskProbabilityDTO, ListRiskProbability> {



    default ListRiskProbability fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListRiskProbability listRiskProbability = new ListRiskProbability();
        listRiskProbability.setId(id);
        return listRiskProbability;
    }
}
