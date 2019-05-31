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

import io.github.jhipster.application.domain.ListPipeManufacturer;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListPipeManufacturerRepository;
import io.github.jhipster.application.service.dto.ListPipeManufacturerCriteria;
import io.github.jhipster.application.service.dto.ListPipeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListPipeManufacturerMapper;

/**
 * Service for executing complex queries for {@link ListPipeManufacturer} entities in the database.
 * The main input is a {@link ListPipeManufacturerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListPipeManufacturerDTO} or a {@link Page} of {@link ListPipeManufacturerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListPipeManufacturerQueryService extends QueryService<ListPipeManufacturer> {

    private final Logger log = LoggerFactory.getLogger(ListPipeManufacturerQueryService.class);

    private final ListPipeManufacturerRepository listPipeManufacturerRepository;

    private final ListPipeManufacturerMapper listPipeManufacturerMapper;

    public ListPipeManufacturerQueryService(ListPipeManufacturerRepository listPipeManufacturerRepository, ListPipeManufacturerMapper listPipeManufacturerMapper) {
        this.listPipeManufacturerRepository = listPipeManufacturerRepository;
        this.listPipeManufacturerMapper = listPipeManufacturerMapper;
    }

    /**
     * Return a {@link List} of {@link ListPipeManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListPipeManufacturerDTO> findByCriteria(ListPipeManufacturerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListPipeManufacturer> specification = createSpecification(criteria);
        return listPipeManufacturerMapper.toDto(listPipeManufacturerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListPipeManufacturerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipeManufacturerDTO> findByCriteria(ListPipeManufacturerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListPipeManufacturer> specification = createSpecification(criteria);
        return listPipeManufacturerRepository.findAll(specification, page)
            .map(listPipeManufacturerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListPipeManufacturerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListPipeManufacturer> specification = createSpecification(criteria);
        return listPipeManufacturerRepository.count(specification);
    }

    /**
     * Function to convert ListPipeManufacturerCriteria to a {@link Specification}.
     */
    private Specification<ListPipeManufacturer> createSpecification(ListPipeManufacturerCriteria criteria) {
        Specification<ListPipeManufacturer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListPipeManufacturer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListPipeManufacturer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListPipeManufacturer_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListPipeManufacturer_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListPipeManufacturer_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListPipeManufacturer_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListPipeManufacturer_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListPipeManufacturer_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListPipeManufacturer_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListPipeManufacturer_.editor));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListPipeManufacturer_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
        }
        return specification;
    }
}
