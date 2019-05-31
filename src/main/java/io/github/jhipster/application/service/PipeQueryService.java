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

import io.github.jhipster.application.domain.Pipe;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PipeRepository;
import io.github.jhipster.application.service.dto.PipeCriteria;
import io.github.jhipster.application.service.dto.PipeDTO;
import io.github.jhipster.application.service.mapper.PipeMapper;

/**
 * Service for executing complex queries for {@link Pipe} entities in the database.
 * The main input is a {@link PipeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PipeDTO} or a {@link Page} of {@link PipeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PipeQueryService extends QueryService<Pipe> {

    private final Logger log = LoggerFactory.getLogger(PipeQueryService.class);

    private final PipeRepository pipeRepository;

    private final PipeMapper pipeMapper;

    public PipeQueryService(PipeRepository pipeRepository, PipeMapper pipeMapper) {
        this.pipeRepository = pipeRepository;
        this.pipeMapper = pipeMapper;
    }

    /**
     * Return a {@link List} of {@link PipeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PipeDTO> findByCriteria(PipeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Pipe> specification = createSpecification(criteria);
        return pipeMapper.toDto(pipeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PipeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PipeDTO> findByCriteria(PipeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Pipe> specification = createSpecification(criteria);
        return pipeRepository.findAll(specification, page)
            .map(pipeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PipeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Pipe> specification = createSpecification(criteria);
        return pipeRepository.count(specification);
    }

    /**
     * Function to convert PipeCriteria to a {@link Specification}.
     */
    private Specification<Pipe> createSpecification(PipeCriteria criteria) {
        Specification<Pipe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Pipe_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Pipe_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Pipe_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Pipe_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Pipe_.editor));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(Pipe_.id, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(Pipe_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
        }
        return specification;
    }
}
