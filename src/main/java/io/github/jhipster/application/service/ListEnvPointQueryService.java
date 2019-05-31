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

import io.github.jhipster.application.domain.ListEnvPoint;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListEnvPointRepository;
import io.github.jhipster.application.service.dto.ListEnvPointCriteria;
import io.github.jhipster.application.service.dto.ListEnvPointDTO;
import io.github.jhipster.application.service.mapper.ListEnvPointMapper;

/**
 * Service for executing complex queries for {@link ListEnvPoint} entities in the database.
 * The main input is a {@link ListEnvPointCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListEnvPointDTO} or a {@link Page} of {@link ListEnvPointDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListEnvPointQueryService extends QueryService<ListEnvPoint> {

    private final Logger log = LoggerFactory.getLogger(ListEnvPointQueryService.class);

    private final ListEnvPointRepository listEnvPointRepository;

    private final ListEnvPointMapper listEnvPointMapper;

    public ListEnvPointQueryService(ListEnvPointRepository listEnvPointRepository, ListEnvPointMapper listEnvPointMapper) {
        this.listEnvPointRepository = listEnvPointRepository;
        this.listEnvPointMapper = listEnvPointMapper;
    }

    /**
     * Return a {@link List} of {@link ListEnvPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListEnvPointDTO> findByCriteria(ListEnvPointCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListEnvPoint> specification = createSpecification(criteria);
        return listEnvPointMapper.toDto(listEnvPointRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListEnvPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEnvPointDTO> findByCriteria(ListEnvPointCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListEnvPoint> specification = createSpecification(criteria);
        return listEnvPointRepository.findAll(specification, page)
            .map(listEnvPointMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListEnvPointCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListEnvPoint> specification = createSpecification(criteria);
        return listEnvPointRepository.count(specification);
    }

    /**
     * Function to convert ListEnvPointCriteria to a {@link Specification}.
     */
    private Specification<ListEnvPoint> createSpecification(ListEnvPointCriteria criteria) {
        Specification<ListEnvPoint> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListEnvPoint_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListEnvPoint_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListEnvPoint_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListEnvPoint_.fullName));
            }
            if (criteria.getDegreeStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDegreeStart(), ListEnvPoint_.degreeStart));
            }
            if (criteria.getDegreeEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDegreeEnd(), ListEnvPoint_.degreeEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListEnvPoint_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListEnvPoint_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListEnvPoint_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListEnvPoint_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListEnvPoint_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListEnvPoint_.editor));
            }
        }
        return specification;
    }
}
