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

import io.github.jhipster.application.domain.ListClcResult;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListClcResultRepository;
import io.github.jhipster.application.service.dto.ListClcResultCriteria;
import io.github.jhipster.application.service.dto.ListClcResultDTO;
import io.github.jhipster.application.service.mapper.ListClcResultMapper;

/**
 * Service for executing complex queries for {@link ListClcResult} entities in the database.
 * The main input is a {@link ListClcResultCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListClcResultDTO} or a {@link Page} of {@link ListClcResultDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListClcResultQueryService extends QueryService<ListClcResult> {

    private final Logger log = LoggerFactory.getLogger(ListClcResultQueryService.class);

    private final ListClcResultRepository listClcResultRepository;

    private final ListClcResultMapper listClcResultMapper;

    public ListClcResultQueryService(ListClcResultRepository listClcResultRepository, ListClcResultMapper listClcResultMapper) {
        this.listClcResultRepository = listClcResultRepository;
        this.listClcResultMapper = listClcResultMapper;
    }

    /**
     * Return a {@link List} of {@link ListClcResultDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListClcResultDTO> findByCriteria(ListClcResultCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListClcResult> specification = createSpecification(criteria);
        return listClcResultMapper.toDto(listClcResultRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListClcResultDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcResultDTO> findByCriteria(ListClcResultCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListClcResult> specification = createSpecification(criteria);
        return listClcResultRepository.findAll(specification, page)
            .map(listClcResultMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListClcResultCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListClcResult> specification = createSpecification(criteria);
        return listClcResultRepository.count(specification);
    }

    /**
     * Function to convert ListClcResultCriteria to a {@link Specification}.
     */
    private Specification<ListClcResult> createSpecification(ListClcResultCriteria criteria) {
        Specification<ListClcResult> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListClcResult_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListClcResult_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListClcResult_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListClcResult_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListClcResult_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListClcResult_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListClcResult_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListClcResult_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListClcResult_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListClcResult_.editor));
            }
        }
        return specification;
    }
}
