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

import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListMillLocationRepository;
import io.github.jhipster.application.service.dto.ListMillLocationCriteria;
import io.github.jhipster.application.service.dto.ListMillLocationDTO;
import io.github.jhipster.application.service.mapper.ListMillLocationMapper;

/**
 * Service for executing complex queries for {@link ListMillLocation} entities in the database.
 * The main input is a {@link ListMillLocationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListMillLocationDTO} or a {@link Page} of {@link ListMillLocationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListMillLocationQueryService extends QueryService<ListMillLocation> {

    private final Logger log = LoggerFactory.getLogger(ListMillLocationQueryService.class);

    private final ListMillLocationRepository listMillLocationRepository;

    private final ListMillLocationMapper listMillLocationMapper;

    public ListMillLocationQueryService(ListMillLocationRepository listMillLocationRepository, ListMillLocationMapper listMillLocationMapper) {
        this.listMillLocationRepository = listMillLocationRepository;
        this.listMillLocationMapper = listMillLocationMapper;
    }

    /**
     * Return a {@link List} of {@link ListMillLocationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListMillLocationDTO> findByCriteria(ListMillLocationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListMillLocation> specification = createSpecification(criteria);
        return listMillLocationMapper.toDto(listMillLocationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListMillLocationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMillLocationDTO> findByCriteria(ListMillLocationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListMillLocation> specification = createSpecification(criteria);
        return listMillLocationRepository.findAll(specification, page)
            .map(listMillLocationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListMillLocationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListMillLocation> specification = createSpecification(criteria);
        return listMillLocationRepository.count(specification);
    }

    /**
     * Function to convert ListMillLocationCriteria to a {@link Specification}.
     */
    private Specification<ListMillLocation> createSpecification(ListMillLocationCriteria criteria) {
        Specification<ListMillLocation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListMillLocation_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListMillLocation_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListMillLocation_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListMillLocation_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListMillLocation_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListMillLocation_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListMillLocation_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListMillLocation_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListMillLocation_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListMillLocation_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListMillLocation_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListMillLocation_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListMillLocation_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListMillLocation_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListMillLocation_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
