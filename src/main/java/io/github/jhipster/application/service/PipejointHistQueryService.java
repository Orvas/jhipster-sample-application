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

import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipejointHistRepository;
import io.github.jhipster.application.service.dto.PipejointHistCriteria;
import io.github.jhipster.application.service.dto.PipejointHistDTO;
import io.github.jhipster.application.service.mapper.PipejointHistMapper;

/**
 * Service for executing complex queries for {@link PipejointHist} entities in the database.
 * The main input is a {@link PipejointHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipejointHistDTO} or a {@link Page} of {@link PipejointHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipejointHistQueryService extends QueryService<PipejointHist> {

    private final Logger log = LoggerFactory.getLogger(PipejointHistQueryService.class);

    private final PipejointHistRepository pipejointHistRepository;

    private final PipejointHistMapper pipejointHistMapper;

    public PipejointHistQueryService(PipejointHistRepository pipejointHistRepository, PipejointHistMapper pipejointHistMapper) {
        this.pipejointHistRepository = pipejointHistRepository;
        this.pipejointHistMapper = pipejointHistMapper;
    }

    /**
     * Return a {@link List} of {@link PipejointHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipejointHistDTO> findByCriteria(PipejointHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PipejointHist> specification = createSpecification(criteria);
        return pipejointHistMapper.toDto(pipejointHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipejointHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipejointHistDTO> findByCriteria(PipejointHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PipejointHist> specification = createSpecification(criteria);
        return pipejointHistRepository.findAll(specification, page)
            .map(pipejointHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipejointHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PipejointHist> specification = createSpecification(criteria);
        return pipejointHistRepository.count(specification);
    }

    /**
     * Function to convert PipejointHistCriteria to a {@link Specification}.
     */
    private Specification<PipejointHist> createSpecification(PipejointHistCriteria criteria) {
        Specification<PipejointHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PipejointHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), PipejointHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), PipejointHist_.dateTo));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PipejointHist_.name));
            }
            if (criteria.getExternalCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExternalCoatThickness(), PipejointHist_.externalCoatThickness));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), PipejointHist_.coord));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), PipejointHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PipejointHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), PipejointHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), PipejointHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), PipejointHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), PipejointHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), PipejointHist_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(PipejointHist_.id, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(PipejointHist_.idType, JoinType.LEFT).get(ListPipejointType_.id)));
            }
            if (criteria.getIdExternalCoatTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdExternalCoatTypeId(),
                    root -> root.join(PipejointHist_.idExternalCoatType, JoinType.LEFT).get(ListExternalCoating_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(PipejointHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdSpecificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdSpecificationId(),
                    root -> root.join(PipejointHist_.idSpecification, JoinType.LEFT).get(ListPipejointSpecification_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(PipejointHist_.idStatus, JoinType.LEFT).get(ListObjectStatus_.id)));
            }
        }
        return specification;
    }
}
