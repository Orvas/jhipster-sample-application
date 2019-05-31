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

import io.github.jhipster.application.domain.Tee;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.TeeRepository;
import io.github.jhipster.application.service.dto.TeeCriteria;
import io.github.jhipster.application.service.dto.TeeDTO;
import io.github.jhipster.application.service.mapper.TeeMapper;

/**
 * Service for executing complex queries for {@link Tee} entities in the database.
 * The main input is a {@link TeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeeDTO} or a {@link Page} of {@link TeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeeQueryService extends QueryService<Tee> {

    private final Logger log = LoggerFactory.getLogger(TeeQueryService.class);

    private final TeeRepository teeRepository;

    private final TeeMapper teeMapper;

    public TeeQueryService(TeeRepository teeRepository, TeeMapper teeMapper) {
        this.teeRepository = teeRepository;
        this.teeMapper = teeMapper;
    }

    /**
     * Return a {@link List} of {@link TeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeeDTO> findByCriteria(TeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Tee> specification = createSpecification(criteria);
        return teeMapper.toDto(teeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeeDTO> findByCriteria(TeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Tee> specification = createSpecification(criteria);
        return teeRepository.findAll(specification, page)
            .map(teeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Tee> specification = createSpecification(criteria);
        return teeRepository.count(specification);
    }

    /**
     * Function to convert TeeCriteria to a {@link Specification}.
     */
    private Specification<Tee> createSpecification(TeeCriteria criteria) {
        Specification<Tee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Tee_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Tee_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Tee_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Tee_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Tee_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(Tee_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(Tee_.teeHist, JoinType.LEFT).get(TeeHist_.id)));
            }
        }
        return specification;
    }
}
