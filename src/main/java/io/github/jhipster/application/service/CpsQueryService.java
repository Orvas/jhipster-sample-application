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

import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CpsRepository;
import io.github.jhipster.application.service.dto.CpsCriteria;
import io.github.jhipster.application.service.dto.CpsDTO;
import io.github.jhipster.application.service.mapper.CpsMapper;

/**
 * Service for executing complex queries for {@link Cps} entities in the database.
 * The main input is a {@link CpsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CpsDTO} or a {@link Page} of {@link CpsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CpsQueryService extends QueryService<Cps> {

    private final Logger log = LoggerFactory.getLogger(CpsQueryService.class);

    private final CpsRepository cpsRepository;

    private final CpsMapper cpsMapper;

    public CpsQueryService(CpsRepository cpsRepository, CpsMapper cpsMapper) {
        this.cpsRepository = cpsRepository;
        this.cpsMapper = cpsMapper;
    }

    /**
     * Return a {@link List} of {@link CpsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CpsDTO> findByCriteria(CpsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cps> specification = createSpecification(criteria);
        return cpsMapper.toDto(cpsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CpsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsDTO> findByCriteria(CpsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cps> specification = createSpecification(criteria);
        return cpsRepository.findAll(specification, page)
            .map(cpsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CpsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cps> specification = createSpecification(criteria);
        return cpsRepository.count(specification);
    }

    /**
     * Function to convert CpsCriteria to a {@link Specification}.
     */
    private Specification<Cps> createSpecification(CpsCriteria criteria) {
        Specification<Cps> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Cps_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Cps_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Cps_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Cps_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Cps_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(Cps_.id, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getCpsHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsHistId(),
                    root -> root.join(Cps_.cpsHists, JoinType.LEFT).get(CpsHist_.id)));
            }
            if (criteria.getCpsRangeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsRangeId(),
                    root -> root.join(Cps_.cpsRanges, JoinType.LEFT).get(CpsRange_.id)));
            }
        }
        return specification;
    }
}
