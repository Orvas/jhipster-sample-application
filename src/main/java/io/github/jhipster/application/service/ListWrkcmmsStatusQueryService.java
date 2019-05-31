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

import io.github.jhipster.application.domain.ListWrkcmmsStatus;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListWrkcmmsStatusRepository;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusCriteria;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkcmmsStatusMapper;

/**
 * Service for executing complex queries for {@link ListWrkcmmsStatus} entities in the database.
 * The main input is a {@link ListWrkcmmsStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListWrkcmmsStatusDTO} or a {@link Page} of {@link ListWrkcmmsStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListWrkcmmsStatusQueryService extends QueryService<ListWrkcmmsStatus> {

    private final Logger log = LoggerFactory.getLogger(ListWrkcmmsStatusQueryService.class);

    private final ListWrkcmmsStatusRepository listWrkcmmsStatusRepository;

    private final ListWrkcmmsStatusMapper listWrkcmmsStatusMapper;

    public ListWrkcmmsStatusQueryService(ListWrkcmmsStatusRepository listWrkcmmsStatusRepository, ListWrkcmmsStatusMapper listWrkcmmsStatusMapper) {
        this.listWrkcmmsStatusRepository = listWrkcmmsStatusRepository;
        this.listWrkcmmsStatusMapper = listWrkcmmsStatusMapper;
    }

    /**
     * Return a {@link List} of {@link ListWrkcmmsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListWrkcmmsStatusDTO> findByCriteria(ListWrkcmmsStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListWrkcmmsStatus> specification = createSpecification(criteria);
        return listWrkcmmsStatusMapper.toDto(listWrkcmmsStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListWrkcmmsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkcmmsStatusDTO> findByCriteria(ListWrkcmmsStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListWrkcmmsStatus> specification = createSpecification(criteria);
        return listWrkcmmsStatusRepository.findAll(specification, page)
            .map(listWrkcmmsStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListWrkcmmsStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListWrkcmmsStatus> specification = createSpecification(criteria);
        return listWrkcmmsStatusRepository.count(specification);
    }

    /**
     * Function to convert ListWrkcmmsStatusCriteria to a {@link Specification}.
     */
    private Specification<ListWrkcmmsStatus> createSpecification(ListWrkcmmsStatusCriteria criteria) {
        Specification<ListWrkcmmsStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListWrkcmmsStatus_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListWrkcmmsStatus_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListWrkcmmsStatus_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListWrkcmmsStatus_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListWrkcmmsStatus_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListWrkcmmsStatus_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListWrkcmmsStatus_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListWrkcmmsStatus_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListWrkcmmsStatus_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListWrkcmmsStatus_.editor));
            }
        }
        return specification;
    }
}
