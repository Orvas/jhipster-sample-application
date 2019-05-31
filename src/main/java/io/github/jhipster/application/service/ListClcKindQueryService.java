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

import io.github.jhipster.application.domain.ListClcKind;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListClcKindRepository;
import io.github.jhipster.application.service.dto.ListClcKindCriteria;
import io.github.jhipster.application.service.dto.ListClcKindDTO;
import io.github.jhipster.application.service.mapper.ListClcKindMapper;

/**
 * Service for executing complex queries for {@link ListClcKind} entities in the database.
 * The main input is a {@link ListClcKindCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListClcKindDTO} or a {@link Page} of {@link ListClcKindDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListClcKindQueryService extends QueryService<ListClcKind> {

    private final Logger log = LoggerFactory.getLogger(ListClcKindQueryService.class);

    private final ListClcKindRepository listClcKindRepository;

    private final ListClcKindMapper listClcKindMapper;

    public ListClcKindQueryService(ListClcKindRepository listClcKindRepository, ListClcKindMapper listClcKindMapper) {
        this.listClcKindRepository = listClcKindRepository;
        this.listClcKindMapper = listClcKindMapper;
    }

    /**
     * Return a {@link List} of {@link ListClcKindDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListClcKindDTO> findByCriteria(ListClcKindCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListClcKind> specification = createSpecification(criteria);
        return listClcKindMapper.toDto(listClcKindRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListClcKindDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcKindDTO> findByCriteria(ListClcKindCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListClcKind> specification = createSpecification(criteria);
        return listClcKindRepository.findAll(specification, page)
            .map(listClcKindMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListClcKindCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListClcKind> specification = createSpecification(criteria);
        return listClcKindRepository.count(specification);
    }

    /**
     * Function to convert ListClcKindCriteria to a {@link Specification}.
     */
    private Specification<ListClcKind> createSpecification(ListClcKindCriteria criteria) {
        Specification<ListClcKind> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListClcKind_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListClcKind_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListClcKind_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListClcKind_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListClcKind_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListClcKind_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListClcKind_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListClcKind_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListClcKind_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListClcKind_.editor));
            }
        }
        return specification;
    }
}
