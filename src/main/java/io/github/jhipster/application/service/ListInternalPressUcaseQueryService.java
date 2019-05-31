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

import io.github.jhipster.application.domain.ListInternalPressUcase;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListInternalPressUcaseRepository;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseCriteria;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListInternalPressUcaseMapper;

/**
 * Service for executing complex queries for {@link ListInternalPressUcase} entities in the database.
 * The main input is a {@link ListInternalPressUcaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListInternalPressUcaseDTO} or a {@link Page} of {@link ListInternalPressUcaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListInternalPressUcaseQueryService extends QueryService<ListInternalPressUcase> {

    private final Logger log = LoggerFactory.getLogger(ListInternalPressUcaseQueryService.class);

    private final ListInternalPressUcaseRepository listInternalPressUcaseRepository;

    private final ListInternalPressUcaseMapper listInternalPressUcaseMapper;

    public ListInternalPressUcaseQueryService(ListInternalPressUcaseRepository listInternalPressUcaseRepository, ListInternalPressUcaseMapper listInternalPressUcaseMapper) {
        this.listInternalPressUcaseRepository = listInternalPressUcaseRepository;
        this.listInternalPressUcaseMapper = listInternalPressUcaseMapper;
    }

    /**
     * Return a {@link List} of {@link ListInternalPressUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListInternalPressUcaseDTO> findByCriteria(ListInternalPressUcaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListInternalPressUcase> specification = createSpecification(criteria);
        return listInternalPressUcaseMapper.toDto(listInternalPressUcaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListInternalPressUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListInternalPressUcaseDTO> findByCriteria(ListInternalPressUcaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListInternalPressUcase> specification = createSpecification(criteria);
        return listInternalPressUcaseRepository.findAll(specification, page)
            .map(listInternalPressUcaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListInternalPressUcaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListInternalPressUcase> specification = createSpecification(criteria);
        return listInternalPressUcaseRepository.count(specification);
    }

    /**
     * Function to convert ListInternalPressUcaseCriteria to a {@link Specification}.
     */
    private Specification<ListInternalPressUcase> createSpecification(ListInternalPressUcaseCriteria criteria) {
        Specification<ListInternalPressUcase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListInternalPressUcase_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListInternalPressUcase_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListInternalPressUcase_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListInternalPressUcase_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListInternalPressUcase_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListInternalPressUcase_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListInternalPressUcase_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListInternalPressUcase_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListInternalPressUcase_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListInternalPressUcase_.editor));
            }
        }
        return specification;
    }
}
