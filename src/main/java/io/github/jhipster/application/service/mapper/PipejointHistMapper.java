package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipejointHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PipejointHist} and its DTO {@link PipejointHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {PipejointMapper.class, ListPipejointTypeMapper.class, ListExternalCoatingMapper.class, ListMaterialMapper.class, ListPipejointSpecificationMapper.class, ListObjectStatusMapper.class})
public interface PipejointHistMapper extends EntityMapper<PipejointHistDTO, PipejointHist> {

    @Mapping(source = "pipejoint.id", target = "pipejointId")
    @Mapping(source = "idType.id", target = "idTypeId")
    @Mapping(source = "idExternalCoatType.id", target = "idExternalCoatTypeId")
    @Mapping(source = "idMaterial.id", target = "idMaterialId")
    @Mapping(source = "idSpecification.id", target = "idSpecificationId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    PipejointHistDTO toDto(PipejointHist pipejointHist);

    @Mapping(source = "pipejointId", target = "pipejoint")
    @Mapping(source = "idTypeId", target = "idType")
    @Mapping(source = "idExternalCoatTypeId", target = "idExternalCoatType")
    @Mapping(source = "idMaterialId", target = "idMaterial")
    @Mapping(source = "idSpecificationId", target = "idSpecification")
    @Mapping(source = "idStatusId", target = "idStatus")
    PipejointHist toEntity(PipejointHistDTO pipejointHistDTO);

    default PipejointHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        PipejointHist pipejointHist = new PipejointHist();
        pipejointHist.setId(id);
        return pipejointHist;
    }
}
