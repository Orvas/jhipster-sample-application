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

import io.github.jhipster.application.domain.ListBucklarrSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBucklarrSpecificationRepository;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListBucklarrSpecification} entities in the database.
 * The main input is a {@link ListBucklarrSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBucklarrSpecificationDTO} or a {@link Page} of {@link ListBucklarrSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBucklarrSpecificationQueryService extends QueryService<ListBucklarrSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrSpecificationQueryService.class);

    private final ListBucklarrSpecificationRepository listBucklarrSpecificationRepository;

    private final ListBucklarrSpecificationMapper listBucklarrSpecificationMapper;

    public ListBucklarrSpecificationQueryService(ListBucklarrSpecificationRepository listBucklarrSpecificationRepository, ListBucklarrSpecificationMapper listBucklarrSpecificationMapper) {
        this.listBucklarrSpecificationRepository = listBucklarrSpecificationRepository;
        this.listBucklarrSpecificationMapper = listBucklarrSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListBucklarrSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBucklarrSpecificationDTO> findByCriteria(ListBucklarrSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBucklarrSpecification> specification = createSpecification(criteria);
        return listBucklarrSpecificationMapper.toDto(listBucklarrSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBucklarrSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrSpecificationDTO> findByCriteria(ListBucklarrSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBucklarrSpecification> specification = createSpecification(criteria);
        return listBucklarrSpecificationRepository.findAll(specification, page)
            .map(listBucklarrSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBucklarrSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBucklarrSpecification> specification = createSpecification(criteria);
        return listBucklarrSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListBucklarrSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListBucklarrSpecification> createSpecification(ListBucklarrSpecificationCriteria criteria) {
        Specification<ListBucklarrSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBucklarrSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBucklarrSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBucklarrSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBucklarrSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBucklarrSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBucklarrSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBucklarrSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBucklarrSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBucklarrSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBucklarrSpecification_.editor));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListBucklarrSpecification_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
        }
        return specification;
    }
}
