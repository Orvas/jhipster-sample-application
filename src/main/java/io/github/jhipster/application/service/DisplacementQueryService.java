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

import io.github.jhipster.application.domain.Displacement;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.DisplacementRepository;
import io.github.jhipster.application.service.dto.DisplacementCriteria;
import io.github.jhipster.application.service.dto.DisplacementDTO;
import io.github.jhipster.application.service.mapper.DisplacementMapper;

/**
 * Service for executing complex queries for {@link Displacement} entities in the database.
 * The main input is a {@link DisplacementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DisplacementDTO} or a {@link Page} of {@link DisplacementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DisplacementQueryService extends QueryService<Displacement> {

    private final Logger log = LoggerFactory.getLogger(DisplacementQueryService.class);

    private final DisplacementRepository displacementRepository;

    private final DisplacementMapper displacementMapper;

    public DisplacementQueryService(DisplacementRepository displacementRepository, DisplacementMapper displacementMapper) {
        this.displacementRepository = displacementRepository;
        this.displacementMapper = displacementMapper;
    }

    /**
     * Return a {@link List} of {@link DisplacementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DisplacementDTO> findByCriteria(DisplacementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Displacement> specification = createSpecification(criteria);
        return displacementMapper.toDto(displacementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DisplacementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DisplacementDTO> findByCriteria(DisplacementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Displacement> specification = createSpecification(criteria);
        return displacementRepository.findAll(specification, page)
            .map(displacementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DisplacementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Displacement> specification = createSpecification(criteria);
        return displacementRepository.count(specification);
    }

    /**
     * Function to convert DisplacementCriteria to a {@link Specification}.
     */
    private Specification<Displacement> createSpecification(DisplacementCriteria criteria) {
        Specification<Displacement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Displacement_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Displacement_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Displacement_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Displacement_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Displacement_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(Displacement_.id, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getDisplacementHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getDisplacementHistId(),
                    root -> root.join(Displacement_.displacementHists, JoinType.LEFT).get(DisplacementHist_.id)));
            }
        }
        return specification;
    }
}
