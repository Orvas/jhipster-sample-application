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

import io.github.jhipster.application.domain.ListSafetyClass;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListSafetyClassRepository;
import io.github.jhipster.application.service.dto.ListSafetyClassCriteria;
import io.github.jhipster.application.service.dto.ListSafetyClassDTO;
import io.github.jhipster.application.service.mapper.ListSafetyClassMapper;

/**
 * Service for executing complex queries for {@link ListSafetyClass} entities in the database.
 * The main input is a {@link ListSafetyClassCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListSafetyClassDTO} or a {@link Page} of {@link ListSafetyClassDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListSafetyClassQueryService extends QueryService<ListSafetyClass> {

    private final Logger log = LoggerFactory.getLogger(ListSafetyClassQueryService.class);

    private final ListSafetyClassRepository listSafetyClassRepository;

    private final ListSafetyClassMapper listSafetyClassMapper;

    public ListSafetyClassQueryService(ListSafetyClassRepository listSafetyClassRepository, ListSafetyClassMapper listSafetyClassMapper) {
        this.listSafetyClassRepository = listSafetyClassRepository;
        this.listSafetyClassMapper = listSafetyClassMapper;
    }

    /**
     * Return a {@link List} of {@link ListSafetyClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListSafetyClassDTO> findByCriteria(ListSafetyClassCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListSafetyClass> specification = createSpecification(criteria);
        return listSafetyClassMapper.toDto(listSafetyClassRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListSafetyClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSafetyClassDTO> findByCriteria(ListSafetyClassCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListSafetyClass> specification = createSpecification(criteria);
        return listSafetyClassRepository.findAll(specification, page)
            .map(listSafetyClassMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListSafetyClassCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListSafetyClass> specification = createSpecification(criteria);
        return listSafetyClassRepository.count(specification);
    }

    /**
     * Function to convert ListSafetyClassCriteria to a {@link Specification}.
     */
    private Specification<ListSafetyClass> createSpecification(ListSafetyClassCriteria criteria) {
        Specification<ListSafetyClass> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListSafetyClass_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListSafetyClass_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListSafetyClass_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListSafetyClass_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListSafetyClass_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListSafetyClass_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListSafetyClass_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListSafetyClass_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListSafetyClass_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListSafetyClass_.editor));
            }
            if (criteria.getPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineSectionId(),
                    root -> root.join(ListSafetyClass_.pipelineSections, JoinType.LEFT).get(PipelineSection_.id)));
            }
        }
        return specification;
    }
}
