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

import io.github.jhipster.application.domain.ListTeeType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListTeeTypeRepository;
import io.github.jhipster.application.service.dto.ListTeeTypeCriteria;
import io.github.jhipster.application.service.dto.ListTeeTypeDTO;
import io.github.jhipster.application.service.mapper.ListTeeTypeMapper;

/**
 * Service for executing complex queries for {@link ListTeeType} entities in the database.
 * The main input is a {@link ListTeeTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListTeeTypeDTO} or a {@link Page} of {@link ListTeeTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListTeeTypeQueryService extends QueryService<ListTeeType> {

    private final Logger log = LoggerFactory.getLogger(ListTeeTypeQueryService.class);

    private final ListTeeTypeRepository listTeeTypeRepository;

    private final ListTeeTypeMapper listTeeTypeMapper;

    public ListTeeTypeQueryService(ListTeeTypeRepository listTeeTypeRepository, ListTeeTypeMapper listTeeTypeMapper) {
        this.listTeeTypeRepository = listTeeTypeRepository;
        this.listTeeTypeMapper = listTeeTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListTeeTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListTeeTypeDTO> findByCriteria(ListTeeTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListTeeType> specification = createSpecification(criteria);
        return listTeeTypeMapper.toDto(listTeeTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListTeeTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeTypeDTO> findByCriteria(ListTeeTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListTeeType> specification = createSpecification(criteria);
        return listTeeTypeRepository.findAll(specification, page)
            .map(listTeeTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListTeeTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListTeeType> specification = createSpecification(criteria);
        return listTeeTypeRepository.count(specification);
    }

    /**
     * Function to convert ListTeeTypeCriteria to a {@link Specification}.
     */
    private Specification<ListTeeType> createSpecification(ListTeeTypeCriteria criteria) {
        Specification<ListTeeType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListTeeType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListTeeType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListTeeType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListTeeType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListTeeType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListTeeType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListTeeType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListTeeType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListTeeType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListTeeType_.editor));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListTeeType_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
        }
        return specification;
    }
}
