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

import io.github.jhipster.application.domain.ListWrkStatus;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListWrkStatusRepository;
import io.github.jhipster.application.service.dto.ListWrkStatusCriteria;
import io.github.jhipster.application.service.dto.ListWrkStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkStatusMapper;

/**
 * Service for executing complex queries for {@link ListWrkStatus} entities in the database.
 * The main input is a {@link ListWrkStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListWrkStatusDTO} or a {@link Page} of {@link ListWrkStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListWrkStatusQueryService extends QueryService<ListWrkStatus> {

    private final Logger log = LoggerFactory.getLogger(ListWrkStatusQueryService.class);

    private final ListWrkStatusRepository listWrkStatusRepository;

    private final ListWrkStatusMapper listWrkStatusMapper;

    public ListWrkStatusQueryService(ListWrkStatusRepository listWrkStatusRepository, ListWrkStatusMapper listWrkStatusMapper) {
        this.listWrkStatusRepository = listWrkStatusRepository;
        this.listWrkStatusMapper = listWrkStatusMapper;
    }

    /**
     * Return a {@link List} of {@link ListWrkStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListWrkStatusDTO> findByCriteria(ListWrkStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListWrkStatus> specification = createSpecification(criteria);
        return listWrkStatusMapper.toDto(listWrkStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListWrkStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkStatusDTO> findByCriteria(ListWrkStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListWrkStatus> specification = createSpecification(criteria);
        return listWrkStatusRepository.findAll(specification, page)
            .map(listWrkStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListWrkStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListWrkStatus> specification = createSpecification(criteria);
        return listWrkStatusRepository.count(specification);
    }

    /**
     * Function to convert ListWrkStatusCriteria to a {@link Specification}.
     */
    private Specification<ListWrkStatus> createSpecification(ListWrkStatusCriteria criteria) {
        Specification<ListWrkStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListWrkStatus_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListWrkStatus_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListWrkStatus_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListWrkStatus_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListWrkStatus_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListWrkStatus_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListWrkStatus_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListWrkStatus_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListWrkStatus_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListWrkStatus_.editor));
            }
            if (criteria.getIdWrkKindId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdWrkKindId(),
                    root -> root.join(ListWrkStatus_.idWrkKind, JoinType.LEFT).get(ListWrkKind_.id)));
            }
            if (criteria.getAnodeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeHistId(),
                    root -> root.join(ListWrkStatus_.anodeHists, JoinType.LEFT).get(AnodeHist_.id)));
            }
        }
        return specification;
    }
}
