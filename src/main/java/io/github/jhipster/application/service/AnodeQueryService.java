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

import io.github.jhipster.application.domain.Anode;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.AnodeRepository;
import io.github.jhipster.application.service.dto.AnodeCriteria;
import io.github.jhipster.application.service.dto.AnodeDTO;
import io.github.jhipster.application.service.mapper.AnodeMapper;

/**
 * Service for executing complex queries for {@link Anode} entities in the database.
 * The main input is a {@link AnodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnodeDTO} or a {@link Page} of {@link AnodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnodeQueryService extends QueryService<Anode> {

    private final Logger log = LoggerFactory.getLogger(AnodeQueryService.class);

    private final AnodeRepository anodeRepository;

    private final AnodeMapper anodeMapper;

    public AnodeQueryService(AnodeRepository anodeRepository, AnodeMapper anodeMapper) {
        this.anodeRepository = anodeRepository;
        this.anodeMapper = anodeMapper;
    }

    /**
     * Return a {@link List} of {@link AnodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnodeDTO> findByCriteria(AnodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Anode> specification = createSpecification(criteria);
        return anodeMapper.toDto(anodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnodeDTO> findByCriteria(AnodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Anode> specification = createSpecification(criteria);
        return anodeRepository.findAll(specification, page)
            .map(anodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Anode> specification = createSpecification(criteria);
        return anodeRepository.count(specification);
    }

    /**
     * Function to convert AnodeCriteria to a {@link Specification}.
     */
    private Specification<Anode> createSpecification(AnodeCriteria criteria) {
        Specification<Anode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Anode_.id));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), Anode_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), Anode_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), Anode_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), Anode_.editor));
            }
            if (criteria.getBaseClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getBaseClassId(),
                    root -> root.join(Anode_.baseClass, JoinType.LEFT).get(BaseClass_.id)));
            }
            if (criteria.getAnodeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeHistId(),
                    root -> root.join(Anode_.anodeHist, JoinType.LEFT).get(AnodeHist_.id)));
            }
        }
        return specification;
    }
}
