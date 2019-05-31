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

import io.github.jhipster.application.domain.ListIliPigType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListIliPigTypeRepository;
import io.github.jhipster.application.service.dto.ListIliPigTypeCriteria;
import io.github.jhipster.application.service.dto.ListIliPigTypeDTO;
import io.github.jhipster.application.service.mapper.ListIliPigTypeMapper;

/**
 * Service for executing complex queries for {@link ListIliPigType} entities in the database.
 * The main input is a {@link ListIliPigTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListIliPigTypeDTO} or a {@link Page} of {@link ListIliPigTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListIliPigTypeQueryService extends QueryService<ListIliPigType> {

    private final Logger log = LoggerFactory.getLogger(ListIliPigTypeQueryService.class);

    private final ListIliPigTypeRepository listIliPigTypeRepository;

    private final ListIliPigTypeMapper listIliPigTypeMapper;

    public ListIliPigTypeQueryService(ListIliPigTypeRepository listIliPigTypeRepository, ListIliPigTypeMapper listIliPigTypeMapper) {
        this.listIliPigTypeRepository = listIliPigTypeRepository;
        this.listIliPigTypeMapper = listIliPigTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListIliPigTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListIliPigTypeDTO> findByCriteria(ListIliPigTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListIliPigType> specification = createSpecification(criteria);
        return listIliPigTypeMapper.toDto(listIliPigTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListIliPigTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListIliPigTypeDTO> findByCriteria(ListIliPigTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListIliPigType> specification = createSpecification(criteria);
        return listIliPigTypeRepository.findAll(specification, page)
            .map(listIliPigTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListIliPigTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListIliPigType> specification = createSpecification(criteria);
        return listIliPigTypeRepository.count(specification);
    }

    /**
     * Function to convert ListIliPigTypeCriteria to a {@link Specification}.
     */
    private Specification<ListIliPigType> createSpecification(ListIliPigTypeCriteria criteria) {
        Specification<ListIliPigType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListIliPigType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListIliPigType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListIliPigType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListIliPigType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListIliPigType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListIliPigType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListIliPigType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListIliPigType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListIliPigType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListIliPigType_.editor));
            }
        }
        return specification;
    }
}
