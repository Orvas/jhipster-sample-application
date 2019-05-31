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

import io.github.jhipster.application.domain.ListBucklarrType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBucklarrTypeRepository;
import io.github.jhipster.application.service.dto.ListBucklarrTypeCriteria;
import io.github.jhipster.application.service.dto.ListBucklarrTypeDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrTypeMapper;

/**
 * Service for executing complex queries for {@link ListBucklarrType} entities in the database.
 * The main input is a {@link ListBucklarrTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBucklarrTypeDTO} or a {@link Page} of {@link ListBucklarrTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBucklarrTypeQueryService extends QueryService<ListBucklarrType> {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrTypeQueryService.class);

    private final ListBucklarrTypeRepository listBucklarrTypeRepository;

    private final ListBucklarrTypeMapper listBucklarrTypeMapper;

    public ListBucklarrTypeQueryService(ListBucklarrTypeRepository listBucklarrTypeRepository, ListBucklarrTypeMapper listBucklarrTypeMapper) {
        this.listBucklarrTypeRepository = listBucklarrTypeRepository;
        this.listBucklarrTypeMapper = listBucklarrTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListBucklarrTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBucklarrTypeDTO> findByCriteria(ListBucklarrTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBucklarrType> specification = createSpecification(criteria);
        return listBucklarrTypeMapper.toDto(listBucklarrTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBucklarrTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrTypeDTO> findByCriteria(ListBucklarrTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBucklarrType> specification = createSpecification(criteria);
        return listBucklarrTypeRepository.findAll(specification, page)
            .map(listBucklarrTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBucklarrTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBucklarrType> specification = createSpecification(criteria);
        return listBucklarrTypeRepository.count(specification);
    }

    /**
     * Function to convert ListBucklarrTypeCriteria to a {@link Specification}.
     */
    private Specification<ListBucklarrType> createSpecification(ListBucklarrTypeCriteria criteria) {
        Specification<ListBucklarrType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBucklarrType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBucklarrType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBucklarrType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBucklarrType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBucklarrType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBucklarrType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBucklarrType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBucklarrType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBucklarrType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBucklarrType_.editor));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListBucklarrType_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
        }
        return specification;
    }
}
