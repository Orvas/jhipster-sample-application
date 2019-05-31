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

import io.github.jhipster.application.domain.DisplacementHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.DisplacementHistRepository;
import io.github.jhipster.application.service.dto.DisplacementHistCriteria;
import io.github.jhipster.application.service.dto.DisplacementHistDTO;
import io.github.jhipster.application.service.mapper.DisplacementHistMapper;

/**
 * Service for executing complex queries for {@link DisplacementHist} entities in the database.
 * The main input is a {@link DisplacementHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DisplacementHistDTO} or a {@link Page} of {@link DisplacementHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DisplacementHistQueryService extends QueryService<DisplacementHist> {

    private final Logger log = LoggerFactory.getLogger(DisplacementHistQueryService.class);

    private final DisplacementHistRepository displacementHistRepository;

    private final DisplacementHistMapper displacementHistMapper;

    public DisplacementHistQueryService(DisplacementHistRepository displacementHistRepository, DisplacementHistMapper displacementHistMapper) {
        this.displacementHistRepository = displacementHistRepository;
        this.displacementHistMapper = displacementHistMapper;
    }

    /**
     * Return a {@link List} of {@link DisplacementHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DisplacementHistDTO> findByCriteria(DisplacementHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DisplacementHist> specification = createSpecification(criteria);
        return displacementHistMapper.toDto(displacementHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DisplacementHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DisplacementHistDTO> findByCriteria(DisplacementHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DisplacementHist> specification = createSpecification(criteria);
        return displacementHistRepository.findAll(specification, page)
            .map(displacementHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DisplacementHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DisplacementHist> specification = createSpecification(criteria);
        return displacementHistRepository.count(specification);
    }

    /**
     * Function to convert DisplacementHistCriteria to a {@link Specification}.
     */
    private Specification<DisplacementHist> createSpecification(DisplacementHistCriteria criteria) {
        Specification<DisplacementHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DisplacementHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), DisplacementHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), DisplacementHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), DisplacementHist_.idWrk));
            }
            if (criteria.getDeltaX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeltaX(), DisplacementHist_.deltaX));
            }
            if (criteria.getDeltaY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeltaY(), DisplacementHist_.deltaY));
            }
            if (criteria.getDeltaZ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeltaZ(), DisplacementHist_.deltaZ));
            }
            if (criteria.getDeltaTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeltaTotal(), DisplacementHist_.deltaTotal));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), DisplacementHist_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), DisplacementHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), DisplacementHist_.isCurrentFlag));
            }
            if (criteria.getIdStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdStatus(), DisplacementHist_.idStatus));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), DisplacementHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), DisplacementHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), DisplacementHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), DisplacementHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), DisplacementHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(DisplacementHist_.id, JoinType.LEFT).get(Displacement_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(DisplacementHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
        }
        return specification;
    }
}
