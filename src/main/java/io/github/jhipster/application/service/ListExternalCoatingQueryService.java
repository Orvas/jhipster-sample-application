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

import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListExternalCoatingRepository;
import io.github.jhipster.application.service.dto.ListExternalCoatingCriteria;
import io.github.jhipster.application.service.dto.ListExternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListExternalCoatingMapper;

/**
 * Service for executing complex queries for {@link ListExternalCoating} entities in the database.
 * The main input is a {@link ListExternalCoatingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListExternalCoatingDTO} or a {@link Page} of {@link ListExternalCoatingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListExternalCoatingQueryService extends QueryService<ListExternalCoating> {

    private final Logger log = LoggerFactory.getLogger(ListExternalCoatingQueryService.class);

    private final ListExternalCoatingRepository listExternalCoatingRepository;

    private final ListExternalCoatingMapper listExternalCoatingMapper;

    public ListExternalCoatingQueryService(ListExternalCoatingRepository listExternalCoatingRepository, ListExternalCoatingMapper listExternalCoatingMapper) {
        this.listExternalCoatingRepository = listExternalCoatingRepository;
        this.listExternalCoatingMapper = listExternalCoatingMapper;
    }

    /**
     * Return a {@link List} of {@link ListExternalCoatingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListExternalCoatingDTO> findByCriteria(ListExternalCoatingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListExternalCoating> specification = createSpecification(criteria);
        return listExternalCoatingMapper.toDto(listExternalCoatingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListExternalCoatingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListExternalCoatingDTO> findByCriteria(ListExternalCoatingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListExternalCoating> specification = createSpecification(criteria);
        return listExternalCoatingRepository.findAll(specification, page)
            .map(listExternalCoatingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListExternalCoatingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListExternalCoating> specification = createSpecification(criteria);
        return listExternalCoatingRepository.count(specification);
    }

    /**
     * Function to convert ListExternalCoatingCriteria to a {@link Specification}.
     */
    private Specification<ListExternalCoating> createSpecification(ListExternalCoatingCriteria criteria) {
        Specification<ListExternalCoating> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListExternalCoating_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListExternalCoating_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListExternalCoating_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListExternalCoating_.fullName));
            }
            if (criteria.getDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDensity(), ListExternalCoating_.density));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListExternalCoating_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListExternalCoating_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListExternalCoating_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListExternalCoating_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListExternalCoating_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListExternalCoating_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListExternalCoating_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListExternalCoating_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListExternalCoating_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(ListExternalCoating_.pipejointHists, JoinType.LEFT).get(PipejointHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListExternalCoating_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListExternalCoating_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
