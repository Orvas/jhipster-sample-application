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

import io.github.jhipster.application.domain.BuckleArrestor;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BuckleArrestorRepository;
import io.github.jhipster.application.service.dto.BuckleArrestorCriteria;
import io.github.jhipster.application.service.dto.BuckleArrestorDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorMapper;

/**
 * Service for executing complex queries for {@link BuckleArrestor} entities in the database.
 * The main input is a {@link BuckleArrestorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BuckleArrestorDTO} or a {@link Page} of {@link BuckleArrestorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BuckleArrestorQueryService extends QueryService<BuckleArrestor> {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorQueryService.class);

    private final BuckleArrestorRepository buckleArrestorRepository;

    private final BuckleArrestorMapper buckleArrestorMapper;

    public BuckleArrestorQueryService(BuckleArrestorRepository buckleArrestorRepository, BuckleArrestorMapper buckleArrestorMapper) {
        this.buckleArrestorRepository = buckleArrestorRepository;
        this.buckleArrestorMapper = buckleArrestorMapper;
    }

    /**
     * Return a {@link List} of {@link BuckleArrestorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BuckleArrestorDTO> findByCriteria(BuckleArrestorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BuckleArrestor> specification = createSpecification(criteria);
        return buckleArrestorMapper.toDto(buckleArrestorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BuckleArrestorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BuckleArrestorDTO> findByCriteria(BuckleArrestorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BuckleArrestor> specification = createSpecification(criteria);
        return buckleArrestorRepository.findAll(specification, page)
            .map(buckleArrestorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BuckleArrestorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BuckleArrestor> specification = createSpecification(criteria);
        return buckleArrestorRepository.count(specification);
    }

    /**
     * Function to convert BuckleArrestorCriteria to a {@link Specification}.
     */
    private Specification<BuckleArrestor> createSpecification(BuckleArrestorCriteria criteria) {
        Specification<BuckleArrestor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BuckleArrestor_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), BuckleArrestor_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), BuckleArrestor_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), BuckleArrestor_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), BuckleArrestor_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(BuckleArrestor_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(BuckleArrestor_.buckleArrestorHist, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
        }
        return specification;
    }
}
