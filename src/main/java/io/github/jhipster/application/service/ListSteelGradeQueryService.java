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

import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListSteelGradeRepository;
import io.github.jhipster.application.service.dto.ListSteelGradeCriteria;
import io.github.jhipster.application.service.dto.ListSteelGradeDTO;
import io.github.jhipster.application.service.mapper.ListSteelGradeMapper;

/**
 * Service for executing complex queries for {@link ListSteelGrade} entities in the database.
 * The main input is a {@link ListSteelGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListSteelGradeDTO} or a {@link Page} of {@link ListSteelGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListSteelGradeQueryService extends QueryService<ListSteelGrade> {

    private final Logger log = LoggerFactory.getLogger(ListSteelGradeQueryService.class);

    private final ListSteelGradeRepository listSteelGradeRepository;

    private final ListSteelGradeMapper listSteelGradeMapper;

    public ListSteelGradeQueryService(ListSteelGradeRepository listSteelGradeRepository, ListSteelGradeMapper listSteelGradeMapper) {
        this.listSteelGradeRepository = listSteelGradeRepository;
        this.listSteelGradeMapper = listSteelGradeMapper;
    }

    /**
     * Return a {@link List} of {@link ListSteelGradeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListSteelGradeDTO> findByCriteria(ListSteelGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListSteelGrade> specification = createSpecification(criteria);
        return listSteelGradeMapper.toDto(listSteelGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListSteelGradeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSteelGradeDTO> findByCriteria(ListSteelGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListSteelGrade> specification = createSpecification(criteria);
        return listSteelGradeRepository.findAll(specification, page)
            .map(listSteelGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListSteelGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListSteelGrade> specification = createSpecification(criteria);
        return listSteelGradeRepository.count(specification);
    }

    /**
     * Function to convert ListSteelGradeCriteria to a {@link Specification}.
     */
    private Specification<ListSteelGrade> createSpecification(ListSteelGradeCriteria criteria) {
        Specification<ListSteelGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListSteelGrade_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListSteelGrade_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListSteelGrade_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListSteelGrade_.fullName));
            }
            if (criteria.getSteelDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSteelDensity(), ListSteelGrade_.steelDensity));
            }
            if (criteria.getThermExpCoef() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThermExpCoef(), ListSteelGrade_.thermExpCoef));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListSteelGrade_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListSteelGrade_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListSteelGrade_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListSteelGrade_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListSteelGrade_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListSteelGrade_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListSteelGrade_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListSteelGrade_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListSteelGrade_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListSteelGrade_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListSteelGrade_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
