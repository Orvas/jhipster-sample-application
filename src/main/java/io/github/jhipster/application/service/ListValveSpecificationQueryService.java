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

import io.github.jhipster.application.domain.ListValveSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListValveSpecificationRepository;
import io.github.jhipster.application.service.dto.ListValveSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListValveSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListValveSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListValveSpecification} entities in the database.
 * The main input is a {@link ListValveSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListValveSpecificationDTO} or a {@link Page} of {@link ListValveSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListValveSpecificationQueryService extends QueryService<ListValveSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListValveSpecificationQueryService.class);

    private final ListValveSpecificationRepository listValveSpecificationRepository;

    private final ListValveSpecificationMapper listValveSpecificationMapper;

    public ListValveSpecificationQueryService(ListValveSpecificationRepository listValveSpecificationRepository, ListValveSpecificationMapper listValveSpecificationMapper) {
        this.listValveSpecificationRepository = listValveSpecificationRepository;
        this.listValveSpecificationMapper = listValveSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListValveSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListValveSpecificationDTO> findByCriteria(ListValveSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListValveSpecification> specification = createSpecification(criteria);
        return listValveSpecificationMapper.toDto(listValveSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListValveSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveSpecificationDTO> findByCriteria(ListValveSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListValveSpecification> specification = createSpecification(criteria);
        return listValveSpecificationRepository.findAll(specification, page)
            .map(listValveSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListValveSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListValveSpecification> specification = createSpecification(criteria);
        return listValveSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListValveSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListValveSpecification> createSpecification(ListValveSpecificationCriteria criteria) {
        Specification<ListValveSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListValveSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListValveSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListValveSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListValveSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListValveSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListValveSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListValveSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListValveSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListValveSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListValveSpecification_.editor));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListValveSpecification_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
