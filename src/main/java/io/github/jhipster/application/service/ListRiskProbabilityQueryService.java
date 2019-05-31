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

import io.github.jhipster.application.domain.ListRiskProbability;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListRiskProbabilityRepository;
import io.github.jhipster.application.service.dto.ListRiskProbabilityCriteria;
import io.github.jhipster.application.service.dto.ListRiskProbabilityDTO;
import io.github.jhipster.application.service.mapper.ListRiskProbabilityMapper;

/**
 * Service for executing complex queries for {@link ListRiskProbability} entities in the database.
 * The main input is a {@link ListRiskProbabilityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListRiskProbabilityDTO} or a {@link Page} of {@link ListRiskProbabilityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListRiskProbabilityQueryService extends QueryService<ListRiskProbability> {

    private final Logger log = LoggerFactory.getLogger(ListRiskProbabilityQueryService.class);

    private final ListRiskProbabilityRepository listRiskProbabilityRepository;

    private final ListRiskProbabilityMapper listRiskProbabilityMapper;

    public ListRiskProbabilityQueryService(ListRiskProbabilityRepository listRiskProbabilityRepository, ListRiskProbabilityMapper listRiskProbabilityMapper) {
        this.listRiskProbabilityRepository = listRiskProbabilityRepository;
        this.listRiskProbabilityMapper = listRiskProbabilityMapper;
    }

    /**
     * Return a {@link List} of {@link ListRiskProbabilityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListRiskProbabilityDTO> findByCriteria(ListRiskProbabilityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListRiskProbability> specification = createSpecification(criteria);
        return listRiskProbabilityMapper.toDto(listRiskProbabilityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListRiskProbabilityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListRiskProbabilityDTO> findByCriteria(ListRiskProbabilityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListRiskProbability> specification = createSpecification(criteria);
        return listRiskProbabilityRepository.findAll(specification, page)
            .map(listRiskProbabilityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListRiskProbabilityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListRiskProbability> specification = createSpecification(criteria);
        return listRiskProbabilityRepository.count(specification);
    }

    /**
     * Function to convert ListRiskProbabilityCriteria to a {@link Specification}.
     */
    private Specification<ListRiskProbability> createSpecification(ListRiskProbabilityCriteria criteria) {
        Specification<ListRiskProbability> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListRiskProbability_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListRiskProbability_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListRiskProbability_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListRiskProbability_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListRiskProbability_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListRiskProbability_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListRiskProbability_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListRiskProbability_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListRiskProbability_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListRiskProbability_.editor));
            }
        }
        return specification;
    }
}
