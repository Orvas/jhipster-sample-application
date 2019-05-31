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

import io.github.jhipster.application.domain.ListBendManufacturer;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBendManufacturerRepository;
import io.github.jhipster.application.service.dto.ListBendManufacturerCriteria;
import io.github.jhipster.application.service.dto.ListBendManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBendManufacturerMapper;

/**
 * Service for executing complex queries for {@link ListBendManufacturer} entities in the database.
 * The main input is a {@link ListBendManufacturerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBendManufacturerDTO} or a {@link Page} of {@link ListBendManufacturerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBendManufacturerQueryService extends QueryService<ListBendManufacturer> {

    private final Logger log = LoggerFactory.getLogger(ListBendManufacturerQueryService.class);

    private final ListBendManufacturerRepository listBendManufacturerRepository;

    private final ListBendManufacturerMapper listBendManufacturerMapper;

    public ListBendManufacturerQueryService(ListBendManufacturerRepository listBendManufacturerRepository, ListBendManufacturerMapper listBendManufacturerMapper) {
        this.listBendManufacturerRepository = listBendManufacturerRepository;
        this.listBendManufacturerMapper = listBendManufacturerMapper;
    }

    /**
     * Return a {@link List} of {@link ListBendManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBendManufacturerDTO> findByCriteria(ListBendManufacturerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBendManufacturer> specification = createSpecification(criteria);
        return listBendManufacturerMapper.toDto(listBendManufacturerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBendManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendManufacturerDTO> findByCriteria(ListBendManufacturerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBendManufacturer> specification = createSpecification(criteria);
        return listBendManufacturerRepository.findAll(specification, page)
            .map(listBendManufacturerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBendManufacturerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBendManufacturer> specification = createSpecification(criteria);
        return listBendManufacturerRepository.count(specification);
    }

    /**
     * Function to convert ListBendManufacturerCriteria to a {@link Specification}.
     */
    private Specification<ListBendManufacturer> createSpecification(ListBendManufacturerCriteria criteria) {
        Specification<ListBendManufacturer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBendManufacturer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBendManufacturer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBendManufacturer_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBendManufacturer_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBendManufacturer_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBendManufacturer_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBendManufacturer_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBendManufacturer_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBendManufacturer_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBendManufacturer_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListBendManufacturer_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
        }
        return specification;
    }
}
