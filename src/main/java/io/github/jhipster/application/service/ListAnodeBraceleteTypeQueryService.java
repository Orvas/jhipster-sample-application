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

import io.github.jhipster.application.domain.ListAnodeBraceleteType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListAnodeBraceleteTypeRepository;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeCriteria;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeDTO;
import io.github.jhipster.application.service.mapper.ListAnodeBraceleteTypeMapper;

/**
 * Service for executing complex queries for {@link ListAnodeBraceleteType} entities in the database.
 * The main input is a {@link ListAnodeBraceleteTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListAnodeBraceleteTypeDTO} or a {@link Page} of {@link ListAnodeBraceleteTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListAnodeBraceleteTypeQueryService extends QueryService<ListAnodeBraceleteType> {

    private final Logger log = LoggerFactory.getLogger(ListAnodeBraceleteTypeQueryService.class);

    private final ListAnodeBraceleteTypeRepository listAnodeBraceleteTypeRepository;

    private final ListAnodeBraceleteTypeMapper listAnodeBraceleteTypeMapper;

    public ListAnodeBraceleteTypeQueryService(ListAnodeBraceleteTypeRepository listAnodeBraceleteTypeRepository, ListAnodeBraceleteTypeMapper listAnodeBraceleteTypeMapper) {
        this.listAnodeBraceleteTypeRepository = listAnodeBraceleteTypeRepository;
        this.listAnodeBraceleteTypeMapper = listAnodeBraceleteTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListAnodeBraceleteTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListAnodeBraceleteTypeDTO> findByCriteria(ListAnodeBraceleteTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListAnodeBraceleteType> specification = createSpecification(criteria);
        return listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListAnodeBraceleteTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListAnodeBraceleteTypeDTO> findByCriteria(ListAnodeBraceleteTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListAnodeBraceleteType> specification = createSpecification(criteria);
        return listAnodeBraceleteTypeRepository.findAll(specification, page)
            .map(listAnodeBraceleteTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListAnodeBraceleteTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListAnodeBraceleteType> specification = createSpecification(criteria);
        return listAnodeBraceleteTypeRepository.count(specification);
    }

    /**
     * Function to convert ListAnodeBraceleteTypeCriteria to a {@link Specification}.
     */
    private Specification<ListAnodeBraceleteType> createSpecification(ListAnodeBraceleteTypeCriteria criteria) {
        Specification<ListAnodeBraceleteType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListAnodeBraceleteType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListAnodeBraceleteType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListAnodeBraceleteType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListAnodeBraceleteType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListAnodeBraceleteType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListAnodeBraceleteType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListAnodeBraceleteType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListAnodeBraceleteType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListAnodeBraceleteType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListAnodeBraceleteType_.editor));
            }
            if (criteria.getAnodeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeHistId(),
                    root -> root.join(ListAnodeBraceleteType_.anodeHists, JoinType.LEFT).get(AnodeHist_.id)));
            }
        }
        return specification;
    }
}
