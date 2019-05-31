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

import io.github.jhipster.application.domain.FreeSpan;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FreeSpanRepository;
import io.github.jhipster.application.service.dto.FreeSpanCriteria;
import io.github.jhipster.application.service.dto.FreeSpanDTO;
import io.github.jhipster.application.service.mapper.FreeSpanMapper;

/**
 * Service for executing complex queries for {@link FreeSpan} entities in the database.
 * The main input is a {@link FreeSpanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FreeSpanDTO} or a {@link Page} of {@link FreeSpanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FreeSpanQueryService extends QueryService<FreeSpan> {

    private final Logger log = LoggerFactory.getLogger(FreeSpanQueryService.class);

    private final FreeSpanRepository freeSpanRepository;

    private final FreeSpanMapper freeSpanMapper;

    public FreeSpanQueryService(FreeSpanRepository freeSpanRepository, FreeSpanMapper freeSpanMapper) {
        this.freeSpanRepository = freeSpanRepository;
        this.freeSpanMapper = freeSpanMapper;
    }

    /**
     * Return a {@link List} of {@link FreeSpanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FreeSpanDTO> findByCriteria(FreeSpanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FreeSpan> specification = createSpecification(criteria);
        return freeSpanMapper.toDto(freeSpanRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FreeSpanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanDTO> findByCriteria(FreeSpanCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FreeSpan> specification = createSpecification(criteria);
        return freeSpanRepository.findAll(specification, page)
            .map(freeSpanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FreeSpanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FreeSpan> specification = createSpecification(criteria);
        return freeSpanRepository.count(specification);
    }

    /**
     * Function to convert FreeSpanCriteria to a {@link Specification}.
     */
    private Specification<FreeSpan> createSpecification(FreeSpanCriteria criteria) {
        Specification<FreeSpan> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FreeSpan_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), FreeSpan_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), FreeSpan_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), FreeSpan_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), FreeSpan_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(FreeSpan_.id, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getFreeSpanHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanHistId(),
                    root -> root.join(FreeSpan_.freeSpanHists, JoinType.LEFT).get(FreeSpanHist_.id)));
            }
        }
        return specification;
    }
}
