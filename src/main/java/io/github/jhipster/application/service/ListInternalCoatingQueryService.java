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

import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListInternalCoatingRepository;
import io.github.jhipster.application.service.dto.ListInternalCoatingCriteria;
import io.github.jhipster.application.service.dto.ListInternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListInternalCoatingMapper;

/**
 * Service for executing complex queries for {@link ListInternalCoating} entities in the database.
 * The main input is a {@link ListInternalCoatingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListInternalCoatingDTO} or a {@link Page} of {@link ListInternalCoatingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListInternalCoatingQueryService extends QueryService<ListInternalCoating> {

    private final Logger log = LoggerFactory.getLogger(ListInternalCoatingQueryService.class);

    private final ListInternalCoatingRepository listInternalCoatingRepository;

    private final ListInternalCoatingMapper listInternalCoatingMapper;

    public ListInternalCoatingQueryService(ListInternalCoatingRepository listInternalCoatingRepository, ListInternalCoatingMapper listInternalCoatingMapper) {
        this.listInternalCoatingRepository = listInternalCoatingRepository;
        this.listInternalCoatingMapper = listInternalCoatingMapper;
    }

    /**
     * Return a {@link List} of {@link ListInternalCoatingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListInternalCoatingDTO> findByCriteria(ListInternalCoatingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListInternalCoating> specification = createSpecification(criteria);
        return listInternalCoatingMapper.toDto(listInternalCoatingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListInternalCoatingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListInternalCoatingDTO> findByCriteria(ListInternalCoatingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListInternalCoating> specification = createSpecification(criteria);
        return listInternalCoatingRepository.findAll(specification, page)
            .map(listInternalCoatingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListInternalCoatingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListInternalCoating> specification = createSpecification(criteria);
        return listInternalCoatingRepository.count(specification);
    }

    /**
     * Function to convert ListInternalCoatingCriteria to a {@link Specification}.
     */
    private Specification<ListInternalCoating> createSpecification(ListInternalCoatingCriteria criteria) {
        Specification<ListInternalCoating> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListInternalCoating_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListInternalCoating_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListInternalCoating_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListInternalCoating_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListInternalCoating_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListInternalCoating_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListInternalCoating_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListInternalCoating_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListInternalCoating_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListInternalCoating_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListInternalCoating_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListInternalCoating_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListInternalCoating_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListInternalCoating_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListInternalCoating_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
