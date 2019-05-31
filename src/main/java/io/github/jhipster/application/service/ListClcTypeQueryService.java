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

import io.github.jhipster.application.domain.ListClcType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListClcTypeRepository;
import io.github.jhipster.application.service.dto.ListClcTypeCriteria;
import io.github.jhipster.application.service.dto.ListClcTypeDTO;
import io.github.jhipster.application.service.mapper.ListClcTypeMapper;

/**
 * Service for executing complex queries for {@link ListClcType} entities in the database.
 * The main input is a {@link ListClcTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListClcTypeDTO} or a {@link Page} of {@link ListClcTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListClcTypeQueryService extends QueryService<ListClcType> {

    private final Logger log = LoggerFactory.getLogger(ListClcTypeQueryService.class);

    private final ListClcTypeRepository listClcTypeRepository;

    private final ListClcTypeMapper listClcTypeMapper;

    public ListClcTypeQueryService(ListClcTypeRepository listClcTypeRepository, ListClcTypeMapper listClcTypeMapper) {
        this.listClcTypeRepository = listClcTypeRepository;
        this.listClcTypeMapper = listClcTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListClcTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListClcTypeDTO> findByCriteria(ListClcTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListClcType> specification = createSpecification(criteria);
        return listClcTypeMapper.toDto(listClcTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListClcTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcTypeDTO> findByCriteria(ListClcTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListClcType> specification = createSpecification(criteria);
        return listClcTypeRepository.findAll(specification, page)
            .map(listClcTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListClcTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListClcType> specification = createSpecification(criteria);
        return listClcTypeRepository.count(specification);
    }

    /**
     * Function to convert ListClcTypeCriteria to a {@link Specification}.
     */
    private Specification<ListClcType> createSpecification(ListClcTypeCriteria criteria) {
        Specification<ListClcType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListClcType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListClcType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListClcType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListClcType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListClcType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListClcType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListClcType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListClcType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListClcType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListClcType_.editor));
            }
        }
        return specification;
    }
}
