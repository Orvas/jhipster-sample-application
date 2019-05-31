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

import io.github.jhipster.application.domain.ListBendSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBendSpecificationRepository;
import io.github.jhipster.application.service.dto.ListBendSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListBendSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBendSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListBendSpecification} entities in the database.
 * The main input is a {@link ListBendSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBendSpecificationDTO} or a {@link Page} of {@link ListBendSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBendSpecificationQueryService extends QueryService<ListBendSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListBendSpecificationQueryService.class);

    private final ListBendSpecificationRepository listBendSpecificationRepository;

    private final ListBendSpecificationMapper listBendSpecificationMapper;

    public ListBendSpecificationQueryService(ListBendSpecificationRepository listBendSpecificationRepository, ListBendSpecificationMapper listBendSpecificationMapper) {
        this.listBendSpecificationRepository = listBendSpecificationRepository;
        this.listBendSpecificationMapper = listBendSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListBendSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBendSpecificationDTO> findByCriteria(ListBendSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBendSpecification> specification = createSpecification(criteria);
        return listBendSpecificationMapper.toDto(listBendSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBendSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendSpecificationDTO> findByCriteria(ListBendSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBendSpecification> specification = createSpecification(criteria);
        return listBendSpecificationRepository.findAll(specification, page)
            .map(listBendSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBendSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBendSpecification> specification = createSpecification(criteria);
        return listBendSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListBendSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListBendSpecification> createSpecification(ListBendSpecificationCriteria criteria) {
        Specification<ListBendSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBendSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBendSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBendSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBendSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBendSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBendSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBendSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBendSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBendSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBendSpecification_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListBendSpecification_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
        }
        return specification;
    }
}
