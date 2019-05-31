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

import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.LaunchReceiverHistRepository;
import io.github.jhipster.application.service.dto.LaunchReceiverHistCriteria;
import io.github.jhipster.application.service.dto.LaunchReceiverHistDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverHistMapper;

/**
 * Service for executing complex queries for {@link LaunchReceiverHist} entities in the database.
 * The main input is a {@link LaunchReceiverHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LaunchReceiverHistDTO} or a {@link Page} of {@link LaunchReceiverHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LaunchReceiverHistQueryService extends QueryService<LaunchReceiverHist> {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverHistQueryService.class);

    private final LaunchReceiverHistRepository launchReceiverHistRepository;

    private final LaunchReceiverHistMapper launchReceiverHistMapper;

    public LaunchReceiverHistQueryService(LaunchReceiverHistRepository launchReceiverHistRepository, LaunchReceiverHistMapper launchReceiverHistMapper) {
        this.launchReceiverHistRepository = launchReceiverHistRepository;
        this.launchReceiverHistMapper = launchReceiverHistMapper;
    }

    /**
     * Return a {@link List} of {@link LaunchReceiverHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LaunchReceiverHistDTO> findByCriteria(LaunchReceiverHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LaunchReceiverHist> specification = createSpecification(criteria);
        return launchReceiverHistMapper.toDto(launchReceiverHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LaunchReceiverHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LaunchReceiverHistDTO> findByCriteria(LaunchReceiverHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LaunchReceiverHist> specification = createSpecification(criteria);
        return launchReceiverHistRepository.findAll(specification, page)
            .map(launchReceiverHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LaunchReceiverHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LaunchReceiverHist> specification = createSpecification(criteria);
        return launchReceiverHistRepository.count(specification);
    }

    /**
     * Function to convert LaunchReceiverHistCriteria to a {@link Specification}.
     */
    private Specification<LaunchReceiverHist> createSpecification(LaunchReceiverHistCriteria criteria) {
        Specification<LaunchReceiverHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LaunchReceiverHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), LaunchReceiverHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), LaunchReceiverHist_.dateTo));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), LaunchReceiverHist_.name));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), LaunchReceiverHist_.coord));
            }
            if (criteria.getKpInst() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpInst(), LaunchReceiverHist_.kpInst));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), LaunchReceiverHist_.isCurrentFlag));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), LaunchReceiverHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), LaunchReceiverHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), LaunchReceiverHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), LaunchReceiverHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), LaunchReceiverHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(LaunchReceiverHist_.id, JoinType.LEFT).get(LaunchReceiver_.id)));
            }
            if (criteria.getIdPipelineId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineId(),
                    root -> root.join(LaunchReceiverHist_.idPipeline, JoinType.LEFT).get(Pipeline_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(LaunchReceiverHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
