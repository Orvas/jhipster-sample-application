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

import io.github.jhipster.application.domain.ListPipejointType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListPipejointTypeRepository;
import io.github.jhipster.application.service.dto.ListPipejointTypeCriteria;
import io.github.jhipster.application.service.dto.ListPipejointTypeDTO;
import io.github.jhipster.application.service.mapper.ListPipejointTypeMapper;

/**
 * Service for executing complex queries for {@link ListPipejointType} entities in the database.
 * The main input is a {@link ListPipejointTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListPipejointTypeDTO} or a {@link Page} of {@link ListPipejointTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListPipejointTypeQueryService extends QueryService<ListPipejointType> {

    private final Logger log = LoggerFactory.getLogger(ListPipejointTypeQueryService.class);

    private final ListPipejointTypeRepository listPipejointTypeRepository;

    private final ListPipejointTypeMapper listPipejointTypeMapper;

    public ListPipejointTypeQueryService(ListPipejointTypeRepository listPipejointTypeRepository, ListPipejointTypeMapper listPipejointTypeMapper) {
        this.listPipejointTypeRepository = listPipejointTypeRepository;
        this.listPipejointTypeMapper = listPipejointTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListPipejointTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListPipejointTypeDTO> findByCriteria(ListPipejointTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListPipejointType> specification = createSpecification(criteria);
        return listPipejointTypeMapper.toDto(listPipejointTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListPipejointTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipejointTypeDTO> findByCriteria(ListPipejointTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListPipejointType> specification = createSpecification(criteria);
        return listPipejointTypeRepository.findAll(specification, page)
            .map(listPipejointTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListPipejointTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListPipejointType> specification = createSpecification(criteria);
        return listPipejointTypeRepository.count(specification);
    }

    /**
     * Function to convert ListPipejointTypeCriteria to a {@link Specification}.
     */
    private Specification<ListPipejointType> createSpecification(ListPipejointTypeCriteria criteria) {
        Specification<ListPipejointType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListPipejointType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListPipejointType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListPipejointType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListPipejointType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListPipejointType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListPipejointType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListPipejointType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListPipejointType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListPipejointType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListPipejointType_.editor));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(ListPipejointType_.pipejointHists, JoinType.LEFT).get(PipejointHist_.id)));
            }
        }
        return specification;
    }
}
