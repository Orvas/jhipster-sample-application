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

import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipelineSectionRepository;
import io.github.jhipster.application.service.dto.PipelineSectionCriteria;
import io.github.jhipster.application.service.dto.PipelineSectionDTO;
import io.github.jhipster.application.service.mapper.PipelineSectionMapper;

/**
 * Service for executing complex queries for {@link PipelineSection} entities in the database.
 * The main input is a {@link PipelineSectionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipelineSectionDTO} or a {@link Page} of {@link PipelineSectionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipelineSectionQueryService extends QueryService<PipelineSection> {

    private final Logger log = LoggerFactory.getLogger(PipelineSectionQueryService.class);

    private final PipelineSectionRepository pipelineSectionRepository;

    private final PipelineSectionMapper pipelineSectionMapper;

    public PipelineSectionQueryService(PipelineSectionRepository pipelineSectionRepository, PipelineSectionMapper pipelineSectionMapper) {
        this.pipelineSectionRepository = pipelineSectionRepository;
        this.pipelineSectionMapper = pipelineSectionMapper;
    }

    /**
     * Return a {@link List} of {@link PipelineSectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipelineSectionDTO> findByCriteria(PipelineSectionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PipelineSection> specification = createSpecification(criteria);
        return pipelineSectionMapper.toDto(pipelineSectionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipelineSectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineSectionDTO> findByCriteria(PipelineSectionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PipelineSection> specification = createSpecification(criteria);
        return pipelineSectionRepository.findAll(specification, page)
            .map(pipelineSectionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipelineSectionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PipelineSection> specification = createSpecification(criteria);
        return pipelineSectionRepository.count(specification);
    }

    /**
     * Function to convert PipelineSectionCriteria to a {@link Specification}.
     */
    private Specification<PipelineSection> createSpecification(PipelineSectionCriteria criteria) {
        Specification<PipelineSection> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PipelineSection_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PipelineSection_.name));
            }
            if (criteria.getIsOnshore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsOnshore(), PipelineSection_.isOnshore));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), PipelineSection_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), PipelineSection_.kpEnd));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), PipelineSection_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), PipelineSection_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), PipelineSection_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), PipelineSection_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(PipelineSection_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getIdPipelineId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineId(),
                    root -> root.join(PipelineSection_.idPipeline, JoinType.LEFT).get(Pipeline_.id)));
            }
            if (criteria.getIdSafetyClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSafetyClassId(),
                    root -> root.join(PipelineSection_.idSafetyClass, JoinType.LEFT).get(ListSafetyClass_.id)));
            }
            if (criteria.getAnodeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeHistId(),
                    root -> root.join(PipelineSection_.anodeHists, JoinType.LEFT).get(AnodeHist_.id)));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(PipelineSection_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(PipelineSection_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getCpsHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsHistId(),
                    root -> root.join(PipelineSection_.cpsHists, JoinType.LEFT).get(CpsHist_.id)));
            }
            if (criteria.getCpsRangeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsRangeId(),
                    root -> root.join(PipelineSection_.cpsRanges, JoinType.LEFT).get(CpsRange_.id)));
            }
            if (criteria.getDisplacementHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getDisplacementHistId(),
                    root -> root.join(PipelineSection_.displacementHists, JoinType.LEFT).get(DisplacementHist_.id)));
            }
            if (criteria.getFreeSpanHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanHistId(),
                    root -> root.join(PipelineSection_.freeSpanHists, JoinType.LEFT).get(FreeSpanHist_.id)));
            }
            if (criteria.getFreeSpanSupportHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanSupportHistId(),
                    root -> root.join(PipelineSection_.freeSpanSupportHists, JoinType.LEFT).get(FreeSpanSupportHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(PipelineSection_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(PipelineSection_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(PipelineSection_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
