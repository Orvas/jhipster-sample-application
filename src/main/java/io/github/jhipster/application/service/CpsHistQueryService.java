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

import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CpsHistRepository;
import io.github.jhipster.application.service.dto.CpsHistCriteria;
import io.github.jhipster.application.service.dto.CpsHistDTO;
import io.github.jhipster.application.service.mapper.CpsHistMapper;

/**
 * Service for executing complex queries for {@link CpsHist} entities in the database.
 * The main input is a {@link CpsHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CpsHistDTO} or a {@link Page} of {@link CpsHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CpsHistQueryService extends QueryService<CpsHist> {

    private final Logger log = LoggerFactory.getLogger(CpsHistQueryService.class);

    private final CpsHistRepository cpsHistRepository;

    private final CpsHistMapper cpsHistMapper;

    public CpsHistQueryService(CpsHistRepository cpsHistRepository, CpsHistMapper cpsHistMapper) {
        this.cpsHistRepository = cpsHistRepository;
        this.cpsHistMapper = cpsHistMapper;
    }

    /**
     * Return a {@link List} of {@link CpsHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CpsHistDTO> findByCriteria(CpsHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CpsHist> specification = createSpecification(criteria);
        return cpsHistMapper.toDto(cpsHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CpsHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsHistDTO> findByCriteria(CpsHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CpsHist> specification = createSpecification(criteria);
        return cpsHistRepository.findAll(specification, page)
            .map(cpsHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CpsHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CpsHist> specification = createSpecification(criteria);
        return cpsHistRepository.count(specification);
    }

    /**
     * Function to convert CpsHistCriteria to a {@link Specification}.
     */
    private Specification<CpsHist> createSpecification(CpsHistCriteria criteria) {
        Specification<CpsHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CpsHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), CpsHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), CpsHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), CpsHist_.idWrk));
            }
            if (criteria.getCurrent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrent(), CpsHist_.current));
            }
            if (criteria.getPotential() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPotential(), CpsHist_.potential));
            }
            if (criteria.getDowntime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDowntime(), CpsHist_.downtime));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), CpsHist_.coord));
            }
            if (criteria.getKpInst() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpInst(), CpsHist_.kpInst));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), CpsHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CpsHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), CpsHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), CpsHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), CpsHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), CpsHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), CpsHist_.editor));
            }
            if (criteria.getCpsId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsId(),
                    root -> root.join(CpsHist_.cps, JoinType.LEFT).get(Cps_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(CpsHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(CpsHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
