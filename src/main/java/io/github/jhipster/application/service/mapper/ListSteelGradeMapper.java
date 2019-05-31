package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ListSteelGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ListSteelGrade} and its DTO {@link ListSteelGradeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ListSteelGradeMapper extends EntityMapper<ListSteelGradeDTO, ListSteelGrade> {


    @Mapping(target = "bendHists", ignore = true)
    @Mapping(target = "buckleArrestorHists", ignore = true)
    @Mapping(target = "pipeHists", ignore = true)
    @Mapping(target = "teeHists", ignore = true)
    @Mapping(target = "valveHists", ignore = true)
    ListSteelGrade toEntity(ListSteelGradeDTO listSteelGradeDTO);

    default ListSteelGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListSteelGrade listSteelGrade = new ListSteelGrade();
        listSteelGrade.setId(id);
        return listSteelGrade;
    }
}
