package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BuckleArrestorHistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BuckleArrestorHist} and its DTO {@link BuckleArrestorHistDTO}.
 */
@Mapper(componentModel = "spring", uses = {BuckleArrestorMapper.class, PipelineSectionMapper.class, ListBucklarrTypeMapper.class, ListInternalCoatingMapper.class, ListExternalCoatingMapper.class, ListNominalWallThicknessMapper.class, PipejointMapper.class, ListBucklarrManufacturerMapper.class, ListBucklarrSpecificationMapper.class, ListLongSeamWeldTypeMapper.class, ListFabricationTypeMapper.class, ListMaterialMapper.class, ListMillLocationMapper.class, ListSteelGradeMapper.class})
public interface BuckleArrestorHistMapper extends EntityMapper<BuckleArrestorHistDTO, BuckleArrestorHist> {

    @Mapping(source = "buckleArrestor.id", target = "buckleArrestorId")
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
    BuckleArrestorHistDTO toDto(BuckleArrestorHist buckleArrestorHist);

    @Mapping(source = "buckleArrestorId", target = "buckleArrestor")
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
    BuckleArrestorHist toEntity(BuckleArrestorHistDTO buckleArrestorHistDTO);

    default BuckleArrestorHist fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuckleArrestorHist buckleArrestorHist = new BuckleArrestorHist();
        buckleArrestorHist.setId(id);
        return buckleArrestorHist;
    }
}
