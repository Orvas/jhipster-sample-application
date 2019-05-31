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

import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FreeSpanHistRepository;
import io.github.jhipster.application.service.dto.FreeSpanHistCriteria;
import io.github.jhipster.application.service.dto.FreeSpanHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanHistMapper;

/**
 * Service for executing complex queries for {@link FreeSpanHist} entities in the database.
 * The main input is a {@link FreeSpanHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FreeSpanHistDTO} or a {@link Page} of {@link FreeSpanHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FreeSpanHistQueryService extends QueryService<FreeSpanHist> {

    private final Logger log = LoggerFactory.getLogger(FreeSpanHistQueryService.class);

    private final FreeSpanHistRepository freeSpanHistRepository;

    private final FreeSpanHistMapper freeSpanHistMapper;

    public FreeSpanHistQueryService(FreeSpanHistRepository freeSpanHistRepository, FreeSpanHistMapper freeSpanHistMapper) {
        this.freeSpanHistRepository = freeSpanHistRepository;
        this.freeSpanHistMapper = freeSpanHistMapper;
    }

    /**
     * Return a {@link List} of {@link FreeSpanHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FreeSpanHistDTO> findByCriteria(FreeSpanHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FreeSpanHist> specification = createSpecification(criteria);
        return freeSpanHistMapper.toDto(freeSpanHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FreeSpanHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanHistDTO> findByCriteria(FreeSpanHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FreeSpanHist> specification = createSpecification(criteria);
        return freeSpanHistRepository.findAll(specification, page)
            .map(freeSpanHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FreeSpanHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FreeSpanHist> specification = createSpecification(criteria);
        return freeSpanHistRepository.count(specification);
    }

    /**
     * Function to convert FreeSpanHistCriteria to a {@link Specification}.
     */
    private Specification<FreeSpanHist> createSpecification(FreeSpanHistCriteria criteria) {
        Specification<FreeSpanHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FreeSpanHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), FreeSpanHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), FreeSpanHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), FreeSpanHist_.idWrk));
            }
            if (criteria.getLenght() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLenght(), FreeSpanHist_.lenght));
            }
            if (criteria.getLenghtAllow() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLenghtAllow(), FreeSpanHist_.lenghtAllow));
            }
            if (criteria.getHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeight(), FreeSpanHist_.height));
            }
            if (criteria.getIsMultispan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsMultispan(), FreeSpanHist_.isMultispan));
            }
            if (criteria.getGap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGap(), FreeSpanHist_.gap));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), FreeSpanHist_.kpStart));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), FreeSpanHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), FreeSpanHist_.isCurrentFlag));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), FreeSpanHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), FreeSpanHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), FreeSpanHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), FreeSpanHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), FreeSpanHist_.editor));
            }
            if (criteria.getFreeSpanId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanId(),
                    root -> root.join(FreeSpanHist_.freeSpan, JoinType.LEFT).get(FreeSpan_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(FreeSpanHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(FreeSpanHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
