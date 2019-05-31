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

import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipeHistRepository;
import io.github.jhipster.application.service.dto.PipeHistCriteria;
import io.github.jhipster.application.service.dto.PipeHistDTO;
import io.github.jhipster.application.service.mapper.PipeHistMapper;

/**
 * Service for executing complex queries for {@link PipeHist} entities in the database.
 * The main input is a {@link PipeHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipeHistDTO} or a {@link Page} of {@link PipeHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipeHistQueryService extends QueryService<PipeHist> {

    private final Logger log = LoggerFactory.getLogger(PipeHistQueryService.class);

    private final PipeHistRepository pipeHistRepository;

    private final PipeHistMapper pipeHistMapper;

    public PipeHistQueryService(PipeHistRepository pipeHistRepository, PipeHistMapper pipeHistMapper) {
        this.pipeHistRepository = pipeHistRepository;
        this.pipeHistMapper = pipeHistMapper;
    }

    /**
     * Return a {@link List} of {@link PipeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipeHistDTO> findByCriteria(PipeHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PipeHist> specification = createSpecification(criteria);
        return pipeHistMapper.toDto(pipeHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipeHistDTO> findByCriteria(PipeHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PipeHist> specification = createSpecification(criteria);
        return pipeHistRepository.findAll(specification, page)
            .map(pipeHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipeHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PipeHist> specification = createSpecification(criteria);
        return pipeHistRepository.count(specification);
    }

    /**
     * Function to convert PipeHistCriteria to a {@link Specification}.
     */
    private Specification<PipeHist> createSpecification(PipeHistCriteria criteria) {
        Specification<PipeHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PipeHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), PipeHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), PipeHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), PipeHist_.idWrk));
            }
            if (criteria.getNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNum(), PipeHist_.num));
            }
            if (criteria.getDiameterOuterSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiameterOuterSteel(), PipeHist_.diameterOuterSteel));
            }
            if (criteria.getInternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInternalCoatThickness(), PipeHist_.internalCoatThickness));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), PipeHist_.externalCoatThickness));
            }
            if (criteria.getIsConcrCoating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsConcrCoating(), PipeHist_.isConcrCoating));
            }
            if (criteria.getConcrCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatThickness(), PipeHist_.concrCoatThickness));
            }
            if (criteria.getConcrCoatDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConcrCoatDensity(), PipeHist_.concrCoatDensity));
            }
            if (criteria.getMeasWallThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasWallThickness(), PipeHist_.measWallThickness));
            }
            if (criteria.getLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLength(), PipeHist_.length));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), PipeHist_.weight));
            }
            if (criteria.getSmts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmts(), PipeHist_.smts));
            }
            if (criteria.getSmys() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSmys(), PipeHist_.smys));
            }
            if (criteria.getSdbm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdbm(), PipeHist_.sdbm));
            }
            if (criteria.getSdaf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdaf(), PipeHist_.sdaf));
            }
            if (criteria.getSdcs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSdcs(), PipeHist_.sdcs));
            }
            if (criteria.getAllowTensStrain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowTensStrain(), PipeHist_.allowTensStrain));
            }
            if (criteria.getCorrosionAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrosionAllowance(), PipeHist_.corrosionAllowance));
            }
            if (criteria.getFabricationAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFabricationAllowance(), PipeHist_.fabricationAllowance));
            }
            if (criteria.getIsBurial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsBurial(), PipeHist_.isBurial));
            }
            if (criteria.getBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBurialDepth(), PipeHist_.burialDepth));
            }
            if (criteria.getFactBurialDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFactBurialDepth(), PipeHist_.factBurialDepth));
            }
            if (criteria.getDateManufactured() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateManufactured(), PipeHist_.dateManufactured));
            }
            if (criteria.getMillTestPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMillTestPressure(), PipeHist_.millTestPressure));
            }
            if (criteria.getDesignPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignPressure(), PipeHist_.designPressure));
            }
            if (criteria.getIncidentalPressure() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncidentalPressure(), PipeHist_.incidentalPressure));
            }
            if (criteria.getDateInstalled() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateInstalled(), PipeHist_.dateInstalled));
            }
            if (criteria.getSeamOrient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamOrient(), PipeHist_.seamOrient));
            }
            if (criteria.getSeamAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeamAngle(), PipeHist_.seamAngle));
            }
            if (criteria.getAzimuth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzimuth(), PipeHist_.azimuth));
            }
            if (criteria.getSeabInstallTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeabInstallTemp(), PipeHist_.seabInstallTemp));
            }
            if (criteria.getVerticalAngle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerticalAngle(), PipeHist_.verticalAngle));
            }
            if (criteria.getHeatTreatDurat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeatTreatDurat(), PipeHist_.heatTreatDurat));
            }
            if (criteria.getMaxDesignTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDesignTemp(), PipeHist_.maxDesignTemp));
            }
            if (criteria.getHeatNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeatNumber(), PipeHist_.heatNumber));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), PipeHist_.coord));
            }
            if (criteria.getDesignCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesignCoord(), PipeHist_.designCoord));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), PipeHist_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), PipeHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), PipeHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PipeHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), PipeHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), PipeHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), PipeHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), PipeHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), PipeHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(PipeHist_.id, JoinType.LEFT).get(Pipe_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(PipeHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdInternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdInternalCoatTypeId(),
                    root -> root.join(PipeHist_.idInternalCoatType, JoinType.LEFT).get(ListInternalCoating_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(PipeHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdNominalWallThicknessId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdNominalWallThicknessId(),
                    root -> root.join(PipeHist_.idNominalWallThickness, JoinType.LEFT).get(ListNominalWallThickness_.id)));
            }
            if (criteria.getIdPipeJointId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipeJointId(),
                    root -> root.join(PipeHist_.idPipeJoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdManufacturerId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdManufacturerId(),
                    root -> root.join(PipeHist_.idManufacturer, JoinType.LEFT).get(ListPipeManufacturer_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(PipeHist_.idSpecification, JoinType.LEFT).get(ListPipeSpecification_.id)));
            }
            if (criteria.getIdLongSeamWeldTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLongSeamWeldTypeId(),
                    root -> root.join(PipeHist_.idLongSeamWeldType, JoinType.LEFT).get(ListLongSeamWeldType_.id)));
            }
            if (criteria.getIdFabricationTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdFabricationTypeId(),
                    root -> root.join(PipeHist_.idFabricationType, JoinType.LEFT).get(ListFabricationType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(PipeHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdMillLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMillLocationId(),
                    root -> root.join(PipeHist_.idMillLocation, JoinType.LEFT).get(ListMillLocation_.id)));
            }
            if (criteria.getIdSteelGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSteelGradeId(),
                    root -> root.join(PipeHist_.idSteelGrade, JoinType.LEFT).get(ListSteelGrade_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(PipeHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
