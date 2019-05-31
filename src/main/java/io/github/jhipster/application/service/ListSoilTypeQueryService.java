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

import io.github.jhipster.application.domain.ListSoilType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListSoilTypeRepository;
import io.github.jhipster.application.service.dto.ListSoilTypeCriteria;
import io.github.jhipster.application.service.dto.ListSoilTypeDTO;
import io.github.jhipster.application.service.mapper.ListSoilTypeMapper;

/**
 * Service for executing complex queries for {@link ListSoilType} entities in the database.
 * The main input is a {@link ListSoilTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListSoilTypeDTO} or a {@link Page} of {@link ListSoilTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListSoilTypeQueryService extends QueryService<ListSoilType> {

    private final Logger log = LoggerFactory.getLogger(ListSoilTypeQueryService.class);

    private final ListSoilTypeRepository listSoilTypeRepository;

    private final ListSoilTypeMapper listSoilTypeMapper;

    public ListSoilTypeQueryService(ListSoilTypeRepository listSoilTypeRepository, ListSoilTypeMapper listSoilTypeMapper) {
        this.listSoilTypeRepository = listSoilTypeRepository;
        this.listSoilTypeMapper = listSoilTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListSoilTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListSoilTypeDTO> findByCriteria(ListSoilTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListSoilType> specification = createSpecification(criteria);
        return listSoilTypeMapper.toDto(listSoilTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListSoilTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSoilTypeDTO> findByCriteria(ListSoilTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListSoilType> specification = createSpecification(criteria);
        return listSoilTypeRepository.findAll(specification, page)
            .map(listSoilTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListSoilTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListSoilType> specification = createSpecification(criteria);
        return listSoilTypeRepository.count(specification);
    }

    /**
     * Function to convert ListSoilTypeCriteria to a {@link Specification}.
     */
    private Specification<ListSoilType> createSpecification(ListSoilTypeCriteria criteria) {
        Specification<ListSoilType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListSoilType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListSoilType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListSoilType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListSoilType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListSoilType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListSoilType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListSoilType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListSoilType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListSoilType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListSoilType_.editor));
            }
        }
        return specification;
    }
}
