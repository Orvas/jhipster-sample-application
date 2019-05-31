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

import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FreeSpanSupportHistRepository;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistCriteria;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportHistMapper;

/**
 * Service for executing complex queries for {@link FreeSpanSupportHist} entities in the database.
 * The main input is a {@link FreeSpanSupportHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FreeSpanSupportHistDTO} or a {@link Page} of {@link FreeSpanSupportHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FreeSpanSupportHistQueryService extends QueryService<FreeSpanSupportHist> {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportHistQueryService.class);

    private final FreeSpanSupportHistRepository freeSpanSupportHistRepository;

    private final FreeSpanSupportHistMapper freeSpanSupportHistMapper;

    public FreeSpanSupportHistQueryService(FreeSpanSupportHistRepository freeSpanSupportHistRepository, FreeSpanSupportHistMapper freeSpanSupportHistMapper) {
        this.freeSpanSupportHistRepository = freeSpanSupportHistRepository;
        this.freeSpanSupportHistMapper = freeSpanSupportHistMapper;
    }

    /**
     * Return a {@link List} of {@link FreeSpanSupportHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FreeSpanSupportHistDTO> findByCriteria(FreeSpanSupportHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FreeSpanSupportHist> specification = createSpecification(criteria);
        return freeSpanSupportHistMapper.toDto(freeSpanSupportHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FreeSpanSupportHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanSupportHistDTO> findByCriteria(FreeSpanSupportHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FreeSpanSupportHist> specification = createSpecification(criteria);
        return freeSpanSupportHistRepository.findAll(specification, page)
            .map(freeSpanSupportHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FreeSpanSupportHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FreeSpanSupportHist> specification = createSpecification(criteria);
        return freeSpanSupportHistRepository.count(specification);
    }

    /**
     * Function to convert FreeSpanSupportHistCriteria to a {@link Specification}.
     */
    private Specification<FreeSpanSupportHist> createSpecification(FreeSpanSupportHistCriteria criteria) {
        Specification<FreeSpanSupportHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FreeSpanSupportHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), FreeSpanSupportHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), FreeSpanSupportHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), FreeSpanSupportHist_.idWrk));
            }
            if (criteria.getHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeight(), FreeSpanSupportHist_.height));
            }
            if (criteria.getKpInst() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpInst(), FreeSpanSupportHist_.kpInst));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), FreeSpanSupportHist_.isCurrentFlag));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), FreeSpanSupportHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), FreeSpanSupportHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), FreeSpanSupportHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), FreeSpanSupportHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), FreeSpanSupportHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(FreeSpanSupportHist_.id, JoinType.LEFT).get(FreeSpanSupport_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(FreeSpanSupportHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(FreeSpanSupportHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
