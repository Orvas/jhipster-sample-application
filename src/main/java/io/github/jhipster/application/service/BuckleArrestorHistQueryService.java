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

import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BuckleArrestorHistRepository;
import io.github.jhipster.application.service.dto.BuckleArrestorHistCriteria;
import io.github.jhipster.application.service.dto.BuckleArrestorHistDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorHistMapper;

/**
 * Service for executing complex queries for {@link BuckleArrestorHist} entities in the database.
 * The main input is a {@link BuckleArrestorHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BuckleArrestorHistDTO} or a {@link Page} of {@link BuckleArrestorHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BuckleArrestorHistQueryService extends QueryService<BuckleArrestorHist> {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorHistQueryService.class);

    private final BuckleArrestorHistRepository buckleArrestorHistRepository;

    private final BuckleArrestorHistMapper buckleArrestorHistMapper;

    public BuckleArrestorHistQueryService(BuckleArrestorHistRepository buckleArrestorHistRepository, BuckleArrestorHistMapper buckleArrestorHistMapper) {
        this.buckleArrestorHistRepository = buckleArrestorHistRepository;
        this.buckleArrestorHistMapper = buckleArrestorHistMapper;
    }

    /**
     * Return a {@link List} of {@link BuckleArrestorHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BuckleArrestorHistDTO> findByCriteria(BuckleArrestorHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BuckleArrestorHist> specification = createSpecification(criteria);
        return buckleArrestorHistMapper.toDto(buckleArrestorHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BuckleArrestorHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BuckleArrestorHistDTO> findByCriteria(BuckleArrestorHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BuckleArrestorHist> specification = createSpecification(criteria);
        return buckleArrestorHistRepository.findAll(specification, page)
            .map(buckleArrestorHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BuckleArrestorHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BuckleArrestorHist> specification = createSpecification(criteria);
        return buckleArrestorHistRepository.count(specification);
    }

    /**
     * Function to convert BuckleArrestorHistCriteria to a {@link Specification}.
     */
    private Specification<BuckleArrestorHist> createSpecification(BuckleArrestorHistCriteria criteria) {
        Specification<BuckleArrestorHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BuckleArrestorHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), BuckleArrestorHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), BuckleArrestorHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), BuckleArrestorHist_.idWrk));
            }
            if (criteria.getSerialNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNum(), BuckleArrestorHist_.serialNum));
            }
            if (criteria.getDiameterOuterSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteel(), BuckleArrestorHist_.diameterOuterSteel));
            }
            if (criteria.getInternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInternalCoatThickness(), BuckleArrestorHist_.internalCoatThickness));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), BuckleArrestorHist_.externalCoatThickness));
            }
            if (criteria.getIsConcrCoating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsConcrCoating(), BuckleArrestorHist_.isConcrCoating));
            }
            if (criteria.getConcrCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatThickness(), BuckleArrestorHist_.concrCoatThickness));
            }
            if (criteria.getConcrCoatDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatDensity(), BuckleArrestorHist_.concrCoatDensity));
            }
            if (criteria.getMeasColWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasColWallThickness(), BuckleArrestorHist_.measColWallThickness));
            }
            if (criteria.getMeasPipeWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasPipeWallThickness(), BuckleArrestorHist_.measPipeWallThickness));
            }
            if (criteria.getColLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColLength(), BuckleArrestorHist_.colLength));
            }
            if (criteria.getPipeLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPipeLength(), BuckleArrestorHist_.pipeLength));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), BuckleArrestorHist_.weight));
            }
            if (criteria.getSmts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmts(), BuckleArrestorHist_.smts));
            }
            if (criteria.getSmys() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmys(), BuckleArrestorHist_.smys));
            }
            if (criteria.getSdbm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdbm(), BuckleArrestorHist_.sdbm));
            }
            if (criteria.getSdaf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdaf(), BuckleArrestorHist_.sdaf));
            }
            if (criteria.getSdcs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdcs(), BuckleArrestorHist_.sdcs));
            }
            if (criteria.getAllowTensStrain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowTensStrain(), BuckleArrestorHist_.allowTensStrain));
            }
            if (criteria.getCorrosionAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrosionAllowance(), BuckleArrestorHist_.corrosionAllowance));
            }
            if (criteria.getFabricationAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFabricationAllowance(), BuckleArrestorHist_.fabricationAllowance));
            }
            if (criteria.getIsBurial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsBurial(), BuckleArrestorHist_.isBurial));
            }
            if (criteria.getBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBurialDepth(), BuckleArrestorHist_.burialDepth));
            }
            if (criteria.getFactBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFactBurialDepth(), BuckleArrestorHist_.factBurialDepth));
            }
            if (criteria.getDateManufactured() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateManufactured(), BuckleArrestorHist_.dateManufactured));
            }
            if (criteria.getMillTestPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMillTestPressure(), BuckleArrestorHist_.millTestPressure));
            }
            if (criteria.getDesignPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressure(), BuckleArrestorHist_.designPressure));
            }
            if (criteria.getIncidentalPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncidentalPressure(), BuckleArrestorHist_.incidentalPressure));
            }
            if (criteria.getDateInstalled() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateInstalled(), BuckleArrestorHist_.dateInstalled));
            }
            if (criteria.getSeamOrient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamOrient(), BuckleArrestorHist_.seamOrient));
            }
            if (criteria.getSeamAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamAngle(), BuckleArrestorHist_.seamAngle));
            }
            if (criteria.getAzimuth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzimuth(), BuckleArrestorHist_.azimuth));
            }
            if (criteria.getSeabInstallTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeabInstallTemp(), BuckleArrestorHist_.seabInstallTemp));
            }
            if (criteria.getVerticalAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerticalAngle(), BuckleArrestorHist_.verticalAngle));
            }
            if (criteria.getHeatTreatDurat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeatTreatDurat(), BuckleArrestorHist_.heatTreatDurat));
            }
            if (criteria.getMaxDesignTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDesignTemp(), BuckleArrestorHist_.maxDesignTemp));
            }
            if (criteria.getHeatNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeatNumber(), BuckleArrestorHist_.heatNumber));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), BuckleArrestorHist_.coord));
            }
            if (criteria.getDesignCoord() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignCoord(), BuckleArrestorHist_.designCoord));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), BuckleArrestorHist_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), BuckleArrestorHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), BuckleArrestorHist_.isCurrentFlag));
            }
            if (criteria.getIdStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdStatus(), BuckleArrestorHist_.idStatus));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), BuckleArrestorHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), BuckleArrestorHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), BuckleArrestorHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), BuckleArrestorHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), BuckleArrestorHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), BuckleArrestorHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(BuckleArrestorHist_.id, JoinType.LEFT).get(BuckleArrestor_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(BuckleArrestorHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(BuckleArrestorHist_.idType, JoinType.LEFT).get(ListBucklarrType_.id)));
            }
            if (criteria.getIdInternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdInternalCoatTypeId(),
                    root -> root.join(BuckleArrestorHist_.idInternalCoatType, JoinType.LEFT).get(ListInternalCoating_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(BuckleArrestorHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdNominalWallThicknessId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdNominalWallThicknessId(),
                    root -> root.join(BuckleArrestorHist_.idNominalWallThickness, JoinType.LEFT).get(ListNominalWallThickness_.id)));
            }
            if (criteria.getIdPipeJointId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipeJointId(),
                    root -> root.join(BuckleArrestorHist_.idPipeJoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdManufacturerId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdManufacturerId(),
                    root -> root.join(BuckleArrestorHist_.idManufacturer, JoinType.LEFT).get(ListBucklarrManufacturer_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(BuckleArrestorHist_.idSpecification, JoinType.LEFT).get(ListBucklarrSpecification_.id)));
            }
            if (criteria.getIdLongSeamWeldTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLongSeamWeldTypeId(),
                    root -> root.join(BuckleArrestorHist_.idLongSeamWeldType, JoinType.LEFT).get(ListLongSeamWeldType_.id)));
            }
            if (criteria.getIdFabricationTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFabricationTypeId(),
                    root -> root.join(BuckleArrestorHist_.idFabricationType, JoinType.LEFT).get(ListFabricationType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(BuckleArrestorHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdMillLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMillLocationId(),
                    root -> root.join(BuckleArrestorHist_.idMillLocation, JoinType.LEFT).get(ListMillLocation_.id)));
            }
            if (criteria.getIdSteelGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSteelGradeId(),
                    root -> root.join(BuckleArrestorHist_.idSteelGrade, JoinType.LEFT).get(ListSteelGrade_.id)));
            }
        }
        return specification;
    }
}
