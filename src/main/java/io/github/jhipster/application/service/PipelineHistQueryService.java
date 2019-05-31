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

import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipelineHistRepository;
import io.github.jhipster.application.service.dto.PipelineHistCriteria;
import io.github.jhipster.application.service.dto.PipelineHistDTO;
import io.github.jhipster.application.service.mapper.PipelineHistMapper;

/**
 * Service for executing complex queries for {@link PipelineHist} entities in the database.
 * The main input is a {@link PipelineHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipelineHistDTO} or a {@link Page} of {@link PipelineHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipelineHistQueryService extends QueryService<PipelineHist> {

    private final Logger log = LoggerFactory.getLogger(PipelineHistQueryService.class);

    private final PipelineHistRepository pipelineHistRepository;

    private final PipelineHistMapper pipelineHistMapper;

    public PipelineHistQueryService(PipelineHistRepository pipelineHistRepository, PipelineHistMapper pipelineHistMapper) {
        this.pipelineHistRepository = pipelineHistRepository;
        this.pipelineHistMapper = pipelineHistMapper;
    }

    /**
     * Return a {@link List} of {@link PipelineHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipelineHistDTO> findByCriteria(PipelineHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PipelineHist> specification = createSpecification(criteria);
        return pipelineHistMapper.toDto(pipelineHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipelineHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineHistDTO> findByCriteria(PipelineHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PipelineHist> specification = createSpecification(criteria);
        return pipelineHistRepository.findAll(specification, page)
            .map(pipelineHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipelineHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PipelineHist> specification = createSpecification(criteria);
        return pipelineHistRepository.count(specification);
    }

    /**
     * Function to convert PipelineHistCriteria to a {@link Specification}.
     */
    private Specification<PipelineHist> createSpecification(PipelineHistCriteria criteria) {
        Specification<PipelineHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PipelineHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), PipelineHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), PipelineHist_.dateTo));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PipelineHist_.name));
            }
            if (criteria.getDesignLife() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignLife(), PipelineHist_.designLife));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), PipelineHist_.isCurrentFlag));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), PipelineHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), PipelineHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), PipelineHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), PipelineHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), PipelineHist_.editor));
            }
            if (criteria.getPipelineId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineId(),
                    root -> root.join(PipelineHist_.pipeline, JoinType.LEFT).get(Pipeline_.id)));
            }
            if (criteria.getIdLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLocationId(),
                    root -> root.join(PipelineHist_.idLocation, JoinType.LEFT).get(ListPipelineLocation_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(PipelineHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
