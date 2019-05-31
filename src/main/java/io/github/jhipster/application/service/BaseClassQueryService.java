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

import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BaseClassRepository;
import io.github.jhipster.application.service.dto.BaseClassCriteria;
import io.github.jhipster.application.service.dto.BaseClassDTO;
import io.github.jhipster.application.service.mapper.BaseClassMapper;

/**
 * Service for executing complex queries for {@link BaseClass} entities in the database.
 * The main input is a {@link BaseClassCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BaseClassDTO} or a {@link Page} of {@link BaseClassDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BaseClassQueryService extends QueryService<BaseClass> {

    private final Logger log = LoggerFactory.getLogger(BaseClassQueryService.class);

    private final BaseClassRepository baseClassRepository;

    private final BaseClassMapper baseClassMapper;

    public BaseClassQueryService(BaseClassRepository baseClassRepository, BaseClassMapper baseClassMapper) {
        this.baseClassRepository = baseClassRepository;
        this.baseClassMapper = baseClassMapper;
    }

    /**
     * Return a {@link List} of {@link BaseClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BaseClassDTO> findByCriteria(BaseClassCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BaseClass> specification = createSpecification(criteria);
        return baseClassMapper.toDto(baseClassRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BaseClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BaseClassDTO> findByCriteria(BaseClassCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BaseClass> specification = createSpecification(criteria);
        return baseClassRepository.findAll(specification, page)
            .map(baseClassMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BaseClassCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BaseClass> specification = createSpecification(criteria);
        return baseClassRepository.count(specification);
    }

    /**
     * Function to convert BaseClassCriteria to a {@link Specification}.
     */
    private Specification<BaseClass> createSpecification(BaseClassCriteria criteria) {
        Specification<BaseClass> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BaseClass_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), BaseClass_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), BaseClass_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), BaseClass_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), BaseClass_.editor));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(BaseClass_.idType, JoinType.LEFT).get(ListObjectType_.id)));
            }
            if (criteria.getAnodeId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeId(),
                    root -> root.join(BaseClass_.anode, JoinType.LEFT).get(Anode_.id)));
            }
            if (criteria.getBendId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendId(),
                    root -> root.join(BaseClass_.bend, JoinType.LEFT).get(Bend_.id)));
            }
            if (criteria.getBuckleArrestorId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorId(),
                    root -> root.join(BaseClass_.buckleArrestor, JoinType.LEFT).get(BuckleArrestor_.id)));
            }
            if (criteria.getCpsId() != null) {
                specification = specification.and(buildSpecification(criteria.getCpsId(),
                    root -> root.join(BaseClass_.cps, JoinType.LEFT).get(Cps_.id)));
            }
            if (criteria.getDisplacementId() != null) {
                specification = specification.and(buildSpecification(criteria.getDisplacementId(),
                    root -> root.join(BaseClass_.displacement, JoinType.LEFT).get(Displacement_.id)));
            }
            if (criteria.getFreeSpanId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanId(),
                    root -> root.join(BaseClass_.freeSpan, JoinType.LEFT).get(FreeSpan_.id)));
            }
            if (criteria.getFreeSpanSupportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeSpanSupportId(),
                    root -> root.join(BaseClass_.freeSpanSupport, JoinType.LEFT).get(FreeSpanSupport_.id)));
            }
            if (criteria.getLaunchReceiverId() != null) {
                specification = specification.and(buildSpecification(criteria.getLaunchReceiverId(),
                    root -> root.join(BaseClass_.launchReceiver, JoinType.LEFT).get(LaunchReceiver_.id)));
            }
            if (criteria.getPipeId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeId(),
                    root -> root.join(BaseClass_.pipe, JoinType.LEFT).get(Pipe_.id)));
            }
            if (criteria.getPipejointId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointId(),
                    root -> root.join(BaseClass_.pipejoint, JoinType.LEFT).get(Pipejoint_.id)));
            }
            if (criteria.getPipelineId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineId(),
                    root -> root.join(BaseClass_.pipeline, JoinType.LEFT).get(Pipeline_.id)));
            }
            if (criteria.getPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineSectionId(),
                    root -> root.join(BaseClass_.pipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getTeeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeId(),
                    root -> root.join(BaseClass_.tee, JoinType.LEFT).get(Tee_.id)));
            }
            if (criteria.getValveId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveId(),
                    root -> root.join(BaseClass_.valve, JoinType.LEFT).get(Valve_.id)));
            }
        }
        return specification;
    }
}
