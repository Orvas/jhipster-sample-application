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

import io.github.jhipster.application.domain.ListBucklarrManufacturer;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBucklarrManufacturerRepository;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerCriteria;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrManufacturerMapper;

/**
 * Service for executing complex queries for {@link ListBucklarrManufacturer} entities in the database.
 * The main input is a {@link ListBucklarrManufacturerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBucklarrManufacturerDTO} or a {@link Page} of {@link ListBucklarrManufacturerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBucklarrManufacturerQueryService extends QueryService<ListBucklarrManufacturer> {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrManufacturerQueryService.class);

    private final ListBucklarrManufacturerRepository listBucklarrManufacturerRepository;

    private final ListBucklarrManufacturerMapper listBucklarrManufacturerMapper;

    public ListBucklarrManufacturerQueryService(ListBucklarrManufacturerRepository listBucklarrManufacturerRepository, ListBucklarrManufacturerMapper listBucklarrManufacturerMapper) {
        this.listBucklarrManufacturerRepository = listBucklarrManufacturerRepository;
        this.listBucklarrManufacturerMapper = listBucklarrManufacturerMapper;
    }

    /**
     * Return a {@link List} of {@link ListBucklarrManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBucklarrManufacturerDTO> findByCriteria(ListBucklarrManufacturerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBucklarrManufacturer> specification = createSpecification(criteria);
        return listBucklarrManufacturerMapper.toDto(listBucklarrManufacturerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBucklarrManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrManufacturerDTO> findByCriteria(ListBucklarrManufacturerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBucklarrManufacturer> specification = createSpecification(criteria);
        return listBucklarrManufacturerRepository.findAll(specification, page)
            .map(listBucklarrManufacturerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBucklarrManufacturerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBucklarrManufacturer> specification = createSpecification(criteria);
        return listBucklarrManufacturerRepository.count(specification);
    }

    /**
     * Function to convert ListBucklarrManufacturerCriteria to a {@link Specification}.
     */
    private Specification<ListBucklarrManufacturer> createSpecification(ListBucklarrManufacturerCriteria criteria) {
        Specification<ListBucklarrManufacturer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBucklarrManufacturer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBucklarrManufacturer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBucklarrManufacturer_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBucklarrManufacturer_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBucklarrManufacturer_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBucklarrManufacturer_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBucklarrManufacturer_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBucklarrManufacturer_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBucklarrManufacturer_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBucklarrManufacturer_.editor));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListBucklarrManufacturer_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
        }
        return specification;
    }
}
