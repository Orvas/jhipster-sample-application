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

import io.github.jhipster.application.domain.ListDfctPosType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListDfctPosTypeRepository;
import io.github.jhipster.application.service.dto.ListDfctPosTypeCriteria;
import io.github.jhipster.application.service.dto.ListDfctPosTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctPosTypeMapper;

/**
 * Service for executing complex queries for {@link ListDfctPosType} entities in the database.
 * The main input is a {@link ListDfctPosTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListDfctPosTypeDTO} or a {@link Page} of {@link ListDfctPosTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListDfctPosTypeQueryService extends QueryService<ListDfctPosType> {

    private final Logger log = LoggerFactory.getLogger(ListDfctPosTypeQueryService.class);

    private final ListDfctPosTypeRepository listDfctPosTypeRepository;

    private final ListDfctPosTypeMapper listDfctPosTypeMapper;

    public ListDfctPosTypeQueryService(ListDfctPosTypeRepository listDfctPosTypeRepository, ListDfctPosTypeMapper listDfctPosTypeMapper) {
        this.listDfctPosTypeRepository = listDfctPosTypeRepository;
        this.listDfctPosTypeMapper = listDfctPosTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListDfctPosTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListDfctPosTypeDTO> findByCriteria(ListDfctPosTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListDfctPosType> specification = createSpecification(criteria);
        return listDfctPosTypeMapper.toDto(listDfctPosTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListDfctPosTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctPosTypeDTO> findByCriteria(ListDfctPosTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListDfctPosType> specification = createSpecification(criteria);
        return listDfctPosTypeRepository.findAll(specification, page)
            .map(listDfctPosTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListDfctPosTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListDfctPosType> specification = createSpecification(criteria);
        return listDfctPosTypeRepository.count(specification);
    }

    /**
     * Function to convert ListDfctPosTypeCriteria to a {@link Specification}.
     */
    private Specification<ListDfctPosType> createSpecification(ListDfctPosTypeCriteria criteria) {
        Specification<ListDfctPosType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListDfctPosType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListDfctPosType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListDfctPosType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListDfctPosType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListDfctPosType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListDfctPosType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListDfctPosType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListDfctPosType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListDfctPosType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListDfctPosType_.editor));
            }
        }
        return specification;
    }
}
