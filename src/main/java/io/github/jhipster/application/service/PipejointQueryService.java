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

import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipejointRepository;
import io.github.jhipster.application.service.dto.PipejointCriteria;
import io.github.jhipster.application.service.dto.PipejointDTO;
import io.github.jhipster.application.service.mapper.PipejointMapper;

/**
 * Service for executing complex queries for {@link Pipejoint} entities in the database.
 * The main input is a {@link PipejointCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipejointDTO} or a {@link Page} of {@link PipejointDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipejointQueryService extends QueryService<Pipejoint> {

    private final Logger log = LoggerFactory.getLogger(PipejointQueryService.class);

    private final PipejointRepository pipejointRepository;

    private final PipejointMapper pipejointMapper;

    public PipejointQueryService(PipejointRepository pipejointRepository, PipejointMapper pipejointMapper) {
        this.pipejointRepository = pipejointRepository;
        this.pipejointMapper = pipejointMapper;
    }

    /**
     * Return a {@link List} of {@link PipejointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipejointDTO> findByCriteria(PipejointCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Pipejoint> specification = createSpecification(criteria);
        return pipejointMapper.toDto(pipejointRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipejointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipejointDTO> findByCriteria(PipejointCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Pipejoint> specification = createSpecification(criteria);
        return pipejointRepository.findAll(specification, page)
            .map(pipejointMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipejointCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Pipejoint> specification = createSpecification(criteria);
        return pipejointRepository.count(specification);
    }

    /**
     * Function to convert PipejointCriteria to a {@link Specification}.
     */
    private Specification<Pipejoint> createSpecification(PipejointCriteria criteria) {
        Specification<Pipejoint> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Pipejoint_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Pipejoint_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Pipejoint_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Pipejoint_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Pipejoint_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(Pipejoint_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(Pipejoint_.pipejointHist, JoinType.LEFT).get(PipejointHist_.id)));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(Pipejoint_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(Pipejoint_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(Pipejoint_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(Pipejoint_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(Pipejoint_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
