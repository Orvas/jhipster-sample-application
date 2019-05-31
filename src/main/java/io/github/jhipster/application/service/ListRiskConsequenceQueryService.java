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

import io.github.jhipster.application.domain.ListRiskConsequence;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListRiskConsequenceRepository;
import io.github.jhipster.application.service.dto.ListRiskConsequenceCriteria;
import io.github.jhipster.application.service.dto.ListRiskConsequenceDTO;
import io.github.jhipster.application.service.mapper.ListRiskConsequenceMapper;

/**
 * Service for executing complex queries for {@link ListRiskConsequence} entities in the database.
 * The main input is a {@link ListRiskConsequenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListRiskConsequenceDTO} or a {@link Page} of {@link ListRiskConsequenceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListRiskConsequenceQueryService extends QueryService<ListRiskConsequence> {

    private final Logger log = LoggerFactory.getLogger(ListRiskConsequenceQueryService.class);

    private final ListRiskConsequenceRepository listRiskConsequenceRepository;

    private final ListRiskConsequenceMapper listRiskConsequenceMapper;

    public ListRiskConsequenceQueryService(ListRiskConsequenceRepository listRiskConsequenceRepository, ListRiskConsequenceMapper listRiskConsequenceMapper) {
        this.listRiskConsequenceRepository = listRiskConsequenceRepository;
        this.listRiskConsequenceMapper = listRiskConsequenceMapper;
    }

    /**
     * Return a {@link List} of {@link ListRiskConsequenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListRiskConsequenceDTO> findByCriteria(ListRiskConsequenceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListRiskConsequence> specification = createSpecification(criteria);
        return listRiskConsequenceMapper.toDto(listRiskConsequenceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListRiskConsequenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListRiskConsequenceDTO> findByCriteria(ListRiskConsequenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListRiskConsequence> specification = createSpecification(criteria);
        return listRiskConsequenceRepository.findAll(specification, page)
            .map(listRiskConsequenceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListRiskConsequenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListRiskConsequence> specification = createSpecification(criteria);
        return listRiskConsequenceRepository.count(specification);
    }

    /**
     * Function to convert ListRiskConsequenceCriteria to a {@link Specification}.
     */
    private Specification<ListRiskConsequence> createSpecification(ListRiskConsequenceCriteria criteria) {
        Specification<ListRiskConsequence> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListRiskConsequence_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListRiskConsequence_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListRiskConsequence_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListRiskConsequence_.fullName));
            }
            if (criteria.getSafety() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSafety(), ListRiskConsequence_.safety));
            }
            if (criteria.getCommerImpact() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommerImpact(), ListRiskConsequence_.commerImpact));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListRiskConsequence_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListRiskConsequence_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListRiskConsequence_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListRiskConsequence_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListRiskConsequence_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListRiskConsequence_.editor));
            }
        }
        return specification;
    }
}
