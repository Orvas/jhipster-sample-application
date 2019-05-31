package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ValveHistRepository;
import io.github.jhipster.application.service.dto.ValveHistCriteria;
import io.github.jhipster.application.service.dto.ValveHistDTO;
import io.github.jhipster.application.service.mapper.ValveHistMapper;

/**
 * Service for executing complex queries for {@link ValveHist} entities in the database.
 * The main input is a {@link ValveHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ValveHistDTO} or a {@link Page} of {@link ValveHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ValveHistQueryService extends QueryService<ValveHist> {

    private final Logger log = LoggerFactory.getLogger(ValveHistQueryService.class);

    private final ValveHistRepository valveHistRepository;

    private final ValveHistMapper valveHistMapper;

    public ValveHistQueryService(ValveHistRepository valveHistRepository, ValveHistMapper valveHistMapper) {
        this.valveHistRepository = valveHistRepository;
        this.valveHistMapper = valveHistMapper;
    }

    /**
     * Return a {@link List} of {@link ValveHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ValveHistDTO> findByCriteria(ValveHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ValveHist> specification = createSpecification(criteria);
        return valveHistMapper.toDto(valveHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ValveHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ValveHistDTO> findByCriteria(ValveHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ValveHist> specification = createSpecification(criteria);
        return valveHistRepository.findAll(specification, page)
            .map(valveHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ValveHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ValveHist> specification = createSpecification(criteria);
        return valveHistRepository.count(specification);
    }

    /**
     * Function to convert ValveHistCriteria to a {@link Specification}.
     */
    private Specification<ValveHist> createSpecification(ValveHistCriteria criteria) {
        Specification<ValveHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ValveHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), ValveHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), ValveHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), ValveHist_.idWrk));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ValveHist_.name));
            }
            if (criteria.getSerialNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNum(), ValveHist_.serialNum));
            }
            if (criteria.getModel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModel(), ValveHist_.model));
            }
            if (criteria.getDiameterOuterSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteel(), ValveHist_.diameterOuterSteel));
            }
            if (criteria.getInternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInternalCoatThickness(), ValveHist_.internalCoatThickness));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), ValveHist_.externalCoatThickness));
            }
            if (criteria.getIsConcrCoating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsConcrCoating(), ValveHist_.isConcrCoating));
            }
            if (criteria.getConcrCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatThickness(), ValveHist_.concrCoatThickness));
            }
            if (criteria.getConcrCoatDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatDensity(), ValveHist_.concrCoatDensity));
            }
            if (criteria.getMeasWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasWallThickness(), ValveHist_.measWallThickness));
            }
            if (criteria.getLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLength(), ValveHist_.length));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), ValveHist_.weight));
            }
            if (criteria.getSmts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmts(), ValveHist_.smts));
            }
            if (criteria.getSmys() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmys(), ValveHist_.smys));
            }
            if (criteria.getSdbm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdbm(), ValveHist_.sdbm));
            }
            if (criteria.getSdaf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdaf(), ValveHist_.sdaf));
            }
            if (criteria.getSdcs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdcs(), ValveHist_.sdcs));
            }
            if (criteria.getAllowTensStrain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowTensStrain(), ValveHist_.allowTensStrain));
            }
            if (criteria.getCorrosionAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrosionAllowance(), ValveHist_.corrosionAllowance));
            }
            if (criteria.getFabricationAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFabricationAllowance(), ValveHist_.fabricationAllowance));
            }
            if (criteria.getDateManufactured() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateManufactured(), ValveHist_.dateManufactured));
            }
            if (criteria.getMillTestPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMillTestPressure(), ValveHist_.millTestPressure));
            }
            if (criteria.getDesignPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressure(), ValveHist_.designPressure));
            }
            if (criteria.getDesignPressureIn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressureIn(), ValveHist_.designPressureIn));
            }
            if (criteria.getDesignPressureOut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressureOut(), ValveHist_.designPressureOut));
            }
            if (criteria.getIncidentalPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncidentalPressure(), ValveHist_.incidentalPressure));
            }
            if (criteria.getDateInstalled() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateInstalled(), ValveHist_.dateInstalled));
            }
            if (criteria.getSeamOrient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamOrient(), ValveHist_.seamOrient));
            }
            if (criteria.getSeamAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamAngle(), ValveHist_.seamAngle));
            }
            if (criteria.getAzimuth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzimuth(), ValveHist_.azimuth));
            }
            if (criteria.getSeabInstallTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeabInstallTemp(), ValveHist_.seabInstallTemp));
            }
            if (criteria.getVerticalAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerticalAngle(), ValveHist_.verticalAngle));
            }
            if (criteria.getHeatTreatDurat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeatTreatDurat(), ValveHist_.heatTreatDurat));
            }
            if (criteria.getMaxDesignTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDesignTemp(), ValveHist_.maxDesignTemp));
            }
            if (criteria.getHeatNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeatNumber(), ValveHist_.heatNumber));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), ValveHist_.coord));
            }
            if (criteria.getDesignCoord() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignCoord(), ValveHist_.designCoord));
            }
            if (criteria.getKpInst() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpInst(), ValveHist_.kpInst));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ValveHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ValveHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), ValveHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ValveHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ValveHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ValveHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ValveHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(ValveHist_.id, JoinType.LEFT).get(Valve_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(ValveHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(ValveHist_.idType, JoinType.LEFT).get(ListValveType_.id)));
            }
            if (criteria.getIdInternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdInternalCoatTypeId(),
                    root -> root.join(ValveHist_.idInternalCoatType, JoinType.LEFT).get(ListInternalCoating_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(ValveHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdNominalWallThicknessId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdNominalWallThicknessId(),
                    root -> root.join(ValveHist_.idNominalWallThickness, JoinType.LEFT).get(ListNominalWallThickness_.id)));
            }
            if (criteria.getIdPipeJointId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipeJointId(),
                    root -> root.join(ValveHist_.idPipeJoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdManufacturerId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdManufacturerId(),
                    root -> root.join(ValveHist_.idManufacturer, JoinType.LEFT).get(ListValveManufacturer_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(ValveHist_.idSpecification, JoinType.LEFT).get(ListValveSpecification_.id)));
            }
            if (criteria.getIdFunctionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFunctionId(),
                    root -> root.join(ValveHist_.idFunction, JoinType.LEFT).get(ListValveFunction_.id)));
            }
            if (criteria.getIdLongSeamWeldTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLongSeamWeldTypeId(),
                    root -> root.join(ValveHist_.idLongSeamWeldType, JoinType.LEFT).get(ListLongSeamWeldType_.id)));
            }
            if (criteria.getIdFabricationTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFabricationTypeId(),
                    root -> root.join(ValveHist_.idFabricationType, JoinType.LEFT).get(ListFabricationType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(ValveHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdMillLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMillLocationId(),
                    root -> root.join(ValveHist_.idMillLocation, JoinType.LEFT).get(ListMillLocation_.id)));
            }
            if (criteria.getIdSteelGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSteelGradeId(),
                    root -> root.join(ValveHist_.idSteelGrade, JoinType.LEFT).get(ListSteelGrade_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(ValveHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
