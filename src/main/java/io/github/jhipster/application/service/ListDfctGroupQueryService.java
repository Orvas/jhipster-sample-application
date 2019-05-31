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

import io.github.jhipster.application.domain.ListDfctGroup;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListDfctGroupRepository;
import io.github.jhipster.application.service.dto.ListDfctGroupCriteria;
import io.github.jhipster.application.service.dto.ListDfctGroupDTO;
import io.github.jhipster.application.service.mapper.ListDfctGroupMapper;

/**
 * Service for executing complex queries for {@link ListDfctGroup} entities in the database.
 * The main input is a {@link ListDfctGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListDfctGroupDTO} or a {@link Page} of {@link ListDfctGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListDfctGroupQueryService extends QueryService<ListDfctGroup> {

    private final Logger log = LoggerFactory.getLogger(ListDfctGroupQueryService.class);

    private final ListDfctGroupRepository listDfctGroupRepository;

    private final ListDfctGroupMapper listDfctGroupMapper;

    public ListDfctGroupQueryService(ListDfctGroupRepository listDfctGroupRepository, ListDfctGroupMapper listDfctGroupMapper) {
        this.listDfctGroupRepository = listDfctGroupRepository;
        this.listDfctGroupMapper = listDfctGroupMapper;
    }

    /**
     * Return a {@link List} of {@link ListDfctGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListDfctGroupDTO> findByCriteria(ListDfctGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListDfctGroup> specification = createSpecification(criteria);
        return listDfctGroupMapper.toDto(listDfctGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListDfctGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctGroupDTO> findByCriteria(ListDfctGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListDfctGroup> specification = createSpecification(criteria);
        return listDfctGroupRepository.findAll(specification, page)
            .map(listDfctGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListDfctGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListDfctGroup> specification = createSpecification(criteria);
        return listDfctGroupRepository.count(specification);
    }

    /**
     * Function to convert ListDfctGroupCriteria to a {@link Specification}.
     */
    private Specification<ListDfctGroup> createSpecification(ListDfctGroupCriteria criteria) {
        Specification<ListDfctGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListDfctGroup_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListDfctGroup_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListDfctGroup_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListDfctGroup_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListDfctGroup_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListDfctGroup_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListDfctGroup_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListDfctGroup_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListDfctGroup_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListDfctGroup_.editor));
            }
            if (criteria.getListDfctTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getListDfctTypeId(),
                    root -> root.join(ListDfctGroup_.listDfctTypes, JoinType.LEFT).get(ListDfctType_.id)));
            }
        }
        return specification;
    }
}
