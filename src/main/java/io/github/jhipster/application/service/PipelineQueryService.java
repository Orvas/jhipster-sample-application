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

import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipelineRepository;
import io.github.jhipster.application.service.dto.PipelineCriteria;
import io.github.jhipster.application.service.dto.PipelineDTO;
import io.github.jhipster.application.service.mapper.PipelineMapper;

/**
 * Service for executing complex queries for {@link Pipeline} entities in the database.
 * The main input is a {@link PipelineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipelineDTO} or a {@link Page} of {@link PipelineDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipelineQueryService extends QueryService<Pipeline> {

    private final Logger log = LoggerFactory.getLogger(PipelineQueryService.class);

    private final PipelineRepository pipelineRepository;

    private final PipelineMapper pipelineMapper;

    public PipelineQueryService(PipelineRepository pipelineRepository, PipelineMapper pipelineMapper) {
        this.pipelineRepository = pipelineRepository;
        this.pipelineMapper = pipelineMapper;
    }

    /**
     * Return a {@link List} of {@link PipelineDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipelineDTO> findByCriteria(PipelineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Pipeline> specification = createSpecification(criteria);
        return pipelineMapper.toDto(pipelineRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipelineDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineDTO> findByCriteria(PipelineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Pipeline> specification = createSpecification(criteria);
        return pipelineRepository.findAll(specification, page)
            .map(pipelineMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipelineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Pipeline> specification = createSpecification(criteria);
        return pipelineRepository.count(specification);
    }

    /**
     * Function to convert PipelineCriteria to a {@link Specification}.
     */
    private Specification<Pipeline> createSpecification(PipelineCriteria criteria) {
        Specification<Pipeline> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Pipeline_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Pipeline_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Pipeline_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Pipeline_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Pipeline_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(Pipeline_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getPipelineHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineHistId(),
                    root -> root.join(Pipeline_.pipelineHist, JoinType.LEFT).get(PipelineHist_.id)));
            }
            if (criteria.getLaunchReceiverHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getLaunchReceiverHistId(),
                    root -> root.join(Pipeline_.launchReceiverHists, JoinType.LEFT).get(LaunchReceiverHist_.id)));
            }
            if (criteria.getPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineSectionId(),
                    root -> root.join(Pipeline_.pipelineSections, JoinType.LEFT).get(PipelineSection_.id)));
            }
        }
        return specification;
    }
}
