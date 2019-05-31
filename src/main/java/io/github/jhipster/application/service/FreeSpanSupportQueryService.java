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

import io.github.jhipster.application.domain.FreeSpanSupport;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FreeSpanSupportRepository;
import io.github.jhipster.application.service.dto.FreeSpanSupportCriteria;
import io.github.jhipster.application.service.dto.FreeSpanSupportDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportMapper;

/**
 * Service for executing complex queries for {@link FreeSpanSupport} entities in the database.
 * The main input is a {@link FreeSpanSupportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FreeSpanSupportDTO} or a {@link Page} of {@link FreeSpanSupportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FreeSpanSupportQueryService extends QueryService<FreeSpanSupport> {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportQueryService.class);

    private final FreeSpanSupportRepository freeSpanSupportRepository;

    private final FreeSpanSupportMapper freeSpanSupportMapper;

    public FreeSpanSupportQueryService(FreeSpanSupportRepository freeSpanSupportRepository, FreeSpanSupportMapper freeSpanSupportMapper) {
        this.freeSpanSupportRepository = freeSpanSupportRepository;
        this.freeSpanSupportMapper = freeSpanSupportMapper;
    }

    /**
     * Return a {@link List} of {@link FreeSpanSupportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FreeSpanSupportDTO> findByCriteria(FreeSpanSupportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FreeSpanSupport> specification = createSpecification(criteria);
        return freeSpanSupportMapper.toDto(freeSpanSupportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FreeSpanSupportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanSupportDTO> findByCriteria(FreeSpanSupportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FreeSpanSupport> specification = createSpecification(criteria);
        return freeSpanSupportRepository.findAll(specification, page)
            .map(freeSpanSupportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FreeSpanSupportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FreeSpanSupport> specification = createSpecification(criteria);
        return freeSpanSupportRepository.count(specification);
    }

    /**
     * Function to convert FreeSpanSupportCriteria to a {@link Specification}.
     */
    private Specification<FreeSpanSupport> createSpecification(FreeSpanSupportCriteria criteria) {
        Specification<FreeSpanSupport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FreeSpanSupport_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), FreeSpanSupport_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), FreeSpanSupport_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), FreeSpanSupport_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), FreeSpanSupport_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(FreeSpanSupport_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getFreeSpanSupportHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanSupportHistId(),
                    root -> root.join(FreeSpanSupport_.freeSpanSupportHist, JoinType.LEFT).get(FreeSpanSupportHist_.id)));
            }
        }
        return specification;
    }
}
