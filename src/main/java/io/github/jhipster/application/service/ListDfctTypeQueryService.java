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

import io.github.jhipster.application.domain.ListDfctType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListDfctTypeRepository;
import io.github.jhipster.application.service.dto.ListDfctTypeCriteria;
import io.github.jhipster.application.service.dto.ListDfctTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctTypeMapper;

/**
 * Service for executing complex queries for {@link ListDfctType} entities in the database.
 * The main input is a {@link ListDfctTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListDfctTypeDTO} or a {@link Page} of {@link ListDfctTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListDfctTypeQueryService extends QueryService<ListDfctType> {

    private final Logger log = LoggerFactory.getLogger(ListDfctTypeQueryService.class);

    private final ListDfctTypeRepository listDfctTypeRepository;

    private final ListDfctTypeMapper listDfctTypeMapper;

    public ListDfctTypeQueryService(ListDfctTypeRepository listDfctTypeRepository, ListDfctTypeMapper listDfctTypeMapper) {
        this.listDfctTypeRepository = listDfctTypeRepository;
        this.listDfctTypeMapper = listDfctTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListDfctTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListDfctTypeDTO> findByCriteria(ListDfctTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListDfctType> specification = createSpecification(criteria);
        return listDfctTypeMapper.toDto(listDfctTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListDfctTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctTypeDTO> findByCriteria(ListDfctTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListDfctType> specification = createSpecification(criteria);
        return listDfctTypeRepository.findAll(specification, page)
            .map(listDfctTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListDfctTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListDfctType> specification = createSpecification(criteria);
        return listDfctTypeRepository.count(specification);
    }

    /**
     * Function to convert ListDfctTypeCriteria to a {@link Specification}.
     */
    private Specification<ListDfctType> createSpecification(ListDfctTypeCriteria criteria) {
        Specification<ListDfctType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListDfctType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListDfctType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListDfctType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListDfctType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListDfctType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListDfctType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListDfctType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListDfctType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListDfctType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListDfctType_.editor));
            }
            if (criteria.getIdGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdGroupId(),
                    root -> root.join(ListDfctType_.idGroup, JoinType.LEFT).get(ListDfctGroup_.id)));
            }
        }
        return specification;
    }
}
