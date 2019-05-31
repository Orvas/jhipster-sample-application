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

import io.github.jhipster.application.domain.ListValveType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListValveTypeRepository;
import io.github.jhipster.application.service.dto.ListValveTypeCriteria;
import io.github.jhipster.application.service.dto.ListValveTypeDTO;
import io.github.jhipster.application.service.mapper.ListValveTypeMapper;

/**
 * Service for executing complex queries for {@link ListValveType} entities in the database.
 * The main input is a {@link ListValveTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListValveTypeDTO} or a {@link Page} of {@link ListValveTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListValveTypeQueryService extends QueryService<ListValveType> {

    private final Logger log = LoggerFactory.getLogger(ListValveTypeQueryService.class);

    private final ListValveTypeRepository listValveTypeRepository;

    private final ListValveTypeMapper listValveTypeMapper;

    public ListValveTypeQueryService(ListValveTypeRepository listValveTypeRepository, ListValveTypeMapper listValveTypeMapper) {
        this.listValveTypeRepository = listValveTypeRepository;
        this.listValveTypeMapper = listValveTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListValveTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListValveTypeDTO> findByCriteria(ListValveTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListValveType> specification = createSpecification(criteria);
        return listValveTypeMapper.toDto(listValveTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListValveTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveTypeDTO> findByCriteria(ListValveTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListValveType> specification = createSpecification(criteria);
        return listValveTypeRepository.findAll(specification, page)
            .map(listValveTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListValveTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListValveType> specification = createSpecification(criteria);
        return listValveTypeRepository.count(specification);
    }

    /**
     * Function to convert ListValveTypeCriteria to a {@link Specification}.
     */
    private Specification<ListValveType> createSpecification(ListValveTypeCriteria criteria) {
        Specification<ListValveType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListValveType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListValveType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListValveType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListValveType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListValveType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListValveType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListValveType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListValveType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListValveType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListValveType_.editor));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListValveType_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
