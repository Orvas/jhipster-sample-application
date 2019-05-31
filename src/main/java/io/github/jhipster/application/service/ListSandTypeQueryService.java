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

import io.github.jhipster.application.domain.ListSandType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListSandTypeRepository;
import io.github.jhipster.application.service.dto.ListSandTypeCriteria;
import io.github.jhipster.application.service.dto.ListSandTypeDTO;
import io.github.jhipster.application.service.mapper.ListSandTypeMapper;

/**
 * Service for executing complex queries for {@link ListSandType} entities in the database.
 * The main input is a {@link ListSandTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListSandTypeDTO} or a {@link Page} of {@link ListSandTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListSandTypeQueryService extends QueryService<ListSandType> {

    private final Logger log = LoggerFactory.getLogger(ListSandTypeQueryService.class);

    private final ListSandTypeRepository listSandTypeRepository;

    private final ListSandTypeMapper listSandTypeMapper;

    public ListSandTypeQueryService(ListSandTypeRepository listSandTypeRepository, ListSandTypeMapper listSandTypeMapper) {
        this.listSandTypeRepository = listSandTypeRepository;
        this.listSandTypeMapper = listSandTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListSandTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListSandTypeDTO> findByCriteria(ListSandTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListSandType> specification = createSpecification(criteria);
        return listSandTypeMapper.toDto(listSandTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListSandTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSandTypeDTO> findByCriteria(ListSandTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListSandType> specification = createSpecification(criteria);
        return listSandTypeRepository.findAll(specification, page)
            .map(listSandTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListSandTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListSandType> specification = createSpecification(criteria);
        return listSandTypeRepository.count(specification);
    }

    /**
     * Function to convert ListSandTypeCriteria to a {@link Specification}.
     */
    private Specification<ListSandType> createSpecification(ListSandTypeCriteria criteria) {
        Specification<ListSandType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListSandType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListSandType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListSandType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListSandType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListSandType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListSandType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListSandType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListSandType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListSandType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListSandType_.editor));
            }
        }
        return specification;
    }
}
