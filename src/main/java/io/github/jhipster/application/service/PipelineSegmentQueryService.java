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

import io.github.jhipster.application.domain.PipelineSegment;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipelineSegmentRepository;
import io.github.jhipster.application.service.dto.PipelineSegmentCriteria;
import io.github.jhipster.application.service.dto.PipelineSegmentDTO;
import io.github.jhipster.application.service.mapper.PipelineSegmentMapper;

/**
 * Service for executing complex queries for {@link PipelineSegment} entities in the database.
 * The main input is a {@link PipelineSegmentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipelineSegmentDTO} or a {@link Page} of {@link PipelineSegmentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipelineSegmentQueryService extends QueryService<PipelineSegment> {

    private final Logger log = LoggerFactory.getLogger(PipelineSegmentQueryService.class);

    private final PipelineSegmentRepository pipelineSegmentRepository;

    private final PipelineSegmentMapper pipelineSegmentMapper;

    public PipelineSegmentQueryService(PipelineSegmentRepository pipelineSegmentRepository, PipelineSegmentMapper pipelineSegmentMapper) {
        this.pipelineSegmentRepository = pipelineSegmentRepository;
        this.pipelineSegmentMapper = pipelineSegmentMapper;
    }

    /**
     * Return a {@link List} of {@link PipelineSegmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipelineSegmentDTO> findByCriteria(PipelineSegmentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PipelineSegment> specification = createSpecification(criteria);
        return pipelineSegmentMapper.toDto(pipelineSegmentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipelineSegmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineSegmentDTO> findByCriteria(PipelineSegmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PipelineSegment> specification = createSpecification(criteria);
        return pipelineSegmentRepository.findAll(specification, page)
            .map(pipelineSegmentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipelineSegmentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PipelineSegment> specification = createSpecification(criteria);
        return pipelineSegmentRepository.count(specification);
    }

    /**
     * Function to convert PipelineSegmentCriteria to a {@link Specification}.
     */
    private Specification<PipelineSegment> createSpecification(PipelineSegmentCriteria criteria) {
        Specification<PipelineSegment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PipelineSegment_.id));
            }
            if (criteria.getNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNum(), PipelineSegment_.num));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PipelineSegment_.name));
            }
            if (criteria.getKpStart1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart1(), PipelineSegment_.kpStart1));
            }
            if (criteria.getKpEnd1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd1(), PipelineSegment_.kpEnd1));
            }
            if (criteria.getKpStart4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart4(), PipelineSegment_.kpStart4));
            }
            if (criteria.getKpEnd4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd4(), PipelineSegment_.kpEnd4));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), PipelineSegment_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), PipelineSegment_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), PipelineSegment_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), PipelineSegment_.editor));
            }
        }
        return specification;
    }
}
