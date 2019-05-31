package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TeeHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TeeHist} and its DTO {@link TeeHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeeMapper.class, PipelineSectionMapper.class, ListTeeTypeMapper.class, ListInternalCoatingMapper.class, ListExternalCoatingMapper.class, ListNominalWallThicknessMapper.class, PipejointMapper.class, ListTeeManufacturerMapper.class, ListTeeSpecificationMapper.class, ListLongSeamWeldTypeMapper.class, ListFabricationTypeMapper.class, ListMaterialMapper.class, ListMillLocationMapper.class, ListSteelGradeMapper.class, ListObjectStatusMapper.class})
public interface TeeHistMapper extends EntityMapper<TeeHistDTO, TeeHist> {

    @Mapping(source = "id.id", target = "idId")
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
    TeeHistDTO toDto(TeeHist teeHist);

    @Mapping(source = "idId", target = "id")
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
    TeeHist toEntity(TeeHistDTO teeHistDTO);

    default TeeHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeeHist teeHist = new TeeHist();
        teeHist.setId(id);
        return teeHist;
    }
}
