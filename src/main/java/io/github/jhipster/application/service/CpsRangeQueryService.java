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

import io.github.jhipster.application.domain.CpsRange;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CpsRangeRepository;
import io.github.jhipster.application.service.dto.CpsRangeCriteria;
import io.github.jhipster.application.service.dto.CpsRangeDTO;
import io.github.jhipster.application.service.mapper.CpsRangeMapper;

/**
 * Service for executing complex queries for {@link CpsRange} entities in the database.
 * The main input is a {@link CpsRangeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CpsRangeDTO} or a {@link Page} of {@link CpsRangeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CpsRangeQueryService extends QueryService<CpsRange> {

    private final Logger log = LoggerFactory.getLogger(CpsRangeQueryService.class);

    private final CpsRangeRepository cpsRangeRepository;

    private final CpsRangeMapper cpsRangeMapper;

    public CpsRangeQueryService(CpsRangeRepository cpsRangeRepository, CpsRangeMapper cpsRangeMapper) {
        this.cpsRangeRepository = cpsRangeRepository;
        this.cpsRangeMapper = cpsRangeMapper;
    }

    /**
     * Return a {@link List} of {@link CpsRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CpsRangeDTO> findByCriteria(CpsRangeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CpsRange> specification = createSpecification(criteria);
        return cpsRangeMapper.toDto(cpsRangeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CpsRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsRangeDTO> findByCriteria(CpsRangeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CpsRange> specification = createSpecification(criteria);
        return cpsRangeRepository.findAll(specification, page)
            .map(cpsRangeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CpsRangeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CpsRange> specification = createSpecification(criteria);
        return cpsRangeRepository.count(specification);
    }

    /**
     * Function to convert CpsRangeCriteria to a {@link Specification}.
     */
    private Specification<CpsRange> createSpecification(CpsRangeCriteria criteria) {
        Specification<CpsRange> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CpsRange_.id));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), CpsRange_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), CpsRange_.kpEnd));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), CpsRange_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), CpsRange_.dateTo));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), CpsRange_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), CpsRange_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), CpsRange_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), CpsRange_.editor));
            }
            if (criteria.getIdCpsId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdCpsId(),
                    root -> root.join(CpsRange_.idCps, JoinType.LEFT).get(Cps_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(CpsRange_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
        }
        return specification;
    }
}
