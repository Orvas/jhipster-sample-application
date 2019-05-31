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

import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListLongSeamWeldTypeRepository;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeCriteria;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeDTO;
import io.github.jhipster.application.service.mapper.ListLongSeamWeldTypeMapper;

/**
 * Service for executing complex queries for {@link ListLongSeamWeldType} entities in the database.
 * The main input is a {@link ListLongSeamWeldTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListLongSeamWeldTypeDTO} or a {@link Page} of {@link ListLongSeamWeldTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListLongSeamWeldTypeQueryService extends QueryService<ListLongSeamWeldType> {

    private final Logger log = LoggerFactory.getLogger(ListLongSeamWeldTypeQueryService.class);

    private final ListLongSeamWeldTypeRepository listLongSeamWeldTypeRepository;

    private final ListLongSeamWeldTypeMapper listLongSeamWeldTypeMapper;

    public ListLongSeamWeldTypeQueryService(ListLongSeamWeldTypeRepository listLongSeamWeldTypeRepository, ListLongSeamWeldTypeMapper listLongSeamWeldTypeMapper) {
        this.listLongSeamWeldTypeRepository = listLongSeamWeldTypeRepository;
        this.listLongSeamWeldTypeMapper = listLongSeamWeldTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListLongSeamWeldTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListLongSeamWeldTypeDTO> findByCriteria(ListLongSeamWeldTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListLongSeamWeldType> specification = createSpecification(criteria);
        return listLongSeamWeldTypeMapper.toDto(listLongSeamWeldTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListLongSeamWeldTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListLongSeamWeldTypeDTO> findByCriteria(ListLongSeamWeldTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListLongSeamWeldType> specification = createSpecification(criteria);
        return listLongSeamWeldTypeRepository.findAll(specification, page)
            .map(listLongSeamWeldTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListLongSeamWeldTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListLongSeamWeldType> specification = createSpecification(criteria);
        return listLongSeamWeldTypeRepository.count(specification);
    }

    /**
     * Function to convert ListLongSeamWeldTypeCriteria to a {@link Specification}.
     */
    private Specification<ListLongSeamWeldType> createSpecification(ListLongSeamWeldTypeCriteria criteria) {
        Specification<ListLongSeamWeldType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListLongSeamWeldType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListLongSeamWeldType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListLongSeamWeldType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListLongSeamWeldType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListLongSeamWeldType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListLongSeamWeldType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListLongSeamWeldType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListLongSeamWeldType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListLongSeamWeldType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListLongSeamWeldType_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListLongSeamWeldType_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListLongSeamWeldType_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListLongSeamWeldType_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListLongSeamWeldType_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListLongSeamWeldType_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
