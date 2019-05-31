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

import io.github.jhipster.application.domain.ListClcLvl;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListClcLvlRepository;
import io.github.jhipster.application.service.dto.ListClcLvlCriteria;
import io.github.jhipster.application.service.dto.ListClcLvlDTO;
import io.github.jhipster.application.service.mapper.ListClcLvlMapper;

/**
 * Service for executing complex queries for {@link ListClcLvl} entities in the database.
 * The main input is a {@link ListClcLvlCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListClcLvlDTO} or a {@link Page} of {@link ListClcLvlDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListClcLvlQueryService extends QueryService<ListClcLvl> {

    private final Logger log = LoggerFactory.getLogger(ListClcLvlQueryService.class);

    private final ListClcLvlRepository listClcLvlRepository;

    private final ListClcLvlMapper listClcLvlMapper;

    public ListClcLvlQueryService(ListClcLvlRepository listClcLvlRepository, ListClcLvlMapper listClcLvlMapper) {
        this.listClcLvlRepository = listClcLvlRepository;
        this.listClcLvlMapper = listClcLvlMapper;
    }

    /**
     * Return a {@link List} of {@link ListClcLvlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListClcLvlDTO> findByCriteria(ListClcLvlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListClcLvl> specification = createSpecification(criteria);
        return listClcLvlMapper.toDto(listClcLvlRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListClcLvlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcLvlDTO> findByCriteria(ListClcLvlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListClcLvl> specification = createSpecification(criteria);
        return listClcLvlRepository.findAll(specification, page)
            .map(listClcLvlMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListClcLvlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListClcLvl> specification = createSpecification(criteria);
        return listClcLvlRepository.count(specification);
    }

    /**
     * Function to convert ListClcLvlCriteria to a {@link Specification}.
     */
    private Specification<ListClcLvl> createSpecification(ListClcLvlCriteria criteria) {
        Specification<ListClcLvl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListClcLvl_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListClcLvl_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListClcLvl_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListClcLvl_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListClcLvl_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListClcLvl_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListClcLvl_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListClcLvl_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListClcLvl_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListClcLvl_.editor));
            }
        }
        return specification;
    }
}
