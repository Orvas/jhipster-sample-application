package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AnodeHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnodeHist} and its DTO {@link AnodeHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnodeMapper.class, PipelineSectionMapper.class, ListAnodeBraceleteTypeMapper.class, ListMaterialMapper.class, ListWrkStatusMapper.class})
public interface AnodeHistMapper extends EntityMapper<AnodeHistDTO, AnodeHist> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idBraceleteType.id", target = "idBraceleteTypeId")
    @Mapping(source = "idMaterial.id", target = "idMaterialId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    AnodeHistDTO toDto(AnodeHist anodeHist);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idBraceleteTypeId", target = "idBraceleteType")
    @Mapping(source = "idMaterialId", target = "idMaterial")
    @Mapping(source = "idStatusId", target = "idStatus")
    AnodeHist toEntity(AnodeHistDTO anodeHistDTO);

    default AnodeHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnodeHist anodeHist = new AnodeHist();
        anodeHist.setId(id);
        return anodeHist;
    }
}
