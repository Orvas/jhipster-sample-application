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

import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BendHistRepository;
import io.github.jhipster.application.service.dto.BendHistCriteria;
import io.github.jhipster.application.service.dto.BendHistDTO;
import io.github.jhipster.application.service.mapper.BendHistMapper;

/**
 * Service for executing complex queries for {@link BendHist} entities in the database.
 * The main input is a {@link BendHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BendHistDTO} or a {@link Page} of {@link BendHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BendHistQueryService extends QueryService<BendHist> {

    private final Logger log = LoggerFactory.getLogger(BendHistQueryService.class);

    private final BendHistRepository bendHistRepository;

    private final BendHistMapper bendHistMapper;

    public BendHistQueryService(BendHistRepository bendHistRepository, BendHistMapper bendHistMapper) {
        this.bendHistRepository = bendHistRepository;
        this.bendHistMapper = bendHistMapper;
    }

    /**
     * Return a {@link List} of {@link BendHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BendHistDTO> findByCriteria(BendHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BendHist> specification = createSpecification(criteria);
        return bendHistMapper.toDto(bendHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BendHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BendHistDTO> findByCriteria(BendHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BendHist> specification = createSpecification(criteria);
        return bendHistRepository.findAll(specification, page)
            .map(bendHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BendHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BendHist> specification = createSpecification(criteria);
        return bendHistRepository.count(specification);
    }

    /**
     * Function to convert BendHistCriteria to a {@link Specification}.
     */
    private Specification<BendHist> createSpecification(BendHistCriteria criteria) {
        Specification<BendHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BendHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), BendHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), BendHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), BendHist_.idWrk));
            }
            if (criteria.getNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNum(), BendHist_.num));
            }
            if (criteria.getRadius() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRadius(), BendHist_.radius));
            }
            if (criteria.getDiameterOuterSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteel(), BendHist_.diameterOuterSteel));
            }
            if (criteria.getInternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInternalCoatThickness(), BendHist_.internalCoatThickness));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), BendHist_.externalCoatThickness));
            }
            if (criteria.getIsConcrCoating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsConcrCoating(), BendHist_.isConcrCoating));
            }
            if (criteria.getConcrCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatThickness(), BendHist_.concrCoatThickness));
            }
            if (criteria.getConcrCoatDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatDensity(), BendHist_.concrCoatDensity));
            }
            if (criteria.getMeasWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasWallThickness(), BendHist_.measWallThickness));
            }
            if (criteria.getLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLength(), BendHist_.length));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), BendHist_.weight));
            }
            if (criteria.getSmts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmts(), BendHist_.smts));
            }
            if (criteria.getSmys() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmys(), BendHist_.smys));
            }
            if (criteria.getSdbm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdbm(), BendHist_.sdbm));
            }
            if (criteria.getSdaf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdaf(), BendHist_.sdaf));
            }
            if (criteria.getSdcs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdcs(), BendHist_.sdcs));
            }
            if (criteria.getAllowTensStrain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowTensStrain(), BendHist_.allowTensStrain));
            }
            if (criteria.getCorrosionAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrosionAllowance(), BendHist_.corrosionAllowance));
            }
            if (criteria.getFabricationAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFabricationAllowance(), BendHist_.fabricationAllowance));
            }
            if (criteria.getIsBurial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsBurial(), BendHist_.isBurial));
            }
            if (criteria.getBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBurialDepth(), BendHist_.burialDepth));
            }
            if (criteria.getFactBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFactBurialDepth(), BendHist_.factBurialDepth));
            }
            if (criteria.getDateManufactured() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateManufactured(), BendHist_.dateManufactured));
            }
            if (criteria.getMillTestPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMillTestPressure(), BendHist_.millTestPressure));
            }
            if (criteria.getDesignPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressure(), BendHist_.designPressure));
            }
            if (criteria.getIncidentalPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncidentalPressure(), BendHist_.incidentalPressure));
            }
            if (criteria.getDateInstalled() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateInstalled(), BendHist_.dateInstalled));
            }
            if (criteria.getSeamOrient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamOrient(), BendHist_.seamOrient));
            }
            if (criteria.getSeamAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamAngle(), BendHist_.seamAngle));
            }
            if (criteria.getAzimuth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzimuth(), BendHist_.azimuth));
            }
            if (criteria.getSeabInstallTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeabInstallTemp(), BendHist_.seabInstallTemp));
            }
            if (criteria.getVerticalAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerticalAngle(), BendHist_.verticalAngle));
            }
            if (criteria.getHeatTreatDurat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeatTreatDurat(), BendHist_.heatTreatDurat));
            }
            if (criteria.getMaxDesignTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDesignTemp(), BendHist_.maxDesignTemp));
            }
            if (criteria.getHeatNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeatNumber(), BendHist_.heatNumber));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), BendHist_.coord));
            }
            if (criteria.getDesignCoord() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignCoord(), BendHist_.designCoord));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), BendHist_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), BendHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), BendHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), BendHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), BendHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), BendHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), BendHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), BendHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), BendHist_.editor));
            }
            if (criteria.getBendId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendId(),
                    root -> root.join(BendHist_.bend, JoinType.LEFT).get(Bend_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(BendHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(BendHist_.idType, JoinType.LEFT).get(ListBendType_.id)));
            }
            if (criteria.getIdInternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdInternalCoatTypeId(),
                    root -> root.join(BendHist_.idInternalCoatType, JoinType.LEFT).get(ListInternalCoating_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(BendHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdNominalWallThicknessId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdNominalWallThicknessId(),
                    root -> root.join(BendHist_.idNominalWallThickness, JoinType.LEFT).get(ListNominalWallThickness_.id)));
            }
            if (criteria.getIdPipeJointId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipeJointId(),
                    root -> root.join(BendHist_.idPipeJoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdManufacturerId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdManufacturerId(),
                    root -> root.join(BendHist_.idManufacturer, JoinType.LEFT).get(ListBendManufacturer_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(BendHist_.idSpecification, JoinType.LEFT).get(ListBendSpecification_.id)));
            }
            if (criteria.getIdLongSeamWeldTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLongSeamWeldTypeId(),
                    root -> root.join(BendHist_.idLongSeamWeldType, JoinType.LEFT).get(ListLongSeamWeldType_.id)));
            }
            if (criteria.getIdFabricationTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFabricationTypeId(),
                    root -> root.join(BendHist_.idFabricationType, JoinType.LEFT).get(ListFabricationType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(BendHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdMillLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMillLocationId(),
                    root -> root.join(BendHist_.idMillLocation, JoinType.LEFT).get(ListMillLocation_.id)));
            }
            if (criteria.getIdSteelGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSteelGradeId(),
                    root -> root.join(BendHist_.idSteelGrade, JoinType.LEFT).get(ListSteelGrade_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(BendHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
