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

import io.github.jhipster.application.domain.ListValveFunction;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListValveFunctionRepository;
import io.github.jhipster.application.service.dto.ListValveFunctionCriteria;
import io.github.jhipster.application.service.dto.ListValveFunctionDTO;
import io.github.jhipster.application.service.mapper.ListValveFunctionMapper;

/**
 * Service for executing complex queries for {@link ListValveFunction} entities in the database.
 * The main input is a {@link ListValveFunctionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListValveFunctionDTO} or a {@link Page} of {@link ListValveFunctionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListValveFunctionQueryService extends QueryService<ListValveFunction> {

    private final Logger log = LoggerFactory.getLogger(ListValveFunctionQueryService.class);

    private final ListValveFunctionRepository listValveFunctionRepository;

    private final ListValveFunctionMapper listValveFunctionMapper;

    public ListValveFunctionQueryService(ListValveFunctionRepository listValveFunctionRepository, ListValveFunctionMapper listValveFunctionMapper) {
        this.listValveFunctionRepository = listValveFunctionRepository;
        this.listValveFunctionMapper = listValveFunctionMapper;
    }

    /**
     * Return a {@link List} of {@link ListValveFunctionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListValveFunctionDTO> findByCriteria(ListValveFunctionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListValveFunction> specification = createSpecification(criteria);
        return listValveFunctionMapper.toDto(listValveFunctionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListValveFunctionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveFunctionDTO> findByCriteria(ListValveFunctionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListValveFunction> specification = createSpecification(criteria);
        return listValveFunctionRepository.findAll(specification, page)
            .map(listValveFunctionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListValveFunctionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListValveFunction> specification = createSpecification(criteria);
        return listValveFunctionRepository.count(specification);
    }

    /**
     * Function to convert ListValveFunctionCriteria to a {@link Specification}.
     */
    private Specification<ListValveFunction> createSpecification(ListValveFunctionCriteria criteria) {
        Specification<ListValveFunction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListValveFunction_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListValveFunction_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListValveFunction_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListValveFunction_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListValveFunction_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListValveFunction_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListValveFunction_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListValveFunction_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListValveFunction_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListValveFunction_.editor));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListValveFunction_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
