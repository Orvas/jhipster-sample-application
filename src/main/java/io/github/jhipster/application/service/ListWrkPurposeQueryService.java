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

import io.github.jhipster.application.domain.ListWrkPurpose;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListWrkPurposeRepository;
import io.github.jhipster.application.service.dto.ListWrkPurposeCriteria;
import io.github.jhipster.application.service.dto.ListWrkPurposeDTO;
import io.github.jhipster.application.service.mapper.ListWrkPurposeMapper;

/**
 * Service for executing complex queries for {@link ListWrkPurpose} entities in the database.
 * The main input is a {@link ListWrkPurposeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListWrkPurposeDTO} or a {@link Page} of {@link ListWrkPurposeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListWrkPurposeQueryService extends QueryService<ListWrkPurpose> {

    private final Logger log = LoggerFactory.getLogger(ListWrkPurposeQueryService.class);

    private final ListWrkPurposeRepository listWrkPurposeRepository;

    private final ListWrkPurposeMapper listWrkPurposeMapper;

    public ListWrkPurposeQueryService(ListWrkPurposeRepository listWrkPurposeRepository, ListWrkPurposeMapper listWrkPurposeMapper) {
        this.listWrkPurposeRepository = listWrkPurposeRepository;
        this.listWrkPurposeMapper = listWrkPurposeMapper;
    }

    /**
     * Return a {@link List} of {@link ListWrkPurposeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListWrkPurposeDTO> findByCriteria(ListWrkPurposeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListWrkPurpose> specification = createSpecification(criteria);
        return listWrkPurposeMapper.toDto(listWrkPurposeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListWrkPurposeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkPurposeDTO> findByCriteria(ListWrkPurposeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListWrkPurpose> specification = createSpecification(criteria);
        return listWrkPurposeRepository.findAll(specification, page)
            .map(listWrkPurposeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListWrkPurposeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListWrkPurpose> specification = createSpecification(criteria);
        return listWrkPurposeRepository.count(specification);
    }

    /**
     * Function to convert ListWrkPurposeCriteria to a {@link Specification}.
     */
    private Specification<ListWrkPurpose> createSpecification(ListWrkPurposeCriteria criteria) {
        Specification<ListWrkPurpose> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListWrkPurpose_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListWrkPurpose_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListWrkPurpose_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListWrkPurpose_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListWrkPurpose_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListWrkPurpose_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListWrkPurpose_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListWrkPurpose_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListWrkPurpose_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListWrkPurpose_.editor));
            }
        }
        return specification;
    }
}
