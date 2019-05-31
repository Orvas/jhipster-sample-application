package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FreeSpanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreeSpan} and its DTO {@link FreeSpanDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface FreeSpanMapper extends EntityMapper<FreeSpanDTO, FreeSpan> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    FreeSpanDTO toDto(FreeSpan freeSpan);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "freeSpanHist", ignore = true)
    FreeSpan toEntity(FreeSpanDTO freeSpanDTO);

    default FreeSpan fromId(Long id) {
        if (id == null) {
            return null;
        }
        FreeSpan freeSpan = new FreeSpan();
        freeSpan.setId(id);
        return freeSpan;
    }
}
