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

import io.github.jhipster.application.domain.LaunchReceiver;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.LaunchReceiverRepository;
import io.github.jhipster.application.service.dto.LaunchReceiverCriteria;
import io.github.jhipster.application.service.dto.LaunchReceiverDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverMapper;

/**
 * Service for executing complex queries for {@link LaunchReceiver} entities in the database.
 * The main input is a {@link LaunchReceiverCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LaunchReceiverDTO} or a {@link Page} of {@link LaunchReceiverDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LaunchReceiverQueryService extends QueryService<LaunchReceiver> {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverQueryService.class);

    private final LaunchReceiverRepository launchReceiverRepository;

    private final LaunchReceiverMapper launchReceiverMapper;

    public LaunchReceiverQueryService(LaunchReceiverRepository launchReceiverRepository, LaunchReceiverMapper launchReceiverMapper) {
        this.launchReceiverRepository = launchReceiverRepository;
        this.launchReceiverMapper = launchReceiverMapper;
    }

    /**
     * Return a {@link List} of {@link LaunchReceiverDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LaunchReceiverDTO> findByCriteria(LaunchReceiverCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LaunchReceiver> specification = createSpecification(criteria);
        return launchReceiverMapper.toDto(launchReceiverRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LaunchReceiverDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LaunchReceiverDTO> findByCriteria(LaunchReceiverCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LaunchReceiver> specification = createSpecification(criteria);
        return launchReceiverRepository.findAll(specification, page)
            .map(launchReceiverMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LaunchReceiverCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LaunchReceiver> specification = createSpecification(criteria);
        return launchReceiverRepository.count(specification);
    }

    /**
     * Function to convert LaunchReceiverCriteria to a {@link Specification}.
     */
    private Specification<LaunchReceiver> createSpecification(LaunchReceiverCriteria criteria) {
        Specification<LaunchReceiver> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LaunchReceiver_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), LaunchReceiver_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), LaunchReceiver_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), LaunchReceiver_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), LaunchReceiver_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(LaunchReceiver_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getLaunchReceiverHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getLaunchReceiverHistId(),
                    root -> root.join(LaunchReceiver_.launchReceiverHist, JoinType.LEFT).get(LaunchReceiverHist_.id)));
            }
        }
        return specification;
    }
}
