package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ValveHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ValveHist} and its DTO {@link ValveHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {ValveMapper.class, PipelineSectionMapper.class, ListValveTypeMapper.class, ListInternalCoatingMapper.class, ListExternalCoatingMapper.class, ListNominalWallThicknessMapper.class, PipejointMapper.class, ListValveManufacturerMapper.class, ListValveSpecificationMapper.class, ListValveFunctionMapper.class, ListLongSeamWeldTypeMapper.class, ListFabricationTypeMapper.class, ListMaterialMapper.class, ListMillLocationMapper.class, ListSteelGradeMapper.class, ListObjectStatusMapper.class})
public interface ValveHistMapper extends EntityMapper<ValveHistDTO, ValveHist> {

    @Mapping(source = "valve.id", target = "valveId")
    @Mapping(source = "idPipelineSection.id", target = "idPipelineSectionId")
    @Mapping(source = "idType.id", target = "idTypeId")
    @Mapping(source = "idInternalCoatType.id", target = "idInternalCoatTypeId")
    @Mapping(source = "idExternalCoatType.id", target = "idExternalCoatTypeId")
    @Mapping(source = "idNominalWallThickness.id", target = "idNominalWallThicknessId")
    @Mapping(source = "idPipeJoint.id", target = "idPipeJointId")
    @Mapping(source = "idManufacturer.id", target = "idManufacturerId")
    @Mapping(source = "idSpecification.id", target = "idSpecificationId")
    @Mapping(source = "idFunction.id", target = "idFunctionId")
    @Mapping(source = "idLongSeamWeldType.id", target = "idLongSeamWeldTypeId")
    @Mapping(source = "idFabricationType.id", target = "idFabricationTypeId")
    @Mapping(source = "idMaterial.id", target = "idMaterialId")
    @Mapping(source = "idMillLocation.id", target = "idMillLocationId")
    @Mapping(source = "idSteelGrade.id", target = "idSteelGradeId")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    ValveHistDTO toDto(ValveHist valveHist);

    @Mapping(source = "valveId", target = "valve")
    @Mapping(source = "idPipelineSectionId", target = "idPipelineSection")
    @Mapping(source = "idTypeId", target = "idType")
    @Mapping(source = "idInternalCoatTypeId", target = "idInternalCoatType")
    @Mapping(source = "idExternalCoatTypeId", target = "idExternalCoatType")
    @Mapping(source = "idNominalWallThicknessId", target = "idNominalWallThickness")
    @Mapping(source = "idPipeJointId", target = "idPipeJoint")
    @Mapping(source = "idManufacturerId", target = "idManufacturer")
    @Mapping(source = "idSpecificationId", target = "idSpecification")
    @Mapping(source = "idFunctionId", target = "idFunction")
    @Mapping(source = "idLongSeamWeldTypeId", target = "idLongSeamWeldType")
    @Mapping(source = "idFabricationTypeId", target = "idFabricationType")
    @Mapping(source = "idMaterialId", target = "idMaterial")
    @Mapping(source = "idMillLocationId", target = "idMillLocation")
    @Mapping(source = "idSteelGradeId", target = "idSteelGrade")
    @Mapping(source = "idStatusId", target = "idStatus")
    ValveHist toEntity(ValveHistDTO valveHistDTO);

    default ValveHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        ValveHist valveHist = new ValveHist();
        valveHist.setId(id);
        return valveHist;
    }
}
