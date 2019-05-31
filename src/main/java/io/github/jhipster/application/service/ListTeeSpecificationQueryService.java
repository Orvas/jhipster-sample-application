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

import io.github.jhipster.application.domain.ListTeeSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListTeeSpecificationRepository;
import io.github.jhipster.application.service.dto.ListTeeSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListTeeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListTeeSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListTeeSpecification} entities in the database.
 * The main input is a {@link ListTeeSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListTeeSpecificationDTO} or a {@link Page} of {@link ListTeeSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListTeeSpecificationQueryService extends QueryService<ListTeeSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListTeeSpecificationQueryService.class);

    private final ListTeeSpecificationRepository listTeeSpecificationRepository;

    private final ListTeeSpecificationMapper listTeeSpecificationMapper;

    public ListTeeSpecificationQueryService(ListTeeSpecificationRepository listTeeSpecificationRepository, ListTeeSpecificationMapper listTeeSpecificationMapper) {
        this.listTeeSpecificationRepository = listTeeSpecificationRepository;
        this.listTeeSpecificationMapper = listTeeSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListTeeSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListTeeSpecificationDTO> findByCriteria(ListTeeSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListTeeSpecification> specification = createSpecification(criteria);
        return listTeeSpecificationMapper.toDto(listTeeSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListTeeSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeSpecificationDTO> findByCriteria(ListTeeSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListTeeSpecification> specification = createSpecification(criteria);
        return listTeeSpecificationRepository.findAll(specification, page)
            .map(listTeeSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListTeeSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListTeeSpecification> specification = createSpecification(criteria);
        return listTeeSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListTeeSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListTeeSpecification> createSpecification(ListTeeSpecificationCriteria criteria) {
        Specification<ListTeeSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListTeeSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListTeeSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListTeeSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListTeeSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListTeeSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListTeeSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListTeeSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListTeeSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListTeeSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListTeeSpecification_.editor));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListTeeSpecification_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
        }
        return specification;
    }
}
