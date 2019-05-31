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

import io.github.jhipster.application.domain.ListWrkKind;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListWrkKindRepository;
import io.github.jhipster.application.service.dto.ListWrkKindCriteria;
import io.github.jhipster.application.service.dto.ListWrkKindDTO;
import io.github.jhipster.application.service.mapper.ListWrkKindMapper;

/**
 * Service for executing complex queries for {@link ListWrkKind} entities in the database.
 * The main input is a {@link ListWrkKindCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListWrkKindDTO} or a {@link Page} of {@link ListWrkKindDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListWrkKindQueryService extends QueryService<ListWrkKind> {

    private final Logger log = LoggerFactory.getLogger(ListWrkKindQueryService.class);

    private final ListWrkKindRepository listWrkKindRepository;

    private final ListWrkKindMapper listWrkKindMapper;

    public ListWrkKindQueryService(ListWrkKindRepository listWrkKindRepository, ListWrkKindMapper listWrkKindMapper) {
        this.listWrkKindRepository = listWrkKindRepository;
        this.listWrkKindMapper = listWrkKindMapper;
    }

    /**
     * Return a {@link List} of {@link ListWrkKindDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListWrkKindDTO> findByCriteria(ListWrkKindCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListWrkKind> specification = createSpecification(criteria);
        return listWrkKindMapper.toDto(listWrkKindRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListWrkKindDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkKindDTO> findByCriteria(ListWrkKindCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListWrkKind> specification = createSpecification(criteria);
        return listWrkKindRepository.findAll(specification, page)
            .map(listWrkKindMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListWrkKindCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListWrkKind> specification = createSpecification(criteria);
        return listWrkKindRepository.count(specification);
    }

    /**
     * Function to convert ListWrkKindCriteria to a {@link Specification}.
     */
    private Specification<ListWrkKind> createSpecification(ListWrkKindCriteria criteria) {
        Specification<ListWrkKind> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListWrkKind_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListWrkKind_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListWrkKind_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListWrkKind_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListWrkKind_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListWrkKind_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListWrkKind_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListWrkKind_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListWrkKind_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListWrkKind_.editor));
            }
            if (criteria.getListWrkStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getListWrkStatusId(),
                    root -> root.join(ListWrkKind_.listWrkStatuses, JoinType.LEFT).get(ListWrkStatus_.id)));
            }
        }
        return specification;
    }
}
