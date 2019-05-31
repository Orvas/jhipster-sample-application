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

import io.github.jhipster.application.domain.ListObjectType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListObjectTypeRepository;
import io.github.jhipster.application.service.dto.ListObjectTypeCriteria;
import io.github.jhipster.application.service.dto.ListObjectTypeDTO;
import io.github.jhipster.application.service.mapper.ListObjectTypeMapper;

/**
 * Service for executing complex queries for {@link ListObjectType} entities in the database.
 * The main input is a {@link ListObjectTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListObjectTypeDTO} or a {@link Page} of {@link ListObjectTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListObjectTypeQueryService extends QueryService<ListObjectType> {

    private final Logger log = LoggerFactory.getLogger(ListObjectTypeQueryService.class);

    private final ListObjectTypeRepository listObjectTypeRepository;

    private final ListObjectTypeMapper listObjectTypeMapper;

    public ListObjectTypeQueryService(ListObjectTypeRepository listObjectTypeRepository, ListObjectTypeMapper listObjectTypeMapper) {
        this.listObjectTypeRepository = listObjectTypeRepository;
        this.listObjectTypeMapper = listObjectTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListObjectTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListObjectTypeDTO> findByCriteria(ListObjectTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListObjectType> specification = createSpecification(criteria);
        return listObjectTypeMapper.toDto(listObjectTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListObjectTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListObjectTypeDTO> findByCriteria(ListObjectTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListObjectType> specification = createSpecification(criteria);
        return listObjectTypeRepository.findAll(specification, page)
            .map(listObjectTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListObjectTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListObjectType> specification = createSpecification(criteria);
        return listObjectTypeRepository.count(specification);
    }

    /**
     * Function to convert ListObjectTypeCriteria to a {@link Specification}.
     */
    private Specification<ListObjectType> createSpecification(ListObjectTypeCriteria criteria) {
        Specification<ListObjectType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListObjectType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListObjectType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListObjectType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListObjectType_.fullName));
            }
            if (criteria.getTableName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTableName(), ListObjectType_.tableName));
            }
            if (criteria.getTableNameHist() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTableNameHist(), ListObjectType_.tableNameHist));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListObjectType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListObjectType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListObjectType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListObjectType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListObjectType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListObjectType_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(ListObjectType_.baseClasses, JoinType.LEFT).get(BaseClass_.id)));
            }
        }
        return specification;
    }
}
