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

import io.github.jhipster.application.domain.ListEnvDirection;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListEnvDirectionRepository;
import io.github.jhipster.application.service.dto.ListEnvDirectionCriteria;
import io.github.jhipster.application.service.dto.ListEnvDirectionDTO;
import io.github.jhipster.application.service.mapper.ListEnvDirectionMapper;

/**
 * Service for executing complex queries for {@link ListEnvDirection} entities in the database.
 * The main input is a {@link ListEnvDirectionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListEnvDirectionDTO} or a {@link Page} of {@link ListEnvDirectionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListEnvDirectionQueryService extends QueryService<ListEnvDirection> {

    private final Logger log = LoggerFactory.getLogger(ListEnvDirectionQueryService.class);

    private final ListEnvDirectionRepository listEnvDirectionRepository;

    private final ListEnvDirectionMapper listEnvDirectionMapper;

    public ListEnvDirectionQueryService(ListEnvDirectionRepository listEnvDirectionRepository, ListEnvDirectionMapper listEnvDirectionMapper) {
        this.listEnvDirectionRepository = listEnvDirectionRepository;
        this.listEnvDirectionMapper = listEnvDirectionMapper;
    }

    /**
     * Return a {@link List} of {@link ListEnvDirectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListEnvDirectionDTO> findByCriteria(ListEnvDirectionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListEnvDirection> specification = createSpecification(criteria);
        return listEnvDirectionMapper.toDto(listEnvDirectionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListEnvDirectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEnvDirectionDTO> findByCriteria(ListEnvDirectionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListEnvDirection> specification = createSpecification(criteria);
        return listEnvDirectionRepository.findAll(specification, page)
            .map(listEnvDirectionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListEnvDirectionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListEnvDirection> specification = createSpecification(criteria);
        return listEnvDirectionRepository.count(specification);
    }

    /**
     * Function to convert ListEnvDirectionCriteria to a {@link Specification}.
     */
    private Specification<ListEnvDirection> createSpecification(ListEnvDirectionCriteria criteria) {
        Specification<ListEnvDirection> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListEnvDirection_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListEnvDirection_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListEnvDirection_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListEnvDirection_.fullName));
            }
            if (criteria.getDegreeStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDegreeStart(), ListEnvDirection_.degreeStart));
            }
            if (criteria.getDegreeEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDegreeEnd(), ListEnvDirection_.degreeEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListEnvDirection_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListEnvDirection_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListEnvDirection_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListEnvDirection_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListEnvDirection_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListEnvDirection_.editor));
            }
        }
        return specification;
    }
}
