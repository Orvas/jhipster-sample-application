package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FreeSpanSupportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreeSpanSupport} and its DTO {@link FreeSpanSupportDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface FreeSpanSupportMapper extends EntityMapper<FreeSpanSupportDTO, FreeSpanSupport> {

    @Mapping(source = "id.id", target = "idId")
    FreeSpanSupportDTO toDto(FreeSpanSupport freeSpanSupport);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "freeSpanSupportHists", ignore = true)
    FreeSpanSupport toEntity(FreeSpanSupportDTO freeSpanSupportDTO);

    default FreeSpanSupport fromId(Long id) {
        if (id == null) {
            return null;
        }
        FreeSpanSupport freeSpanSupport = new FreeSpanSupport();
        freeSpanSupport.setId(id);
        return freeSpanSupport;
    }
}
