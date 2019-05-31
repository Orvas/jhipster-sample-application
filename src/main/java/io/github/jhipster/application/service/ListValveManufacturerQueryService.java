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

import io.github.jhipster.application.domain.ListValveManufacturer;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListValveManufacturerRepository;
import io.github.jhipster.application.service.dto.ListValveManufacturerCriteria;
import io.github.jhipster.application.service.dto.ListValveManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListValveManufacturerMapper;

/**
 * Service for executing complex queries for {@link ListValveManufacturer} entities in the database.
 * The main input is a {@link ListValveManufacturerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListValveManufacturerDTO} or a {@link Page} of {@link ListValveManufacturerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListValveManufacturerQueryService extends QueryService<ListValveManufacturer> {

    private final Logger log = LoggerFactory.getLogger(ListValveManufacturerQueryService.class);

    private final ListValveManufacturerRepository listValveManufacturerRepository;

    private final ListValveManufacturerMapper listValveManufacturerMapper;

    public ListValveManufacturerQueryService(ListValveManufacturerRepository listValveManufacturerRepository, ListValveManufacturerMapper listValveManufacturerMapper) {
        this.listValveManufacturerRepository = listValveManufacturerRepository;
        this.listValveManufacturerMapper = listValveManufacturerMapper;
    }

    /**
     * Return a {@link List} of {@link ListValveManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListValveManufacturerDTO> findByCriteria(ListValveManufacturerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListValveManufacturer> specification = createSpecification(criteria);
        return listValveManufacturerMapper.toDto(listValveManufacturerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListValveManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveManufacturerDTO> findByCriteria(ListValveManufacturerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListValveManufacturer> specification = createSpecification(criteria);
        return listValveManufacturerRepository.findAll(specification, page)
            .map(listValveManufacturerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListValveManufacturerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListValveManufacturer> specification = createSpecification(criteria);
        return listValveManufacturerRepository.count(specification);
    }

    /**
     * Function to convert ListValveManufacturerCriteria to a {@link Specification}.
     */
    private Specification<ListValveManufacturer> createSpecification(ListValveManufacturerCriteria criteria) {
        Specification<ListValveManufacturer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListValveManufacturer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListValveManufacturer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListValveManufacturer_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListValveManufacturer_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListValveManufacturer_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListValveManufacturer_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListValveManufacturer_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListValveManufacturer_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListValveManufacturer_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListValveManufacturer_.editor));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListValveManufacturer_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
