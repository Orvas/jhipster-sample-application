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

import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListObjectStatusRepository;
import io.github.jhipster.application.service.dto.ListObjectStatusCriteria;
import io.github.jhipster.application.service.dto.ListObjectStatusDTO;
import io.github.jhipster.application.service.mapper.ListObjectStatusMapper;

/**
 * Service for executing complex queries for {@link ListObjectStatus} entities in the database.
 * The main input is a {@link ListObjectStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListObjectStatusDTO} or a {@link Page} of {@link ListObjectStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListObjectStatusQueryService extends QueryService<ListObjectStatus> {

    private final Logger log = LoggerFactory.getLogger(ListObjectStatusQueryService.class);

    private final ListObjectStatusRepository listObjectStatusRepository;

    private final ListObjectStatusMapper listObjectStatusMapper;

    public ListObjectStatusQueryService(ListObjectStatusRepository listObjectStatusRepository, ListObjectStatusMapper listObjectStatusMapper) {
        this.listObjectStatusRepository = listObjectStatusRepository;
        this.listObjectStatusMapper = listObjectStatusMapper;
    }

    /**
     * Return a {@link List} of {@link ListObjectStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListObjectStatusDTO> findByCriteria(ListObjectStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListObjectStatus> specification = createSpecification(criteria);
        return listObjectStatusMapper.toDto(listObjectStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListObjectStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListObjectStatusDTO> findByCriteria(ListObjectStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListObjectStatus> specification = createSpecification(criteria);
        return listObjectStatusRepository.findAll(specification, page)
            .map(listObjectStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListObjectStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListObjectStatus> specification = createSpecification(criteria);
        return listObjectStatusRepository.count(specification);
    }

    /**
     * Function to convert ListObjectStatusCriteria to a {@link Specification}.
     */
    private Specification<ListObjectStatus> createSpecification(ListObjectStatusCriteria criteria) {
        Specification<ListObjectStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListObjectStatus_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListObjectStatus_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListObjectStatus_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListObjectStatus_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListObjectStatus_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListObjectStatus_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListObjectStatus_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListObjectStatus_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListObjectStatus_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListObjectStatus_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListObjectStatus_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getCpsHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsHistId(),
                    root -> root.join(ListObjectStatus_.cpsHists, JoinType.LEFT).get(CpsHist_.id)));
            }
            if (criteria.getFreeSpanHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanHistId(),
                    root -> root.join(ListObjectStatus_.freeSpanHists, JoinType.LEFT).get(FreeSpanHist_.id)));
            }
            if (criteria.getFreeSpanSupportHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanSupportHistId(),
                    root -> root.join(ListObjectStatus_.freeSpanSupportHists, JoinType.LEFT).get(FreeSpanSupportHist_.id)));
            }
            if (criteria.getLaunchReceiverHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getLaunchReceiverHistId(),
                    root -> root.join(ListObjectStatus_.launchReceiverHists, JoinType.LEFT).get(LaunchReceiverHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListObjectStatus_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(ListObjectStatus_.pipejointHists, JoinType.LEFT).get(PipejointHist_.id)));
            }
            if (criteria.getPipelineHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineHistId(),
                    root -> root.join(ListObjectStatus_.pipelineHists, JoinType.LEFT).get(PipelineHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListObjectStatus_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListObjectStatus_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
