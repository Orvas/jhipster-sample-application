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

import io.github.jhipster.application.domain.ListMinpressUcase;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListMinpressUcaseRepository;
import io.github.jhipster.application.service.dto.ListMinpressUcaseCriteria;
import io.github.jhipster.application.service.dto.ListMinpressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListMinpressUcaseMapper;

/**
 * Service for executing complex queries for {@link ListMinpressUcase} entities in the database.
 * The main input is a {@link ListMinpressUcaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListMinpressUcaseDTO} or a {@link Page} of {@link ListMinpressUcaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListMinpressUcaseQueryService extends QueryService<ListMinpressUcase> {

    private final Logger log = LoggerFactory.getLogger(ListMinpressUcaseQueryService.class);

    private final ListMinpressUcaseRepository listMinpressUcaseRepository;

    private final ListMinpressUcaseMapper listMinpressUcaseMapper;

    public ListMinpressUcaseQueryService(ListMinpressUcaseRepository listMinpressUcaseRepository, ListMinpressUcaseMapper listMinpressUcaseMapper) {
        this.listMinpressUcaseRepository = listMinpressUcaseRepository;
        this.listMinpressUcaseMapper = listMinpressUcaseMapper;
    }

    /**
     * Return a {@link List} of {@link ListMinpressUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListMinpressUcaseDTO> findByCriteria(ListMinpressUcaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListMinpressUcase> specification = createSpecification(criteria);
        return listMinpressUcaseMapper.toDto(listMinpressUcaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListMinpressUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMinpressUcaseDTO> findByCriteria(ListMinpressUcaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListMinpressUcase> specification = createSpecification(criteria);
        return listMinpressUcaseRepository.findAll(specification, page)
            .map(listMinpressUcaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListMinpressUcaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListMinpressUcase> specification = createSpecification(criteria);
        return listMinpressUcaseRepository.count(specification);
    }

    /**
     * Function to convert ListMinpressUcaseCriteria to a {@link Specification}.
     */
    private Specification<ListMinpressUcase> createSpecification(ListMinpressUcaseCriteria criteria) {
        Specification<ListMinpressUcase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListMinpressUcase_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListMinpressUcase_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListMinpressUcase_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListMinpressUcase_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListMinpressUcase_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListMinpressUcase_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListMinpressUcase_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListMinpressUcase_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListMinpressUcase_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListMinpressUcase_.editor));
            }
        }
        return specification;
    }
}
