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

import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.TeeHistRepository;
import io.github.jhipster.application.service.dto.TeeHistCriteria;
import io.github.jhipster.application.service.dto.TeeHistDTO;
import io.github.jhipster.application.service.mapper.TeeHistMapper;

/**
 * Service for executing complex queries for {@link TeeHist} entities in the database.
 * The main input is a {@link TeeHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeeHistDTO} or a {@link Page} of {@link TeeHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeeHistQueryService extends QueryService<TeeHist> {

    private final Logger log = LoggerFactory.getLogger(TeeHistQueryService.class);

    private final TeeHistRepository teeHistRepository;

    private final TeeHistMapper teeHistMapper;

    public TeeHistQueryService(TeeHistRepository teeHistRepository, TeeHistMapper teeHistMapper) {
        this.teeHistRepository = teeHistRepository;
        this.teeHistMapper = teeHistMapper;
    }

    /**
     * Return a {@link List} of {@link TeeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeeHistDTO> findByCriteria(TeeHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeeHist> specification = createSpecification(criteria);
        return teeHistMapper.toDto(teeHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeeHistDTO> findByCriteria(TeeHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeeHist> specification = createSpecification(criteria);
        return teeHistRepository.findAll(specification, page)
            .map(teeHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeeHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeeHist> specification = createSpecification(criteria);
        return teeHistRepository.count(specification);
    }

    /**
     * Function to convert TeeHistCriteria to a {@link Specification}.
     */
    private Specification<TeeHist> createSpecification(TeeHistCriteria criteria) {
        Specification<TeeHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeeHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), TeeHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), TeeHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), TeeHist_.idWrk));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), TeeHist_.name));
            }
            if (criteria.getDiameterOuterSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteel(), TeeHist_.diameterOuterSteel));
            }
            if (criteria.getDiameterOuterSteelBr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteelBr(), TeeHist_.diameterOuterSteelBr));
            }
            if (criteria.getInternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInternalCoatThickness(), TeeHist_.internalCoatThickness));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), TeeHist_.externalCoatThickness));
            }
            if (criteria.getIsConcrCoating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsConcrCoating(), TeeHist_.isConcrCoating));
            }
            if (criteria.getConcrCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatThickness(), TeeHist_.concrCoatThickness));
            }
            if (criteria.getConcrCoatDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatDensity(), TeeHist_.concrCoatDensity));
            }
            if (criteria.getMeasWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasWallThickness(), TeeHist_.measWallThickness));
            }
            if (criteria.getLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLength(), TeeHist_.length));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), TeeHist_.weight));
            }
            if (criteria.getSmts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmts(), TeeHist_.smts));
            }
            if (criteria.getSmys() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmys(), TeeHist_.smys));
            }
            if (criteria.getSdbm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdbm(), TeeHist_.sdbm));
            }
            if (criteria.getSdaf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdaf(), TeeHist_.sdaf));
            }
            if (criteria.getSdcs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdcs(), TeeHist_.sdcs));
            }
            if (criteria.getAllowTensStrain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowTensStrain(), TeeHist_.allowTensStrain));
            }
            if (criteria.getCorrosionAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrosionAllowance(), TeeHist_.corrosionAllowance));
            }
            if (criteria.getFabricationAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFabricationAllowance(), TeeHist_.fabricationAllowance));
            }
            if (criteria.getIsBurial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsBurial(), TeeHist_.isBurial));
            }
            if (criteria.getBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBurialDepth(), TeeHist_.burialDepth));
            }
            if (criteria.getFactBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFactBurialDepth(), TeeHist_.factBurialDepth));
            }
            if (criteria.getDateManufactured() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateManufactured(), TeeHist_.dateManufactured));
            }
            if (criteria.getMillTestPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMillTestPressure(), TeeHist_.millTestPressure));
            }
            if (criteria.getDesignPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressure(), TeeHist_.designPressure));
            }
            if (criteria.getIncidentalPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncidentalPressure(), TeeHist_.incidentalPressure));
            }
            if (criteria.getDateInstalled() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateInstalled(), TeeHist_.dateInstalled));
            }
            if (criteria.getSeamOrient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamOrient(), TeeHist_.seamOrient));
            }
            if (criteria.getSeamAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamAngle(), TeeHist_.seamAngle));
            }
            if (criteria.getAzimuth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzimuth(), TeeHist_.azimuth));
            }
            if (criteria.getSeabInstallTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeabInstallTemp(), TeeHist_.seabInstallTemp));
            }
            if (criteria.getVerticalAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerticalAngle(), TeeHist_.verticalAngle));
            }
            if (criteria.getHeatTreatDurat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeatTreatDurat(), TeeHist_.heatTreatDurat));
            }
            if (criteria.getMaxDesignTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDesignTemp(), TeeHist_.maxDesignTemp));
            }
            if (criteria.getHeatNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeatNumber(), TeeHist_.heatNumber));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), TeeHist_.coord));
            }
            if (criteria.getDesignCoord() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignCoord(), TeeHist_.designCoord));
            }
            if (criteria.getKpInst() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpInst(), TeeHist_.kpInst));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), TeeHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeeHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), TeeHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), TeeHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), TeeHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), TeeHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), TeeHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(TeeHist_.id, JoinType.LEFT).get(Tee_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(TeeHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(TeeHist_.idType, JoinType.LEFT).get(ListTeeType_.id)));
            }
            if (criteria.getIdInternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdInternalCoatTypeId(),
                    root -> root.join(TeeHist_.idInternalCoatType, JoinType.LEFT).get(ListInternalCoating_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(TeeHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdNominalWallThicknessId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdNominalWallThicknessId(),
                    root -> root.join(TeeHist_.idNominalWallThickness, JoinType.LEFT).get(ListNominalWallThickness_.id)));
            }
            if (criteria.getIdPipeJointId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipeJointId(),
                    root -> root.join(TeeHist_.idPipeJoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdManufacturerId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdManufacturerId(),
                    root -> root.join(TeeHist_.idManufacturer, JoinType.LEFT).get(ListTeeManufacturer_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(TeeHist_.idSpecification, JoinType.LEFT).get(ListTeeSpecification_.id)));
            }
            if (criteria.getIdLongSeamWeldTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLongSeamWeldTypeId(),
                    root -> root.join(TeeHist_.idLongSeamWeldType, JoinType.LEFT).get(ListLongSeamWeldType_.id)));
            }
            if (criteria.getIdFabricationTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFabricationTypeId(),
                    root -> root.join(TeeHist_.idFabricationType, JoinType.LEFT).get(ListFabricationType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(TeeHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdMillLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMillLocationId(),
                    root -> root.join(TeeHist_.idMillLocation, JoinType.LEFT).get(ListMillLocation_.id)));
            }
            if (criteria.getIdSteelGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSteelGradeId(),
                    root -> root.join(TeeHist_.idSteelGrade, JoinType.LEFT).get(ListSteelGrade_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(TeeHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
