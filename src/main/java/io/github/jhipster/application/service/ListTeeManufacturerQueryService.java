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

import io.github.jhipster.application.domain.ListTeeManufacturer;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListTeeManufacturerRepository;
import io.github.jhipster.application.service.dto.ListTeeManufacturerCriteria;
import io.github.jhipster.application.service.dto.ListTeeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListTeeManufacturerMapper;

/**
 * Service for executing complex queries for {@link ListTeeManufacturer} entities in the database.
 * The main input is a {@link ListTeeManufacturerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListTeeManufacturerDTO} or a {@link Page} of {@link ListTeeManufacturerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListTeeManufacturerQueryService extends QueryService<ListTeeManufacturer> {

    private final Logger log = LoggerFactory.getLogger(ListTeeManufacturerQueryService.class);

    private final ListTeeManufacturerRepository listTeeManufacturerRepository;

    private final ListTeeManufacturerMapper listTeeManufacturerMapper;

    public ListTeeManufacturerQueryService(ListTeeManufacturerRepository listTeeManufacturerRepository, ListTeeManufacturerMapper listTeeManufacturerMapper) {
        this.listTeeManufacturerRepository = listTeeManufacturerRepository;
        this.listTeeManufacturerMapper = listTeeManufacturerMapper;
    }

    /**
     * Return a {@link List} of {@link ListTeeManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListTeeManufacturerDTO> findByCriteria(ListTeeManufacturerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListTeeManufacturer> specification = createSpecification(criteria);
        return listTeeManufacturerMapper.toDto(listTeeManufacturerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListTeeManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeManufacturerDTO> findByCriteria(ListTeeManufacturerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListTeeManufacturer> specification = createSpecification(criteria);
        return listTeeManufacturerRepository.findAll(specification, page)
            .map(listTeeManufacturerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListTeeManufacturerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListTeeManufacturer> specification = createSpecification(criteria);
        return listTeeManufacturerRepository.count(specification);
    }

    /**
     * Function to convert ListTeeManufacturerCriteria to a {@link Specification}.
     */
    private Specification<ListTeeManufacturer> createSpecification(ListTeeManufacturerCriteria criteria) {
        Specification<ListTeeManufacturer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListTeeManufacturer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListTeeManufacturer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListTeeManufacturer_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListTeeManufacturer_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListTeeManufacturer_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListTeeManufacturer_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListTeeManufacturer_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListTeeManufacturer_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListTeeManufacturer_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListTeeManufacturer_.editor));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListTeeManufacturer_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
        }
        return specification;
    }
}
