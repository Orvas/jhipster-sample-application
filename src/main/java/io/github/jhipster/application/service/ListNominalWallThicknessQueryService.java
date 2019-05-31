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

import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListNominalWallThicknessRepository;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessCriteria;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessDTO;
import io.github.jhipster.application.service.mapper.ListNominalWallThicknessMapper;

/**
 * Service for executing complex queries for {@link ListNominalWallThickness} entities in the database.
 * The main input is a {@link ListNominalWallThicknessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListNominalWallThicknessDTO} or a {@link Page} of {@link ListNominalWallThicknessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListNominalWallThicknessQueryService extends QueryService<ListNominalWallThickness> {

    private final Logger log = LoggerFactory.getLogger(ListNominalWallThicknessQueryService.class);

    private final ListNominalWallThicknessRepository listNominalWallThicknessRepository;

    private final ListNominalWallThicknessMapper listNominalWallThicknessMapper;

    public ListNominalWallThicknessQueryService(ListNominalWallThicknessRepository listNominalWallThicknessRepository, ListNominalWallThicknessMapper listNominalWallThicknessMapper) {
        this.listNominalWallThicknessRepository = listNominalWallThicknessRepository;
        this.listNominalWallThicknessMapper = listNominalWallThicknessMapper;
    }

    /**
     * Return a {@link List} of {@link ListNominalWallThicknessDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListNominalWallThicknessDTO> findByCriteria(ListNominalWallThicknessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListNominalWallThickness> specification = createSpecification(criteria);
        return listNominalWallThicknessMapper.toDto(listNominalWallThicknessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListNominalWallThicknessDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListNominalWallThicknessDTO> findByCriteria(ListNominalWallThicknessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListNominalWallThickness> specification = createSpecification(criteria);
        return listNominalWallThicknessRepository.findAll(specification, page)
            .map(listNominalWallThicknessMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListNominalWallThicknessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListNominalWallThickness> specification = createSpecification(criteria);
        return listNominalWallThicknessRepository.count(specification);
    }

    /**
     * Function to convert ListNominalWallThicknessCriteria to a {@link Specification}.
     */
    private Specification<ListNominalWallThickness> createSpecification(ListNominalWallThicknessCriteria criteria) {
        Specification<ListNominalWallThickness> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListNominalWallThickness_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListNominalWallThickness_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListNominalWallThickness_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListNominalWallThickness_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListNominalWallThickness_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListNominalWallThickness_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListNominalWallThickness_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListNominalWallThickness_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListNominalWallThickness_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListNominalWallThickness_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListNominalWallThickness_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListNominalWallThickness_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListNominalWallThickness_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListNominalWallThickness_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListNominalWallThickness_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
