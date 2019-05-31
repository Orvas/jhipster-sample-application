package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BendHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BendHist} and its DTO {@link BendHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {BendMapper.class, PipelineSectionMapper.class, ListBendTypeMapper.class, ListInternalCoatingMapper.class, ListExternalCoatingMapper.class, ListNominalWallThicknessMapper.class, PipejointMapper.class, ListBendManufacturerMapper.class, ListBendSpecificationMapper.class, ListLongSeamWeldTypeMapper.class, ListFabricationTypeMapper.class, ListMaterialMapper.class, ListMillLocationMapper.class, ListSteelGradeMapper.class, ListObjectStatusMapper.class})
public interface BendHistMapper extends EntityMapper<BendHistDTO, BendHist> {

    @Mapping(source = "bend.id", target = "bendId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idType.id", target = "idTypeId")
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
    BendHistDTO toDto(BendHist bendHist);

    @Mapping(source = "bendId", target = "bend")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idTypeId", target = "idType")
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
    BendHist toEntity(BendHistDTO bendHistDTO);

    default BendHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        BendHist bendHist = new BendHist();
        bendHist.setId(id);
        return bendHist;
    }
}
