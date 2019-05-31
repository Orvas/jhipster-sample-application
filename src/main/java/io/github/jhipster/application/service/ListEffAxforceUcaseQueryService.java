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

import io.github.jhipster.application.domain.ListEffAxforceUcase;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListEffAxforceUcaseRepository;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseCriteria;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseDTO;
import io.github.jhipster.application.service.mapper.ListEffAxforceUcaseMapper;

/**
 * Service for executing complex queries for {@link ListEffAxforceUcase} entities in the database.
 * The main input is a {@link ListEffAxforceUcaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListEffAxforceUcaseDTO} or a {@link Page} of {@link ListEffAxforceUcaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListEffAxforceUcaseQueryService extends QueryService<ListEffAxforceUcase> {

    private final Logger log = LoggerFactory.getLogger(ListEffAxforceUcaseQueryService.class);

    private final ListEffAxforceUcaseRepository listEffAxforceUcaseRepository;

    private final ListEffAxforceUcaseMapper listEffAxforceUcaseMapper;

    public ListEffAxforceUcaseQueryService(ListEffAxforceUcaseRepository listEffAxforceUcaseRepository, ListEffAxforceUcaseMapper listEffAxforceUcaseMapper) {
        this.listEffAxforceUcaseRepository = listEffAxforceUcaseRepository;
        this.listEffAxforceUcaseMapper = listEffAxforceUcaseMapper;
    }

    /**
     * Return a {@link List} of {@link ListEffAxforceUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListEffAxforceUcaseDTO> findByCriteria(ListEffAxforceUcaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListEffAxforceUcase> specification = createSpecification(criteria);
        return listEffAxforceUcaseMapper.toDto(listEffAxforceUcaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListEffAxforceUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEffAxforceUcaseDTO> findByCriteria(ListEffAxforceUcaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListEffAxforceUcase> specification = createSpecification(criteria);
        return listEffAxforceUcaseRepository.findAll(specification, page)
            .map(listEffAxforceUcaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListEffAxforceUcaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListEffAxforceUcase> specification = createSpecification(criteria);
        return listEffAxforceUcaseRepository.count(specification);
    }

    /**
     * Function to convert ListEffAxforceUcaseCriteria to a {@link Specification}.
     */
    private Specification<ListEffAxforceUcase> createSpecification(ListEffAxforceUcaseCriteria criteria) {
        Specification<ListEffAxforceUcase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListEffAxforceUcase_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListEffAxforceUcase_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListEffAxforceUcase_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListEffAxforceUcase_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListEffAxforceUcase_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListEffAxforceUcase_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListEffAxforceUcase_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListEffAxforceUcase_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListEffAxforceUcase_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListEffAxforceUcase_.editor));
            }
        }
        return specification;
    }
}
