package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PipeHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PipeHist} and its DTO {@link PipeHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {PipeMapper.class, PipelineSectionMapper.class, ListInternalCoatingMapper.class, ListExternalCoatingMapper.class, ListNominalWallThicknessMapper.class, PipejointMapper.class, ListPipeManufacturerMapper.class, ListPipeSpecificationMapper.class, ListLongSeamWeldTypeMapper.class, ListFabricationTypeMapper.class, ListMaterialMapper.class, ListMillLocationMapper.class, ListSteelGradeMapper.class, ListObjectStatusMapper.class})
public interface PipeHistMapper extends EntityMapper<PipeHistDTO, PipeHist> {

    @Mapping(source = "pipe.id", target = "pipeId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idInternalCoatType.id", target = "idInternalCoatTypeId")
    @Mapping(source = "idExternalCoatType.id", target = "idExternalCoatTypeId")
    @Mapping(source = "idNominalWallThickness.id", target = "idNominalWallThicknessId")
    @Mapping(source = "idPipeJoint.id", target = "idPipeJointId")
    @Mapping(source = "idManufacturer.id", target = "idManufacturerId")
    @Mapping(source = "idSpecification.id", target = "idSpecificationId")
    @Mapping(source = "idLongSeamWeldType.id", target = "idLongSeamWeldTypeId")
    @Mapping(source = "idFabricationType.id", target = "idFabricationTypeId")
    @Mapping(source = "idMaterial.id", target = "idMaterialId")
    @Mapping(source = "idMillLocation.id", target = "idMillLocationId")
    @Mapping(source = "idSteelGrade.id", target = "idSteelGradeId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    PipeHistDTO toDto(PipeHist pipeHist);

    @Mapping(source = "pipeId", target = "pipe")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idInternalCoatTypeId", target = "idInternalCoatType")
    @Mapping(source = "idExternalCoatTypeId", target = "idExternalCoatType")
    @Mapping(source = "idNominalWallThicknessId", target = "idNominalWallThickness")
    @Mapping(source = "idPipeJointId", target = "idPipeJoint")
    @Mapping(source = "idManufacturerId", target = "idManufacturer")
    @Mapping(source = "idSpecificationId", target = "idSpecification")
    @Mapping(source = "idLongSeamWeldTypeId", target = "idLongSeamWeldType")
    @Mapping(source = "idFabricationTypeId", target = "idFabricationType")
    @Mapping(source = "idMaterialId", target = "idMaterial")
    @Mapping(source = "idMillLocationId", target = "idMillLocation")
    @Mapping(source = "idSteelGradeId", target = "idSteelGrade")
    @Mapping(source = "idStatusId", target = "idStatus")
    PipeHist toEntity(PipeHistDTO pipeHistDTO);

    default PipeHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        PipeHist pipeHist = new PipeHist();
        pipeHist.setId(id);
        return pipeHist;
    }
}
