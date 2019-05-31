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

import io.github.jhipster.application.domain.ListBoundaryCondUcase;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBoundaryCondUcaseRepository;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseCriteria;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseDTO;
import io.github.jhipster.application.service.mapper.ListBoundaryCondUcaseMapper;

/**
 * Service for executing complex queries for {@link ListBoundaryCondUcase} entities in the database.
 * The main input is a {@link ListBoundaryCondUcaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBoundaryCondUcaseDTO} or a {@link Page} of {@link ListBoundaryCondUcaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBoundaryCondUcaseQueryService extends QueryService<ListBoundaryCondUcase> {

    private final Logger log = LoggerFactory.getLogger(ListBoundaryCondUcaseQueryService.class);

    private final ListBoundaryCondUcaseRepository listBoundaryCondUcaseRepository;

    private final ListBoundaryCondUcaseMapper listBoundaryCondUcaseMapper;

    public ListBoundaryCondUcaseQueryService(ListBoundaryCondUcaseRepository listBoundaryCondUcaseRepository, ListBoundaryCondUcaseMapper listBoundaryCondUcaseMapper) {
        this.listBoundaryCondUcaseRepository = listBoundaryCondUcaseRepository;
        this.listBoundaryCondUcaseMapper = listBoundaryCondUcaseMapper;
    }

    /**
     * Return a {@link List} of {@link ListBoundaryCondUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBoundaryCondUcaseDTO> findByCriteria(ListBoundaryCondUcaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBoundaryCondUcase> specification = createSpecification(criteria);
        return listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBoundaryCondUcaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBoundaryCondUcaseDTO> findByCriteria(ListBoundaryCondUcaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBoundaryCondUcase> specification = createSpecification(criteria);
        return listBoundaryCondUcaseRepository.findAll(specification, page)
            .map(listBoundaryCondUcaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBoundaryCondUcaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBoundaryCondUcase> specification = createSpecification(criteria);
        return listBoundaryCondUcaseRepository.count(specification);
    }

    /**
     * Function to convert ListBoundaryCondUcaseCriteria to a {@link Specification}.
     */
    private Specification<ListBoundaryCondUcase> createSpecification(ListBoundaryCondUcaseCriteria criteria) {
        Specification<ListBoundaryCondUcase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBoundaryCondUcase_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBoundaryCondUcase_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBoundaryCondUcase_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBoundaryCondUcase_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBoundaryCondUcase_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBoundaryCondUcase_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBoundaryCondUcase_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBoundaryCondUcase_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBoundaryCondUcase_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBoundaryCondUcase_.editor));
            }
        }
        return specification;
    }
}
