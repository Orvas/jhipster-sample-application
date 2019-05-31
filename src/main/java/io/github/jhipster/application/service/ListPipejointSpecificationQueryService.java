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

import io.github.jhipster.application.domain.ListPipejointSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListPipejointSpecificationRepository;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipejointSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListPipejointSpecification} entities in the database.
 * The main input is a {@link ListPipejointSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListPipejointSpecificationDTO} or a {@link Page} of {@link ListPipejointSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListPipejointSpecificationQueryService extends QueryService<ListPipejointSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListPipejointSpecificationQueryService.class);

    private final ListPipejointSpecificationRepository listPipejointSpecificationRepository;

    private final ListPipejointSpecificationMapper listPipejointSpecificationMapper;

    public ListPipejointSpecificationQueryService(ListPipejointSpecificationRepository listPipejointSpecificationRepository, ListPipejointSpecificationMapper listPipejointSpecificationMapper) {
        this.listPipejointSpecificationRepository = listPipejointSpecificationRepository;
        this.listPipejointSpecificationMapper = listPipejointSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListPipejointSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListPipejointSpecificationDTO> findByCriteria(ListPipejointSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListPipejointSpecification> specification = createSpecification(criteria);
        return listPipejointSpecificationMapper.toDto(listPipejointSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListPipejointSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipejointSpecificationDTO> findByCriteria(ListPipejointSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListPipejointSpecification> specification = createSpecification(criteria);
        return listPipejointSpecificationRepository.findAll(specification, page)
            .map(listPipejointSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListPipejointSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListPipejointSpecification> specification = createSpecification(criteria);
        return listPipejointSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListPipejointSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListPipejointSpecification> createSpecification(ListPipejointSpecificationCriteria criteria) {
        Specification<ListPipejointSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListPipejointSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListPipejointSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListPipejointSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListPipejointSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListPipejointSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListPipejointSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListPipejointSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListPipejointSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListPipejointSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListPipejointSpecification_.editor));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(ListPipejointSpecification_.pipejointHists, JoinType.LEFT).get(PipejointHist_.id)));
            }
        }
        return specification;
    }
}
