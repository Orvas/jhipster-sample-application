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

import io.github.jhipster.application.domain.ListClayType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListClayTypeRepository;
import io.github.jhipster.application.service.dto.ListClayTypeCriteria;
import io.github.jhipster.application.service.dto.ListClayTypeDTO;
import io.github.jhipster.application.service.mapper.ListClayTypeMapper;

/**
 * Service for executing complex queries for {@link ListClayType} entities in the database.
 * The main input is a {@link ListClayTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListClayTypeDTO} or a {@link Page} of {@link ListClayTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListClayTypeQueryService extends QueryService<ListClayType> {

    private final Logger log = LoggerFactory.getLogger(ListClayTypeQueryService.class);

    private final ListClayTypeRepository listClayTypeRepository;

    private final ListClayTypeMapper listClayTypeMapper;

    public ListClayTypeQueryService(ListClayTypeRepository listClayTypeRepository, ListClayTypeMapper listClayTypeMapper) {
        this.listClayTypeRepository = listClayTypeRepository;
        this.listClayTypeMapper = listClayTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListClayTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListClayTypeDTO> findByCriteria(ListClayTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListClayType> specification = createSpecification(criteria);
        return listClayTypeMapper.toDto(listClayTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListClayTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClayTypeDTO> findByCriteria(ListClayTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListClayType> specification = createSpecification(criteria);
        return listClayTypeRepository.findAll(specification, page)
            .map(listClayTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListClayTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListClayType> specification = createSpecification(criteria);
        return listClayTypeRepository.count(specification);
    }

    /**
     * Function to convert ListClayTypeCriteria to a {@link Specification}.
     */
    private Specification<ListClayType> createSpecification(ListClayTypeCriteria criteria) {
        Specification<ListClayType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListClayType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListClayType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListClayType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListClayType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListClayType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListClayType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListClayType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListClayType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListClayType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListClayType_.editor));
            }
        }
        return specification;
    }
}
