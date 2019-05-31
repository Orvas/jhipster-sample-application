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

import io.github.jhipster.application.domain.ListThreat;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListThreatRepository;
import io.github.jhipster.application.service.dto.ListThreatCriteria;
import io.github.jhipster.application.service.dto.ListThreatDTO;
import io.github.jhipster.application.service.mapper.ListThreatMapper;

/**
 * Service for executing complex queries for {@link ListThreat} entities in the database.
 * The main input is a {@link ListThreatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListThreatDTO} or a {@link Page} of {@link ListThreatDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListThreatQueryService extends QueryService<ListThreat> {

    private final Logger log = LoggerFactory.getLogger(ListThreatQueryService.class);

    private final ListThreatRepository listThreatRepository;

    private final ListThreatMapper listThreatMapper;

    public ListThreatQueryService(ListThreatRepository listThreatRepository, ListThreatMapper listThreatMapper) {
        this.listThreatRepository = listThreatRepository;
        this.listThreatMapper = listThreatMapper;
    }

    /**
     * Return a {@link List} of {@link ListThreatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListThreatDTO> findByCriteria(ListThreatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListThreat> specification = createSpecification(criteria);
        return listThreatMapper.toDto(listThreatRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListThreatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListThreatDTO> findByCriteria(ListThreatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListThreat> specification = createSpecification(criteria);
        return listThreatRepository.findAll(specification, page)
            .map(listThreatMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListThreatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListThreat> specification = createSpecification(criteria);
        return listThreatRepository.count(specification);
    }

    /**
     * Function to convert ListThreatCriteria to a {@link Specification}.
     */
    private Specification<ListThreat> createSpecification(ListThreatCriteria criteria) {
        Specification<ListThreat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListThreat_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListThreat_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListThreat_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListThreat_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListThreat_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListThreat_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListThreat_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListThreat_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListThreat_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListThreat_.editor));
            }
            if (criteria.getIdGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdGroupId(),
                    root -> root.join(ListThreat_.idGroup, JoinType.LEFT).get(ListThreatGroup_.id)));
            }
        }
        return specification;
    }
}
