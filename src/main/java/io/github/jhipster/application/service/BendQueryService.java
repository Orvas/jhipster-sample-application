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

import io.github.jhipster.application.domain.Bend;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BendRepository;
import io.github.jhipster.application.service.dto.BendCriteria;
import io.github.jhipster.application.service.dto.BendDTO;
import io.github.jhipster.application.service.mapper.BendMapper;

/**
 * Service for executing complex queries for {@link Bend} entities in the database.
 * The main input is a {@link BendCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BendDTO} or a {@link Page} of {@link BendDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BendQueryService extends QueryService<Bend> {

    private final Logger log = LoggerFactory.getLogger(BendQueryService.class);

    private final BendRepository bendRepository;

    private final BendMapper bendMapper;

    public BendQueryService(BendRepository bendRepository, BendMapper bendMapper) {
        this.bendRepository = bendRepository;
        this.bendMapper = bendMapper;
    }

    /**
     * Return a {@link List} of {@link BendDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BendDTO> findByCriteria(BendCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Bend> specification = createSpecification(criteria);
        return bendMapper.toDto(bendRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BendDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BendDTO> findByCriteria(BendCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Bend> specification = createSpecification(criteria);
        return bendRepository.findAll(specification, page)
            .map(bendMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BendCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Bend> specification = createSpecification(criteria);
        return bendRepository.count(specification);
    }

    /**
     * Function to convert BendCriteria to a {@link Specification}.
     */
    private Specification<Bend> createSpecification(BendCriteria criteria) {
        Specification<Bend> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Bend_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Bend_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Bend_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Bend_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Bend_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(Bend_.id, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(Bend_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
        }
        return specification;
    }
}
