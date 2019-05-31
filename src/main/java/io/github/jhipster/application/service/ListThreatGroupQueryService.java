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

import io.github.jhipster.application.domain.ListThreatGroup;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListThreatGroupRepository;
import io.github.jhipster.application.service.dto.ListThreatGroupCriteria;
import io.github.jhipster.application.service.dto.ListThreatGroupDTO;
import io.github.jhipster.application.service.mapper.ListThreatGroupMapper;

/**
 * Service for executing complex queries for {@link ListThreatGroup} entities in the database.
 * The main input is a {@link ListThreatGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListThreatGroupDTO} or a {@link Page} of {@link ListThreatGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListThreatGroupQueryService extends QueryService<ListThreatGroup> {

    private final Logger log = LoggerFactory.getLogger(ListThreatGroupQueryService.class);

    private final ListThreatGroupRepository listThreatGroupRepository;

    private final ListThreatGroupMapper listThreatGroupMapper;

    public ListThreatGroupQueryService(ListThreatGroupRepository listThreatGroupRepository, ListThreatGroupMapper listThreatGroupMapper) {
        this.listThreatGroupRepository = listThreatGroupRepository;
        this.listThreatGroupMapper = listThreatGroupMapper;
    }

    /**
     * Return a {@link List} of {@link ListThreatGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListThreatGroupDTO> findByCriteria(ListThreatGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListThreatGroup> specification = createSpecification(criteria);
        return listThreatGroupMapper.toDto(listThreatGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListThreatGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListThreatGroupDTO> findByCriteria(ListThreatGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListThreatGroup> specification = createSpecification(criteria);
        return listThreatGroupRepository.findAll(specification, page)
            .map(listThreatGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListThreatGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListThreatGroup> specification = createSpecification(criteria);
        return listThreatGroupRepository.count(specification);
    }

    /**
     * Function to convert ListThreatGroupCriteria to a {@link Specification}.
     */
    private Specification<ListThreatGroup> createSpecification(ListThreatGroupCriteria criteria) {
        Specification<ListThreatGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListThreatGroup_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListThreatGroup_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListThreatGroup_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListThreatGroup_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListThreatGroup_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListThreatGroup_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListThreatGroup_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListThreatGroup_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListThreatGroup_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListThreatGroup_.editor));
            }
            if (criteria.getListThreatId() != null) {
                specification = specification.and(buildSpecification(criteria.getListThreatId(),
                    root -> root.join(ListThreatGroup_.listThreats, JoinType.LEFT).get(ListThreat_.id)));
            }
        }
        return specification;
    }
}
