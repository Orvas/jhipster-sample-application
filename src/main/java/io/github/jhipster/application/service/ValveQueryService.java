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

import io.github.jhipster.application.domain.Valve;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ValveRepository;
import io.github.jhipster.application.service.dto.ValveCriteria;
import io.github.jhipster.application.service.dto.ValveDTO;
import io.github.jhipster.application.service.mapper.ValveMapper;

/**
 * Service for executing complex queries for {@link Valve} entities in the database.
 * The main input is a {@link ValveCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ValveDTO} or a {@link Page} of {@link ValveDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ValveQueryService extends QueryService<Valve> {

    private final Logger log = LoggerFactory.getLogger(ValveQueryService.class);

    private final ValveRepository valveRepository;

    private final ValveMapper valveMapper;

    public ValveQueryService(ValveRepository valveRepository, ValveMapper valveMapper) {
        this.valveRepository = valveRepository;
        this.valveMapper = valveMapper;
    }

    /**
     * Return a {@link List} of {@link ValveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ValveDTO> findByCriteria(ValveCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Valve> specification = createSpecification(criteria);
        return valveMapper.toDto(valveRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ValveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ValveDTO> findByCriteria(ValveCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Valve> specification = createSpecification(criteria);
        return valveRepository.findAll(specification, page)
            .map(valveMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ValveCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Valve> specification = createSpecification(criteria);
        return valveRepository.count(specification);
    }

    /**
     * Function to convert ValveCriteria to a {@link Specification}.
     */
    private Specification<Valve> createSpecification(ValveCriteria criteria) {
        Specification<Valve> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Valve_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Valve_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Valve_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Valve_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Valve_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(Valve_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(Valve_.valveHist, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
