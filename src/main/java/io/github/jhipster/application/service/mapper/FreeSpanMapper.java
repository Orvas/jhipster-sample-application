package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FreeSpanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreeSpan} and its DTO {@link FreeSpanDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface FreeSpanMapper extends EntityMapper<FreeSpanDTO, FreeSpan> {

    @Mapping(source = "id.id", target = "idId")
    FreeSpanDTO toDto(FreeSpan freeSpan);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "freeSpanHists", ignore = true)
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
